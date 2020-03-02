package com.fjnu8211.practice2_1.view;

import com.fjnu8211.practice2_1.domain.TotalData;
import com.fjnu8211.practice2_1.service.CompileService;
import com.fjnu8211.practice2_1.util.WindowXY;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * 功能描述:主界面
 *
 * @ClassName: MainFrame
 * @Author: Yu Xiaogai
 * @Date: 2020/3/1 20:28
 */
public class MainFrame {

    private TotalData totalData = new TotalData();

    private JFrame frame;

    private JLabel L_Title;
    private JLabel L_Input;
    private JLabel L_Result;
    private JLabel L_Process;

    private JTextArea resultArea;

    private JTextField T_Input;

    private JButton Btn_Compile;

    private JScrollPane ScrollPane1;
    private JScrollPane resultPanel;

    public static void main(String[] args) {
        new MainFrame().frame.setVisible(true);
    }

    public MainFrame() {
        initialize();
    }

    private void initialize() {
        //设置可视化界面的窗口
        frame = new JFrame();
        frame.setSize(1200, 700);
        frame.setTitle("递归下降分析");
        frame.setLocation(WindowXY.getXY(frame.getSize()));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        //设置标题标签
        L_Title = new JLabel("递归下降分析");
        L_Title.setHorizontalAlignment(SwingConstants.CENTER);
        L_Title.setFont(new Font("宋体", Font.BOLD, 30));
        L_Title.setBounds(420, 35, 350, 30);
        frame.getContentPane().add(L_Title);

        //设置输入提示框
        L_Input = new JLabel("分析内容：");
        L_Input.setHorizontalAlignment(SwingConstants.CENTER);
        L_Input.setFont(new Font("宋体", Font.BOLD, 20));
        L_Input.setBounds(200, 120, 150, 30);
        frame.getContentPane().add(L_Input);

        //设置输入框
        T_Input = new JTextField();
        T_Input.setFont(new Font("宋体", Font.BOLD, 20));
        T_Input.setBounds(350, 120, 505, 25);
        T_Input.setColumns(10);
        T_Input.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
        T_Input.setBackground(new Color(0,0,0,0));
        T_Input.setOpaque(false);
        frame.getContentPane().add(T_Input);

        //设置分析按钮
        Btn_Compile = new JButton("分    析");
        Btn_Compile.addActionListener(e -> {
            Compile();
        });
        Btn_Compile.setFont(new Font("宋体", Font.BOLD, 20));
        Btn_Compile.setBounds(880, 120, 120, 30);
        frame.getContentPane().add(Btn_Compile);

        //设置分析步骤提示框
        L_Process = new JLabel("分析步骤：");
        L_Process.setHorizontalAlignment(SwingConstants.CENTER);
        L_Process.setFont(new Font("宋体", Font.BOLD, 20));
        L_Process.setBounds(200, 185, 150, 30);
        frame.getContentPane().add(L_Process);

        //设置表格滚动条
        ScrollPane1 = new JScrollPane();
        ScrollPane1.setBounds(350, 185, 650, 300);
        frame.getContentPane().add(ScrollPane1);

        //设置分析结果提示框
        L_Result = new JLabel("分析结果：");
        L_Result.setHorizontalAlignment(SwingConstants.CENTER);
        L_Result.setFont(new Font("宋体", Font.BOLD, 20));
        L_Result.setBounds(200, 520, 150, 30);
        frame.getContentPane().add(L_Result);

        //设置分析结果文本框
        resultArea = new JTextArea();
        resultArea.setFont(new Font("宋体", Font.BOLD, 20));
        resultArea.setLineWrap(true);
        resultArea.setEditable(false);

        //设置分析结果文本框滚动条
        resultPanel = new JScrollPane(resultArea);
        resultPanel.setBounds(350, 520, 650, 110);
        frame.getContentPane().add(resultPanel);
    }

    /*
     * 功能描述:编译操作
     *
     * @param
     * @return: void
     * @Author: Yu Xiaogai
     * @Date: 2020/3/1 21:07
     */
    private void Compile() {
        String getInputText = T_Input.getText().trim();
        if(getInputText.equals("")) {
            JOptionPane.showMessageDialog(null, "请输入需要编译的内容");
        }else if(!getInputText.endsWith("#")) {
            JOptionPane.showMessageDialog(null, "内容末尾请以“#”结尾");
        }else {
            CompileService compileService = new CompileService();
            totalData = compileService.setTableContext(getInputText);
            ScrollPane1.setViewportView(totalData.getTable());              //添加表格到滚动框中
            String resultString = "编制人：余绍文 116052017163 软工4班\n";
            if(totalData.getMsg().equals("accept")) {
                resultString += getInputText + "为合法字符串\n推导式：\n" + totalData.getProcess();
            }else {
                resultString += getInputText + "为非法字符串";
            }
            resultArea.setText(resultString);
        }
    }

}
