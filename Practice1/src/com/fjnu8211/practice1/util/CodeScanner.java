package com.fjnu8211.practice1.util;

import com.fjnu8211.practice1.domain.Word;

/**
 * 功能描述:语法编译
 *
 * @ClassName: CodeScanner
 * @Author: Yu Xiaogai
 * @Date: 2020/2/22 2:06
 */
public class CodeScanner {

    private char[] input;                                   //定义输入的字符数组
    private char[] token;                                   //定义用于保存逐个读取到的字符数组
    private int w_input = 0;                                //定义记录input的下标
    private int w_token = 0;                                //定义记录token的下标
    private char getInputChar;                              //定义得到输入的下一个字符

    /*
     * 识别码
     * 关键字：1、标识符：2、常数：3、运算符：4、分隔符：5
     */
    private String[] keywords = {"if", "int", "for", "while", "do", "return", "break", "continue"};         //定义关键字数组

    /*
     * 功能描述:复写带参的CodeScanner类
     *
     * @param input
     * @return:
     * @Author: Yu Xiaogai
     * @Date: 2020/2/22 18:40
     */
    public CodeScanner(char[] input) {
        this.input = input;
    }

    /*
     * 功能描述:对每一个字符或字符串进行编译操作
     *
     * @param 
     * @return: com.fjnu8211.practice1.domain.Word
     * @Author: Yu Xiaogai
     * @Date: 2020/2/22 18:41
     */
    public Word Scan() {
        token = new char[255];
        Word word = new Word();

        if(w_input >= input.length) {
            word.setTypenum(0);
            word.setWord("End");
            return word;
        }

        w_token = 0;                //每次调用Scan方法，都需使token数组的下标为0开始
        getNextChar();
        removeUselessChar();
        if(adjustLetter()) {    //判断是否为字母
            while(adjustLetter() || adjustDigital()) {              //循环到下一个字符不为数字或者字母
                contactChar();
                getNextChar();
            }
            fallbackChar();
            word.setTypenum(adjustKeyword());
            word.setWord(new String(token).trim());
            return word;
        }else if(adjustDigital()) {     //判断是否为数字
            while(adjustDigital()) {                                //循环到下一个字符不为数字
                contactChar();
                getNextChar();
            }
            fallbackChar();
            word.setTypenum(3);
            word.setWord(new String(token).trim());
            return word;
        }else {
            switch (getInputChar) {
                case '=':
                    word.setTypenum(4);
                    word.setWord("=");
                    return word;
                case '+':
                    word.setTypenum(4);
                    word.setWord("+");
                    return word;
                case '-':
                    word.setTypenum(4);
                    word.setWord("-");
                    return word;
                case '*':
                    word.setTypenum(4);
                    word.setWord("*");
                    return word;
                case '/':
                    getNextChar();
                    //识别单行注释
                    if (getInputChar == '/') {
                        while(getNextChar() != '\n');
                        word.setTypenum(2);
                        word.setWord("\\n");
                        return word;
                    }
                    //识别多行注释
                    if(getInputChar=='*') {
                        String string = "";
                        while(true) {
                            if (getInputChar == '*') {
                                if (getNextChar() == '/') {
                                    word.setTypenum(2);
                                    word.setWord(string);
                                    return word;
                                }
                                fallbackChar();
                            }
                            if (getNextChar() == '\n') {
                                string += "\\n";
                            }
                        }
                    }
                    fallbackChar();
                    word.setTypenum(4);
                    word.setWord("/");
                    return word;
                case ':':
                    getNextChar();
                    if(getInputChar=='=') {
                        word.setTypenum(2);
                        word.setWord(":=");
                        return word;
                    }
                    fallbackChar();
                    word.setTypenum(2);
                    word.setWord(":");
                    return word;
                case '<':
                    getNextChar();
                    if(getInputChar=='=') {
                        word.setTypenum(4);
                        word.setWord("<=");
                        return word;
                    }else if (getInputChar == '>') {
                        word.setTypenum(4);
                        word.setWord("<>");
                        return word;
                    }
                    fallbackChar();
                    word.setTypenum(4);
                    word.setWord("<");
                    return word;
                case '>':
                    getNextChar();
                    if(getInputChar=='=') {
                        word.setTypenum(4);
                        word.setWord(">=");
                        return word;
                    }
                    fallbackChar();
                    word.setTypenum(4);
                    word.setWord(">");
                    return word;
                case '!':
                    getNextChar();
                    if(getInputChar=='=') {
                        word.setTypenum(4);
                        word.setWord("!=");
                        return word;
                    }
                    fallbackChar();
                    word.setTypenum(2);
                    word.setWord("!");
                    return word;
                case ',':
                    word.setTypenum(5);
                    word.setWord(",");
                    return word;
                case ';':
                    word.setTypenum(5);
                    word.setWord(";");
                    return word;
                case '(':
                    word.setTypenum(5);
                    word.setWord("(");
                    return word;
                case ')':
                    word.setTypenum(5);
                    word.setWord(")");
                    return word;
                case '{':
                    word.setTypenum(5);
                    word.setWord("{");
                    return word;
                case '}':
                    word.setTypenum(5);
                    word.setWord("}");
                    return word;
                case '\n':
                    word.setTypenum(2);
                    word.setWord("\\n");
                    return word;
                default:
                    contactChar();
                    word.setTypenum(-1);
                    word.setWord("ERROR INFO: WORD = \"" + new String(token).trim() + "\"");
                    return word;
            }
        }
    }

    /*
     * 功能描述:得到input数组中的下一字符
     *
     * @param
     * @return: void
     * @Author: Yu Xiaogai
     * @Date: 2020/2/22 19:03
     */
    private char getNextChar() {
        if(w_input < input.length) {
            getInputChar = input[w_input];
            w_input++;
        }
        return getInputChar;
    }

    /*
     * 功能描述:去除无用字符（空格、tab等）
     *
     * @param
     * @return: void
     * @Author: Yu Xiaogai
     * @Date: 2020/2/22 19:05
     */
    private void removeUselessChar() {
        while((getInputChar==' ' || getInputChar=='\t') && w_input<input.length) {
            getInputChar = input[w_input];
            w_input++;
        }
    }

    /*
     * 功能描述:判断得到的下一个字符是否为字母
     *
     * @param
     * @return: boolean
     * @Author: Yu Xiaogai
     * @Date: 2020/2/22 19:11
     */
    private boolean adjustLetter() {
        if((getInputChar>='a' && getInputChar<='z') || (getInputChar>='A' && getInputChar<='Z')) {
            return true;
        }
        return false;
    }

    /*
     * 功能描述:判断得到的下一个字符是否为数字
     *
     * @param
     * @return: boolean
     * @Author: Yu Xiaogai
     * @Date: 2020/2/22 19:15
     */
    private boolean adjustDigital() {
        if(getInputChar>='0' && getInputChar<='9') {
            return true;
        }
        return false;
    }

    /*
     * 功能描述:把得到下一个字符与前一个字符进行拼接
     *
     * @param null
     * @return:
     * @Author: Yu Xiaogai
     * @Date: 2020/2/22 19:18
     */
    private void contactChar() {
        token[w_token] = getInputChar;
        w_token++;
        token[w_token] = '\0';              //字符数组需以\0结尾
    }

    /*
     * 功能描述:回退一个字符
     *
     * @param
     * @return: void
     * @Author: Yu Xiaogai
     * @Date: 2020/2/22 19:31
     */
    private void fallbackChar() {
        w_input--;
    }

    /*
     * 功能描述:判断是否为关键字
     *
     * @param
     * @return: int
     * @Author: Yu Xiaogai
     * @Date: 2020/2/22 19:52
     */
    private int adjustKeyword() {
        for(int i=0; i<keywords.length; i++) {
            if(keywords[i].equals(new String(token).trim())) {
                return 1;
            }
        }
        return 2;
    }

}
