package com.yys.mouse;

import com.yys.color.MousePick;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author wangmsh on 2018/11/9
 * 鼠标点击事件监听
 */
public class MouseActionListener implements ActionListener {
    private static final String START = "Start";
    private static final String STOP = "Stop";
    private static final String CLICK_TASK = "click_task";
    private static final int RGB_LENGTH = 3;
    private MouseInfo mouseInfo;
    private MousePick mousePick;
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private List<Color> colorList;
    private final Map<String, Future> futures = new HashMap<>();

    MouseActionListener(MouseInfo mouseInfo) {
        this.mouseInfo = mouseInfo;
        this.mousePick = new MousePick();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String clickColor = mouseInfo.getTextField().getText();
        colorList = getColorList(clickColor);
        if (cmd.equals(START) && !colorList.isEmpty()) {
            start();
        } else if (cmd.equals(STOP)) {
            stop();
        }
    }

    private List<Color> getColorList(String color) {
        if (null != color && !"".equals(color)) {
            String[] rgbArray = color.split(";");
            return Arrays.stream(rgbArray).map(c -> {
                String[] rgb = c.split(",");
                if (rgb.length == RGB_LENGTH) {
                    Integer r = new Integer(rgb[0]);
                    Integer g = new Integer(rgb[1]);
                    Integer b = new Integer(rgb[2]);
                    return new Color(r, g, b);
                } else {
                    return new Color(0, 0, 0);
                }

            }).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }

    }

    private final Runnable click = new Runnable() {
        @Override
        public void run() {
            Point point = java.awt.MouseInfo.getPointerInfo().getLocation();
            mouseInfo.getValueX().setText("" + point.x);
            mouseInfo.getValueY().setText("" + point.y);
            Color pickColor = mousePick.getPointColor(point.x, point.y);
            mouseInfo.getValueColor().setText(pickColor.toString());
            mouseInfo.getTextField().setEnabled(false);
            if (colorList.contains(pickColor)) {
                mousePick.click(point.x, point.y);
                System.out.println("click the point" + point.toString());
            }
        }
    };

    private void start() {
        Future future = executorService.scheduleAtFixedRate(click, 100, 100, TimeUnit.MILLISECONDS);
        futures.put(CLICK_TASK, future);
    }

    private void stop() {
        Future future = futures.get(CLICK_TASK);
        future.cancel(true);
        futures.remove(CLICK_TASK);
        mouseInfo.getTextField().setEnabled(true);
    }

}
