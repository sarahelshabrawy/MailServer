package eg.edu.alexu.csd.datastructure.mailServer;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;



public class GUIContactsDisplay extends JDialog {

    Operations_Folder folder = new Operations_Folder();
    private final JPanel contentPanel = new JPanel();
    private JPanel panel;
    private JSeparator separator;
    private JTextField Search_textF;
    /////////////////////////////////////
    String[] colNames = {"Name", "E-mail"};
    Object[][] data ;
    DefaultTableModel dtm;

    JTable table;
    JTable table_1;


    public GUIContactsDisplay(String user) {
        table_1 = new JTable();
        table = new JTable();
        dtm = new DefaultTableModel(data,colNames);
        table_1.setModel(dtm);
        ////////////////////////////////////////////
        data= folder.file_to_2Darray("the parent//"+user+"//contacts.txt");

        Object []col1=folder.get_col(data, 0);
        Object []col2=folder.get_col(data, 1);

        String[] stringArray1 = new String[data.length];
        for(int p =0 ; p<col1.length;p++){
            stringArray1[p]=Arrays.toString((String[])col1[p]).replace("[","" ).replace("]", " ");

        }
        String[] stringArray2 = new String[data.length];
        for(int p =0 ; p<col2.length;p++){
            stringArray2[p]=Arrays.toString((String[])col2[p]).replace("[","" ).replace("]", "");

        }
        for(int i=0;i<data.length;i++) {
            String temp1 = stringArray1[i];
            String temp2 = stringArray2[i];
            dtm.addRow(new Object[]{temp1, temp2});

        }

        table_1.setBackground(Color.decode("#222831"));
        table_1.setForeground(Color.decode("#eeeeee"));
        table_1.setShowGrid(false);
        table_1.getTableHeader().setReorderingAllowed(false);
        table_1.setRowSelectionAllowed(true);
        table_1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table_1.setIntercellSpacing(new Dimension(0, 0));
        table_1.setSelectionBackground(Color.decode("#c3edea"));
        setVisible(true);
        ////////////////////////////////////////////
        setBounds(100, 100, 500, 500);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        panel = new JPanel();
        panel.setBackground(Color.decode("#222831"));
        panel.add(table_1);
        //panel.setBackground(Color.WHITE);
        GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
        gl_contentPanel.setHorizontalGroup(
                gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPanel.createSequentialGroup()
                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                                .addGap(0))
        );
        gl_contentPanel.setVerticalGroup(
                gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPanel.createSequentialGroup()
                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                                .addGap(0))
        );

        JLabel Contacts = new JLabel("Contacts");
        Contacts.setForeground(Color.WHITE);
        Contacts.setFont(new Font("Tahoma", Font.BOLD, 20));

        separator = new JSeparator();
        separator.setForeground(Color.WHITE);

        JButton Add = new JButton("+ Add");
        Add.addActionListener(e -> {
            dispose();
           GUIAddContact add = new GUIAddContact(user);
            GUIAddContact.AddContactScreen(user);
        });
        Add.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Add.setForeground(Color.black);
        Add.setBackground(Color.WHITE);

        Search_textF = new JTextField();
        Search_textF.setColumns(10);

        JButton SearchB = new JButton("Search");
        SearchB.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String searchFor = Search_textF.getText();
        		int cols = 2;
        		int rows = col1.length;
        		for(int i = 0 ;i<cols;i++) {
        			for(int j = 0 ; j<rows ; j++) {
        				String test = (String) table_1.getValueAt(i, j);
        				if(test.contains(searchFor)) {
        					table_1.addRowSelectionInterval(i, j);
        				}
        			}
        		}
        
        SearchB.setFont(new Font("Tahoma", Font.PLAIN, 14));
        SearchB.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane();

        JButton btnNewButton = new JButton("Remove Selected Contact");
        btnNewButton.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) table_1.getModel();
            try {
                int SelectedRowIndex =  table_1.getSelectedRow();
                model.removeRow(SelectedRowIndex);


            }catch(Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }


        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(189)
                                                .addComponent(Contacts, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                                .addGap(81)
                                                .addComponent(Add, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(42)
                                                .addComponent(separator, GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(42)
                                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                                                        .addGroup(gl_panel.createSequentialGroup()
                                                                .addComponent(Search_textF, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(SearchB, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                                                                .addGap(2))
                                                        .addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(35))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(11)
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addComponent(Contacts, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(6)
                                                .addComponent(Add, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)))
                                .addGap(6)
                                .addComponent(separator, GroupLayout.DEFAULT_SIZE, 5, Short.MAX_VALUE)
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(9)
                                                .addComponent(Search_textF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(6)
                                                .addComponent(SearchB, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                .addGap(16))
        );


        scrollPane.setViewportView(table_1);


        panel.setLayout(gl_panel);
        contentPanel.setLayout(gl_contentPanel);
    }
}