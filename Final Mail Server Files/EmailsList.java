/*
 * Created by JFormDesigner on Fri May 01 08:40:18 EET 2020
 */

package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * @author hyl
 */
public class EmailsList extends JPanel {
    App myApp = new App();
    public EmailsList() {
        initComponents();
    }

    private void filterActionPerformed(ActionEvent e) {
        FilterMenu filterMenu = new FilterMenu();
        filterMenu.setVisible(true);
        // TODO add your code here
    }

    private void SortActionPerformed(ActionEvent e) {

        Sort sort = new Sort(Objects.requireNonNull(Sort.getSelectedItem()).toString());
        myApp.setViewingOptions(MailGUI.current,null,sort);
        myTable.buildGUI(1);
        // FilterMenu.

    }
    private void ButtonSearchActionPerformed(ActionEvent e){
        if(textSearch.getText().trim().equals(""))
            JOptionPane.showMessageDialog(invisible,"Enter a word in the search bar");
        search = new Search(MailGUI.current,textSearch.getText());
        myTable.buildGUI(1);
        if(myTable.getNumberOfMails()==0){
            JOptionPane.showMessageDialog(invisible, "No mail in this Folder");
        }

    }
    private void RefreshActionPerformed(ActionEvent e){
      myApp.setViewingOptions(MailGUI.current,null,new Sort("Date ( Lastest )"));
        JOptionPane.showMessageDialog(invisible, "Mail box refreshed");


    }
    private void prevActionPerformed(ActionEvent e) {

        if(myTable.page==0)
            return;
        myTable.buildGUI(--myTable.page);

    }
    private void nextActionPerformed(ActionEvent e) {

        myTable.buildGUI(++myTable.page);

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - hyl
        emailListHeader = new JPanel();
        Sort = new JComboBox<>();
        DisplayedMail = new JLabel();
        refresh = new JButton();
        labelSort = new JLabel();
        searchFilter = new JPanel();
        filter = new JButton();
        textSearch = new JTextField();
        paging = new JPanel();
        nextPage = new JButton();
        prevPage = new JButton();
        buttonSearch = new JButton();
        moveToComboBox = new JComboBox<>();
        buttonDelete = new JButton();
        myTable = new table(1);
        scrollMails = new JScrollPane(myTable);
        if(myTable.getNumberOfMails()==0){
            JOptionPane.showMessageDialog(invisible, "No mail in this Folder");
        }


        //======== this ========
        setPreferredSize(new Dimension(754, 533));
        setMinimumSize(new Dimension(754, 533));
        //======== emailListHeader ========
        {

            //---- Sort ----
            Sort.setModel(new DefaultComboBoxModel<>(new String[] {
                    "Date ( Lastest )",
                    "Date ( Oldest )",
                    "Sender ( A to Z )",
                    "Sender ( Z to A )",
                    "Subject ( A to Z )",
                    "Attachment Size"
            }));
            Sort.addActionListener(e -> SortActionPerformed(e));

            //---- DisplayedMail ----
            DisplayedMail.setText("Inbox");

            //---- refresh ----
            refresh.setText("\u27f3");
            refresh.setFont(refresh.getFont().deriveFont(refresh.getFont().getStyle() | Font.BOLD, refresh.getFont().getSize() + 2f));
            refresh.setOpaque(false);
            refresh.setContentAreaFilled(false);
            refresh.setBorderPainted(false);
            refresh.addActionListener(e -> RefreshActionPerformed(e));

            //---- labelSort ----
            labelSort.setText("Sort by");

            GroupLayout emailListHeaderLayout = new GroupLayout(emailListHeader);
            emailListHeader.setLayout(emailListHeaderLayout);
            emailListHeaderLayout.setHorizontalGroup(
                    emailListHeaderLayout.createParallelGroup()
                            .addGroup(emailListHeaderLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(DisplayedMail, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)                        .addComponent(labelSort)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(Sort, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(refresh, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
            );
            emailListHeaderLayout.setVerticalGroup(
                    emailListHeaderLayout.createParallelGroup()
                            .addGroup(emailListHeaderLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addGroup(emailListHeaderLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(DisplayedMail, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(labelSort)
                                            .addComponent(Sort, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(refresh, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE))
            );
        }

        //======== searchFilter ========
        {

            //---- filter ----
            filter.setText("Filter \ud83d\udd3d");
            filter.addActionListener(e -> filterActionPerformed(e));
//---- buttonSearch ----

            buttonSearch.setText("Search");
            buttonSearch.addActionListener(e -> ButtonSearchActionPerformed(e));
            //---- textSearch ----
            textSearch.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            textSearch.setText("Search");
            textSearch.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    textSearch.setText("");
                }
            });

            GroupLayout searchFilterLayout = new GroupLayout(searchFilter);
            searchFilter.setLayout(searchFilterLayout);
            searchFilterLayout.setHorizontalGroup(
                    searchFilterLayout.createParallelGroup()
                            .addGroup(searchFilterLayout.createSequentialGroup()
                                    .addComponent(textSearch, GroupLayout.PREFERRED_SIZE, 543, GroupLayout.PREFERRED_SIZE)

                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(buttonSearch, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(filter)
                                    .addContainerGap())
            );
            searchFilterLayout.setVerticalGroup(
                    searchFilterLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, searchFilterLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(searchFilterLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addComponent(filter, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                                            .addComponent(textSearch, GroupLayout.Alignment.LEADING)
                                            .addComponent(buttonSearch, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                                    .addContainerGap()
                            ));
        }

        //======== paging ========
        {

            //---- nextPage ----
            nextPage.setText(">");
            nextPage.setFont(new Font("Segoe UI", Font.BOLD, 14));
            nextPage.addActionListener(this::nextActionPerformed);
            //---- prevPage ----
            prevPage.setText("<");
            prevPage.setFont(new Font("Segoe UI", Font.BOLD, 14));
            prevPage.addActionListener(this::prevActionPerformed);
            //---- moveToComboBox ----
            moveToComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "Move to :",
                    "Inbox",
                    "Sent",
                    "Drafts",
                    "Trash"
            }));

            //---- buttonDelete ----
            buttonDelete.setText("Delete");

            GroupLayout pagingLayout = new GroupLayout(paging);
            paging.setLayout(pagingLayout);
            pagingLayout.setHorizontalGroup(
                    pagingLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, pagingLayout.createSequentialGroup()
                                    .addComponent(moveToComboBox, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(buttonDelete, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)                        .addComponent(prevPage)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(nextPage)
                                    .addContainerGap())
            );
            pagingLayout.setVerticalGroup(
                    pagingLayout.createParallelGroup()
                            .addGroup(pagingLayout.createSequentialGroup()
                                    .addGroup(pagingLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(prevPage)
                                            .addComponent(nextPage))
                                    .addGap(0, 5, Short.MAX_VALUE))
                            .addGroup(pagingLayout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addGroup(pagingLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)                                                        .addComponent(moveToComboBox, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                                            .addComponent(buttonDelete, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)))
            );
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addComponent(emailListHeader, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollMails)
                        .addComponent(paging, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchFilter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(emailListHeader, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchFilter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(paging, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollMails, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE))        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - hyl
    private JPanel emailListHeader;
    public static JComboBox<String> Sort;
    private JLabel DisplayedMail;
    private JButton refresh;
    private JLabel labelSort;
    private JPanel searchFilter;
    private JButton filter;
    private JTextField textSearch;
    private JPanel paging;
    private JButton nextPage;
    private JButton prevPage;
    private JScrollPane scrollMails;
    private JButton buttonSearch;
    private JButton buttonDelete;
    private JComboBox<String> moveToComboBox;
    Search search;
    JFrame invisible = new JFrame();
static table myTable;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}