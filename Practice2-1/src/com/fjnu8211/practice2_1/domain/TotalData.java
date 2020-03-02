package com.fjnu8211.practice2_1.domain;

import javax.swing.*;

/**
 * 功能描述:所有数据类
 *
 * @ClassName: TotalData
 * @Author: Yu Xiaogai
 * @Date: 2020/3/3 1:36
 */
public class TotalData {

    private JTable table;
    private String msg;
    private String process;

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

}
