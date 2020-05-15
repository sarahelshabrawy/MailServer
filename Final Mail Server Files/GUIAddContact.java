package eg.edu.alexu.csd.datastructure.mailServer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIAddContact extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField last;
    private JTextField first;
    private JTextField email;
    Operations_Folder of = new Operations_Folder();


    public GUIAddContact(String user) {
        setBounds(100, 100, 517, 335);
        getContentPane().setLayout(null);
        contentPanel.setBounds(0, 0, 501, 296);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel);
        contentPanel.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 501, 296);
        contentPanel.add(panel);
        panel.setLayout(null);

        JButton save = new JButton("SAVE");
        save.setFont(new Font("Tahoma", Font.BOLD, 17));
        save.setBounds(348, 216, 122, 34);
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                save_contacts(user);

            }
        });
        panel.add(save);

        JLabel Email = new JLabel("E-mail :");
        Email.setFont(new Font("Tahoma", Font.PLAIN, 16));
        Email.setBounds(201, 122, 85, 20);
        panel.add(Email);

        JLabel last_name = new JLabel("Last Name :");
        last_name.setFont(new Font("Tahoma", Font.PLAIN, 16));
        last_name.setBounds(348, 58, 98, 20);
        panel.add(last_name);

        JLabel first_name = new JLabel("First Name :");
        first_name.setFont(new Font("Tahoma", Font.PLAIN, 16));
        first_name.setBounds(201, 58, 98, 20);
        panel.add(first_name);

        JLabel image = new JLabel("");
        image.setIcon(new ImageIcon(Add_Contact.class.getResource("/eg/edu/alexu/csd/datastructure/mailServer/Add Contact.jpg")));
        image.setBounds(0, 0, 501, 296);
        image.setBackground(Color.PINK);
        panel.add(image);

        last = new JTextField();
        last.setFont(new Font("Tahoma", Font.PLAIN, 15));
        last.setBounds(348, 85, 122, 28);
        panel.add(last);
        last.setColumns(10);

        first = new JTextField();
        first.setFont(new Font("Tahoma", Font.PLAIN, 15));
        first.setColumns(10);
        first.setBounds(201, 85, 122, 28);
        panel.add(first);

        email = new JTextField();
        email.setFont(new Font("Tahoma", Font.PLAIN, 15));
        email.setBounds(200, 151, 270, 28);
        panel.add(email);
        email.setColumns(10);
    }
    public static void AddContactScreen(String user) {
        try {
            GUIAddContact dialog = new GUIAddContact(user);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void save_contacts(String user) {
    	String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";
		Pattern pat = Pattern.compile(emailRegex);
		if(!first.getText().equals("") && !last.getText().equals("") && !email.getText().equals("")) {
			if (pat.matcher(email.getText()).matches()) {
        String first_last=first.getText()+"\t\t"+last.getText();
        of.WriteFile("the parent//"+user+"//contacts.txt",first_last,true);
       of.WriteFile("the parent//"+user+"//contacts.txt",email.getText(),true);
			} else {
				JOptionPane.showMessageDialog(contentPanel, "Invalid E-mail!");
			}
		}else {
			JOptionPane.showMessageDialog(contentPanel, "You should complete all fields to save contact");
		}
       dispose();
       GUIContactsDisplay guiContactsDisplay = new GUIContactsDisplay(user);
    }

}