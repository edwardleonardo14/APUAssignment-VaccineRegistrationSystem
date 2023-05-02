package oodj.apu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageAdminVaccineMod extends JFrame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            setVisible(false);
            PageAdminVaccineView adminVaccineSupplyView = new PageAdminVaccineView();
            adminVaccineSupplyView.setVisible(true);
        }
        else if (e.getSource() == confirm){
            if(amountField.getText().isEmpty()){
                JOptionPane.showMessageDialog(confirm,"Amount cannot be empty!");
            }
            else if(!(DataIO.isNumeric(daysField.getText()))) {
                JOptionPane.showMessageDialog(confirm, "Days Required must be Numeric!");
                daysField.setText("");
            }
            else if(!(DataIO.isNumeric(doseField.getText()))) {
                JOptionPane.showMessageDialog(confirm, "Dose Required must be Numeric!");
                doseField.setText("");
            }
            else if(!(DataIO.isNumeric(amountField.getText()))) {
                JOptionPane.showMessageDialog(confirm, "Amount must be Numeric!");
                amountField.setText("");
            }
            else if(Integer.parseInt(amountField.getText()) < 0){
                JOptionPane.showMessageDialog(confirm,"Amount cannot be negative!");
                amountField.setText("");
            }
            else if(Integer.parseInt(doseField.getText()) < 0){
                JOptionPane.showMessageDialog(confirm,"Dose Required cannot be negative!");
                doseField.setText("");
            }
            else if(Integer.parseInt(daysField.getText()) < 0){
                JOptionPane.showMessageDialog(confirm,"Days Required cannot be negative!");
                daysField.setText("");
            }
            else{
                if(verificationField.getText().equals(Main.currentAdmin.getPassword())) {
                    Main.currentVaccine.setAmount(Integer.parseInt(amountField.getText()));
                    Main.currentVaccine.setDayRequired(Integer.parseInt(daysField.getText()));
                    Main.currentVaccine.setDoseRequired(Integer.parseInt(doseField.getText()));
                    DataIO.write();
                    JOptionPane.showMessageDialog(confirm, "Modify Successful!");
                    setVisible(false);
                    dispose();
                    Main.currentVaccine = null;
                    PageAdminVaccineHub adminSupplyHub = new PageAdminVaccineHub();
                    adminSupplyHub.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(confirm,"Wrong Verification Password!");
                    verificationField.setText("");
                }
            }
        }
    }

    private TextField vaccineNameField, doseField, daysField, amountField, verificationField;
    private Label vaccineName, dose, days, amount, verification;
    private Panel mainPanel, buttonPanel, u, p, f, l, v;
    private Button confirm, back;
    private Panel titlePanel;
    private Label title, subtitle;


    public PageAdminVaccineMod(){
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
        mainPanel.setLayout(new GridLayout(10,1));
        buttonPanel = new Panel();
        buttonPanel.setPreferredSize(new Dimension(800,50));
        this.add(buttonPanel,BorderLayout.SOUTH);

        confirm = new Button("Confirm");
        confirm.addActionListener(this);
        back = new Button("Back");
        back.addActionListener(this);

        vaccineName = new Label("Vaccine Name",Label.CENTER);
        vaccineName.setForeground(Color.WHITE);
        dose = new Label("Dose Required",Label.CENTER);
        dose.setForeground(Color.WHITE);
        days = new Label("Days Required",Label.CENTER);
        days.setForeground(Color.WHITE);
        amount = new Label("Supply Amount",Label.CENTER);
        amount.setForeground(Color.WHITE);
        verification = new Label("Admin Password (Verification)", Label.CENTER);
        verification.setForeground(Color.WHITE);

        vaccineNameField = new TextField(30);
        vaccineNameField.setText(Main.currentVaccine.getName());
        vaccineNameField.setEditable(false);
        doseField = new TextField(30);
        doseField.setText(String.valueOf(Main.currentVaccine.getDoseRequired()));
        daysField = new TextField(30);
        daysField.setText(String.valueOf(Main.currentVaccine.getDayRequired()));
        amountField = new TextField(30);
        amountField.setText(String.valueOf(Main.currentVaccine.getAmount()));
        verificationField = new TextField(30);
        verificationField.setEchoChar('*');

        u = new Panel();
        u.add(vaccineNameField);
        p = new Panel();
        p.add(doseField);
        f = new Panel();
        f.add(daysField);
        l = new Panel();
        l.add(amountField);
        v = new Panel();
        v.add(verificationField);


        //TAMBAHAN

        mainPanel.add(vaccineName);
        mainPanel.add(u);
        mainPanel.add(dose);
        mainPanel.add(p);
        mainPanel.add(days);
        mainPanel.add(f);
        mainPanel.add(amount);
        mainPanel.add(l);
        mainPanel.add(verification);
        mainPanel.add(v);

        buttonPanel.add(confirm);
        buttonPanel.add(back);

    }
}