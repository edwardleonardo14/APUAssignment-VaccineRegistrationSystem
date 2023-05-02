package oodj.apu;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageAdminProfileHub extends JFrame implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == register){
            setVisible(false);
            dispose();
            PageAdminRegister adminRegister = new PageAdminRegister();
            adminRegister.setVisible(true);
        }
        else if(e.getSource() == back){
            setVisible(false);
            dispose();
            PageAdminHub adminHub = new PageAdminHub();
            adminHub.setVisible(true);
        }

        else if(e.getSource() == search){
            String a = String.valueOf(JOptionPane.showInputDialog("Admin's Username : "));
            Admin found = DataIO.checkAdmin(a);
            if(found != null){
                DefaultTableModel tempModel = (DefaultTableModel) table.getModel();
                tempModel.setRowCount(0);
                for(Admin ad : DataIO.allAdmin){
                    if(ad.equals(found)){
                        String censor = "";
                        for (int j = 0; j<found.getPassword().length(); j++){
                            censor += "*";
                        }
                        tempModel.addRow(new Object[]{found.getUsername(),censor});
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(search,"No Admin Found!");
            }
        }
    }
    private Panel mainPanel, buttonPanel, p,t;
    private Label profile;
    private DefaultTableModel tableModel;
    private JTable table;
    private Button back, register, search;
    public static Admin viewAdmin = null;

    private Panel titlePanel;
    private Label title, subtitle;

    public PageAdminProfileHub(){
        this.setTitle("COVID-19 VACCINATION SYSTEM");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10,6));
        this.setLocation(300,300);
        this.setSize(1000,400);
        this.getContentPane().setBackground(Color.DARK_GRAY);

        titlePanel = new Panel();
        titlePanel.setBackground(new Color(0xFF781F));
        titlePanel.setPreferredSize(new Dimension(1000,80));
        titlePanel.setLayout(new BorderLayout());
        this.add(titlePanel,BorderLayout.NORTH);

        title = new Label("Malaysia Vaccination Center Program",Label.CENTER);
        title.setFont(new Font("Helvetica",Font.BOLD,35));
        titlePanel.add(title,BorderLayout.NORTH);

        subtitle = new Label("Healthy Me, Healthy You",Label.CENTER);
        subtitle.setFont(new Font("Helvetica",Font.BOLD,20));
        titlePanel.add(subtitle,BorderLayout.CENTER);

        profile = new Label("Admin's Profile", Label.CENTER);
        profile.setFont(new Font("Helvetica",Font.BOLD,20));
        profile.setForeground(Color.WHITE);

        mainPanel = new Panel();
        mainPanel.setLayout(new GridLayout(1,1));
        p = new Panel();
        t = new Panel();

//        t.add(profile);
//        mainPanel.add(t);

        int size = DataIO.allAdmin.size();
        String[][] data = new String[size][2];
        for(int i = 0; i<size; i++) {
            Admin a = DataIO.allAdmin.get(i);
            data[i][0] = a.getUsername();
            String censor = "";
            for (int j = 0; j < a.getPassword().length(); j++) {
                censor += "*";
            }
            data[i][1] = censor;
        }
        String[] column = {"Username", "Password (Censored)"};
        tableModel = new DefaultTableModel(data, column);
        table = new JTable(tableModel);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){ //to only invoked the selection only once
                    String x = (table.getValueAt(table.getSelectedRow(),0).toString());
                    viewAdmin = DataIO.checkAdmin(x);
                    setVisible(false);
                    dispose();
                    PageAdminProfileView adminProfileView = new PageAdminProfileView();
                    adminProfileView.setVisible(true);
                }
            }
        });

        JScrollPane sp = new JScrollPane(table);
        mainPanel.add(sp);
        this.add(mainPanel,BorderLayout.CENTER);
        buttonPanel = new Panel();
        buttonPanel.setPreferredSize(new Dimension(800,50));
        this.add(buttonPanel,BorderLayout.SOUTH);
        register = new Button("Register");
        back = new Button("Back");
        search = new Button("Search");
        register.addActionListener(this);
        buttonPanel.add(register);
        search.addActionListener(this);
        buttonPanel.add(search);
        back.addActionListener(this);
        buttonPanel.add(back);
    }
}
