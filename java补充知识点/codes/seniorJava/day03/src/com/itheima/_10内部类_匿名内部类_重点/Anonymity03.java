package com.itheima._10内部类_匿名内部类_重点;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
    拓展：匿名内部类的真实使用场景演示。
 */
public class Anonymity03 {
    public static void main(String[] args) {
        // 1.创建一个窗口。
        JFrame win = new JFrame("登录界面");

        // 2.设置窗口的大小。
        win.setSize(400 , 300);

        // 3.居中。
        win.setLocationRelativeTo(null);

        // 4.为当前界面加上一个按钮对象。
        JButton btn = new JButton("开始登录");
        JPanel panel = new JPanel();
        panel.add(btn);
        win.add(panel);
        // 5.为当前按钮对象绑定一个点击事件监听器。
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("用户点击了触发登录~~~");
            }
        });
        // btn.addActionListener( s -> System.out.println("用户点击了触发登录~~~"));

        // 6.显示窗口。
        win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        win.setVisible(true);
    }
}
