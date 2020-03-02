package com.fjnu8211.practice2_1.service;

import com.fjnu8211.practice2_1.domain.CompileData;
import com.fjnu8211.practice2_1.domain.TotalData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 功能描述:分析服务
 *
 * @ClassName: CompileService
 * @Author: Yu Xiaogai
 * @Date: 2020/3/1 22:16
 */
public class CompileService {

    private char[] inputText;                                                       //定义存储输入数据的字符数组
    private char[] b = new char[50];                                                //定义存储分析串的字符数组
    private char[] d = new char[1024];                                              //定义存储推导式的字符数组
    private char[] e = new char[10];                                                //定义存储推导式字符的字符数组

    private char ch;                                                                //定义存储inputText数组的下一个字符
    private int i1=0,flag=1,n=5;

    private String[] tableHead = {"文法", "分析串", "分析字符", "剩余串"};              //定义表头

    private JTable table = new JTable();                                            //定义表格

    private DefaultTableModel dtm;                                                  //定义表格模型

    private List<CompileData> dataList = new ArrayList<>();                         //定义存储CompileData类数据的集合
    private CompileData compileData = new CompileData();                            //定义compileData类
    private TotalData totalData = new TotalData();                                  //定义TotalData类

    /*
     * 功能描述:设置表格信息
     *
     * @param input
     * @return: com.fjnu8211.practice2_1.domain.TotalData
     * @Author: Yu Xiaogai
     * @Date: 2020/3/3 2:35
     */
    public TotalData setTableContext(String input) {
        d[0] = 'E';
        d[1] = '=';
        d[2] = '>';
        d[3] = 'T';
        d[4] = 'G';
        d[5] = '#';
        inputText = input.toCharArray();
        ch = inputText[0];
        b[0] = inputText[0];
        dtm = (DefaultTableModel)table.getModel();
        dtm.setColumnIdentifiers(tableHead);
        E1();
        for(int i=0; i<dataList.size(); i++) {
            Vector vector = new Vector();                                           //定义存储行数据的集合
            vector.add(dataList.get(i).getGrammer());
            vector.add(dataList.get(i).getAnalysisString());
            vector.add(dataList.get(i).getAnalysisChar());
            vector.add(dataList.get(i).getRemainingString());
            dtm.addRow(vector);
        }
        totalData.setTable(table);
        if(ch == '#') {
            totalData.setMsg("accept");
            String process = "";
            int p = 0;
            char x = d[p];
            while(x != '#') {
                process += x;
                p = p+1;
                x = d[p];
            }
            totalData.setProcess(process);
        }
        return totalData;
    }

    /*
     * 功能描述:E过程，对应产生式E->TG
     *
     * @param
     * @return: int
     * @Author: Yu Xiaogai
     * @Date: 2020/3/3 0:53
     */
    private int E1()
    {
        int f, t;
        compileData.setGrammer("E-->TG");
        flag = 1;
        input();
        input1();
        f = T();
        if(f == 0) {
            return 0;
        }
        t = G();
        if(t == 0) {
            return 0;
        }else {
            return 1;
        }
    }

    /*
     * 功能描述:E过程，对应产生式E->TG
     *
     * @param
     * @return: int
     * @Author: Yu Xiaogai
     * @Date: 2020/3/3 0:53
     */
    private int E() {
        int f, t;
        compileData.setGrammer("E-->TG");
        e[0] = 'E';
        e[1] = '=';
        e[2] = '>';
        e[3] = 'T';
        e[4] = 'G';
        e[5] = '#';
        output();
        flag = 1;
        input();
        input1();
        f = T();
        if(f==0) {
            return 0;
        }
        t = G();
        if(t == 0) {
            return 0;
        }else {
            return 1;
        }
    }

    /*
     * 功能描述:T过程，对应产生式T->FS
     *
     * @param
     * @return: int
     * @Author: Yu Xiaogai
     * @Date: 2020/3/3 0:53
     */
    private int T() {
        int f, t;
        compileData.setGrammer("T-->FS");
        e[0] = 'T';
        e[1] = '=';
        e[2] = '>';
        e[3] = 'F';
        e[4] = 'S';
        e[5] = '#';
        output();
        flag = 1;
        input();
        input1();
        f = F();
        if(f == 0) {
            return 0;
        }
        t = S();
        if(t == 0) {
            return 0;
        }else {
            return 1;
        }
    }

    /*
     * 功能描述:G过程，对应产生式G->+TG|-TG|ε
     *
     * @param
     * @return: int
     * @Author: Yu Xiaogai
     * @Date: 2020/3/3 0:53
     */
    private int G() {
        int f;
        if(ch == '+') {
            b[i1] = ch;
            compileData.setGrammer("G-->+TG");
            e[0] = 'G';
            e[1] = '=';
            e[2] = '>';
            e[3] = '+';
            e[4] = 'T';
            e[5] = 'G';
            e[6] = '#';
            output();
            flag = 0;
            input();
            input1();
            ch = inputText[++i1];
            f = T();
            if(f == 0) {
                return 0;
            }
            G();
            return 1;
        }
        if(ch == '-') {
            b[i1] = ch;
            compileData.setGrammer("G-->+TG");
            e[0] = 'G';
            e[1] = '=';
            e[2] = '>';
            e[3] = '+';
            e[4] = 'T';
            e[5] = 'G';
            e[6] = '#';
            output();
            flag = 0;
            input();
            input1();
            ch = inputText[++i1];
            f = T();
            if(f == 0) {
                return 0;
            }
            G();
            return 1;
        }
        compileData.setGrammer("G-->^");
        e[0] = 'G';
        e[1] = '=';
        e[2] = '>';
        e[3] = '^';
        e[4] = '#';
        output();
        flag = 1;
        input();
        input1();
        return 1;
    }

    /*
     * 功能描述:S过程，对应产生式S->*FS|/FS|ε
     *
     * @param
     * @return: int
     * @Author: Yu Xiaogai
     * @Date: 2020/3/3 0:53
     */
    private int S() {
        int f, t;
        if(ch == '*') {
            b[i1] = ch;
            compileData.setGrammer("S-->*FS");
            e[0] = 'S';
            e[1] = '=';
            e[2] = '>';
            e[3] = '*';
            e[4] = 'F';
            e[5] = 'S';
            e[6] = '#';
            output();
            flag = 0;
            input();
            input1();
            ch = inputText[++i1];
            f = F();
            if(f == 0) {
                return 0;
            }
            t = S();
            if(t == 0) {
                return 0;
            }else {
                return 1;
            }
        }
        if(ch == '/') {
            b[i1] = ch;
            compileData.setGrammer("S-->*FS");
            e[0] = 'S';
            e[1] = '=';
            e[2] = '>';
            e[3] = '*';
            e[4] = 'F';
            e[5] = 'S';
            e[6] = '#';
            output();
            flag = 0;
            input();
            input1();
            ch = inputText[++i1];
            f = F();
            if(f == 0) {
                return 0;
            }
            t = S();
            if(t == 0) {
                return 0;
            }else {
                return 1;
            }
        }
        compileData.setGrammer("S-->^");
        e[0] = 'S';
        e[1] = '=';
        e[2] = '>';
        e[3] = '^';
        e[4] = '#';
        output();
        flag = 1;
        inputText[i1] = ch;
        input();
        input1();
        return 1;
    }

    /*
     * 功能描述:F过程，对应产生式F->(E)|i
     *
     * @param
     * @return: int
     * @Author: Yu Xiaogai
     * @Date: 2020/3/3 0:54
     */
    private int F() {
        int f;
        if(ch == '(') {
            b[i1] = ch;
            compileData.setGrammer("F-->(E)");
            e[0] = 'F';
            e[1] = '=';
            e[2] = '>';
            e[3] = '(';
            e[4] = 'E';
            e[5] = ')';
            e[6] = '#';
            output();
            flag = 0;
            input();
            input1();
            ch = inputText[++i1];
            f = E();
            if(f == 0) {
                return 0;
            }
            if(ch == ')') {
                b[i1] = ch;
                compileData.setGrammer("F-->(E)");
                flag = 0;
                input();
                input1();
                ch = inputText[++i1];
            }else {
                totalData.setMsg("error");
                return 0;
            }
        }else if(ch == 'i') {
            b[i1] = ch;
            compileData.setGrammer("F-->i");
            e[0] = 'F';
            e[1] = '=';
            e[2] = '>';
            e[3] = 'i';
            e[4] = '#';
            output();
            flag = 0;
            input();
            input1();
            ch = inputText[++i1];
        }else {
            totalData.setMsg("error");
            return 0;
        }
        return 1;
    }

    /*
     * 功能描述:输出分析串与分析字符
     *
     * @param
     * @return: void
     * @Author: Yu Xiaogai
     * @Date: 2020/3/3 0:54
     */
    private void input() {
        String analysisString = "";
        for (int j=0; j<=i1-flag; j++){
            analysisString += b[j];
        }
        compileData.setAnalysisString(analysisString);
        compileData.setAnalysisChar(ch);
    }

    /*
     * 功能描述:输出剩余字符
     *
     * @param
     * @return: void
     * @Author: Yu Xiaogai
     * @Date: 2020/3/3 0:54
     */
    private void input1() {
        String remainingString = "";
        for (int j=i1+1-flag; j<inputText.length; j++) {
            remainingString += inputText[j];
        }
        compileData.setRemainingString(remainingString);
        dataList.add(compileData);
        compileData = new CompileData();
    }

    /*
     * 功能描述:输出已分析字符串和当前字符
     *
     * @param
     * @return: void
     * @Author: Yu Xiaogai
     * @Date: 2020/3/3 2:38
     */
    private void output()
    {
        int m, k, j, q, i;
        d[n] = '=';
        d[n+1] = '>';
        d[n+2] = '#';
        n = n+2;
        i = n;
        i = i-2;
        while(d[i]!='>' && i!=0) {
            i = i-1;
        }
        i = i+1;
        while(d[i] != e[0]) {
            i = i+1;
        }
        q = i;
        m = q;
        k = q;
        while(d[m] != '>') {
            m = m-1;
        }
        m = m+1;
        while(m != q) {
            d[n] = d[m];
            m = m+1;
            n = n+1;
        }
        d[n] = '#';
        for(j=3; e[j]!='#'; j++) {
            d[n] = e[j];
            n = n+1;
        }
        k = k+1;
        while(d[k] != '=') {
            d[n] = d[k];
            n = n+1;
            k = k+1;
        }
        d[n] = '#';
    }
}