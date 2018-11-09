package com.yys.color;

import java.awt.*;
import java.awt.event.InputEvent;

/**
 * @author wangmsh on 2018/11/9
 * 获取当前鼠标的颜色信息
 */
public class MousePick {
    private Robot robot = null;

    public MousePick() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public Color getPointColor(int x, int y) {
        return robot.getPixelColor(x, y);
    }

    public void click(int x, int y) {
        robot.mouseMove(x, y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(randomWithRange(200,500));
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    private int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

}
