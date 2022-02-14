package chap14.bounceThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class BounceThread
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> {
            JFrame frame = new BounceFrame();
            frame.setTitle("BounceThread");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class BounceFrame extends JFrame
{
    private BallComponent comp;
    public static final int STEPS = 1000;
    public static final int DEALY = 5;

    public BounceFrame()
    {
        comp = new BallComponent();
        add(comp, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Start", event -> addBall());
        addButton(buttonPanel, "close", event -> System.exit(0));
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    public void addButton(Container c, String title, ActionListener listener)
    {
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }

    public void addBall()
    {
        Ball ball = new Ball();
        comp.add(ball);
        Runnable r = () -> {
            try
            {
                for (int i = 1; i <= STEPS; i++)
                {
                    ball.move(comp.getBounds());
                    comp.repaint();
                    Thread.sleep(DEALY);
                }
            }
            catch (InterruptedException e)
            {

            }
        };
        Thread t = new Thread(r);
        t.start();
    }
}