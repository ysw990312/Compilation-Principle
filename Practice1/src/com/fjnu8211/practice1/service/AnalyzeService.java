package com.fjnu8211.practice1.service;

import com.fjnu8211.practice1.domain.Word;
import com.fjnu8211.practice1.util.CodeScanner;

import java.io.*;
import java.util.ArrayList;

/**
 * 功能描述:编译服务类
 *
 * @ClassName: AnalyzeContent
 * @Author: Yu Xiaogai
 * @Date: 2020/2/22 2:03
 */
public class AnalyzeService {

    private int over = 1;
    private ArrayList<Word> list = new ArrayList<>();
    private String analyzeContent = "";

    private Word word = new Word();
    private CodeScanner scanner;

    /*
     * 功能描述:获取文本内容
     *
     * @return: java.lang.String
     * @Author: Yu Xiaogai
     * @Date: 2020/2/23 0:06
     */
    public String getTextContent(String fileAddress) throws IOException {
        File file = new File(fileAddress);
        return readTextContent(file);
    }

    /*
     * 功能描述:对文本内容进行语法编译初始化操作
     *
     * @param fileAddress
     * @return: java.lang.String
     * @Author: Yu Xiaogai
     * @Date: 2020/2/22 2:19
     */
    public String AnalyzeInit(String fileContent) {
        scanner = new CodeScanner(fileContent.toCharArray());
        while (over != 0) {
            word = scanner.Scan();
            list.add(word);
            over = word.getTypenum();
        }
        listChangeString();
        return analyzeContent;
    }

    /*
     * 功能描述:读取文本文档内容
     *
     * @param file
     * @return: java.lang.String
     * @Author: Yu Xiaogai
     * @Date: 2020/2/22 1:05
     */
    public String readTextContent(File file) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
        String s = null;
        while((s = br.readLine())!=null){//使用readLine方法，一次读一行
            result.append(s + System.lineSeparator());
        }
        br.close();
        return result.toString();
    }

    /*
     * 功能描述:list集合转字符串
     *
     * @param
     * @return: void
     * @Author: Yu Xiaogai
     * @Date: 2020/2/22 2:17
     */
    private void listChangeString() {
        for(Word word : list) {
            analyzeContent += "(" + word.getTypenum() + " ," + word.getWord() + ")\n";
        }
    }

}
