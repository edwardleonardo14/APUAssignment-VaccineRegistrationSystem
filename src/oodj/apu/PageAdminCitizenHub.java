package oodj.apu;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageAdminCitizenHub extends JFrame implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == register){
            setVisible(false);
            dispose();
            PageAdminCitizenRegister adminCitizenRegister = new PageAdminCitizenRegister();
            adminCitizenRegister.setVisible(true);
        }
        else if(e.getSource() == back){
            setVisible(false);
            dispose();
            PageAdminHub adminHub = new PageAdminHub();
            adminHub.setVisible(true);
        }

        else if(e.getSource() == search){
            String a = String.valueOf(JOptionPane.showInputDialog("Citizen's Username : "));
            Citizen found = DataIO.checkCitizen(a);
            if(found != null){
                DefaultTableModel tempModel = (DefaultTableModel) table.getModel();
                tempModel.setRowCount(0);
                for(Citizen c : DataIO.allCitizen){
                    if(c.equals(found)){
                        String censor = "";
                        for (int j = 0; j<found.getPassword().length(); j++){
                            censor += "*";
                        }
                        tempModel.addRow(new Object[]{found.getUsername(),censor,found.getFirstName(),
                        found.getLastName(),found.getAge(),found.getEmail(),found.getPhoneNumber(),found.getGender()});
                        }
                    }
                }
            else{
                JOptionPane.showMessageDialog(search,"No Citizen Found!");
            }
        }
    }
    private Panel mainPanel, buttonPanel, p,t;
    private Label profile;
    private DefaultTableModel tableModel;
    private JTable table;
    private Button back, register, search;

    private Panel titlePanel;
    private Label title, subtitle;

    public PageAdminCitizenHub(){
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

        profile = new Label("Citizen's Profile", Label.CENTER);
        profile.setFont(new Font("Helvetica",Font.BOLD,20));
        profile.setForeground(Color.WHITE);

        mainPanel = new Panel();
        mainPanel.setLayout(new GridLayout(1,1));
        p = new Panel();
        t = new Panel();

//        t.add(profile);
//        mainPanel.add(t);

        int size = DataIO.allCitizen.size();
        String[][] data = new String[size][8];
        for(int i = 0; i<size; i++){
            Citizen a = DataIO.allCitizen.get(i);
            data[i][0] = a.getUsername();
            String censor = "";
            for (int j = 0; j<a.getPassword().length(); j++){
                censor += "*";
            }
            data[i][1] = censor;
            data[i][2] = a.getFirstName();
            data[i][3] = a.getLastName();
            data[i][4] = String.valueOf(a.getAge());
            data[i][5] = a.getEmail();
            data[i][6] = a.getPhoneNumber();
            data[i][7] = a.getGender();
        }
        String[] column = {"Username", "Password (Censored)", "First Name", "Last Name", "Age", "Email", "Phone Number", "Gender"};
        tableModel = new DefaultTableModel(data, column);
        table = new JTable(tableModel);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){ //to only invoked the selection only once
                    String x = (table.getValueAt(table.getSelectedRow(),0).toString());
                    Main.currentCitizen = DataIO.checkCitizen(x);
                    setVisible(false);
                    dispose();
                    PageAdminCitizenProfileView adminCitizenProfileView = new PageAdminCitizenProfileView();
                    adminCitizenProfileView.setVisible(true);
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
