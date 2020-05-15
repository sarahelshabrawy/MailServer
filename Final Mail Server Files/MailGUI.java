/*
 * Created by JFormDesigner on Sat Apr 25 05:13:09 EET 2020
 */

package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import java.awt.*;
import javax.swing.border.*;

/**
 * @author hyl
 */
public class MailGUI extends JFrame {
    public MailGUI (){
        initComponents();
    }

App myApp = new App();
    private void filterActionPerformed(ActionEvent e) {
        FilterMenu filterMenu = new FilterMenu();
        filterMenu.setVisible(true);

    }
    private void composeActionPerformed(ActionEvent e) {
        GUICompose compose =new GUICompose();
    }

    private void InboxActionPerformed(ActionEvent e) {
        current = new Folder("inbox",GUISignIn.Email);
        myApp.setViewingOptions(current,null,new Sort("Date ( Lastest )"));
        EmailsList.myTable.buildGUI(1);

    }

    private void SentActionPerformed(ActionEvent e) {
        current = new Folder("sent",GUISignIn.Email);
        myApp.setViewingOptions(current,null,new Sort("Date ( Lastest )"));
        EmailsList.myTable.buildGUI(1);


    }

    private void DraftsActionPerformed(ActionEvent e) {
        current = new Folder("drafts",GUISignIn.Email);
        myApp.setViewingOptions(current,null,new Sort("Date ( Lastest )"));
        EmailsList.myTable.buildGUI(1);

    }


    private void TrashActionPerformed(ActionEvent e) {
        current = new Folder("trash",GUISignIn.Email);
        myApp.setViewingOptions(current,null,new Sort("Date ( Lastest )"));
        EmailsList.myTable.buildGUI(1);


    }
    private void contactsActionPerformed(ActionEvent e) {
GUIContactsDisplay guiContactsDisplay = new GUIContactsDisplay(GUISignIn.Email);
    }

    private void FiltersActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

   /* private void createUIComponents() {
        thumb = new JLabel(new ImageIcon("81kTrSqMuLL._AC_SL1135_.jpg")) ;   }
*/

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - hyl
        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        // Generated using JFormDesigner Evaluation license - hyl
        JPanel Menu = new JPanel();
        JButton compose = new JButton();
        JPanel panel3 = new JPanel();
        Inbox = new JButton();
        Sent = new JButton();
        Drafts = new JButton();
        Trash = new JButton();
        Filters = new JButton();
        contacts = new JButton();
        JPanel panel1 = new JPanel();
        App myApp = new App();
        myApp.setViewingOptions(MailGUI.current,null,new Sort("Date ( Lastest )"));

        //======== this ========
        setForeground(Color.white);
        setBackground(new Color(34, 40, 49));
        var contentPane = getContentPane();

        //======== Menu ========
        {
            Menu.setBorder(new LineBorder(Color.white));
            Menu.setBackground(new Color(34, 40, 49));
            Menu.setForeground(Color.white);

            //---- compose ----
            compose.setText("Compose");
            compose.setBorder(new EmptyBorder(5, 5, 5, 5));
            compose.addActionListener(this::composeActionPerformed);

            //======== panel3 ========
            {
                panel3.setOpaque(false);
                panel3.setBackground(Color.white);
                panel3.setForeground(Color.white);

                //---- Inbox ----
                Inbox.setText("Inbox");
                Inbox.setOpaque(false);
                Inbox.setContentAreaFilled(false);
                Inbox.setBorder(new LineBorder(Color.white, 1, true));
                Inbox.setForeground(Color.white);
                Inbox.addActionListener(e -> {
                    InboxActionPerformed(e);
                    InboxActionPerformed(e);
                });

                //---- Sent ----
                Sent.setText("Sent");
                Sent.setOpaque(false);
                Sent.setContentAreaFilled(false);
                Sent.setBorder(new LineBorder(Color.white, 1, true));
                Sent.setForeground(Color.white);
                Sent.addActionListener(this::SentActionPerformed);

                //---- Drafts ----
                Drafts.setText("Drafts");
                Drafts.setOpaque(false);
                Drafts.setContentAreaFilled(false);
                Drafts.setBorder(new LineBorder(Color.white, 1, true));
                Drafts.setForeground(Color.white);
                Drafts.addActionListener(this::DraftsActionPerformed);

                //---- Trash ----
                Trash.setText("Trash");
                Trash.setOpaque(false);
                Trash.setContentAreaFilled(false);
                Trash.setBorder(new LineBorder(Color.white, 1, true));
                Trash.setForeground(Color.white);
                Trash.addActionListener(this::TrashActionPerformed);
                //---- Filters ----
                Filters.setText("Filters");
                Filters.setOpaque(false);
                Filters.setContentAreaFilled(false);
                Filters.setBorder(new LineBorder(Color.white, 1, true));
                Filters.setForeground(Color.white);
                Filters.addActionListener(
                  this::FiltersActionPerformed
                );

                GroupLayout panel3Layout = new GroupLayout(panel3);
                panel3.setLayout(panel3Layout);
                panel3Layout.setHorizontalGroup(
                        panel3Layout.createParallelGroup()
                                .addGroup(panel3Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(panel3Layout.createParallelGroup()
                                                .addComponent(Sent, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(Inbox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(Drafts, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(Trash, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addComponent(Filters, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))
                                        .addContainerGap())
                );
                panel3Layout.setVerticalGroup(
                        panel3Layout.createParallelGroup()
                                .addGroup(panel3Layout.createSequentialGroup()
                                        .addComponent(Inbox)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Sent)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Drafts)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Trash)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Filters)
                                        .addContainerGap())
                );
            }

            //---- contacts ----
            contacts.setText("My Contacts");
            contacts.setOpaque(false);
            contacts.setContentAreaFilled(false);
            contacts.setBorder(new LineBorder(Color.white, 1, true));
            contacts.setForeground(Color.white);
            contacts.addActionListener(this::contactsActionPerformed);

            GroupLayout MenuLayout = new GroupLayout(Menu);
            Menu.setLayout(MenuLayout);
            MenuLayout.setHorizontalGroup(
                    MenuLayout.createParallelGroup()
                            .addComponent(panel3, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(MenuLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(MenuLayout.createParallelGroup()
                                            .addComponent(contacts, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(compose, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            MenuLayout.setVerticalGroup(
                    MenuLayout.createParallelGroup()
                            .addGroup(MenuLayout.createSequentialGroup()
                                    .addGap(23, 23, 23)
                                    .addComponent(compose)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(panel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(contacts)
                                    .addContainerGap(264, Short.MAX_VALUE))
            );
        }

        //======== panel1 ========

        CardLayout cl = new CardLayout();
        EmailsList el = new EmailsList();
        panel1.setLayout(cl);
        panel1.add(el,"1");



        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addComponent(Menu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                        .addComponent(Menu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        setVisible(true);
        cl.show(panel1,"1");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    private void MailListTo(){
        Sent.setForeground(Color.black);
        Inbox.setForeground(Color.black);
        Trash.setForeground(Color.black);
        Drafts.setForeground(Color.black);

    }

    private JButton Inbox;
    private JButton Sent;
    private JButton Drafts;
    private JButton Trash;
    private JButton Filters;
    private JButton contacts;
    public static Folder current = new Folder("inbox",GUISignIn.Email);
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

