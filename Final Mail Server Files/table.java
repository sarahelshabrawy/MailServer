package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class table extends JTable
{
    //final Color blue = Color.decode(" ")
    JFrame invisible = new JFrame();
    Mail[] mails;
    Object[] colNames = {"", "", "",""};
    Object[][] data = {};
    DefaultTableModel dtm;
    public static int numberOfMails=0;
    int page;
public table(int page){
    this.page = page;
    buildGUI(page);
}

    public void buildGUI(int page)
    {
        this.page = page;
        dtm = new DefaultTableModel(data,colNames);

       // clearTable();
        if(mails!=null)
            Arrays.fill(mails,null);
        setModel(dtm);
        App myApp = new App();
        mails = (Mail[]) myApp.listEmails(page);
        if(mails!=null) {
            for (Mail mail : mails) {
                if(mail==null)
                    break;
                dtm.addRow(new Object[]{Boolean.FALSE, "From: " + mail.from, "Subject: " + mail.Subject, mail.date});
                numberOfMails++;
            }
        }


        setBackground(Color.decode("#222831"));
        setForeground(Color.decode("#eeeeee"));
        TableColumn tc = getColumnModel().getColumn(0);
        tc.setCellEditor(getDefaultEditor(Boolean.class));
        tc.setCellRenderer(getDefaultRenderer(Boolean.class));
        // tc.setHeaderRenderer(new CheckBoxHeader(new MyItemListener()));
        tc.setMaxWidth(1);
        setShowGrid(false);
        getTableHeader().setReorderingAllowed(false);
        setRowSelectionAllowed(true);
        setColumnSelectionAllowed(false);
        setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tc.setCellEditor(getDefaultEditor(Boolean.class));
        tc.getCellEditor().addCellEditorListener(new CellEditorListenerImpl());
        setIntercellSpacing(new Dimension(0, 0));
        setSelectionBackground(Color.decode("#c3edea"));
        setVisible(true);

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = rowAtPoint(evt.getPoint());
                int col = columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    if(getSelectedRow()>=0) {
                        try {
                            JFrame frame = new JFrame();
                            GUIMailPanel guiMailPanel = new GUIMailPanel(mails[getSelectedRow()]);
                            frame.add(guiMailPanel.panel);
                            frame.setSize(700,700);
                            frame.setTitle("Test Mail_panel");
                            frame.setLocationRelativeTo(null);
                            frame.setVisible(true);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }}
        );

    }

    public int getNumberOfMails() {
        return numberOfMails;
    }
    public int getPage() {
        return page;
    }

    public class CellEditorListenerImpl implements CellEditorListener {

        public void editingStopped(ChangeEvent e) {
            for(int i=0; i<getRowCount();i++){
                if((Boolean)getValueAt(i, 0)){
                    addRowSelectionInterval(i, i);
                }
                else{
                    removeRowSelectionInterval(i, i);
                }
            }
        }

        public void editingCanceled(ChangeEvent e) {
        }

    }

    class MyItemListener implements ItemListener
    {
        public void itemStateChanged(ItemEvent e) {
            Object source = e.getSource();
            if (!(source instanceof AbstractButton)) return;//change checkbox?
            boolean checked = e.getStateChange() == ItemEvent.SELECTED;
            //when a checkbox is
            for(int x = 0, y = getRowCount(); x < y; x++) {
                setValueAt(checked, x, 0);
                if (checked) {
                    addRowSelectionInterval(x, x);
                } else {
                    removeRowSelectionInterval(x, x);
                }
            }
        }
    }
    //Disables editing of cells by user
    public boolean isCellEditable(int row, int column) {
        return column==0;
    }

    //Disables selection of SINGLE cells ( we want only the row as a whole "mail" )

   /* @Override public void changeSelection(
        int rowIndex, int columnIndex, boolean toggle, boolean extend) {
        //the body is empty so it does nothing upon cell selection
        //NA2ESHA ENNI A CALL ELFUNCTION ELLI BET VIEW ELMAIL
            try {
                GUIMailPanel guiMailPanel = new GUIMailPanel(mails[rowIndex]);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
*/
  /*  public static void main (String[] args)
    {
        SwingUtilities.invokeLater(() -> new table(0));
    }*/
}
/*
class CheckBoxHeader extends JCheckBox
        implements TableCellRenderer, MouseListener {
    protected CheckBoxHeader rendererComponent;
    protected int column;
    protected boolean mousePressed = false;
    public CheckBoxHeader(ItemListener itemListener) {
        rendererComponent = this;
        rendererComponent.addItemListener(itemListener);
    }
    public Component getTableCellRendererComponent(
            JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (table != null) {
            JTableHeader header = table.getTableHeader();
            if (header != null) {
                rendererComponent.setForeground(header.getForeground());
                rendererComponent.setBackground(header.getBackground());
                rendererComponent.setFont(header.getFont());
                header.addMouseListener(rendererComponent);
            }
        }
        setColumn(column);
        rendererComponent.setText("Check All");
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        return rendererComponent;
    }
    protected void setColumn(int column) {
        this.column = column;
    }
    public int getColumn() {
        return column;
    }
    protected void handleClickEvent(MouseEvent e) {
        if (mousePressed) {
            mousePressed=false;
            JTableHeader header = (JTableHeader)(e.getSource());
            JTable tableView = header.getTable();
            TableColumnModel columnModel = tableView.getColumnModel();
            int viewColumn = columnModel.getColumnIndexAtX(e.getX());
            int column = tableView.convertColumnIndexToModel(viewColumn);

            if (viewColumn == this.column && e.getClickCount() == 1 && column != -1) {
                doClick();
            }
        }
    }
    public void mouseClicked(MouseEvent e) {
        handleClickEvent(e);
        ((JTableHeader)e.getSource()).repaint();
    }
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
}*/