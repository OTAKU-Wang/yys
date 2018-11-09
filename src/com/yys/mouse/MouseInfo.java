package com.yys.mouse;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

/**
 * @author wangmsh on 2018/11/9
 * 获取鼠标当前的位置
 */
public class MouseInfo extends JFrame {
    private volatile static MouseInfo mouseInfo = null;
    private final JPanel contentPanel = new JPanel();
    private final MouseActionListener mouseActionListener = new MouseActionListener(this);

    private JLabel valueX = null;
    private JLabel valueY = null;
    private JLabel valueColor = null;
    private JTextField textField = null;

    private MouseInfo() {
        setTitle("阴阳师鼠标小工具");
        setBounds(100, 100, 500, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblx = new JLabel("鼠标 x:");
        lblx.setFont(new Font("宋体", Font.PLAIN, 15));
        lblx.setBounds(22, 27, 66, 31);
        contentPanel.add(lblx);

        JLabel lbly = new JLabel("鼠标 y:");
        lbly.setFont(new Font("宋体", Font.PLAIN, 15));
        lbly.setBounds(22, 68, 66, 31);
        contentPanel.add(lbly);

        JLabel lblColor = new JLabel("鼠标 color:");
        lblColor.setFont(new Font("宋体", Font.PLAIN, 15));
        lblColor.setBounds(22, 109, 100, 31);
        contentPanel.add(lblColor);

        JLabel inputColor = new JLabel("需要点击的颜色:");
        inputColor.setFont(new Font("宋体", Font.PLAIN, 15));
        inputColor.setBounds(22, 150, 200, 31);
        contentPanel.add(inputColor);

        valueX = new JLabel("0");
        valueX.setForeground(Color.BLUE);
        valueX.setFont(new Font("宋体", Font.PLAIN, 20));
        valueX.setBounds(82, 27, 66, 31);
        contentPanel.add(valueX);

        valueY = new JLabel("0");
        valueY.setForeground(Color.BLUE);
        valueY.setFont(new Font("宋体", Font.PLAIN, 20));
        valueY.setBounds(82, 68, 66, 31);
        contentPanel.add(valueY);

        valueColor = new JLabel("0");
        valueColor.setForeground(Color.RED);
        valueColor.setFont(new Font("宋体", Font.PLAIN, 20));
        valueColor.setBounds(110, 109, 500, 31);
        contentPanel.add(valueColor);

        textField = new JTextField();
        textField.setFont(new Font("宋体", Font.PLAIN, 20));
        textField.setBounds(140, 150, 300, 31);
        contentPanel.add(textField);

        JButton startButton = new JButton("Start");
        startButton.setBounds(110, 200, 62, 40);
        startButton.addActionListener(mouseActionListener);

        JButton stopButton = new JButton("Stop");
        stopButton.setBounds(220, 200, 62, 40);
        stopButton.addActionListener(mouseActionListener);

        contentPanel.add(startButton);
        contentPanel.add(stopButton);
    }

    public static MouseInfo create() {
        if (Objects.isNull(mouseInfo)) {
            synchronized (MouseInfo.class) {
                if (Objects.isNull(mouseInfo)) {
                    mouseInfo = new MouseInfo();
                    mouseInfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    mouseInfo.setVisible(true);
                    mouseInfo.setAlwaysOnTop(true);
                    return mouseInfo;
                } else {
                    return mouseInfo;
                }
            }
        } else {
            return mouseInfo;
        }
    }

    public JLabel getValueX() {
        return valueX;
    }

    public JLabel getValueY() {
        return valueY;
    }

    public JLabel getValueColor() {
        return valueColor;
    }

    public JTextField getTextField() {
        return textField;
    }
}
