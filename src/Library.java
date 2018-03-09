
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shatha Suliman
 */
public class Library extends javax.swing.JFrame {

    /**
     * Creates new form Library
     */
    public Library() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Icons/Logo.jpg")).getImage());
        ShowDocuments();
        JTableHeader header = DocsList.getTableHeader();
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.RIGHT);
        for (int i=0 ; i<=5 ; i++) 
        {
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
            DocsList.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }
        Sort();
        ComboBox_type.setVisible(false);
        Label_extra.setVisible(false);
        TextField_extra.setVisible(false);
        Label_extra1.setVisible(false);
        TextField_extra1.setVisible(false);
    }
     
    public Connection DBConnection()
    {
        Connection con = null;
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            String host = "jdbc:mysql://localhost:3306/mg-marjieidb";
            String unicode= "?useUnicode=yes&characterEncoding=UTF-8";
            con = (Connection) DriverManager.getConnection( host+unicode, "root", "" );
            return con;
        } catch (Exception ex)
        {
            System.out.print(ex.getMessage());
        }
        return con;
    }
    
    public ArrayList<ReferenceDocument> getDocumentList()
    {
        ArrayList<ReferenceDocument> documentList = new ArrayList<ReferenceDocument>();
        Connection connection = DBConnection();
        
        String query = "SELECT * FROM referencedocument";
        Statement stmt;
        ResultSet rs;
        
        try 
        {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            ReferenceDocument rf;
            while (rs.next())
            {
                int id = rs.getInt("documentID");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                int publishYear = rs.getInt("publishYear");
                 String dateAdded = rs.getString("DateAdded");
                rf = new ReferenceDocument (id, title, author, publisher, publishYear, dateAdded);
                documentList.add(rf);
            }
            
        } catch (Exception ex)
        {
            System.out.print(ex.getMessage());
        }
        return documentList;
    } 
    
    public void ShowDocuments()
    {
        ArrayList<ReferenceDocument> List = getDocumentList();
        DefaultTableModel model = (DefaultTableModel) DocsList.getModel();
        Object[] row = new Object[6];
        for (int i=0 ; i<List.size() ; i++)
        {
            row[0] = List.get(i).getDateAdded(); 
            row[1] = List.get(i).getPublishYear(); 
            row[2] = List.get(i).getPublisher();
            row[3] = List.get(i).getAuthor();
            row[4] = List.get(i).getTitle();
            row[5] = List.get(i).getID();
            model.addRow(row);
        }
    }
    
    private void Sort()
      {
          DefaultTableModel model = (DefaultTableModel) DocsList.getModel();
          TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
          DocsList.setRowSorter(sorter);
      }
    
     private void Search(String key)
      {
          DefaultTableModel model = (DefaultTableModel) DocsList.getModel();
          TableRowSorter<DefaultTableModel> filter = new TableRowSorter<DefaultTableModel>(model);
          DocsList.setRowSorter(filter);
          filter.setRowFilter(RowFilter.regexFilter(key));
      }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel = new javax.swing.JPanel();
        Button_add = new javax.swing.JButton();
        Button_manually = new javax.swing.JButton();
        Button_delete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        DocsList = new javax.swing.JTable();
        TextField_search = new javax.swing.JTextField();
        Label_searchIcon = new javax.swing.JLabel();
        Panel_edit = new javax.swing.JPanel();
        Label_title = new javax.swing.JLabel();
        Label_author = new javax.swing.JLabel();
        Label_publisher = new javax.swing.JLabel();
        Label_year = new javax.swing.JLabel();
        Label_pages = new javax.swing.JLabel();
        Label_extra = new javax.swing.JLabel();
        Label_extra1 = new javax.swing.JLabel();
        ComboBox_type = new javax.swing.JComboBox<>();
        TextField_title = new javax.swing.JTextField();
        TextField_author = new javax.swing.JTextField();
        TextField_year = new javax.swing.JTextField();
        TextField_publisher = new javax.swing.JTextField();
        TextField_extra = new javax.swing.JTextField();
        TextField_pages = new javax.swing.JTextField();
        TextField_extra1 = new javax.swing.JTextField();
        Button_edit = new javax.swing.JButton();
        MenuBar = new javax.swing.JMenuBar();
        Menu_file = new javax.swing.JMenu();
        MenuItem_add = new javax.swing.JMenuItem();
        MenuItem_manually = new javax.swing.JMenuItem();
        MenuItem_delete = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        MenuItem_folder = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        MenuItem_exit = new javax.swing.JMenuItem();
        Menu_help = new javax.swing.JMenu();
        MenuItem_help = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("مكتبة مرجعي");

        Panel.setBackground(new java.awt.Color(245, 245, 245));

        Button_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/addIcon.png"))); // NOI18N
        Button_add.setText("إضافة مرجع");

        Button_manually.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/manuallyIcon.png"))); // NOI18N
        Button_manually.setText("إدخال يدوي");
        Button_manually.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_manuallyActionPerformed(evt);
            }
        });

        Button_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/deleteIcon.png"))); // NOI18N
        Button_delete.setText("حذف مرجع");

        DocsList.setBackground(new java.awt.Color(249, 249, 249));
        DocsList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "تاريخ الإضافة", "سنة النشر", "الناشر", "المؤلف", "العنوان", "id", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        DocsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DocsListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(DocsList);
        if (DocsList.getColumnModel().getColumnCount() > 0) {
            DocsList.getColumnModel().getColumn(5).setMinWidth(0);
            DocsList.getColumnModel().getColumn(5).setMaxWidth(0);
            DocsList.getColumnModel().getColumn(6).setResizable(false);
        }

        TextField_search.setColumns(20);
        TextField_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_searchKeyReleased(evt);
            }
        });

        Label_searchIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/search.png"))); // NOI18N

        Panel_edit.setBackground(new java.awt.Color(249, 249, 249));

        Label_title.setText("العنوان");

        Label_author.setText("المؤلف");

        Label_publisher.setText("الناشر");

        Label_year.setText("سنة النشر");

        Label_pages.setText("الصفحات");

        Label_extra.setText("jLabel1");

        Label_extra1.setText("jLabel1");

        ComboBox_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "كتاب", "مقال صحفي", "مقال مجلة", "صفحة ويب", "ورقة مؤتمر", "أخرى" }));

        TextField_title.setColumns(15);

        TextField_author.setColumns(15);

        TextField_year.setColumns(15);

        TextField_publisher.setColumns(15);

        TextField_extra.setColumns(15);

        TextField_pages.setColumns(15);

        TextField_extra1.setColumns(15);

        Button_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/editIcon.png"))); // NOI18N
        Button_edit.setText("تعديل مرجع");

        javax.swing.GroupLayout Panel_editLayout = new javax.swing.GroupLayout(Panel_edit);
        Panel_edit.setLayout(Panel_editLayout);
        Panel_editLayout.setHorizontalGroup(
            Panel_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_editLayout.createSequentialGroup()
                .addGroup(Panel_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_editLayout.createSequentialGroup()
                        .addContainerGap(42, Short.MAX_VALUE)
                        .addGroup(Panel_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextField_title, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextField_author, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextField_year, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextField_publisher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextField_extra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextField_pages, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextField_extra1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(Panel_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Label_extra1)
                            .addComponent(Label_extra)
                            .addComponent(Label_pages)
                            .addComponent(Label_author)
                            .addComponent(Label_publisher)
                            .addComponent(Label_year)
                            .addComponent(Label_title)))
                    .addGroup(Panel_editLayout.createSequentialGroup()
                        .addGroup(Panel_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel_editLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(ComboBox_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Panel_editLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(Button_edit)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        Panel_editLayout.setVerticalGroup(
            Panel_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_editLayout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addComponent(ComboBox_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(Panel_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_title)
                    .addComponent(TextField_title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_author)
                    .addComponent(TextField_author, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_publisher)
                    .addComponent(TextField_publisher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_year)
                    .addComponent(TextField_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_pages)
                    .addComponent(TextField_pages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_extra)
                    .addComponent(TextField_extra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_extra1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_extra1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addComponent(Button_edit)
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel_edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelLayout.createSequentialGroup()
                        .addComponent(TextField_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Label_searchIcon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Button_delete)
                        .addGap(18, 18, 18)
                        .addComponent(Button_manually)
                        .addGap(18, 18, 18)
                        .addComponent(Button_add)))
                .addContainerGap())
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Panel_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelLayout.createSequentialGroup()
                        .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Button_manually)
                                    .addComponent(Button_delete))
                                .addComponent(Button_add))
                            .addComponent(TextField_search, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Label_searchIcon, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))))
        );

        Menu_file.setText("ملف");

        MenuItem_add.setText("إضافة مرجع");
        Menu_file.add(MenuItem_add);

        MenuItem_manually.setText("إدخال يدوي");
        Menu_file.add(MenuItem_manually);

        MenuItem_delete.setText("حذف مرجع");
        Menu_file.add(MenuItem_delete);
        Menu_file.add(jSeparator1);

        MenuItem_folder.setText("إنشاء مجلد");
        Menu_file.add(MenuItem_folder);
        Menu_file.add(jSeparator2);

        MenuItem_exit.setText("خروج");
        Menu_file.add(MenuItem_exit);

        MenuBar.add(Menu_file);

        Menu_help.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/helpIcon.png"))); // NOI18N

        MenuItem_help.setText("مساعدة");
        Menu_help.add(MenuItem_help);

        MenuBar.add(Menu_help);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TextField_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_searchKeyReleased
        // TODO add your handling code here:
        String key = TextField_search.getText();
        Search(key);
    }//GEN-LAST:event_TextField_searchKeyReleased

    private void Button_manuallyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_manuallyActionPerformed
        // TODO add your handling code here:
        addManuallyform frame = new addManuallyform();
        frame.setVisible(true);
    }//GEN-LAST:event_Button_manuallyActionPerformed

    private void DocsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DocsListMouseClicked
        // TODO add your handling code here:
        ComboBox_type.setVisible(true);
        int i = DocsList.getSelectedRow();
      TableModel model = DocsList.getModel();  
        
       try 
        {
            Connection con = DBConnection();
            Statement stmt = con.createStatement();
            int ID = (int) model.getValueAt(i, 5);
            ResultSet rs = stmt.executeQuery("SELECT documentType FROM referencedocument WHERE documentID = "+ID);
            rs.next();
            String type = rs.getString("documentType");
         
            switch (type) {
                case "book":
                    ComboBox_type.setSelectedIndex(0);
                    TextField_year.setText(model.getValueAt(i,1).toString());
                    TextField_publisher.setText(model.getValueAt(i,2).toString());
                    TextField_author.setText(model.getValueAt(i,3).toString());
                    TextField_title.setText(model.getValueAt(i,4).toString());
                    int bookID = (int) model.getValueAt(i, 5);
                    rs = stmt.executeQuery("SELECT pages FROM referencedocument WHERE documentID = "+bookID);
                    rs.next();
                    TextField_pages.setText(rs.getInt("pages")+"");
       
                    Label_extra.setVisible(true);
                    TextField_extra.setVisible(true);
                    Label_extra.setText("الطبعة");
                    Label_extra1.setVisible(false);
                    TextField_extra1.setVisible(false);  
                    rs = stmt.executeQuery("SELECT * FROM book WHERE documentID = "+bookID);
                    rs.next();
                    TextField_extra.setText(rs.getInt("edition")+"");
                    break;
                    
                case "journalarticle":
                    ComboBox_type.setSelectedIndex(1);
                    TextField_year.setText(model.getValueAt(i,1).toString());
                    TextField_publisher.setText(model.getValueAt(i,2).toString());
                    TextField_author.setText(model.getValueAt(i,3).toString());
                    TextField_title.setText(model.getValueAt(i,4).toString());
                    int jarticleID = (int) model.getValueAt(i, 5);
                    rs = stmt.executeQuery("SELECT pages FROM referencedocument WHERE documentID = "+jarticleID);
                    rs.next();
                    TextField_pages.setText(rs.getInt("pages")+"");
       
                    Label_extra.setVisible(true);
                    TextField_extra.setVisible(true);
                    Label_extra1.setVisible(true);
                    TextField_extra1.setVisible(true);
                    Label_extra.setText("الصحيفة");
                    Label_extra1.setText("الحجم");
                    rs = stmt.executeQuery("SELECT * FROM journalarticle WHERE documentID = "+jarticleID);
                    rs.next();
                    TextField_extra.setText(rs.getString("journalName"));
                    TextField_extra1.setText(rs.getString("volume")); 
                    break;
                    
                case "magazinearticle":
                    ComboBox_type.setSelectedIndex(2);
                    TextField_year.setText(model.getValueAt(i,1).toString());
                    TextField_publisher.setText(model.getValueAt(i,2).toString());
                    TextField_author.setText(model.getValueAt(i,3).toString());
                    TextField_title.setText(model.getValueAt(i,4).toString());
                    int marticleID = (int) model.getValueAt(i, 5);
                    rs = stmt.executeQuery("SELECT pages FROM referencedocument WHERE documentID = "+marticleID);
                    rs.next();
                    TextField_pages.setText(rs.getInt("pages")+"");
       
                    Label_extra.setVisible(true);
                    TextField_extra.setVisible(true);
                    Label_extra1.setVisible(true);
                    TextField_extra1.setVisible(true);
                    Label_extra.setText("المجلة");
                    Label_extra1.setText("الشهر");
                    rs = stmt.executeQuery("SELECT * FROM magazinearticle WHERE documentID = "+marticleID);
                    rs.next();
                    TextField_extra.setText(rs.getString("magazineName"));
                    TextField_extra1.setText(rs.getString("month")); 
                    break;
                    
                case "webpage":
                    ComboBox_type.setSelectedIndex(3);
                    TextField_year.setText(model.getValueAt(i,1).toString());
                    TextField_publisher.setText(model.getValueAt(i,2).toString());
                    TextField_author.setText(model.getValueAt(i,3).toString());
                    TextField_title.setText(model.getValueAt(i,4).toString());
                    int webID = (int) model.getValueAt(i, 5);
                    rs = stmt.executeQuery("SELECT pages FROM referencedocument WHERE documentID = "+webID);
                    rs.next();
                    TextField_pages.setText(rs.getInt("pages")+"");
       
                    Label_extra.setVisible(true);
                    TextField_extra.setVisible(true);
                    Label_extra1.setVisible(true);
                    TextField_extra1.setVisible(true);
                    Label_extra.setText("الرابط");
                    Label_extra1.setText("تاريخ الوصول");
                    rs = stmt.executeQuery("SELECT * FROM webpage WHERE documentID = "+webID);
                    rs.next();
                    TextField_extra.setText(rs.getString("url"));
                    TextField_extra1.setText(rs.getString("AccessDate")); 
                    break;
                    
                    case "conferenceproceeding":
                    ComboBox_type.setSelectedIndex(4);
                    TextField_year.setText(model.getValueAt(i,1).toString());
                    TextField_publisher.setText(model.getValueAt(i,2).toString());
                    TextField_author.setText(model.getValueAt(i,3).toString());
                    TextField_title.setText(model.getValueAt(i,4).toString());
                    int cpID = (int) model.getValueAt(i, 5);
                    rs = stmt.executeQuery("SELECT pages FROM referencedocument WHERE documentID = "+cpID);
                    rs.next();
                    TextField_pages.setText(rs.getInt("pages")+"");
       
                    Label_extra.setVisible(true);
                    TextField_extra.setVisible(true);
                    Label_extra1.setVisible(true);
                    TextField_extra1.setVisible(true);
                    Label_extra.setText("المؤتمر");
                    Label_extra1.setText("المكان");
                    rs = stmt.executeQuery("SELECT * FROM conferenceproceeding WHERE documentID = "+cpID);
                    rs.next();
                    TextField_extra.setText(rs.getString("conferenceName"));
                    TextField_extra1.setText(rs.getString("place")); 
                    break;
                    
                case "other":
                    ComboBox_type.setSelectedIndex(5);
                    Label_extra.setVisible(false);
                    TextField_extra.setVisible(false);
                    Label_extra1.setVisible(false);
                    TextField_extra1.setVisible(false);
                    TextField_year.setText(model.getValueAt(i,1).toString());
                    TextField_publisher.setText(model.getValueAt(i,2).toString());
                    TextField_author.setText(model.getValueAt(i,3).toString());
                    TextField_title.setText(model.getValueAt(i,4).toString());
                    int otherID = (int) model.getValueAt(i, 5);
                    rs = stmt.executeQuery("SELECT pages FROM referencedocument WHERE documentID = "+otherID);
                    rs.next();
                    TextField_pages.setText(rs.getInt("pages")+"");
                    break;
            }
                
        
        } catch (Exception ex)
        {
            System.out.print(ex.getMessage());
        }
    }//GEN-LAST:event_DocsListMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Library.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Library.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Library.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Library.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Library().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_add;
    private javax.swing.JButton Button_delete;
    private javax.swing.JButton Button_edit;
    private javax.swing.JButton Button_manually;
    private javax.swing.JComboBox<String> ComboBox_type;
    private javax.swing.JTable DocsList;
    private javax.swing.JLabel Label_author;
    private javax.swing.JLabel Label_extra;
    private javax.swing.JLabel Label_extra1;
    private javax.swing.JLabel Label_pages;
    private javax.swing.JLabel Label_publisher;
    private javax.swing.JLabel Label_searchIcon;
    private javax.swing.JLabel Label_title;
    private javax.swing.JLabel Label_year;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenuItem MenuItem_add;
    private javax.swing.JMenuItem MenuItem_delete;
    private javax.swing.JMenuItem MenuItem_exit;
    private javax.swing.JMenuItem MenuItem_folder;
    private javax.swing.JMenuItem MenuItem_help;
    private javax.swing.JMenuItem MenuItem_manually;
    private javax.swing.JMenu Menu_file;
    private javax.swing.JMenu Menu_help;
    private javax.swing.JPanel Panel;
    private javax.swing.JPanel Panel_edit;
    private javax.swing.JTextField TextField_author;
    private javax.swing.JTextField TextField_extra;
    private javax.swing.JTextField TextField_extra1;
    private javax.swing.JTextField TextField_pages;
    private javax.swing.JTextField TextField_publisher;
    private javax.swing.JTextField TextField_search;
    private javax.swing.JTextField TextField_title;
    private javax.swing.JTextField TextField_year;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
