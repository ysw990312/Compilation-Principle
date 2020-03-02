package com.fjnu8211.practice2_1.domain;

/**
 * 功能描述:分析数据类
 *
 * @ClassName: CompileData
 * @Author: Yu Xiaogai
 * @Date: 2020/3/3 0:47
 */
public class CompileData {

    private String Grammer;                             //文法
    private String AnalysisString;                      //分析串
    private char AnalysisChar;                          //分析字符
    private String RemainingString;                     //剩余串

    public String getGrammer() {
        return Grammer;
    }

    public void setGrammer(String grammer) {
        Grammer = grammer;
    }

    public String getAnalysisString() {
        return AnalysisString;
    }

    public void setAnalysisString(String analysisString) {
        AnalysisString = analysisString;
    }

    public char getAnalysisChar() {
        return AnalysisChar;
    }

    public void setAnalysisChar(char analysisChar) {
        AnalysisChar = analysisChar;
    }

    public String getRemainingString() {
        return RemainingString;
    }

    public void setRemainingString(String remainingString) {
        RemainingString = remainingString;
    }

}
