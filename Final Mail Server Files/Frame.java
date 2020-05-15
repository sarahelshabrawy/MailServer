package eg.edu.alexu.csd.datastructure.mailServer;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    int Framesize = 10;
    public Frame (){
        JPanel panel = new JPanel();
        Button button = new Button();
        JButton haha = new JButton();

        add(panel);
        add(button);
        setSize(Framesize,Framesize);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
    }

    public static void main(String[] args) {
        Frame f = new Frame();
        f.setVisible(true);
    }
    public static class Button extends JButton{

        public Button() {
            setSize(25,30);
            setBackground(Color.decode("#C8933B"));

        }

    }
}
