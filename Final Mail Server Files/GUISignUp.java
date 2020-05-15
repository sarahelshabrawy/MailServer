package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.util.regex.Pattern;

public class GUISignUp {

    private JFrame frame;
    private JTextField FNameF;
    private JTextField LNameF;
    private JTextField EmailF;
    private JPasswordField passwordField;
    private JPasswordField confirmationF;

    /**
     * Launch the application.
     */
    public static void SignUpScreen() {
        EventQueue.invokeLater(() -> {
            try {
                GUISignUp window = new GUISignUp();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the application.
     */
    public GUISignUp() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1097, 683);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle("Mail Server");

        JPanel panel = new JPanel();
        panel.setBounds(0, 11, 1119, 656);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel showpassword = new JLabel("Show Password");
        showpassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        showpassword.setBounds(858, 398, 111, 14);
        panel.add(showpassword);

        JCheckBox ShowPassBox = new JCheckBox("New check box");
        ShowPassBox.addActionListener(e -> {
            if(ShowPassBox.isSelected()) {
                passwordField.setEchoChar((char)0);
            }else {
                passwordField.setEchoChar('*');
            }
        });
        ShowPassBox.setBackground(new Color(0, 0, 0));
        ShowPassBox.setBounds(830, 394, 22, 23);
        panel.add(ShowPassBox);

        JLabel terms_conditions = new JLabel("<html>\r\n<body>\r\n<u>Terms And Conditions</u>\r\n</body>\r\n</html>");
        terms_conditions.setFont(new Font("Tahoma", Font.BOLD, 13));
        terms_conditions.setBounds(590, 551, 157, 23);
        panel.add(terms_conditions);

        JLabel Agree = new JLabel("Agree");
        Agree.setFont(new Font("Tahoma", Font.BOLD, 17));
        Agree.setBounds(534, 545, 59, 23);
        panel.add(Agree);

        JCheckBox agreeBox = new JCheckBox("New check box");
        agreeBox.setBackground(new Color(0, 0, 0));
        agreeBox.setBounds(506, 545, 22, 23);
        panel.add(agreeBox);

        confirmationF = new JPasswordField();
        confirmationF.setFont(new Font("Tahoma", Font.BOLD, 14));
        confirmationF.setBounds(506, 452, 463, 31);
        panel.add(confirmationF);

        EmailF = new JTextField();
        EmailF.setFont(new Font("Tahoma", Font.BOLD, 14));
        EmailF.setBounds(506, 241, 463, 31);
        panel.add(EmailF);
        EmailF.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.BOLD, 14));
        passwordField.setBounds(506, 356, 463, 31);
        panel.add(passwordField);

        FNameF = new JTextField();
        FNameF.setFont(new Font("Tahoma", Font.BOLD, 14));
        FNameF.setBounds(506, 152, 195, 23);
        panel.add(FNameF);
        FNameF.setColumns(10);

        LNameF = new JTextField();
        LNameF.setFont(new Font("Tahoma", Font.BOLD, 14));
        LNameF.setColumns(10);
        LNameF.setBounds(770, 152, 195, 23);
        panel.add(LNameF);

        JButton signUpB = new JButton("Sign Up");
        signUpB.addActionListener(e -> {
            App test = new App();
            String FirstName = FNameF.getText();
            String LastName = LNameF.getText();
            String Email = EmailF.getText();
            String password = new String(passwordField.getPassword());
            String Confirmation = new String(confirmationF.getPassword());

            String emailRegex = "^[a-zA-Z0-9_+&-]+(?:\\." + "[a-zA-Z0-9_+&-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                    + "A-Z]{2,7}$";
            Pattern pat = Pattern.compile(emailRegex);

            Contact contact = new Contact(FirstName, LastName, Email, password, Confirmation);
            boolean confirm = contact.password_affirmation();
            if (!FirstName.equals("") && !LastName.equals("") && !Email.equals("") && !password.equals("")
                    && !Confirmation.equals("")) {
                if (pat.matcher(Email).matches()) {
                    if (password.length() >= 6) {
                        if (agreeBox.isSelected()) {
                            if (confirm) {
                                boolean success = test.signup(contact);
                                if (success) {
                                    JOptionPane.showMessageDialog(frame, "You are successfully signed up");
                                    GUISignIn guiSignIn = new GUISignIn();
                                    frame.dispose();
                                } else {
                                    JOptionPane.showMessageDialog(frame, "This E-mail is used");
                                }
                            } else {
                                JOptionPane.showMessageDialog(frame, "confirmation password is wrong");
                            }
                        } else {
                            JOptionPane.showMessageDialog(frame, "You should agree to terms and condition");
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Password must be 6 characters at least");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid E-mail!");
                }

            } else {
                JOptionPane.showMessageDialog(frame, "You should fill all fields");
            }
        });
        signUpB.setForeground(new Color(255, 255, 255));
        signUpB.setBackground(new Color(0, 0, 0));
        signUpB.setFont(new Font("Tahoma", Font.BOLD, 17));
        signUpB.setBounds(848, 545, 121, 31);
        signUpB.setBackground(Color.black);
        panel.add(signUpB);

        JLabel confirmationL = new JLabel("Password Confirmation :");
        confirmationL.setFont(new Font("Tahoma", Font.BOLD, 17));
        confirmationL.setBounds(502, 410, 224, 31);
        panel.add(confirmationL);

        JLabel passwordL = new JLabel("Password :");
        passwordL.setFont(new Font("Tahoma", Font.BOLD, 17));
        passwordL.setBounds(502, 309, 111, 31);
        panel.add(passwordL);

        JLabel emailL = new JLabel("E-mail :");
        emailL.setFont(new Font("Tahoma", Font.BOLD, 17));
        emailL.setBounds(502, 199, 111, 31);
        panel.add(emailL);

        JLabel LNameL = new JLabel("Last Name :");
        LNameL.setFont(new Font("Tahoma", Font.BOLD, 17));
        LNameL.setBounds(770, 102, 111, 31);
        panel.add(LNameL);

        JLabel FNameL = new JLabel("First Name :");
        FNameL.setFont(new Font("Tahoma", Font.BOLD, 17));
        FNameL.setBounds(502, 102, 111, 31);
        panel.add(FNameL);

        JLabel Image = new JLabel("");
        Image.setFont(new Font("Tahoma", Font.BOLD, 14));
        Image.setIcon(new ImageIcon(Add_Contact.class.getResource("/eg/edu/alexu/csd/datastructure/mailServer/Sign up.jpg")));
        Image.setBounds(0, -12, 1089, 657);
        panel.add(Image);
    }

}