package eg.edu.alexu.csd.datastructure.mailServer;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Date;

import java.util.Scanner;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIMailPanel extends JPanel{
    Mail mail;
    static class MyHighlighterPainter extends DefaultHighlighter.DefaultHighlightPainter {

        /**
         * Constructs a new highlight painter. If <code>c</code> is null,
         * the JTextComponent will be queried for its selection color.
         *
         * @param c the color for the highlight
         */
        public MyHighlighterPainter(Color c) {
            super(c);
        }

    }
    Highlighter.HighlightPainter myHighlightPainter = new MyHighlighterPainter(Color.YELLOW);
    public void highlight (JTextArea textArea,Object[] limits){
        Highlighter highlighter = textArea.getHighlighter();
        Document doc = textArea.getDocument();
        int mailSubjectLength = mail.Subject.length();
        try {
            String text = doc.getText(0,doc.getLength());
            for (Object o : limits) {
                Point limit = (Point) o;
                highlighter.addHighlight(limit.x-mailSubjectLength, limit.y-mailSubjectLength, myHighlightPainter);
            }

        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
    //	DateFormat df = new SimpleDateFormat("yyyy/MM/dd:HH:mm:ss");

    private JTextArea subject;
    private JTextArea from;
    private JTextArea date;
    private JTextArea to;
    JPanel panel ;
    private JScrollPane scrollPane;
    private JLabel attachments;
    private JScrollPane scrollPanelist;
    private JTextArea textArea;
    private JList list;
    /**
     * Create the panel.
     * @throws FileNotFoundException
     */

    public GUIMailPanel(Mail mail) throws FileNotFoundException {
        this.mail = mail;
        String []paths ;
        panel = new JPanel();
        FileReader readTextFile = new FileReader(mail.directory+"//body.txt");         //<<<<<<<<<<<<<<<<
        Scanner fileReaderScan = new Scanner(readTextFile);
        StringBuilder storeAllString = new StringBuilder();
        while (fileReaderScan.hasNextLine()) {
            String temp = fileReaderScan.nextLine() + "\n";

            storeAllString.append(temp);
        }
        panel.setBackground(Color.decode("#222831"));
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
        );

        JLabel Subject = new JLabel("Subject");
        Subject.setForeground(Color.WHITE);
        Subject.setFont(new Font("Tahoma", Font.PLAIN, 14));

        subject = new JTextArea(mail.Subject);
        subject.setColumns(10);
        subject.setEditable(false);
        subject.setFont(new Font("Tahoma", Font.PLAIN, 14));


        JLabel From = new JLabel("From");
        From.setForeground(Color.WHITE);
        From.setFont(new Font("Tahoma", Font.PLAIN, 14));

        from = new JTextArea(mail.from);
        from.setColumns(10);
        from.setEditable(false);
        from.setFont(new Font("Tahoma", Font.PLAIN, 14));


        JLabel Date = new JLabel("Date");
        Date.setForeground(Color.WHITE);
        Date.setFont(new Font("Tahoma", Font.PLAIN, 14));

        date = new JTextArea(mail.date.toString());
        date.setColumns(10);
        date.setEditable(false);
        date.setFont(new Font("Tahoma", Font.PLAIN, 14));


        JLabel To = new JLabel("To");
        To.setForeground(Color.WHITE);
        To.setFont(new Font("Tahoma", Font.PLAIN, 14));

        to = new JTextArea(mail.To);
        to.setColumns(10);
        to.setEditable(false);
        to.setFont(new Font("Tahoma", Font.PLAIN, 14));


        JSeparator separator = new JSeparator();

        scrollPane = new JScrollPane();

        attachments = new JLabel("Attachments");
        attachments.setForeground(Color.WHITE);
        attachments.setFont(new Font("Tahoma", Font.PLAIN, 17));

        scrollPanelist = new JScrollPane();

        ///////////////////////////////////////////////////////////////////////////////////////////



        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(31)
                                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(Subject)
                                                        .addComponent(From)
                                                        .addComponent(Date))
                                                .addGap(18)
                                                .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
                                                        .addComponent(date, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                                        .addComponent(from, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                                        .addComponent(subject, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                                                .addGap(27)
                                                .addComponent(To)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(to, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(29)
                                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(scrollPane)
                                                        .addComponent(separator, GroupLayout.PREFERRED_SIZE, 447, Short.MAX_VALUE)))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(30)
                                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(scrollPanelist, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                                                        .addGroup(gl_panel.createSequentialGroup()
                                                                .addComponent(attachments, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                                                .addGap(348)))))
                                .addGap(24))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(32)
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(Subject)
                                        .addComponent(subject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(From)
                                        .addComponent(from, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(To)
                                        .addComponent(to, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(Date)
                                        .addComponent(date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(3)
                                .addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(attachments, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(scrollPanelist, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                .addContainerGap())
        );
        Operations_Folder op = new Operations_Folder();
        paths = op.ArrayOfFiles(mail.directory+"//attachments");
        list = new JList(paths);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==2) {
                    int index = list.getSelectedIndex();
                    //context.insertSelectedChoice();
                    //System.out.println("Selected value"+index);
                    String path = mail.directory+"//attachments//"+paths[index];
                    File file = new File(path);
                    try
                    {
                        if(!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not
                        {
                            System.out.println("not supported");
                            return;
                        }
                        Desktop desktop = Desktop.getDesktop();
                        if(file.exists())         //checks file exists or not
                            desktop.open(file);              //opens the specified file
                    }
                    catch(Exception ae)
                    {
                        ae.printStackTrace();
                    }

                }
            }
        });
        scrollPanelist.setViewportView(list);


        textArea = new JTextArea(storeAllString.toString());
        textArea.setWrapStyleWord(true);
        //textArea = new JTextArea(mail.Body);
        textArea.setEditable(false);
        textArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textArea.setLineWrap(true);
        //textArea.setWrapStyleWord(true);
        scrollPane.setViewportView(textArea);
        if(mail.highlights!=null && mail.highlights.length!=0){
            highlight(textArea,mail.highlights);
        }
        scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(19, 136, 257, 133);
        scrollPane.getViewport().setBackground(Color.WHITE);

        scrollPanelist.setViewportView(list);
        scrollPanelist.setVerticalScrollBarPolicy(scrollPanelist.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanelist.setBounds(19, 136, 257, 133);
        scrollPanelist.getViewport().setBackground(Color.WHITE);


        panel.setLayout(gl_panel);
        panel.add(scrollPane);
        panel.add(scrollPanelist);
        setLayout(groupLayout);
    }

}