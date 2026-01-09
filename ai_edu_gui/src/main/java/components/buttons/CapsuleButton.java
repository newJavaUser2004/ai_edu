package components.buttons;

import components.BaseComponent;
import components.models.colors.ButtonColor;
import components.models.enums.StateColorEnum;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * 胶囊按钮
 * 确定按钮，只能绑定事件，无法进行自定义
 */
public class CapsuleButton extends BaseComponent {

    //按钮状态（对应不同颜色）
    private StateColorEnum state = StateColorEnum.FREE;

    //圆当前位置
    private Integer capsuleCurrentX;

    //弧度
    private Integer radian;

    //动画
    private Timer action;
    //帧
    private Integer actionFrame = 10;
    //动画持续时间ms
    private Integer actionTime = 150;
    //移动速度（移动距离/多少帧）
    private Integer moveSpeed;

    //绑定的事件处理器
    private ArrayList<Runnable> functions = new ArrayList<>();


    //构造器
    public CapsuleButton() {
        this.panelX = 0;
        this.panelY = 0;
        this.height = 20;
        this.width = 40;
        this.radian = height;
        this.capsuleCurrentX = panelX;
        Integer speed = (panelX+width-radian)/(actionTime/actionFrame);
        this.moveSpeed = speed>0?speed:1;
        this.borderSize = 1.0f;

        //添加鼠标监听事件
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseClickGUIHandle();

                if(functions.isEmpty()){
                    return;
                }

                //执行事件处理器
                for(Runnable runnable : functions){
                    runnable.run();
                }
            }
        });
    }

    //初始即刷新（只运行一次）
    @Override
    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        // 设置抗锯齿和渲染提示
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        //画背景胶囊
        paintCapsule(graphics);
        //画胶囊按钮
        paintRound(graphics);
    }

    //画胶囊
    private void paintCapsule(Graphics2D graphics){
        //根据当前状态绘制底色
        String state = this.state.getState();

        if(state.equals("work")) {
            //设置底色为浅绿色
            graphics.setColor(new Color(209, 237, 196));
            graphics.fillRoundRect(panelX, panelY, width, height, radian, radian);
        }else {
            //设置底色为灰色
            graphics.setColor(new Color(233, 233, 236));
            graphics.fillRoundRect(panelX, panelY, width, height, radian, radian);
        }

        //绘制边框（默认灰色边框）
        drawBorder(graphics,radian,radian, ButtonColor.BorderColor.defaultColor);
    }

    //画圆
    private void paintRound(Graphics2D graphics){
        //根据当前状态设置按钮颜色
        graphics.setColor(this.state.getColor());

        //绘制不同位置的圆
        graphics.fillOval(this.capsuleCurrentX,panelY,radian,radian);
    }

    //设置动画效果
    private void openAction(){
        //10ms执行一次
        this.action = new Timer(actionFrame, e -> {
            String state = this.state.getState();
            //如果为工作状态，则向右绘制
            if("work".equals(state)){
                this.capsuleCurrentX += moveSpeed;
                repaint();
                if(this.capsuleCurrentX>=panelX+width-radian) {
                    this.capsuleCurrentX=panelX+width-radian;
                    repaint();
                    this.action.stop();
                    return;
                }
            }else {
                this.capsuleCurrentX -= moveSpeed;
                repaint();
                if(this.capsuleCurrentX<=panelX) {
                    this.capsuleCurrentX=panelX;
                    repaint();
                    this.action.stop();
                    return;
                }
            }
        });

        this.action.start();
    }

    //鼠标点击事件，点击后切换按钮状态
    private void mouseClickGUIHandle(){
        synchronized (this) {
            //修改当前按钮状态
            if ("work".equals(this.state.getState())) {
                setState(StateColorEnum.FREE);
            } else {
                setState(StateColorEnum.WORK);
            }
            //开启动画
            openAction();
        }
    }

    //绑定事件
    public void bindOnClick(Runnable function){
        this.functions.add(function);
    }

    public void setState(StateColorEnum state) {
        this.state = state;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public void setRadian(Integer radian) {
        this.radian = radian;
    }
}






















































