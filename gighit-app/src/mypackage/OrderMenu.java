package mypackage;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class OrderMenu implements ActionListener, ChangeListener {
    private Connection connection;
    JButton btnHome;
    JButton btnPizza;
    JButton btnDrink;
    JButton btnStarters;
    JButton btnTransaksi;
    JLabel background;
    JPanel panelUtama;

    JLabel labelHome;
    JButton btnOrder;
    JPanel panelOrder;
    JPanel panelScrollPaneOrder;
    JPanel panelIsiSaldo;
    JLabel isiSaldoPelanggan;
    DefaultTableModel dtmOrder;
    JTable tableOrder;
    JPanel pnlBayar;
    JTextField[] questsOrder;
    JTextField total;
    JTextField namaPelanggan;
    JTextField tunai;
    JTextField kembali;
    JButton printReceipt;
    JButton sendReceipt;

    JPanel pizzaMenu;
    String[] sizes;
    Size[] sizess;
    JTextField[] answer;
    JTextArea[] question;
    JPanel formAddNew;
    JPanel panelAddNewProduk;
    JButton btnSaveNewProduk;
    JButton btnBackNewProduk;
    String table;
    ArrayList<Pizza> pizzas = new ArrayList<>();
    int p =0;

    JPanel pizzaScrollPanel;
    JScrollPane jspPizza;
    ArrayList<JPanel> pPanel = new ArrayList<>();
    ArrayList<JButton> pDelete = new ArrayList<>();
    ArrayList<JButton> pEdit = new ArrayList<>();
    ArrayList<JLabel> pName = new ArrayList<>();
    ArrayList<JTextArea> pDesc = new ArrayList<>();
    ArrayList<JLabel> pImage = new ArrayList<>();
    ArrayList<JComboBox> pSize = new ArrayList<>();
    ArrayList<JLabel> pPrice = new ArrayList<>();
    ArrayList<JSpinner> pQtyM = new ArrayList<>();
    ArrayList<JSpinner> pQtyL = new ArrayList<>();
    ArrayList<JPanel> pM = new ArrayList<>();
    ArrayList<JPanel> pL = new ArrayList<>();
    JButton btnAddNewPizza;
    ArrayList<Drink> drinks = new ArrayList<>();
    int d = 0;
    JPanel drinksMenu;
    JPanel drinkScrollPanel;
    JScrollPane jspDrink;
    ArrayList<JPanel> dPanel = new ArrayList<>();
    ArrayList<JButton> dDelete = new ArrayList<>();
    ArrayList<JButton> dEdit = new ArrayList<>();
    ArrayList<JLabel> dName = new ArrayList<>();
    ArrayList<JTextArea> dDesc = new ArrayList<>();
    ArrayList<JLabel> dImage = new ArrayList<>();
    ArrayList<JComboBox> dSize = new ArrayList<>();
    ArrayList<JLabel> dPrice = new ArrayList<>();
    ArrayList<JSpinner> dQtyM = new ArrayList<>();
    ArrayList<JSpinner> dQtyL = new ArrayList<>();
    ArrayList<JPanel> dM = new ArrayList<>();
    ArrayList<JPanel> dL = new ArrayList<>();
    JButton btnAddNewDrinks;
    ArrayList<Starters> starters = new ArrayList<>();
    int s = 0;
    JPanel startersMenu;
    JPanel startersScrollPanel;
    JScrollPane jspStarters;
    ArrayList<JPanel> sPanel = new ArrayList<>();
    ArrayList<JButton> sDelete = new ArrayList<>();
    ArrayList<JButton> sEdit = new ArrayList<>();
    ArrayList<JLabel> sName = new ArrayList<>();
    ArrayList<JTextArea> sDesc = new ArrayList<>();
    ArrayList<JLabel> sImage = new ArrayList<>();
    ArrayList<JLabel> sPrice = new ArrayList<>();
    ArrayList<JSpinner> sQty = new ArrayList<>();
    JButton btnAddNewStarters;

    JTable transaksiTable;
    DefaultTableModel dtmTransaksi;
    JScrollPane jspTransaksi;
    JPanel transaksiScrollPane;

    JLabel login;
    JPanel panelUser;
    String username;
    JLabel nama;
    JLabel saldo;
    boolean admin;

    public void login(JFrame frame){
        username="";
        connect();
        setPanelUtama();
        setPanelOrder();
        setProdukMenu();
        background.add(labelHome);
        setFormAddNew();
        addToListProduk("Pizza");
        addToListProduk("Drink");
        addToListProduk("Starters");
        setTransaksiTable();
        tampilkanTransaksi();
        frame.add(panelUtama);
        JLabel gighit = new JLabel("GiGhit");
        gighit.setHorizontalAlignment(JLabel.CENTER);
        gighit.setBounds(510,70,300,100);
        gighit.setFont(new Font("Jokerman", Font.PLAIN, 80));
        gighit.setForeground(Color.WHITE);
        login = new JLabel();
        login.setLayout(null);
        login.setBounds(0,0,1320,670);
        login.setIcon(new ImageIcon("images/bg/bgLogin.jpg"));
        login.add(gighit);
        frame.add(login);

        JPanel panelLogin = new JPanel(null);
        panelLogin.setBounds(485,270,350,155);
        panelLogin.setOpaque(false);
        login.add(panelLogin);
        JPanel panelRegis = new JPanel(null);
        panelRegis.setBounds(395,250,530,320);
        panelRegis.setOpaque(false);
        panelRegis.setVisible(false);
        login.add(panelRegis);

        JTextField[] quests = {new JTextField("Username"),new JTextField("Password")};
        int y=0;
        for (JTextField quest : quests) {
            quest.setBounds(0, y, 100, 30);
            quest.setFont(new Font("Lucida Console", Font.PLAIN, 15));
            quest.setEnabled(false);
            quest.setOpaque(false);
            quest.setBorder(BorderFactory.createEmptyBorder());
            quest.setDisabledTextColor(Color.WHITE);
            panelLogin.add(quest);
            y+=50;
        }
        JTextField user = new JTextField();
        user.setBounds(100,0,250,30);
        user.setBorder(BorderFactory.createEmptyBorder());
        panelLogin.add(user);
        JPasswordField pass = new JPasswordField();
        pass.setBounds(100,50,250,30);
        pass.setBorder(BorderFactory.createEmptyBorder());
        panelLogin.add(pass);
        JButton btnLogin = new JButton("Sign In");
        btnLogin.setBounds(0,100,350,30);
        btnLogin.setFont(new Font("Lucida Console", Font.PLAIN, 13));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(new Color(106,172,67));
        btnLogin.setFocusable(false);
        btnLogin.addActionListener(e ->{
            connect();
            setPanelUtama();
            setPanelOrder();
            setProdukMenu();
            background.add(labelHome);
            setFormAddNew();
            addToListProduk("Pizza");
            addToListProduk("Drink");
            addToListProduk("Starters");
            setTransaksiTable();
            tampilkanTransaksi();
            frame.add(panelUtama);
            if (user.getText().equals("admin_gighit") && pass.getText().equals("gighit")){
                admin = true;
                username = "";
                isiSaldoPelanggan.setText("Isi Saldo Pelanggan");
                tampilkanTransaksi();
                nama.setText("Admin Gighit");
                login.setVisible(false);
                panelAddNewProduk.setVisible(true);
                for (int i=0 ; i<p ;i++){
                    pEdit.get(i).setVisible(true);
                    pDelete.get(i).setVisible(true);
                }
                for (int i=0 ; i<d ;i++){
                    dEdit.get(i).setVisible(true);
                    dDelete.get(i).setVisible(true);
                }
                for (int i=0 ; i<s ;i++){
                    sEdit.get(i).setVisible(true);
                    sDelete.get(i).setVisible(true);
                }
                panelUtama.setVisible(true);
            }
            else {
                try {
                    String sql = "SELECT * FROM user";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);

                    boolean terdaftar=false;
                    while (resultSet.next()){
                        if (resultSet.getString(1).equals(user.getText())
                                && resultSet.getString(2).equals(pass.getText())){
                            terdaftar=true;
                            username = resultSet.getString(1);
                            nama.setText(resultSet.getString(3)+" "+resultSet.getString(4));
                            saldo.setText(resultSet.getString(6));
                        }
                    }
                    if (terdaftar){
                        admin = false;
                        isiSaldoPelanggan.setText("Transfer ke sesama GiGhit-lovers disini");
                        login.setVisible(false);
                        panelUtama.setVisible(true);
                        pnlBayar.setBounds(0,300,600,75);
                        printReceipt.setVisible(false);
                        sendReceipt.setVisible(false);
                        questsOrder[2].setVisible(false);
                        questsOrder[3].setVisible(false);
                        tunai.setVisible(false);
                        kembali.setVisible(false);
                        namaPelanggan.setText(nama.getText());
                        JButton orderNow = new JButton("Order Now");
                        orderNow.setBounds(400,17,140,50);
                        orderNow.setFocusable(false);
                        orderNow.setBackground(new Color(106,172,67));
                        orderNow.addActionListener(eBarcode -> {
                            if (total.getText().equals("0.0") || namaPelanggan.getText().equals("")){
                                JOptionPane.showMessageDialog(null,
                                        "total tidak boleh 0 dan nama pelanggan tidak boleh kosong");
                            }else{
                                if ((Double.parseDouble(saldo.getText()) < Double.parseDouble(total.getText()))){
                                    JOptionPane.showMessageDialog(null,
                                            "Maaf saldo anda kurang nih :(");
                                }else{
                                    try {
                                        String sq = "UPDATE user SET saldo = "+
                                                (Double.parseDouble(saldo.getText())-Double.parseDouble(total.getText()))
                                                +" WHERE username = '"+username+"';";
                                        Statement statement3 = connection.createStatement();
                                        statement3.executeUpdate(sq);
                                        saldo.setText(String.valueOf(Double.parseDouble(saldo.getText())-Double.parseDouble(total.getText())));
                                    } catch (SQLException sqlException) {
                                        System.out.println("Error : " + sqlException.getMessage());
                                    }
                                    setReceipt();
                                }
                            }
                        });
                        orderNow.setForeground(Color.WHITE);
                        pnlBayar.add(orderNow);
                        btnTransaksi.setText("RIWAYAT ORDER");
                        panelAddNewProduk.setVisible(false);
                        for (int i=0 ; i<p ;i++){
                            pEdit.get(i).setVisible(false);
                            pDelete.get(i).setVisible(false);
                        }
                        for (int i=0 ; i<d ;i++){
                            dEdit.get(i).setVisible(false);
                            dDelete.get(i).setVisible(false);
                        }
                        for (int i=0 ; i<s ;i++){
                            sEdit.get(i).setVisible(false);
                            sDelete.get(i).setVisible(false);
                        }
                    }else {
                        JOptionPane.showMessageDialog(null,
                                "akun belum terdaftar, silahkan registrasi terlebih dahulu");
                    }
                } catch (SQLException ex){
                    System.out.println("Error : " + ex.getMessage());
                }
            }
            user.setText("");
            pass.setText("");
        });
        panelLogin.add(btnLogin);

        JButton btnRegistHere = new JButton("sign up here");
        btnRegistHere.setHorizontalAlignment(JLabel.CENTER);
        btnRegistHere.setBounds(125,140,120,15);
        btnRegistHere.setFont(new Font("Lucida Console", Font.PLAIN, 11));
        btnRegistHere.setForeground(Color.WHITE);
        btnRegistHere.setOpaque(false);
        btnRegistHere.setContentAreaFilled(false);
        btnRegistHere.setFocusPainted(false);
        btnRegistHere.setBorder(BorderFactory.createEmptyBorder());
        btnRegistHere.addActionListener(e -> {
            panelLogin.setVisible(false);
            JTextField[] questsRegis = {new JTextField("Nama Depan"),
                    new JTextField("Nama Belakang"),new JTextField("Username"),
                    new JTextField("Password"),new JTextField("Alamat")};
            int yR=0;
            for (JTextField quest : questsRegis) {
                quest.setBounds(0,yR,180,30);
                quest.setFont(new Font("Lucida Console", Font.PLAIN, 15));
                quest.setEnabled(false);
                quest.setOpaque(false);
                quest.setBorder(BorderFactory.createEmptyBorder());
                quest.setDisabledTextColor(Color.WHITE);
                panelRegis.add(quest);
                yR+=40;
            }
            JTextField[] text = new JTextField[questsRegis.length-2];
            int yT=0;
            for (int i=0 ; i<text.length ; i++){
                text[i] = new JTextField();
                text[i].setBounds(180,yT,350,30);
                text[i].setBorder(BorderFactory.createEmptyBorder());
                panelRegis.add(text[i]);
                yT+=40;
            }
            JPasswordField pw = new JPasswordField();
            pw.setBounds(180,yT,350,30);
            pw.setBackground(Color.WHITE);
            pw.setBorder(BorderFactory.createEmptyBorder());
            panelRegis.add(pw);
            JTextArea alamatText = new JTextArea();
            alamatText.setBounds(180,yT+40,350,60);
            alamatText.setBackground(Color.WHITE);
            panelRegis.add(alamatText);
            JButton btnRegis = new JButton("Sign Up");
            btnRegis.setBackground(new Color(106,172,67));
            btnRegis.setForeground(Color.WHITE);
            btnRegis.setBounds(275,250,255,30);
            btnRegis.setFont(new Font("Lucida Console", Font.PLAIN, 13));
            btnRegis.setForeground(Color.WHITE);
            btnRegis.addActionListener(eRegis ->{
                boolean sukses = true;
                try {
                    String sql = "INSERT INTO user (username,password,namaDepan,namaBelakang,alamat)" +
                            " values " + "('"+text[2].getText()+"','"+pw.getText()+"','"+text[0].getText()+
                            "','"+text[1].getText()+ "','"+alamatText.getText()+"');";
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(sql);
                    for (JTextField jTextField : text) {
                        jTextField.setText("");
                    }pw.setText("");alamatText.setText("");
                } catch (SQLException ex){
                    sukses = false;
                    JOptionPane.showMessageDialog(null,"Maaf username sudah terpakai");
                }
                if (sukses)
                    JOptionPane.showMessageDialog(null,"Berhasil");
            });
            panelRegis.add(btnRegis);
            JButton btnCancel = new JButton("Cancel");
            btnCancel.setBackground(new Color(106,172,67));
            btnCancel.setForeground(Color.WHITE);
            btnCancel.setBounds(0,250,255,30);
            btnCancel.setFont(new Font("Lucida Console", Font.PLAIN, 13));
            btnCancel.setForeground(Color.WHITE);
            btnCancel.addActionListener(eCancel -> {
                for (JTextField jTextField : text) {
                    jTextField.setText("");
                }pw.setText("");alamatText.setText("");
                panelRegis.setVisible(false);
                panelLogin.setVisible(true);
            });
            panelRegis.add(btnCancel);

            panelRegis.setVisible(true);
        });
        panelLogin.add(btnRegistHere);
    }

    public void connect() {
        Connection MySQLConfig;
        try{
            String url = "jdbc:mysql://localhost/gighit?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = "root";
            String pass = "";

            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            MySQLConfig = DriverManager.getConnection(url, user, pass);
            connection = MySQLConfig;

        }catch(SQLException e){
            System.out.println("Koneksi ke Database Gagal" +e.getMessage());
        }
    }

    public void setPanelUtama(){
        JLabel sampul = new JLabel();
        sampul.setIcon(new ImageIcon("images/bg/sampul.jpg"));
        sampul.setBounds(0,0,1320,240);

        JLabel gighit = new JLabel("GiGhit");
        gighit.setBounds(20,80,500,100);
        gighit.setFont(new Font("Jokerman", Font.PLAIN, 80));
        gighit.setForeground(Color.WHITE);
        sampul.add(gighit);

        panelUser = new JPanel(null);
        panelUser.setBackground(new Color(0,0,0,130));
        panelUser.setBounds(720,0,600,210);

        JLabel userIcon = new JLabel();
        userIcon.setIcon(new ImageIcon("images/icon/accountIcon.png"));
        userIcon.setBounds(275,20,50,50);
        userIcon.setOpaque(false);
        panelUser.add(userIcon);

        nama = new JLabel();
        nama.setBounds(0,80,600,30);
        nama.setHorizontalAlignment(JLabel.CENTER);
        nama.setForeground(Color.WHITE);
        nama.setFont(new Font("Lucida Console",Font.PLAIN,14));
        panelUser.add(nama);

        saldo = new JLabel();
        saldo.setBounds(0,120,600,30);
        saldo.setForeground(new Color(135,240,225));
        saldo.setHorizontalAlignment(JLabel.CENTER);
        saldo.setFont(new Font("Lucida Console",Font.PLAIN,14));
        panelUser.add(saldo);

        JButton logOut = new JButton("Sign Out");
        logOut.setBounds(225,160,150,30);
        logOut.setForeground(Color.WHITE);
        logOut.setFocusable(false);
        logOut.setBackground(new Color(106,172,67));
        logOut.addActionListener(e -> {
            int ok = JOptionPane.showConfirmDialog(null,"Sign Out?",
                    "Confirmation",JOptionPane.YES_NO_OPTION);
            if (ok == 0){
                for (int i=0 ; i<pizzas.size() ; i++){
                    pQtyM.get(i).setValue(0);
                    pQtyL.get(i).setValue(0);
                    pSize.get(i).setSelectedItem(null);
                    pPrice.get(i).setText("");
                    pQtyM.get(i).setEnabled(false);
                    pL.get(i).setVisible(false);
                    pM.get(i).setVisible(true);
                }
                for (int i=0 ; i<drinks.size() ; i++){
                    dQtyM.get(i).setValue(0);
                    dQtyL.get(i).setValue(0);
                    dSize.get(i).setSelectedItem(null);
                    dPrice.get(i).setText("");
                    dQtyM.get(i).setEnabled(false);
                    dL.get(i).setVisible(false);
                    dM.get(i).setVisible(true);
                }
                for (int i=0 ; i<starters.size() ; i++){
                    sQty.get(i).setValue(0);
                }
                dtmOrder.setRowCount(0);
                panelOrder.setVisible(false);
                panelUser.setVisible(false);
                panelUtama.setVisible(false);
                login.setVisible(true);
                panelUtama.removeAll();
            }
        });
        panelUser.add(logOut);
        panelUser.setVisible(false);
        sampul.add(panelUser);

        JButton[] buttons = new JButton[]{
                btnHome = new JButton("HOME"),
                btnPizza = new JButton("PIZZA"),
                btnDrink = new JButton("DRINKS"),
                btnStarters = new JButton("STARTERS"),
                btnTransaksi = new JButton("MANAGE")
        };

        int x=0;
        for (JButton button : buttons) {
            button.setBounds(x, 210, 144, 30);
            button.setForeground(Color.WHITE);
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setFont(new Font("Lucida Console", Font.PLAIN, 13));
            button.addActionListener(this);
            sampul.add(button);
            x += 144;
        }
        btnOrder = new JButton("ORDER");
        btnOrder.setBounds(720,210,600,30);
        btnOrder.setOpaque(false);
        btnOrder.setContentAreaFilled(false);
        btnOrder.setFocusPainted(false);
        btnOrder.setForeground(Color.WHITE);
        btnOrder.setFont(new Font("Lucida Console",Font.PLAIN,13));
        btnOrder.addActionListener(this);
        sampul.add(btnOrder);

        background = new JLabel();
        background.setLayout(null);
        background.setBounds(0,240,1320,440);
        background.setIcon(new ImageIcon("images/bg/background.jpg"));

        labelHome = new JLabel("'tidak ada perasaan yang lebih baik di seluruh dunia " +
                "selain kotak pizza hangat dipangkuanmu'-KJ");
        labelHome.setForeground(Color.WHITE);
        labelHome.setFont(new Font("Lucida Console",Font.PLAIN,16));
        labelHome.setBounds(0,0,1320,430);
        labelHome.setHorizontalAlignment(JLabel.CENTER);
        labelHome.setVerticalAlignment(JLabel.CENTER);

        panelUtama = new JPanel(null);
        panelUtama.setBounds(0,0,1320,670);
        panelUtama.setBackground(Color.BLACK);
        panelUtama.add(sampul);
        panelUtama.add(background);
        panelUtama.setVisible(false);
    }

    public void setPanelOrder(){
        String[] header = {"Pesanan","Packing","Size","Qty","Harga","Spinner"};
        dtmOrder = new DefaultTableModel(0,0);
        dtmOrder.setColumnIdentifiers(header);

        tableOrder = new JTable(dtmOrder);
        tableOrder.setPreferredScrollableViewportSize(new Dimension(600,300));
        tableOrder.setFillsViewportHeight(true);
        tableOrder.getTableHeader().setReorderingAllowed(false);
        tableOrder.getTableHeader().setResizingAllowed(false);
        tableOrder.getTableHeader().setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        tableOrder.getTableHeader().setOpaque(false);
        tableOrder.getTableHeader().setBackground(Color.BLACK);
        tableOrder.getTableHeader().setForeground(Color.WHITE);
        tableOrder.getTableHeader().setPreferredSize(new Dimension(600,35));
        tableOrder.setShowVerticalLines(false);
        tableOrder.setRowHeight(30);
        tableOrder.setOpaque(false);
        tableOrder.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        tableOrder.setForeground(Color.WHITE);
        tableOrder.setGridColor(Color.WHITE);
        tableOrder.setEnabled(false);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setOpaque(false);
        center.setHorizontalAlignment(JLabel.CENTER);
        tableOrder.getColumnModel().getColumn(0).setCellRenderer(center);
        tableOrder.getColumnModel().getColumn(0).setMaxWidth(170);
        tableOrder.getColumnModel().getColumn(1).setCellRenderer(center);
        tableOrder.getColumnModel().getColumn(1).setMaxWidth(150);
        tableOrder.getColumnModel().getColumn(2).setCellRenderer(center);
        tableOrder.getColumnModel().getColumn(2).setMaxWidth(100);
        tableOrder.getColumnModel().getColumn(3).setCellRenderer(center);
        tableOrder.getColumnModel().getColumn(3).setMaxWidth(70);
        tableOrder.getColumnModel().getColumn(4).setCellRenderer(center);
        tableOrder.getColumnModel().getColumn(4).setMaxWidth(110);
        tableOrder.getColumnModel().getColumn(5).setMinWidth(0); // for hide
        tableOrder.getColumnModel().getColumn(5).setMaxWidth(0); // for hide

        JScrollPane jspOrder = new JScrollPane(tableOrder);
        jspOrder.setOpaque(false);
        jspOrder.getViewport().setBackground(new Color(0,0,0));
        jspOrder.setBorder(BorderFactory.createEmptyBorder());

        panelScrollPaneOrder = new JPanel(new CardLayout());
        panelScrollPaneOrder.setBounds(0,0,600,300);
        panelScrollPaneOrder.setOpaque(false);
        panelScrollPaneOrder.add(jspOrder);

        pnlBayar = new JPanel(null);
        pnlBayar.setBounds(0,300,600,130);
        pnlBayar.setBackground(Color.BLACK);
        questsOrder = new JTextField[]{
                new JTextField("Total"), new JTextField("Nama Pelanggan"),
                new JTextField("Tunai"), new JTextField("Kembali")
        };
        int y=17;
        for (JTextField quest : questsOrder) {
            quest.setBounds(30, y, 110, 25);
            quest.setEnabled(false);
            quest.getCaret().deinstall(quest);
            quest.setDisabledTextColor(Color.WHITE);
            quest.setBorder(BorderFactory.createEmptyBorder());
            quest.setOpaque(false);
            quest.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
            pnlBayar.add(quest);
            y += 25;
        }
        total = new JTextField();
        total.setEnabled(false);
        total.setHorizontalAlignment(JTextField.CENTER);
        total.setBackground(Color.WHITE);
        total.setDisabledTextColor(new Color(232,59,10));
        total.setText("0.0");
        total.setBounds(150,18,120,20);
        pnlBayar.add(total);
        namaPelanggan = new JTextField();
        namaPelanggan.setBounds(150,43,200,20);
        namaPelanggan.setBackground(Color.WHITE);
        namaPelanggan.setForeground(Color.BLACK);
        namaPelanggan.setBorder(BorderFactory.createEmptyBorder());
        pnlBayar.add(namaPelanggan);
        kembali = new JTextField();
        kembali.setBounds(150,93,120,20);
        kembali.setBackground(Color.WHITE);
        kembali.setForeground(Color.BLACK);
        kembali.setBorder(BorderFactory.createEmptyBorder());
        pnlBayar.add(kembali);
        tunai = new JTextField();
        tunai.setBounds(150,68,120,20);
        tunai.setBackground(Color.WHITE);
        tunai.setForeground(Color.BLACK);
        tunai.setBorder(BorderFactory.createEmptyBorder());
        tunai.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                setKembali();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (tunai.getText().equals(""))
                    kembali.setText("");
                else {
                    setKembali();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                setKembali();
            }

            public void setKembali(){
                if (Double.parseDouble(tunai.getText())>=Double.parseDouble(total.getText())){
                    kembali.setText(String.valueOf(Double.parseDouble(tunai.getText())-Float.parseFloat(total.getText())));
                }else
                    kembali.setText("");
            }
        });
        pnlBayar.add(tunai);
        printReceipt = new JButton();
        printReceipt.setBounds(400,30,70,70);
        printReceipt.setIcon(new ImageIcon("images/icon/print.png"));
        printReceipt.setFocusable(false);
        printReceipt.setBackground(new Color(106,172,67));
        printReceipt.addActionListener(this);
        pnlBayar.add(printReceipt);
        sendReceipt = new JButton();
        sendReceipt.setBounds(500,30,70,70);
        sendReceipt.setIcon(new ImageIcon("images/icon/send.png"));
        sendReceipt.setFocusable(false);
        sendReceipt.setBackground(new Color(106,172,67));
        sendReceipt.addActionListener(this);
        pnlBayar.add(sendReceipt);
        panelOrder = new JPanel(null);
        panelOrder.setBounds(720,0,600,430);
        panelOrder.setOpaque(false);
        panelOrder.add(panelScrollPaneOrder);
        panelOrder.add(pnlBayar);
        panelOrder.setVisible(false);
        background.add(panelOrder);
    }

    public void setReceipt(){
        try {
            String sql = "INSERT INTO transaksi (namaPelanggan,tgl_transaksi,ttl_belanja,username) VALUES\n" +
                    "('"+namaPelanggan.getText()+"',NOW(),"+total.getText()+",'"+username+"');";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }catch (SQLException sqlException){
            System.out.println("Error : " + sqlException.getMessage());
        }

        JFrame frameReceipt  = new JFrame();
        frameReceipt.setSize(400,700);
        frameReceipt.setLocationRelativeTo(null);
        frameReceipt.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameReceipt.setAlwaysOnTop(true);
        frameReceipt.setResizable(false);
        frameReceipt.setVisible(true);

        JLabel gighit = new JLabel("Gig.hit",JLabel.CENTER);
        gighit.setFont(new Font("Jokerman", Font.PLAIN, 50));
        gighit.setBounds(0,0,385,80);

        JTextArea isi = new JTextArea(
                "\n\n\n\n---------------------------------------------\n"+
                        "                  gighit.com\n"+
                        "     Jalan Kramat Utara, Kelurahan Tengah\n"+
                        "             DKI JAKARTA, INDONESIA\n"+
                        "---------------------------------------------\n" +
                        " Nama : " + namaPelanggan.getText() + "\n" +
                        "---------------------------------------------\n"+
                        "---------------------------------------------\n"+
                        " Pesanan                            Harga\n"+
                        "---------------------------------------------\n"
        );
        isi.setLineWrap(true);
        isi.setEnabled(false);
        isi.setDisabledTextColor(Color.BLACK);
        isi.setBounds(0,0,385,80);
        isi.setFont(new Font("monospaced", Font.PLAIN, 13));
        isi.setLayout(null);
        isi.add(gighit);
        for (int row=0 ; row<tableOrder.getRowCount() ; row++){
            isi.append(" "+dtmOrder.getValueAt(row,3)
                    +" x "+ dtmOrder.getValueAt(row,0));
            if (!dtmOrder.getValueAt(row, 2).equals("-"))
                isi.append("("+dtmOrder.getValueAt(row,2)+")");

            isi.append("\n                                    "+dtmOrder.getValueAt(row,4)+"\n");
        }
        isi.append("---------------------------------------------\n" +
                " Total                              "+total.getText() + "\n");
        if (admin){
            isi.append("---------------------------------------------\n" +
                    " Tunai                              "+Double.parseDouble(tunai.getText())+"\n" +
                    "---------------------------------------------\n");
            isi.append(" Kembali                            "+kembali.getText()+"\n");
        }else{
            try {
                String kode_pesanan = "";
                String sql = "SELECT * FROM transaksi;";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    kode_pesanan = resultSet.getString(1);
                }
                isi.append(" Kode : "+kode_pesanan+"\n" +
                           " Tunjukan kode ke kasir dan ambil pesananmu ya \n");
            }catch (SQLException sqlException){
                System.out.println("Error : " + sqlException.getMessage());
            }
        }
        isi.append("---------------------------------------------\n" +
                "      TERIMAKASIH DAN SAMPAI JUMPA LAGI\n");

        JScrollPane jsp = new JScrollPane();
        jsp.setViewportView(isi);
        frameReceipt.add(jsp);

        for (int i=0 ; i<pizzas.size() ; i++){
            pQtyM.get(i).setValue(0);
            pQtyL.get(i).setValue(0);
            pSize.get(i).setSelectedItem(null);
            pPrice.get(i).setText("");
            pQtyM.get(i).setEnabled(false);
            pQtyL.get(i).setVisible(false);
            pQtyM.get(i).setVisible(true);
        }
        for (int i=0 ; i<drinks.size() ; i++){
            dQtyM.get(i).setValue(0);
            dQtyL.get(i).setValue(0);
            dSize.get(i).setSelectedItem(null);
            dPrice.get(i).setText("");
            dQtyM.get(i).setEnabled(false);
            dQtyL.get(i).setVisible(false);
            dQtyM.get(i).setVisible(true);
        }
        for (int i=0 ; i<starters.size() ; i++){
            sQty.get(i).setValue(0);
        }
        panelOrder.setVisible(false);
        panelUser.setVisible(false);
        dtmOrder.setRowCount(0);
        namaPelanggan.setText("");
        tunai.setText("");
        kembali.setText("");
        tampilkanTransaksi();
    }

    public void setProdukMenu(){
        sizes = new String[]{new Medium().getSize(), new Large().getSize()};
        sizess = new Size[]{new Medium(), new Large()};
        pizzaMenu = new JPanel(new BorderLayout());
        pizzaMenu.setBounds(20,0,1244,430);
        pizzaMenu.setOpaque(false);
        drinksMenu = new JPanel(new BorderLayout());
        drinksMenu.setBounds(20,0,1244,430);
        drinksMenu.setOpaque(false);
        startersMenu = new JPanel(new BorderLayout());
        startersMenu.setBounds(20,0,1244,430);
        startersMenu.setOpaque(false);

        pizzaScrollPanel = new JPanel(null);
        pizzaScrollPanel.setOpaque(false);
        drinkScrollPanel = new JPanel(null);
        drinkScrollPanel.setOpaque(false);
        startersScrollPanel = new JPanel(null);
        startersScrollPanel.setOpaque(false);

        jspPizza = new JScrollPane(pizzaScrollPanel);
        jspPizza.setOpaque(false);
        jspPizza.getViewport().setOpaque(false);
        jspPizza.setBorder(BorderFactory.createEmptyBorder());
        jspDrink = new JScrollPane(drinkScrollPanel);
        jspDrink.setOpaque(false);
        jspDrink.getViewport().setOpaque(false);
        jspDrink.setBorder(BorderFactory.createEmptyBorder());
        jspStarters = new JScrollPane(startersScrollPanel);
        jspStarters.setOpaque(false);
        jspStarters.getViewport().setOpaque(false);
        jspStarters.setBorder(BorderFactory.createEmptyBorder());

        pizzaMenu.add(jspPizza);
        drinksMenu.add(jspDrink);
        startersMenu.add(jspStarters);
        background.add(pizzaMenu);
        background.add(drinksMenu);
        background.add(startersMenu);
        pizzaMenu.setVisible(false);
        drinksMenu.setVisible(false);
        startersMenu.setVisible(false);
    }

    public void deleteProduk(int i, String namaTable, String namaPizza){
        try {
            String sql = "DELETE FROM " + namaTable + " WHERE nama"+namaTable+" = '" + namaPizza + "';";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            switch (namaTable) {
                case "Pizza" : {
                    pizzaScrollPanel.remove(pPanel.get(i));
                    pizzas.remove(i);
                    pPanel.remove(i);
                    pDelete.remove(i);
                    pEdit.remove(i);
                    pName.remove(i);
                    pDesc.remove(i);
                    pImage.remove(i);
                    pSize.remove(i);
                    pPrice.remove(i);
                    pQtyM.remove(i);
                    pQtyL.remove(i);
                    pM.remove(i);
                    pL.remove(i);
                    this.p--;
                }
                case "Drink" : {
                    drinkScrollPanel.remove(dPanel.get(i));
                    drinks.remove(i);
                    dPanel.remove(i);
                    dDelete.remove(i);
                    dEdit.remove(i);
                    dName.remove(i);
                    dDesc.remove(i);
                    dImage.remove(i);
                    dSize.remove(i);
                    dPrice.remove(i);
                    dQtyM.remove(i);
                    dQtyL.remove(i);
                    dM.remove(i);
                    dL.remove(i);
                    this.d--;
                }
                case "Starters" : {
                    startersScrollPanel.remove(sPanel.get(i));
                    starters.remove(i);
                    sPanel.remove(i);
                    sDelete.remove(i);
                    sEdit.remove(i);
                    sName.remove(i);
                    sDesc.remove(i);
                    sImage.remove(i);
                    sPrice.remove(i);
                    sQty.remove(i);
                    this.s--;
                }
            }
            addToListProduk(namaTable);
        }catch (SQLException e){
            System.out.println("Error : " + e.getMessage());
        }
    }

    public void addNew(String namaTable, String nama, String image, String sizeM, String sizeL, String desc){
        String sql = "INSERT INTO " + namaTable + " VALUES\n" +
                "('" + nama + "','" + image + "'," + sizeM + "," + sizeL + ",'" + desc + "');";
        if (namaTable.equals("Starters")){
            sql = "INSERT INTO " + namaTable + " VALUES\n" +
                    "('" + nama + "','" + image + "'," + sizeM + ",'" + desc + "');";
        }

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            addToListProduk(namaTable);
            tampil(namaTable);
        }catch (SQLException e){
            System.out.println("Error : " + e.getMessage());
        }
    }

    public void editProduk(int index,String namaTable, String nama){
        try {
            String sql = "SELECT * FROM " + namaTable;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                if (resultSet.getString(1).equals(nama)){
                    if (namaTable.equals("Starters")){
                        answer[0].setText(resultSet.getString(1));
                        answer[1].setText(resultSet.getString(2));
                        answer[2].setText(resultSet.getString(3));
                        answer[4].setText(resultSet.getString(4));
                    }
                    else {
                        for (int i=0 ; i<5 ;i++){
                            answer[i].setText(resultSet.getString(i+1));
                        }
                    }
                }
            }

            deleteProduk(index,namaTable,nama);
            btnBackNewProduk.setEnabled(false);

        }catch (SQLException e){
            System.out.println("Error : " + e.getMessage());
        }
    }

    public void addToListProduk(String namaTable){
        try {
            String sql = "SELECT * FROM " + namaTable;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                boolean belumAda = true;
                if (namaTable == "Pizza") {
                    if (pizzas.size() > 0) {
                        for (Pizza pizza : pizzas) {
                            if (resultSet.getString(1).equals(pizza.getName())) {
                                belumAda = false;
                            }
                        }
                    }
                    if (belumAda) {
                        pizzas.add(new Pizza(resultSet.getString(1),
                                new ImageIcon(resultSet.getString(2)),
                                resultSet.getDouble(3),
                                resultSet.getDouble(4),
                                resultSet.getString(5)));
                        pPanel.add(new JPanel());
                        pDelete.add(new JButton());
                        pEdit.add(new JButton());
                        pName.add(new JLabel());
                        pDesc.add(new JTextArea());
                        pImage.add(new JLabel());
                        pSize.add(new JComboBox(sizes));
                        pPrice.add(new JLabel());
                        pQtyM.add(new JSpinner());
                        pQtyL.add(new JSpinner());
                        pM.add(new JPanel());
                        pL.add(new JPanel());
                        addProdukToPanel(namaTable, pizzas.get(p), pPanel.get(p), pDelete.get(p), pEdit.get(p),
                                pName.get(p), pDesc.get(p), pImage.get(p),
                                pSize.get(p), pPrice.get(p), pQtyM.get(p), pQtyL.get(p), pM.get(p), pL.get(p));
                        this.p++;
                    }
                } else if (namaTable == "Drink") {
                    if (drinks.size() > 0) {
                        for (Drink drink : drinks) {
                            if (resultSet.getString(1).equals(drink.getName())) {
                                belumAda = false;
                            }
                        }
                    }
                    if (belumAda) {
                        drinks.add(new Drink(resultSet.getString(1),
                                new ImageIcon(resultSet.getString(2)),
                                resultSet.getDouble(3),
                                resultSet.getDouble(4),
                                resultSet.getString(5)));
                        dPanel.add(new JPanel());
                        dDelete.add(new JButton());
                        dEdit.add(new JButton());
                        dName.add(new JLabel());
                        dDesc.add(new JTextArea());
                        dImage.add(new JLabel());
                        dSize.add(new JComboBox(sizes));
                        dPrice.add(new JLabel());
                        dQtyM.add(new JSpinner());
                        dQtyL.add(new JSpinner());
                        dM.add(new JPanel());
                        dL.add(new JPanel());
                        addProdukToPanel(namaTable, drinks.get(d), dPanel.get(d), dDelete.get(d), dEdit.get(d),
                                dName.get(d), dDesc.get(d), dImage.get(d), dSize.get(d), dPrice.get(d),
                                dQtyM.get(d), dQtyL.get(d), dM.get(d), dL.get(d));
                        this.d++;
                    }
                } else if (namaTable == "Starters") {
                    if (starters.size() > 0) {
                        for (Starters starter : starters) {
                            if (resultSet.getString(1).equals(starter.getName())) {
                                belumAda = false;
                            }
                        }
                    }
                    if (belumAda) {
                        starters.add(new Starters(resultSet.getString(1),
                                new ImageIcon(resultSet.getString(2)),
                                resultSet.getDouble(3),
                                resultSet.getString(4)));
                        sPanel.add(new JPanel());
                        sDelete.add(new JButton());
                        sEdit.add(new JButton());
                        sName.add(new JLabel());
                        sDesc.add(new JTextArea());
                        sImage.add(new JLabel());
                        sPrice.add(new JLabel());
                        sQty.add(new JSpinner());
                        addProdukToPanel(namaTable, starters.get(s), sPanel.get(s), sDelete.get(s),
                                sEdit.get(s), sName.get(s), sDesc.get(s), sImage.get(s),
                                new JComboBox(), sPrice.get(s), sQty.get(s), new JSpinner(), new JPanel(),
                                new JPanel());
                        this.s++;
                    }
                }
            }
        } catch (SQLException e){
            System.out.println("Error : " + e.getMessage());
        }
    }

    public void addProdukToPanel(String namaTable,Produk produk,JPanel panel,JButton delete,
                                 JButton edit,JLabel name,JTextArea desc,JLabel image,
                                 JComboBox size,JLabel price,JSpinner qtyM,JSpinner qtyL,
                                 JPanel pnlM, JPanel pnlL){

        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(240,300));
        panel.setBackground(Color.BLACK);
        name.setText(produk.getName());
        name.setFont(new Font("Edwardian Script ITC", Font.BOLD, 30));
        name.setBounds(20,160,200,40);
        name.setForeground(new Color(135,240,225));
        desc.setText(produk.getDesc());
        desc.setRows(2);
        desc.setColumns(1);
        desc.setLineWrap(true);
        desc.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        desc.setForeground(Color.WHITE);
        desc.getCaret().deinstall(desc);
        desc.setEnabled(false);
        desc.setBounds(20,210,200,30);
        desc.setOpaque(false);
        image.setIcon(produk.getImage());
        image.setBounds(0,0,240,147);
        image.setHorizontalAlignment(JLabel.CENTER);
        image.setVerticalAlignment(JLabel.BOTTOM);

        if (namaTable.equals("Starters")){
            price.setText(" Rp. "+produk.getPrice());
            price.setBounds(20,260,165,32);
            price.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
            price.setForeground(new Color(232,59,10));
            price.setBackground(new Color(184,207,229));
            price.setOpaque(true);
            price.setBorder(BorderFactory.createLineBorder(new Color(184,207,229),2));
            qtyM.setModel(new SpinnerNumberModel(0,0,50,1));
            qtyM.setBounds(185,260,40,32);
            qtyM.setBorder(BorderFactory.createLineBorder(new Color(184,207,229),2));
            qtyM.getEditor().getComponent(0).setBackground(Color.WHITE);
            qtyM.getComponent(0).setBackground(Color.BLACK);
            qtyM.getComponent(1).setBackground(Color.BLACK);
            qtyM.getEditor().setForeground(Color.BLACK);
            qtyM.setOpaque(true);
            qtyM.addChangeListener(this);
            panel.add(price);
            panel.add(qtyM);
        }
        else {
            qtyM.setModel(new SpinnerNumberModel(0,0,50,1));
            qtyL.setModel(new SpinnerNumberModel(0,0,50,1));
            qtyM.setBounds(0,0,40,32);
            qtyL.setBounds(0,0,40,32);
            qtyM.setBorder(BorderFactory.createLineBorder(new Color(184,207,229),2));
            qtyL.setBorder(BorderFactory.createLineBorder(new Color(184,207,229),2));
            qtyM.getEditor().getComponent(0).setBackground(Color.WHITE);
            qtyL.getEditor().getComponent(0).setBackground(Color.WHITE);
            qtyM.getComponent(0).setBackground(Color.BLACK);
            qtyL.getComponent(0).setBackground(Color.BLACK);
            qtyM.getComponent(1).setBackground(Color.BLACK);
            qtyL.getComponent(1).setBackground(Color.BLACK);
            qtyM.getEditor().setForeground(Color.BLACK);
            qtyL.getEditor().setForeground(Color.BLACK);
            qtyM.setOpaque(true);
            qtyL.setOpaque(true);
            qtyM.setEnabled(false);
            qtyM.addChangeListener(this);
            qtyL.addChangeListener(this);
            pnlM.setLayout(null);
            pnlL.setLayout(null);
            pnlM.setBounds(185,260,40,32);
            pnlL.setBounds(185,260,40,32);
            pnlM.add(qtyM);
            pnlL.add(qtyL);
            pnlL.setVisible(false);
            size.setBounds(20,260,165,32);
            size.setSelectedItem(null);
            size.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
            size.setBackground(new Color(184,207,229));
            size.setBorder(BorderFactory.createLineBorder(new Color(184,207,229),2));
            size.setForeground(Color.BLACK);
            size.setFocusable(false);
            size.addActionListener(this);
            size.setSelectedItem(this);
            price.setText("");
            price.setBounds(50,-1,90,31);
            price.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
            price.setForeground(new Color(232,59,10));
            size.add(price);
            panel.add(pnlM);
            panel.add(pnlL);
        }

        delete.setBounds(196,0,44,14);
        delete.setText("delete");
        delete.setOpaque(false);
        delete.setContentAreaFilled(false);
        delete.setFocusPainted(false);
        delete.setBorder(BorderFactory.createEmptyBorder());
        delete.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
        delete.setForeground(Color.WHITE);
        delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        delete.addActionListener(this);
        edit.setBounds(0,0,27,14);
        edit.setText("edit");
        edit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        edit.setOpaque(false);
        edit.setContentAreaFilled(false);
        edit.setFocusPainted(false);
        edit.addActionListener(this);
        edit.setBorder(BorderFactory.createEmptyBorder());
        edit.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
        edit.setForeground(Color.WHITE);
        image.add(delete);
        image.add(edit);

        panel.add(name);
        panel.add(desc);
        panel.add(image);
        panel.add(size);
        //panel.add(delete);
        //panel.add(edit);

    }

    public void setFormAddNew(){
        question = new JTextArea[]{new JTextArea("Nama Pizza\n\nImage Pizza" +
                "\n\nHarga Medium Size\n\nHarga Large Size\n\nDeskripsi Pizza"),
                new JTextArea("Nama Drink\n\nImage Drink" +
                        "\n\nHarga Medium Size\n\nHarga Large Size\n\nDeskripsi Drink"),
                new JTextArea("Nama starters\n\nImage Starters\n\nHarga Starters\n\nDeskripsi Starters")};

        formAddNew = new JPanel(null);
        formAddNew.setBounds(230,20,860,390);
        formAddNew.setBackground(new Color(0,0,0,100));

        btnAddNewPizza = new JButton("",new ImageIcon("images/icon/plusIcon.png"));
        btnAddNewPizza.setBounds(95,125,50,50);
        btnAddNewPizza.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAddNewPizza.setVisible(false);
        btnAddNewPizza.addActionListener(this);
        btnAddNewDrinks = new JButton("",new ImageIcon("images/icon/plusIcon.png"));
        btnAddNewDrinks.setBounds(95,125,50,50);
        btnAddNewDrinks.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAddNewDrinks.setVisible(false);
        btnAddNewDrinks.addActionListener(this);
        btnAddNewStarters = new JButton("",new ImageIcon("images/icon/plusIcon.png"));
        btnAddNewStarters.setBounds(95,125,50,50);
        btnAddNewStarters.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAddNewStarters.setVisible(false);
        btnAddNewStarters.addActionListener(this);

        panelAddNewProduk = new JPanel(null);
        panelAddNewProduk.setOpaque(false);
        panelAddNewProduk.setPreferredSize(new Dimension(240,300));
        panelAddNewProduk.add(btnAddNewPizza);
        panelAddNewProduk.add(btnAddNewDrinks);
        panelAddNewProduk.add(btnAddNewStarters);

        btnBackNewProduk = new JButton("< b a c k");
        btnBackNewProduk.setBounds(0,5,200,60);
        btnBackNewProduk.setBackground(Color.BLACK);
        btnBackNewProduk.setForeground(Color.WHITE);
        btnBackNewProduk.setFont(new Font("Script MT Bold", Font.PLAIN, 40));
        btnBackNewProduk.setFocusable(false);
        btnBackNewProduk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBackNewProduk.addActionListener(this);
        btnSaveNewProduk = new JButton(" S A V E !");
        btnSaveNewProduk.setBounds(660,320,200,60);
        btnSaveNewProduk.setBackground(Color.WHITE);
        btnSaveNewProduk.setForeground(Color.BLACK);
        btnSaveNewProduk.setFocusable(false);
        btnSaveNewProduk.setFont(new Font("Script MT Bold", Font.PLAIN, 40));
        btnSaveNewProduk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSaveNewProduk.addActionListener(this);

        for (JTextArea jTextArea : question){
            jTextArea.setBounds(100,80,200,225);
            jTextArea.setEnabled(false);
            jTextArea.getCaret().deinstall(jTextArea);
            jTextArea.setDisabledTextColor(Color.WHITE);
            jTextArea.setOpaque(false);
            jTextArea.setFont(new Font("Berlin Sans FB", Font.PLAIN, 22));
            jTextArea.setVisible(false);
            formAddNew.add(jTextArea);
        }

        answer = new JTextField[5];
        int y = 80;
        for (int i=0 ; i<5 ; i++){
            answer[i] = new JTextField();
            answer[i].setBounds(300,y,480,25);
            answer[i].setBackground(new Color(184,207,229));
            answer[i].setBorder(BorderFactory.createEmptyBorder());

            formAddNew.add(answer[i]);
            y+=50;
        }

        formAddNew.add(btnBackNewProduk);
        formAddNew.add(btnSaveNewProduk);
        formAddNew.setVisible(false);
        background.add(formAddNew);
    }

    public void tampil(String namatable){
        formAddNew.setVisible(false);
        pizzaMenu.setVisible(false);
        drinksMenu.setVisible(false);
        startersMenu.setVisible(false);
        labelHome.setVisible(false);
        pizzaScrollPanel.setVisible(false);
        drinkScrollPanel.setVisible(false);
        startersScrollPanel.setVisible(false);

        if (namatable == "Pizza") {
            int pX = 0;
            int pY = 20;
            for (JPanel jPanel : pPanel) {
                jPanel.setBounds(pX, pY, 240, 300);
                pizzaScrollPanel.add(jPanel);
                if (pX == 1040) {
                    pX = 0;
                    pY += 320;
                    pizzaScrollPanel.setPreferredSize(new Dimension(1280, pY * 2));
                } else {
                    pX += 260;
                }
            }
            panelAddNewProduk.setBounds(pX, pY, 240, 300);
            pizzaScrollPanel.add(panelAddNewProduk);
            drinksMenu.setVisible(false);
            startersMenu.setVisible(false);
            btnAddNewDrinks.setVisible(false);
            btnAddNewStarters.setVisible(false);
            btnAddNewPizza.setVisible(true);
            pizzaMenu.setVisible(true);
            pizzaScrollPanel.setVisible(true);
        }
        else if (namatable == "Drink") {
            int dX = 0;
            int dY = 20;
            for (JPanel jPanel : dPanel) {
                jPanel.setBounds(dX, dY, 240, 300);
                drinkScrollPanel.add(jPanel);
                if (dX == 1040) {
                    dX = 0;
                    dY += 320;
                    drinkScrollPanel.setPreferredSize(new Dimension(1280, dY + 320));
                } else {
                    dX += 260;
                }
            }
            panelAddNewProduk.setBounds(dX, dY, 240, 300);
            drinkScrollPanel.add(panelAddNewProduk);
            pizzaMenu.setVisible(false);
            startersMenu.setVisible(false);
            btnAddNewPizza.setVisible(false);
            btnAddNewStarters.setVisible(false);
            btnAddNewDrinks.setVisible(true);
            drinksMenu.setVisible(true);
            drinkScrollPanel.setVisible(true);
        }
        else if (namatable == "Starters") {
            int sX = 0;
            int sY = 20;
            for (JPanel jPanel : sPanel) {
                jPanel.setBounds(sX, sY, 240, 300);
                startersScrollPanel.add(jPanel);
                if (sX == 1040) {
                    sX = 0;
                    sY += 320;
                    startersScrollPanel.setPreferredSize(new Dimension(1280, sY + 320));
                } else {
                    sX += 260;
                }
            }
            panelAddNewProduk.setBounds(sX, sY, 240, 300);
            startersScrollPanel.add(panelAddNewProduk);
            drinksMenu.setVisible(false);
            pizzaMenu.setVisible(false);
            btnAddNewPizza.setVisible(false);
            btnAddNewDrinks.setVisible(false);
            btnAddNewStarters.setVisible(true);
            startersMenu.setVisible(true);
            startersScrollPanel.setVisible(true);
        }
    }

    public void setTransaksiTable(){
        String[] header = {"ID Transaksi","Nama","Tanggal Transaksi","Total Belanja","Username"};
        dtmTransaksi = new DefaultTableModel(0,0);
        dtmTransaksi.setColumnIdentifiers(header);

        transaksiTable = new JTable(dtmTransaksi);
        transaksiTable.setPreferredScrollableViewportSize(new Dimension(610,330));
        transaksiTable.setFillsViewportHeight(true);
        transaksiTable.getTableHeader().setReorderingAllowed(false);
        transaksiTable.getTableHeader().setResizingAllowed(false);
        transaksiTable.getTableHeader().setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        transaksiTable.getTableHeader().setOpaque(false);
        transaksiTable.getTableHeader().setBackground(Color.BLACK);
        transaksiTable.getTableHeader().setForeground(Color.WHITE);
        transaksiTable.getTableHeader().setPreferredSize(new Dimension(610,30));
        transaksiTable.setShowVerticalLines(false);
        transaksiTable.setRowHeight(30);
        transaksiTable.setOpaque(false);
        transaksiTable.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        transaksiTable.setForeground(Color.WHITE);
        transaksiTable.setGridColor(Color.WHITE);
        transaksiTable.setEnabled(false);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setOpaque(false);
        center.setHorizontalAlignment(JLabel.CENTER);
        transaksiTable.getColumnModel().getColumn(0).setCellRenderer(center);
        transaksiTable.getColumnModel().getColumn(0).setMaxWidth(110);
        transaksiTable.getColumnModel().getColumn(0).setMinWidth(110);
        transaksiTable.getColumnModel().getColumn(1).setCellRenderer(center);
        transaksiTable.getColumnModel().getColumn(1).setMaxWidth(200);
        transaksiTable.getColumnModel().getColumn(1).setMinWidth(200);
        transaksiTable.getColumnModel().getColumn(2).setCellRenderer(center);
        transaksiTable.getColumnModel().getColumn(2).setMaxWidth(150);
        transaksiTable.getColumnModel().getColumn(2).setMinWidth(150);
        transaksiTable.getColumnModel().getColumn(3).setCellRenderer(center);
        transaksiTable.getColumnModel().getColumn(3).setMaxWidth(150);
        transaksiTable.getColumnModel().getColumn(3).setMinWidth(150);
        transaksiTable.getColumnModel().getColumn(4).setMaxWidth(0);
        transaksiTable.getColumnModel().getColumn(4).setMinWidth(0);

        jspTransaksi = new JScrollPane(transaksiTable);
        jspTransaksi.setOpaque(false);
        jspTransaksi.getViewport().setBackground(new Color(0,0,0,150));
        jspTransaksi.setBorder(BorderFactory.createEmptyBorder());

        transaksiScrollPane = new JPanel(new CardLayout());
        transaksiScrollPane.setBounds(585,50,610,330);
        transaksiScrollPane.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
        transaksiScrollPane.setOpaque(false);
        transaksiScrollPane.add(jspTransaksi);
        background.add(transaksiScrollPane);
        transaksiScrollPane.setVisible(false);

        panelIsiSaldo = new JPanel(null);
        panelIsiSaldo.setBounds(125,50,460,330);
        panelIsiSaldo.setBackground(Color.BLACK);

        isiSaldoPelanggan = new JLabel();
        isiSaldoPelanggan.setBounds(0,65,460,30);
        isiSaldoPelanggan.setForeground(new Color(106,172,67));
        isiSaldoPelanggan.setFont(new Font("Lucida Console", Font.PLAIN, 16));
        isiSaldoPelanggan.setHorizontalAlignment(JLabel.CENTER);
        panelIsiSaldo.add(isiSaldoPelanggan);

        JTextField[] questSaldo = {new JTextField("Username"),new JTextField("Jumlah")};
        int y=115;
        for (JTextField quest : questSaldo) {
            quest.setBounds(65, y, 80, 30);
            quest.setFont(new Font("Lucida Console", Font.PLAIN, 14));
            quest.setDisabledTextColor(Color.WHITE);
            quest.setBorder(BorderFactory.createEmptyBorder());
            quest.setOpaque(false);
            quest.setEnabled(false);
            panelIsiSaldo.add(quest);
            y += 50;
        }
        JTextField user_saldo = new JTextField();
        user_saldo.setBounds(145,115,250,30);
        user_saldo.setBorder(BorderFactory.createEmptyBorder());
        panelIsiSaldo.add(user_saldo);

        JTextField rp = new JTextField();
        rp.setBounds(145,165,250,30);
        rp.setBorder(BorderFactory.createEmptyBorder());
        panelIsiSaldo.add(rp);

        JButton kirim = new JButton("Transfer");
        kirim.setBounds(65,y,330,30);
        kirim.setForeground(Color.WHITE);
        kirim.setBackground(new Color(106,172,67));
        kirim.setFocusable(false);
        kirim.addActionListener(e -> {
            if (user_saldo.getText().equals("") || rp.getText().equals("")){
                JOptionPane.showMessageDialog(null,"isi username dan jumlah");
            }else {
                double saldoAwal = 0;
                try {
                    String s = "SELECT * FROM user;";
                    Statement st = connection.createStatement();
                    ResultSet resultSet = st.executeQuery(s);
                    boolean ada = false;
                    while (resultSet.next()){
                        if (resultSet.getString(1).equals(user_saldo.getText())){
                            saldoAwal = Double.parseDouble(resultSet.getString(6));
                            ada = true;
                        }
                    }
                    if (!ada){
                        JOptionPane.showMessageDialog(null,"user tidak ditemukan");
                    }
                    else {
                        if (!admin){
                            if (Double.parseDouble(saldo.getText()) < Double.parseDouble(rp.getText()))
                                JOptionPane.showMessageDialog(null,"saldo kamu kurang :(");
                            else{
                                saldo.setText(String.valueOf(Double.parseDouble(saldo.getText())-Double.parseDouble(rp.getText())));
                                String sql = "UPDATE user SET saldo = "+(Double.parseDouble(rp.getText())+saldoAwal)+
                                        " WHERE username = '"+user_saldo.getText()+"';";
                                String sqll = "UPDATE user set saldo = "+ saldo.getText() +
                                        " WHERE username = '"+username+"';";
                                Statement statement = connection.createStatement();
                                statement.executeUpdate(sql);
                                statement.executeUpdate(sqll);
                                JOptionPane.showMessageDialog(null,"Transfer Berhasil");
                            }
                        }else {
                            String sql = "UPDATE user SET saldo = "+(Double.parseDouble(rp.getText())+saldoAwal)+
                                    " WHERE username = '"+user_saldo.getText()+"';";
                            Statement statement = connection.createStatement();
                            statement.executeUpdate(sql);
                            JOptionPane.showMessageDialog(null,"Isi saldo pelanggan berhasil");
                        }
                    }
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }user_saldo.setText("");rp.setText("");
        });
        panelIsiSaldo.add(kirim);

        background.add(panelIsiSaldo);
        panelIsiSaldo.setVisible(false);
    }

    public void tampilkanTransaksi(){
        dtmTransaksi.setRowCount(0);

        try {
            String sql = "SELECT * FROM transaksi";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (admin){
                double saldoAdmin = 0;
                while (resultSet.next()){
                    dtmTransaksi.addRow(new Object[]{resultSet.getString(1),resultSet.getString(2),
                            resultSet.getString(3),resultSet.getString(4),resultSet.getString(5)});
                    saldoAdmin+=Double.parseDouble(resultSet.getString(4));
                }
                saldo.setText(String.valueOf(saldoAdmin));
            }else {
                while (resultSet.next()){
                    if (resultSet.getString(5).equals(username)){
                        dtmTransaksi.addRow(new Object[]{resultSet.getString(1),resultSet.getString(2),
                                resultSet.getString(3),resultSet.getString(4),resultSet.getString(5)});
                    }
                }
            }

        }catch (SQLException e){
            System.out.println("Error : " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnHome){
            btnHome.setForeground(Color.YELLOW);
            btnPizza.setForeground(Color.WHITE);
            btnDrink.setForeground(Color.WHITE);
            btnStarters.setForeground(Color.WHITE);
            btnTransaksi.setForeground(Color.WHITE);
            pizzaMenu.setVisible(false);
            drinksMenu.setVisible(false);
            startersMenu.setVisible(false);
            formAddNew.setVisible(false);
            transaksiScrollPane.setVisible(false);
            panelIsiSaldo.setVisible(false);
            labelHome.setVisible(true);
        }
        else if (e.getSource() == btnOrder){
            panelOrder.setVisible(!panelOrder.isVisible());
            panelUser.setVisible(!panelUser.isVisible());
        }
        else if (e.getSource() == btnPizza){
            transaksiScrollPane.setVisible(false);
            panelIsiSaldo.setVisible(false);
            btnHome.setForeground(Color.WHITE);
            btnPizza.setForeground(Color.YELLOW);
            btnDrink.setForeground(Color.WHITE);
            btnStarters.setForeground(Color.WHITE);
            btnTransaksi.setForeground(Color.WHITE);
            pizzaMenu.setVisible(false);
            drinksMenu.setVisible(false);
            startersMenu.setVisible(false);
            tampil("Pizza");
        }
        else if (e.getSource() == btnDrink){
            transaksiScrollPane.setVisible(false);
            panelIsiSaldo.setVisible(false);
            btnHome.setForeground(Color.WHITE);
            btnPizza.setForeground(Color.WHITE);
            btnDrink.setForeground(Color.YELLOW);
            btnStarters.setForeground(Color.WHITE);
            btnTransaksi.setForeground(Color.WHITE);
            pizzaMenu.setVisible(false);
            drinksMenu.setVisible(false);
            startersMenu.setVisible(false);
            tampil("Drink");
        }
        else if (e.getSource() == btnStarters){
            transaksiScrollPane.setVisible(false);
            panelIsiSaldo.setVisible(false);
            btnHome.setForeground(Color.WHITE);
            btnPizza.setForeground(Color.WHITE);
            btnDrink.setForeground(Color.WHITE);
            btnStarters.setForeground(Color.YELLOW);
            btnTransaksi.setForeground(Color.WHITE);
            pizzaMenu.setVisible(false);
            drinksMenu.setVisible(false);
            startersMenu.setVisible(false);
            tampil("Starters");
        }
        else if (e.getSource() == btnAddNewPizza){
            table = "Pizza";
            pizzaMenu.setVisible(false);
            question[1].setVisible(false);
            question[2].setVisible(false);
            question[0].setVisible(true);
            formAddNew.setVisible(true);
            answer[3].setVisible(true);
            answer[4].setBounds(300,280,480,25);
            for (JTextField jTextField : answer) {
                jTextField.setText(null);
            }
        }
        else if (e.getSource() == btnAddNewDrinks){
            table = "Drink";
            drinksMenu.setVisible(false);
            question[0].setVisible(false);
            question[2].setVisible(false);
            question[1].setVisible(true);
            formAddNew.setVisible(true);
            answer[3].setVisible(true);
            answer[4].setBounds(300,280,480,25);
            for (JTextField jTextField : answer) {
                jTextField.setText(null);
            }
        }
        else if (e.getSource() == btnAddNewStarters){
            table = "Starters";
            startersMenu.setVisible(false);
            question[0].setVisible(false);
            question[1].setVisible(false);
            question[2].setVisible(true);
            formAddNew.setVisible(true);
            answer[3].setVisible(false);
            answer[4].setBounds(answer[3].getBounds());

            for (JTextField jTextField : answer) {
                jTextField.setText(null);
            }
        }
        else if (e.getSource() == btnSaveNewProduk){
            btnHome.setEnabled(true);
            btnPizza.setEnabled(true);
            btnDrink.setEnabled(true);
            btnStarters.setEnabled(true);
            btnOrder.setEnabled(true);
            btnTransaksi.setEnabled(true);
            boolean kosong = false;
            if (answer[1].getText().equals("")){
                answer[1].setText("images/"+table+"/"+table+".png");
            }

            if (table.equals("Starters")){
                if (answer[0].getText().equals("")||answer[2].getText().equals("")||
                        answer[4].getText().equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "Selain Image, tidak boleh ada yang dikosongkan");
                    kosong = true;
                }
            }
            else {
                if (answer[0].getText().equals("") || answer[2].getText().equals("") ||
                        answer[3].getText().equals("") || answer[4].getText().equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "Selain Image, tidak boleh ada yang dikosongkan");
                    kosong = true;
                }
            }

            if (!kosong){
                addNew(table, answer[0].getText(), answer[1].getText(),
                        answer[2].getText(), answer[3].getText(), answer[4].getText());
                tampil(table);
                JOptionPane.showMessageDialog(null,"Berhasil !");
                formAddNew.setVisible(false);
                answer[3].setVisible(true);
                answer[4].setBounds(300,270,480,25);
                btnBackNewProduk.setEnabled(true);
            }
        }
        else if (e.getSource() == btnBackNewProduk){
            btnHome.setForeground(Color.YELLOW);
            btnPizza.setForeground(Color.WHITE);
            btnDrink.setForeground(Color.WHITE);
            btnStarters.setForeground(Color.WHITE);
            btnTransaksi.setForeground(Color.WHITE);
            labelHome.setVisible(true);
            formAddNew.setVisible(false);
            question[0].setVisible(false);
            question[1].setVisible(false);
            question[2].setVisible(false);
            answer[3].setVisible(true);
            answer[4].setBounds(300,270,480,25);
        }
        else if (e.getSource() == sendReceipt){
            if (namaPelanggan.getText().equals("") || tunai.getText().equals("") || kembali.getText().equals("")){
                JOptionPane.showMessageDialog(null,
                        "Nama pelanggan, tunai dan kembali tidak boleh kosong");
            }
            else{
                JOptionPane.showInputDialog("Alamat Email");
                setReceipt();
            }
        }
        else if (e.getSource() == printReceipt){
            if (namaPelanggan.getText().equals("") || tunai.getText().equals("") || kembali.getText().equals("")){
                JOptionPane.showMessageDialog(null,
                        "Nama pelanggan, tunai dan kembali tidak boleh kosong");
            }
            else{
                setReceipt();
            }
        }
        else if (e.getSource() == btnTransaksi){
            transaksiScrollPane.setVisible(false);
            pizzaMenu.setVisible(false);
            drinksMenu.setVisible(false);
            startersMenu.setVisible(false);
            panelOrder.setVisible(false);
            panelUser.setVisible(false);
            labelHome.setVisible(false);
            btnHome.setForeground(Color.WHITE);
            btnPizza.setForeground(Color.WHITE);
            btnDrink.setForeground(Color.WHITE);
            btnStarters.setForeground(Color.WHITE);
            btnTransaksi.setForeground(Color.YELLOW);
            tampilkanTransaksi();
            transaksiScrollPane.setVisible(true);
            panelIsiSaldo.setVisible(true);
        }

        for (int i=0 ; i<pizzas.size() ; i++) {
            if (e.getSource() == pSize.get(i)) {
                pQtyM.get(i).setEnabled(true);
                if (pSize.get(i).getSelectedItem() == sizes[0]) {
                    pizzas.get(i).setSize(sizess[0]);
                    pPrice.get(i).setText(" : Rp. " + pizzas.get(i).getPrice());
                    pM.get(i).setVisible(true);
                    pL.get(i).setVisible(false);
                } else if (pSize.get(i).getSelectedItem() == sizes[1]) {
                    pizzas.get(i).setSize(sizess[1]);
                    pPrice.get(i).setText(" : Rp. " + pizzas.get(i).getPrice());
                    pM.get(i).setVisible(false);
                    pL.get(i).setVisible(true);
                }
            }
            else if (e.getSource() == pDelete.get(i)){
                int ok = JOptionPane.showConfirmDialog(null,"Hapus Pizza " +
                        pizzas.get(i).getName() + " ?", "Confirmation",JOptionPane.YES_NO_OPTION);
                if (ok == 0){
                    deleteProduk(i,"Pizza",pizzas.get(i).getName());
                    tampil("Pizza");
                }
            }
            else if (e.getSource() == pEdit.get(i)){
                btnHome.setEnabled(false);
                btnPizza.setEnabled(false);
                btnDrink.setEnabled(false);
                btnStarters.setEnabled(false);
                btnOrder.setEnabled(false);
                btnTransaksi.setEnabled(false);
                editProduk(i,"Pizza",pizzas.get(i).getName());
                pizzaMenu.setVisible(false);
                question[1].setVisible(false);
                question[0].setVisible(true);
                table = "Pizza";
                formAddNew.setVisible(true);
            }
        }
        for (int i=0 ; i<drinks.size() ; i++){
            if (e.getSource() == dSize.get(i)) {
                dQtyM.get(i).setEnabled(true);
                if (dSize.get(i).getSelectedItem() == sizes[0]) {
                    drinks.get(i).setSize(sizess[0]);
                    dPrice.get(i).setText(" : Rp. " + drinks.get(i).getPrice());
                    dM.get(i).setVisible(true);
                    dL.get(i).setVisible(false);
                } else if (dSize.get(i).getSelectedItem() == sizes[1]) {
                    drinks.get(i).setSize(sizess[1]);
                    dPrice.get(i).setText(" : Rp. " + drinks.get(i).getPrice());
                    dM.get(i).setVisible(false);
                    dL.get(i).setVisible(true);
                }
            }
            else if (e.getSource() == dDelete.get(i)){
                int ok = JOptionPane.showConfirmDialog(null,"Hapus " + drinks.get(i).getName() + " ?",
                        "Confirmation",JOptionPane.YES_NO_OPTION);
                if (ok == 0){
                    deleteProduk(i,"Drink",drinks.get(i).getName());
                    tampil("Drink");
                }
            }
            else if (e.getSource() == dEdit.get(i)){
                btnHome.setEnabled(false);
                btnPizza.setEnabled(false);
                btnDrink.setEnabled(false);
                btnStarters.setEnabled(false);
                btnOrder.setEnabled(false);
                btnTransaksi.setEnabled(false);
                editProduk(i,"Drink",drinks.get(i).getName());
                drinksMenu.setVisible(false);
                question[0].setVisible(false);
                question[1].setVisible(true);
                table = "Drink";
                formAddNew.setVisible(true);
            }
        }
        for (int i=0 ; i<starters.size() ; i++){
            if (e.getSource() == sDelete.get(i)){
                int ok = JOptionPane.showConfirmDialog(null,"Hapus " +
                        starters.get(i).getName() + " ?","Confirmation",JOptionPane.YES_NO_OPTION);
                if (ok == 0){
                    deleteProduk(i,"Starters",starters.get(i).getName());
                    tampil("Starters");
                }
            }
            else if (e.getSource() == sEdit.get(i)){
                btnHome.setEnabled(false);
                btnPizza.setEnabled(false);
                btnDrink.setEnabled(false);
                btnStarters.setEnabled(false);
                btnOrder.setEnabled(false);
                btnTransaksi.setEnabled(false);
                editProduk(i,"Starters",starters.get(i).getName());
                startersMenu.setVisible(false);
                question[0].setVisible(false);
                question[1].setVisible(false);
                question[2].setVisible(true);
                answer[3].setVisible(false);
                answer[4].setBounds(answer[3].getBounds());
                table = "Starters";
                formAddNew.setVisible(true);
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {

        final int quantity = (int) ((JSpinner) e.getSource()).getValue();
        int rows = tableOrder.getRowCount();

        for (int row = 0; row < rows; row++) {
            if (e.getSource() == dtmOrder.getValueAt(row, 5)) {
                for (Drink drink : drinks) {
                    if (dtmOrder.getValueAt(row, 0).equals(drink.getName())) {
                        dtmOrder.setValueAt(quantity, row, 3);
                        dtmOrder.setValueAt(drink.getPrice() * quantity, row, 4);
                    }
                }
                for (Pizza pizza : pizzas) {
                    if (dtmOrder.getValueAt(row, 0).equals(pizza.getName())) {
                        dtmOrder.setValueAt(quantity, row, 3);
                        dtmOrder.setValueAt(pizza.getPrice() * quantity, row, 4);
                    }
                }
                for (Starters starter : starters){
                    if (dtmOrder.getValueAt(row, 0).equals(starter.getName())) {
                        dtmOrder.setValueAt(quantity, row, 3);
                        dtmOrder.setValueAt(starter.getPrice() * quantity, row, 4);
                    }
                }

                if (quantity == 0){
                    dtmOrder.removeRow(row);
                    rows = tableOrder.getRowCount();
                }
            }
        }

        for (int i=0 ; i<pizzas.size() ; i++){
            boolean add = true;
            for (int row=0 ; row<tableOrder.getRowCount() ; row++){
                if (e.getSource() == dtmOrder.getValueAt(row, 5)){
                    add = false;
                }
            }
            if (quantity == 1 && add){
                if (e.getSource() == pQtyM.get(i)) {
                    dtmOrder.addRow(new Object[]{pizzas.get(i).getName(),pizzas.get(i).packing().packName(),pizzas.get(i).getSize().getSize(),
                            quantity,pizzas.get(i).getPrice()*quantity,pQtyM.get(i)});
                    pDelete.get(i).setEnabled(false);
                    pEdit.get(i).setEnabled(false);
                }else if (e.getSource() == pQtyL.get(i)) {
                    dtmOrder.addRow(new Object[]{pizzas.get(i).getName(), pizzas.get(i).packing().packName(), pizzas.get(i).getSize().getSize(),
                            quantity, pizzas.get(i).getPrice() * quantity, pQtyL.get(i)});
                    pDelete.get(i).setEnabled(false);
                    pEdit.get(i).setEnabled(false);
                }
            }
            boolean gaadaDiTable =true;
            for (int row=0 ; row<tableOrder.getRowCount() ; row++){
                if (pizzas.get(i).getName() == dtmOrder.getValueAt(row,0)){
                    gaadaDiTable = false;
                }
            }
            if (gaadaDiTable){
                pDelete.get(i).setEnabled(true);
                pEdit.get(i).setEnabled(true);
            }
        }
        for (int i=0 ; i<drinks.size() ; i++){
            boolean add = true;
            for (int row=0 ; row<tableOrder.getRowCount() ; row++){
                if (e.getSource() == dtmOrder.getValueAt(row, 5)){
                    add = false;
                }
            }
            if (quantity == 1 && add){
                if (e.getSource() == dQtyM.get(i)) {
                    dtmOrder.addRow(new Object[]{drinks.get(i).getName(), drinks.get(i).packing().packName(), drinks.get(i).getSize().getSize(),
                            quantity, drinks.get(i).getPrice() * quantity, dQtyM.get(i)});
                    dDelete.get(i).setEnabled(false);
                    dEdit.get(i).setEnabled(false);
                }else if (e.getSource() == dQtyL.get(i)) {
                    dtmOrder.addRow(new Object[]{drinks.get(i).getName(), drinks.get(i).packing().packName(), drinks.get(i).getSize().getSize(),
                            quantity, drinks.get(i).getPrice() * quantity, dQtyL.get(i)});
                    dDelete.get(i).setEnabled(false);
                    dEdit.get(i).setEnabled(false);
                }
            }
            boolean gaadaDiTable =true;
            for (int row=0 ; row<tableOrder.getRowCount() ; row++){
                if (drinks.get(i).getName() == dtmOrder.getValueAt(row,0)){
                    gaadaDiTable = false;
                }
            }
            if (gaadaDiTable){
                dDelete.get(i).setEnabled(true);
                dEdit.get(i).setEnabled(true);
            }
        }
        for (int i=0 ; i<starters.size() ; i++){
            boolean add = true;
            for (int row=0 ; row<tableOrder.getRowCount() ; row++){
                if (e.getSource() == dtmOrder.getValueAt(row, 5)){
                    add = false;
                }
            }
            if (quantity == 1 && add){
                if (e.getSource() == sQty.get(i)) {
                    sEdit.get(i).setEnabled(false);
                    sDelete.get(i).setEnabled(false);
                    dtmOrder.addRow(new Object[]{starters.get(i).getName(), starters.get(i).packing().packName(),"-",
                            quantity, starters.get(i).getPrice() * quantity, sQty.get(i)});
                    sDelete.get(i).setEnabled(false);
                    sEdit.get(i).setEnabled(false);
                }
            }
            boolean gaadaDiTable =true;
            for (int row=0 ; row<tableOrder.getRowCount() ; row++){
                if (starters.get(i).getName() == dtmOrder.getValueAt(row,0)){
                    gaadaDiTable = false;
                }
            }
            if (gaadaDiTable){
                sDelete.get(i).setEnabled(true);
                sEdit.get(i).setEnabled(true);
            }
        }

        double ttl=0;
        rows = tableOrder.getRowCount();

        for (int row=0 ; row<rows ; row++){
            ttl+=(double)dtmOrder.getValueAt(row,4);
        }total.setText(String.valueOf(ttl));

    }
}
