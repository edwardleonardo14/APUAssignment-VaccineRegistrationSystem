package oodj.apu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageAdminVaccineRegister extends JFrame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            setVisible(false);
            dispose();
            PageAdminVaccineHub adminSupplyHub = new PageAdminVaccineHub();
            adminSupplyHub.setVisible(true);
        } else if (e.getSource() == register) {
            if(vaccineNameField.getText().isEmpty()){
                JOptionPane.showMessageDialog(register,"Vaccine Name cannot be empty!");
            }
            else if(doseField.getText().isEmpty()){
                JOptionPane.showMessageDialog(register,"Dose Required cannot be empty!");
            }
            else if(daysField.getText().isEmpty()){
                JOptionPane.showMessageDialog(register,"Days Required cannot be empty!");
            }
            else if(amountField.getText().isEmpty()){
                JOptionPane.showMessageDialog(register,"Amount cannot be empty!");
            }
            else if(!(DataIO.isNumeric(daysField.getText()))) {
                JOptionPane.showMessageDialog(register, "Days Required must be Numeric!");
                daysField.setText("");
            }
            else if(!(DataIO.isNumeric(doseField.getText()))) {
                JOptionPane.showMessageDialog(register, "Dose Required must be Numeric!");
                doseField.setText("");
            }
            else if(!(DataIO.isNumeric(amountField.getText()))) {
                JOptionPane.showMessageDialog(register, "Amount must be Numeric!");
                amountField.setText("");
            }
            else if(Integer.parseInt(amountField.getText()) < 0){
                JOptionPane.showMessageDialog(register,"Amount cannot be negative!");
                amountField.setText("");
            }
            else if(Integer.parseInt(doseField.getText()) < 0){
                JOptionPane.showMessageDialog(register,"Dose Required cannot be negative!");
                doseField.setText("");
            }
            else if(Integer.parseInt(daysField.getText()) < 0){
                JOptionPane.showMessageDialog(register,"Days Required cannot be negative!");
                daysField.setText("");
            }
            else{
                if(verificationField.getText().equals(Main.currentAdmin.getPassword())){
                    String a = vaccineNameField.getText();
                    int b = Integer.parseInt(doseField.getText());
                    int c = Integer.parseInt(daysField.getText());
                    int d = Integer.parseInt(amountField.getText());
                    Vaccine f = new Vaccine(a,b,c,d);
                    DataIO.allVaccine.add(f);
//                    for(int i = 0; i<DataIO.allVaccine.size();i++){
//                        System.out.println(DataIO.allVaccine.get(i).getName());
//                    }
                    DataIO.write();
                    JOptionPane.showMessageDialog(register,"Register Successful! restart the program to see the effect!");
                    setVisible(false);
                    dispose();
                    PageAdminVaccineHub adminSupplyHub = new PageAdminVaccineHub();
                    adminSupplyHub.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(register,"Wrong Verification Password!");
                    verificationField.setText("");
                }
            }
        }
    }


    private TextField vaccineNameField, doseField, daysField, amountField, verificationField;
    private Label vaccineName, dose, days, amount, verification;
    private Panel mainPanel, buttonPanel, u, p, f, l, v;
    private Button register, back;
    private Panel titlePanel;
    private Label title, subtitle;


    public PageAdminVaccineRegister(){
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

        register = new Button("Register");
        register.addActionListener(this);
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
        verification = new Label("Admin Password (Verification)",Label.CENTER);
        verification.setForeground(Color.WHITE);


        vaccineNameField = new TextField(30);
        doseField = new TextField(30);
        daysField = new TextField(30);
        amountField = new TextField(30);
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

        buttonPanel.add(register);
        buttonPanel.add(back);

    }
}