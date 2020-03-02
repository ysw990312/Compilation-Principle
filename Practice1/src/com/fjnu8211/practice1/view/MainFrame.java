package com.fjnu8211.practice1.view;

import com.fjnu8211.practice1.service.AnalyzeService;
import com.fjnu8211.practice1.util.WindowXY;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 功能描述:主界面
 *
 * @ClassName: MainFrame
 * @Author: Yu Xiaogai
 * @Date: 2020/2/20 16:06
 */
public class MainFrame {

    private JFrame frame;

    private JLabel L_Title;
    private JLabel L_File;
    private JLabel L_Result;
    private JLabel L_Input;

    private JTextField T_Address;

    private JButton Btn_Find;
    private JButton Btn_Compile;

    private JTextArea inputArea;
    private JTextArea resultArea;

    private JScrollPane ScrollPane1;
    private JScrollPane ScrollPane2;

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
        frame.setTitle("语法编译器");
        frame.setLocation(WindowXY.getXY(frame.getSize()));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        //设置标题标签
        L_Title = new JLabel("语法编译器");
        L_Title.setHorizontalAlignment(SwingConstants.CENTER);
        L_Title.setFont(new Font("宋体", Font.BOLD, 30));
        L_Title.setBounds(420, 35, 350, 30);
        frame.getContentPane().add(L_Title);

        //设置选择文件提示标签
        L_File = new JLabel("请选择文件：");
        L_File.setFont(new Font("宋体", Font.BOLD, 20));
        L_File.setBounds(100, 150, 130, 30);
        frame.getContentPane().add(L_File);

        //设置文件地址输入框
        T_Address = new JTextField();
        T_Address.setFont(new Font("宋体", Font.BOLD, 20));
        T_Address.setBounds(230, 150, 730, 30);
        T_Address.setColumns(10);
        T_Address.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
        T_Address.setBackground(new Color(0,0,0,0));
        T_Address.setOpaque(false);
        T_Address.setEditable(false);
        frame.getContentPane().add(T_Address);

        //浏览文件按钮
        Btn_Find = new JButton("浏   览");
        Btn_Find.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Find();
            }
        });
        Btn_Find.setFont(new Font("宋体", Font.BOLD, 20));
        Btn_Find.setBounds(980, 150, 120, 30);
        frame.getContentPane().add(Btn_Find);

        //设置输输入文本标签
        L_Input = new JLabel("输入文本:");
        L_Input.setFont(new Font("宋体", Font.BOLD, 20));
        L_Input.setBounds(100, 220, 130, 30);
        frame.getContentPane().add(L_Input);

        //设置输入文本框
        inputArea = new JTextArea();
        inputArea.setFont(new Font("宋体", Font.BOLD, 20));
        inputArea.setEditable(false);

        //设置文本框滚动条
        ScrollPane1 = new JScrollPane(inputArea);
        ScrollPane1.setBounds(230, 225, 350, 380);
        frame.getContentPane().add(ScrollPane1);

        //设置输出结果标签
        L_Result = new JLabel("输出结果:");
        L_Result.setFont(new Font("宋体", Font.BOLD, 20));
        L_Result.setBounds(620, 220, 130, 30);
        frame.getContentPane().add(L_Result);

        //设置输出结果文本框
        resultArea = new JTextArea();
        resultArea.setFont(new Font("宋体", Font.BOLD, 20));
        resultArea.setEditable(false);

        //设置文本框滚动条
        ScrollPane2 = new JScrollPane(resultArea);
        ScrollPane2.setBounds(750, 225, 350, 380);
        frame.getContentPane().add(ScrollPane2);

        //浏览文件按钮
        Btn_Compile = new JButton("编    译");
        Btn_Compile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String address = T_Address.getText();
                if(address.equals("")) {
                    JOptionPane.showMessageDialog(null, "请选择需要编译的文本文件");
                }else {
                    try {
                        AnalyzeService analyzeService = new AnalyzeService();
                        String content = analyzeService.getTextContent(address);
                        inputArea.setText(content);
                        String analyzeContent = analyzeService.AnalyzeInit(content);
                        resultArea.setText(analyzeContent);
                    }catch (Exception event) {
                        event.printStackTrace();
                    }
                }
            }
        });
        Btn_Compile.setFont(new Font("宋体", Font.BOLD, 20));
        Btn_Compile.setBounds(1055, 630, 120, 30);
        frame.getContentPane().add(Btn_Compile);
    }

    /*
     * 功能描述:浏览本地文件
     *
     * @param
     * @return: void
     * @Author: Yu Xiaogai
     * @Date: 2020/2/21 0:22
     */
    private void Find() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.showDialog(new JLabel(), "选择");
        T_Address.setText(chooser.getSelectedFile().getPath());
    }

}
