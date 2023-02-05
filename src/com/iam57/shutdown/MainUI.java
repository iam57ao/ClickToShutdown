package com.iam57.shutdown;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Random;

public class MainUI extends JFrame {

    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Random RANDOM = new Random();

    private boolean shutdownClicked = false;

    public MainUI() {
        init();
        clickToShutdown();
        setVisible(true);
    }

    private void init() {
        setTitle("这是一个礼物！");
        setSize(300, 100);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                shutdown();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                shutdown();
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                shutdown();
            }
        });
    }

    private void clickToShutdown() {
        JButton jbt = new JButton("点击这里获取礼物！");
        add(jbt);
        jbt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jbt.setEnabled(false);
                shutdown();
            }
        });
    }

    private void shutdown() {

        if (shutdownClicked) return;
        shutdownClicked = true;

        /* 这个dialog不点会卡着不往下执行，改成命令 -c "comment" 通知 */
        //JOptionPane.showMessageDialog(null, "您辛苦了，帮您关机凉快一下捏！");
        try {
            Runtime.getRuntime().exec("shutdown -s -t 9 -f -c \"您辛苦了，帮您关机凉快一下捏~  (还有⑨秒关机！点击“取消”来撤回关机！)\"");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        showCancelButton();
    }

    private void showCancelButton() {

        JFrame frame = new JFrame();
        frame.setSize(64, 64);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true); // 无边框
        frame.setAlwaysOnTop(true); // 置顶

        JButton button = new JButton("取消");
        button.setSize(frame.getSize()); // 等大小
        button.setBackground(Color.RED); // 红色背景
        button.setFocusPainted(false); // 去掉焦点框
        button.setBorderPainted(false); // 去掉边框
        button.setFont(new Font("黑体", Font.BOLD, 14)); // 字体
        button.setForeground(Color.WHITE); // 字体颜色
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                randomMoveOnScreen(frame);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                randomMoveOnScreen(frame);
            }
        });
        frame.add(button);
        frame.setVisible(true);
    }

    private static void randomMoveOnScreen(Component component) {
        int x = RANDOM.nextInt(SCREEN_SIZE.width - component.getWidth());
        int y = RANDOM.nextInt(SCREEN_SIZE.height - component.getHeight());
        component.setLocation(x, y);
    }
}
