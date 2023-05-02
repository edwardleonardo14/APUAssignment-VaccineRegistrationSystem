package oodj.apu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageAdminVaccineView extends JFrame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            Main.currentVaccine = null;
            setVisible(false);
            dispose();
            PageAdminVaccineHub adminSupplyHub = new PageAdminVaccineHub();
            adminSupplyHub.setVisible(true);
        }
        else if (e.getSource() == modify){
            setVisible(false);
            PageAdminVaccineMod adminVaccineSupplyMod = new PageAdminVaccineMod();
            adminVaccineSupplyMod.setVisible(true);
        }
        else if(e.getSource() == delete){
            String a = JOptionPane.showInputDialog("Enter Verification Password to delete the Data!");
            if(Main.currentAdmin.getPassword().equals(a)){
                DataIO.allVaccine.remove(Main.currentVaccine);
                DataIO.write();
                JOptionPane.showMessageDialog(delete,"Data Deleted! Restart program to see the effect!");
                Main.currentVaccine = null;
                setVisible(false);
                dispose();
                PageAdminVaccineHub adminSupplyHub = new PageAdminVaccineHub();
                adminSupplyHub.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(delete,"Wrong Verification Password!");
            }
        }
    }

    private TextField vaccineNameField, doseField, daysField, amountField;
    private Label vaccineName, dose, days, amount;
    private Panel mainPanel, buttonPanel, u, p, f, l;
    private Button modify, back, delete;
    private Panel titlePanel;
    private Label title, subtitle;


    public PageAdminVaccineView(){
        this.setTitle("COVID-19 VACCINATION SYSTEM");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10,6));
        this.setLocation(300,300);
        this.setSize(800,600);
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

        mainPanel = new Panel();
        this.add(mainPanel,BorderLayout.CENTER);
        mainPanel.setLayout(new GridLayout(8,1));
        buttonPanel = new Panel();
        buttonPanel.setPreferredSize(new Dimension(800,50));
        this.add(buttonPanel,BorderLayout.SOUTH);

        modify = new Button("Modify");
        modify.addActionListener(this);
        back = new Button("Back");
        back.addActionListener(this);
        delete = new Button("Delete Vaccine");
        delete.addActionListener(this);

        vaccineName = new Label("Vaccine Name",Label.CENTER);
        vaccineName.setForeground(Color.WHITE);
        dose = new Label("Dose Required",Label.CENTER);
        dose.setForeground(Color.WHITE);
        days = new Label("Days Required",Label.CENTER);
        days.setForeground(Color.WHITE);
        amount = new Label("Supply Amount",Label.CENTER);
        amount.setForeground(Color.WHITE);


        vaccineNameField = new TextField(30);
        vaccineNameField.setText(Main.currentVaccine.getName());
        vaccineNameField.setEditable(false);
        doseField = new TextField(30);
        doseField.setText(String.valueOf(Main.currentVaccine.getDoseRequired()));
        doseField.setEditable(false);
        daysField = new TextField(30);
        daysField.setText(String.valueOf(Main.currentVaccine.getDayRequired()));
        daysField.setEditable(false);
        amountField = new TextField(30);
        amountField.setText(String.valueOf(Main.currentVaccine.getAmount()));
        amountField.setEditable(false);


        u = new Panel();
        u.add(vaccineNameField);
        p = new Panel();
        p.add(doseField);
        f = new Panel();
        f.add(daysField);
        l = new Panel();
        l.add(amountField);


        //TAMBAHAN

        mainPanel.add(vaccineName);
        mainPanel.add(u);
        mainPanel.add(dose);
        mainPanel.add(p);
        mainPanel.add(days);
        mainPanel.add(f);
        mainPanel.add(amount);
        mainPanel.add(l);

        buttonPanel.add(modify);
        buttonPanel.add(delete);
        buttonPanel.add(back);

    }
}