package com.fjnu8211.practice2_1.util;

import java.awt.*;

/**
 * 功能描述:可视化窗口居中
 *
 * @ClassName: WindowXY
 * @Author: Yu Xiaogai
 * @Date: 2020/2/4 21:47
 */
public class WindowXY {

    //传入窗口的宽和高，然后就可以计算出所要的位置
    public static Point getXY(int w, int h) {

        //计算屏幕的宽和高
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int height = toolkit.getScreenSize().height;
        int width = toolkit.getScreenSize().width;

        int x = (width-w)/2;
        int y = (height-h)/2;

        Point p = new Point(x,y);
        return p;

    }

    public static Point getXY(Dimension d) {
        return getXY(d.width, d.height);
    }

}
