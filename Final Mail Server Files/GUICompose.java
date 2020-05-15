package eg.edu.alexu.csd.datastructure.mailServer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import java.io.*;


import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;

import javax.swing.JFileChooser;

import java.awt.event.ActionListener;

public class GUICompose extends JFrame {

    public GUICompose() {
        Mail mail=new Mail();
        App a=new App();
        setBounds(100, 100, 700, 686);
        setResizable(true);
        getContentPane().setLayout(null);
        setTitle("Mail Server");


        JMenuBar mb=new JMenuBar();

        JMenu attachment=new JMenu("insert") ;

        attachment.setFont(new Font("Tahoma", Font.BOLD, 17));
        attachment.setForeground(Color.black);
        JMenuItem open=new JMenuItem("files") ;
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setMultiSelectionEnabled(true);
                chooser.showOpenDialog(null);
                File[] files = chooser.getSelectedFiles();
                mail.get_attachment(files);

            }
        });

        JMenu priority=new JMenu("priority") ;
        priority.setFont(new Font("Tahoma", Font.BOLD, 17));
        priority.setForeground(Color.black);


        JMenuItem low=new JMenuItem("low ") ;

        low.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mail.get_priority("4");

            }
        });
        JMenuItem normal=new JMenuItem("normal") ;
        normal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mail.get_priority("3");

            }
        });
        JMenuItem high=new JMenuItem("high") ;
        high.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mail.get_priority("2");

            }
        });

        JMenuItem veryhigh=new JMenuItem("very high ") ;

        low.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mail.get_priority("1");

            }
        });
        priority.add(low);priority.add(high);priority.add(normal);priority.add(veryhigh);
        mb.add(priority);
        mb.add(attachment);
        attachment.add(open);

        JLabel subject = new JLabel("subject");
        subject.setFont(new Font("Tahoma", Font.PLAIN, 20));
        subject.setBounds(2, 60, 150, 34);

        JLabel to = new JLabel("To");
        to.setFont(new Font("Tahoma", Font.PLAIN, 20));
        to.setBounds(2, 20, 150, 34);

        JTextField textField_subject = new JTextField();
        textField_subject.setFont(new Font("Tahoma", Font.BOLD, 14));
        textField_subject.setBounds(70, 60, 600, 34);

        JTextArea area=new JTextArea();
        area.setBounds(0,100, 686,550);

        JTextField textField_to = new JTextField();
        textField_to.setFont(new Font("Tahoma", Font.BOLD, 14));
        textField_to.setBounds(70, 20, 600, 34);
        add(textField_to);
        add(textField_to);
        add(textField_subject);
        textField_to.setColumns(10);

        JButton send=new JButton("send");
        send.setBounds(550,550,95,30);
        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mail.get_subject(textField_subject.getText());
                mail.get_body(area.getText());

                String[] words = textField_to.getText().split("\\s+");
                for (int i = 0; i < words.length; i++) {
                    // You may want to check for a non-word character before blindly
                    // performing a replacement
                    // It may also be necessary to adjust the character class
                    words[i] = words[i];
                }
                mail.get_sender(words);
                mail.from(GUISignIn.Email);
                if(a.compose(mail)) {
                    JOptionPane.showMessageDialog(send, "your e_mail is sent successfully");

                }

                else {
                    JOptionPane.showMessageDialog(send, "s");

                }
dispose();
            }
        });






       add(send);
        add(area);
        add(subject);
        add(to);
        setJMenuBar(mb);
       setVisible(true);


    }


}