package oodj.apu;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class PageAdminAppointmentHub extends JFrame implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            setVisible(false);
            dispose();
            PageAdminHub adminHub = new PageAdminHub();
            adminHub.setVisible(true);
        }
        else if(e.getSource() == register){
            setVisible(false);
            dispose();
            PageAdminAppointmentRegister adminAppointmentRegister = new PageAdminAppointmentRegister();
            adminAppointmentRegister.setVisible(true);
        }
        else if(e.getSource() == search){
            String a = String.valueOf(JOptionPane.showInputDialog("Appointment Owner's Username : "));
            Citizen found = DataIO.checkCitizen(a);
            if(found != null) {
                DefaultTableModel tempModel = (DefaultTableModel) table.getModel();
                tempModel.setRowCount(0);
                for(Appointment ap : DataIO.allAppointment){
                    if(ap.getOwner().equals(found)){
                        tempModel.addRow(new Object[]{ap.getOwner().getUsername(),ap.getLocation(),ap.getDate(),
                        ap.getTimeString(),ap.getVaccineName(),ap.isStatus()});
                    }
                }
            }
            else {
                JOptionPane.showMessageDialog(search, "No Appointment Found!");
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

    public PageAdminAppointmentHub(){
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

        int size = DataIO.allAppointment.size();
        String[][] data = new String[size][6];
        for(int i = 0; i<size; i++){
            Appointment a = DataIO.allAppointment.get(i);
            data[i][0] = a.getOwner().getUsername();
            data[i][1] = String.valueOf(a.getLocation());
            data[i][2] = String.valueOf(a.getDate());
            data[i][3] = a.getTimeString();
            data[i][4] = a.getVaccineName();
            data[i][5] = String.valueOf(a.isStatus());
        }
        String[] column = {"Owner Username", "Location", "Date", "Time", "Vaccine", "Status"};
        tableModel = new DefaultTableModel(data, column);
        table = new JTable(tableModel);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){ //to only invoked the selection only once
                    String x = (table.getValueAt(table.getSelectedRow(),0).toString());
                    String y = table.getValueAt(table.getSelectedRow(),2).toString();
                    String a = table.getValueAt(table.getSelectedRow(),4).toString();
                    LocalDate z = LocalDate.parse(y);
                    Main.currentVaccine = DataIO.checkVaccine(a);
                    Main.currentAppointment = DataIO.checkAppointment(x,z);
                    Main.currentCitizen = DataIO.checkCitizen(x);
                    setVisible(false);
                    dispose();
                    PageAdminAppointment adminCitizenAppointment = new PageAdminAppointment();
                    adminCitizenAppointment.setVisible(true);
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
