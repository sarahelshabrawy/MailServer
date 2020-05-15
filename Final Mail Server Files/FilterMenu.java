/*
 * Created by JFormDesigner on Sat Apr 25 06:18:45 EET 2020
 */

package eg.edu.alexu.csd.datastructure.mailServer;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author hyl
 */
public class FilterMenu extends JDialog {
    public FilterMenu() {
        super();
        initComponents();
    }

    private void filterbuttonActionPerformed(ActionEvent e) {
        String target;
        Stack filterPairStack = new Stack();
        if(!(target = toText.getText()).isEmpty()){
            filterPairStack.push(new Filter.filterPair(EFilterTypes.To,target));
        }
        if(!(target = fromText.getText()).isEmpty()){
            filterPairStack.push(new Filter.filterPair(EFilterTypes.From,target));
        }
        if(!(target = subjectText.getText()).isEmpty()){
            filterPairStack.push(new Filter.filterPair(EFilterTypes.Subject,target));
        }
       if(withinCombobox.getSelectedIndex()!=0){
           filterPairStack.push(new Filter.filterPair(EFilterTypes.Date, (String) withinCombobox.getSelectedItem()));
       }
       if(hasAttachments.isSelected()){
           filterPairStack.push(new Filter.filterPair(EFilterTypes.Attachments,null));
       }
       dispose();
       Sort sort = new Sort(Objects.requireNonNull(EmailsList.Sort.getSelectedItem()).toString());
        Filter filter = new Filter(filterPairStack);
        App myApp = new App();
        myApp.setViewingOptions(MailGUI.current,filter,sort);
        EmailsList.myTable.buildGUI(1);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - hyl
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label5 = new JLabel();
        hasAttachments = new JCheckBox();
        filterbutton = new JButton();
        fromText = new JTextField();
        subjectText = new JTextField();
        toText = new JTextField();
        withinCombobox = new JComboBox<>();

        //======== this ========
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("From");

        //---- label2 ----
        label2.setText("To");

        //---- label3 ----
        label3.setText("Subject");

        //---- label5 ----
        label5.setText("Date within");

        //---- label6 ----

        //---- hasAttachments ----
        hasAttachments.setText("Has Attachments");

        //---- filterbutton ----
        filterbutton.setText("Filter");
        filterbutton.addActionListener(e -> filterbuttonActionPerformed(e));

        //---- withinCombobox ----
        withinCombobox.setMaximumRowCount(5);
        withinCombobox.setModel(new DefaultComboBoxModel<>(new String[] {
            " ",
            "1 Day",
            "1 Week",
            "1 Month",
            "1 Year"
        }));


        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label5)
                        .addComponent(hasAttachments)
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(filterbutton)
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label2)
                                    .addComponent(label3)
                                    )
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(toText, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                    .addComponent(subjectText, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                    .addComponent(withinCombobox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fromText, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                    ))))
                    .addGap(10, 10, 10))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(fromText, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGap(8, 8, 8)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(toText, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGap(8, 8, 8)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(subjectText, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGap(8, 8, 8)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(withinCombobox, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGap(8, 8, 8)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE))

                    .addGap(8, 8, 8)
                    .addComponent(hasAttachments, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(filterbutton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addGap(25, 25, 25))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - hyl
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label5;
    private JCheckBox hasAttachments;
    private JButton filterbutton;
    private JTextField fromText;
    private JTextField subjectText;
    private JTextField toText;
    private JComboBox<String> withinCombobox;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        FilterMenu h = new FilterMenu();
        h.setVisible(true);
    }
}
