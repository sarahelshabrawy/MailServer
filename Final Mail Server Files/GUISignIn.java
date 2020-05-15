package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GUISignIn {
    Operations_Folder folder = new Operations_Folder();
    private JFrame frame;
    private JTextField email_field;
    private JPasswordField passwordField;
    public static String Email;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
//		Path p = Paths.get("Sign in.jpg");
//		Path folder = p.getParent();
//		String signin = getPath();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUISignIn window = new GUISignIn();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public GUISignIn() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1087, 686);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle("Mail Server");

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1111, 665);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel showPasswordL = new JLabel("Show password");
        showPasswordL.setFont(new Font("Tahoma", Font.PLAIN, 14));
        showPasswordL.setBounds(822, 394, 102, 23);
        panel.add(showPasswordL);

        JCheckBox ShowPassword = new JCheckBox("New check box");
        ShowPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(ShowPassword.isSelected()) {
                    passwordField.setEchoChar((char)0);
                }else {
                    passwordField.setEchoChar('*');
                }
            }
        });
        ShowPassword.setBackground(new Color(0, 0, 0));
        ShowPassword.setBounds(795, 394, 21, 23);
        panel.add(ShowPassword);

        JLabel rememberMe = new JLabel("Remember me");
        rememberMe.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rememberMe.setBounds(555, 392, 117, 23);
        panel.add(rememberMe);

        JCheckBox rememberBox = new JCheckBox("");
        rememberBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ((folder.ReadFile("the parent//" + new String(email_field.getText()) + "//info.txt", 5)
                        .equals(null))) {
                    folder.WriteFile("the parent//" + new String(email_field.getText()) + "//info.txt", "true",true);
                }
//				else {
//
//				}
            }
        });
        rememberBox.setBackground(new Color(0, 0, 0));
        rememberBox.setForeground(new Color(128, 0, 0));
        rememberBox.setBounds(528, 394, 21, 23);
        panel.add(rememberBox);

        JButton signUp = new JButton("Sign Up");
        signUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUISignUp w2 = new GUISignUp();
                frame.setVisible(false);
                w2.SignUpScreen();
            }
        });
        signUp.setForeground(Color.WHITE);
        signUp.setFont(new Font("Tahoma", Font.BOLD, 17));
        signUp.setBackground(Color.black);
        signUp.setBounds(677, 568, 117, 34);
        panel.add(signUp);

        JButton signIn = new JButton("Sign In");
        signIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pass = folder.ReadFile("the parent//" + email_field.getText() + "//info.txt",4);
                App test = new App();
                 Email = new String(email_field.getText());
                String password = new String(passwordField.getPassword());
                if(new File("the parent//" + new String(email_field.getText())+ "//info.txt").exists()) {
                    if ((folder.ReadFile("the parent//" + new String(email_field.getText()) + "//info.txt", 5)
                            .equals("true"))) {
                        boolean success = test.signin(Email,pass);
                        if (success) {
                            passwordField.setText(pass); // <<<<<<<<<<<<
                            JOptionPane.showMessageDialog(frame, "You are successfully logined");
                            MailGUI mailGUI = new MailGUI();
                            frame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(frame, "Invalid username or password");
                        }

                    } else {
                        folder.WriteFile("the parent//" + email_field.getText() + "//info.txt", "true",true);
                        boolean success = test.signin(Email, password);
                        if (success) {
                            JOptionPane.showMessageDialog(frame, "You are successfully logined");
                            MailGUI mailGUI = new MailGUI();
                            frame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(frame, "Invalid username or password");
                        }

                    }
                }else {
                    JOptionPane.showMessageDialog(frame, "This E-mail doesn't exist, you should sign up first !");
                }
            }
        });
        signIn.setFont(new Font("Tahoma", Font.BOLD, 17));
        signIn.setBounds(664, 436, 139, 46);
        signIn.setBackground(Color.white);
        panel.add(signIn);

        email_field = new JTextField();
        email_field.setFont(new Font("Tahoma", Font.BOLD, 14));
        email_field.setBounds(503, 241, 466, 34);
        panel.add(email_field);
        email_field.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.BOLD, 14));
        passwordField.setBounds(503, 337, 466, 34);
        panel.add(passwordField);

        JLabel passwordL = new JLabel("Password :");
        passwordL.setLabelFor(passwordL);
        passwordL.setFont(new Font("Tahoma", Font.BOLD, 18));
        passwordL.setBounds(503, 298, 102, 28);
        panel.add(passwordL);

        JLabel emailL = new JLabel("E-mail Or Username :");
        emailL.setLabelFor(emailL);
        emailL.setFont(new Font("Tahoma", Font.BOLD, 18));
        emailL.setBounds(503, 202, 200, 28);
        panel.add(emailL);

        JLabel show_password = new JLabel("");
        show_password.setIcon(new ImageIcon(Add_Contact.class.getResource("/eg/edu/alexu/csd/datastructure/mailServer/Sign in.jpg")));
        show_password.setBounds(0, 0, 1078, 665);
        panel.add(show_password);
        frame.setVisible(true);

    }
}