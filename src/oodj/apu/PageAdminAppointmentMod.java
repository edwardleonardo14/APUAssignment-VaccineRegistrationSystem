package oodj.apu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class PageAdminAppointmentMod extends JFrame implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            setVisible(false);
            dispose();
            PageAdminAppointment adminAppointment = new PageAdminAppointment();
            adminAppointment.setVisible(true);
        }
        else if(e.getSource() == confirm){
            if(verificationField.getText().equals(Main.currentAdmin.getPassword())){
                if(DataIO.checkCitizen(ownerField.getText()) == null){
                    JOptionPane.showMessageDialog(confirm,"Username not Found!");
                    ownerField.setText("");
                }
                else{
                    try{
                        Main.currentCitizen.getAppointment().remove(Main.currentAppointment);
                        Main.currentAppointment.setStatus(Boolean.parseBoolean(String.valueOf(statusC.getSelectedItem())));
                        Main.currentAppointment.setLocation((Location) locationC.getSelectedItem());
                        Main.currentAppointment.setDate(LocalDate.parse(dateField.getText()));
                        Main.currentAppointment.setTime(DataIO.getTimeValue(String.valueOf(timeC.getSelectedItem())));
                        Main.currentAppointment.setVaccineName(String.valueOf(vaccineC.getSelectedItem()));
                        Main.currentAppointment.setOwner(DataIO.checkCitizen(ownerField.getText()));
                        Main.currentCitizen = DataIO.checkCitizen(Main.currentAppointment.getOwner().getUsername());
                        Main.currentCitizen.getAppointment().add(Main.currentAppointment);
                        DataIO.write();
                        JOptionPane.showMessageDialog(confirm,"Modify Successful!");
                        setVisible(false);
                        dispose();
                        PageAdminAppointmentHub adminAppointmentHub = new PageAdminAppointmentHub();
                        adminAppointmentHub.setVisible(true);
                    }
                    catch(Exception ee){
                        JOptionPane.showMessageDialog(confirm,"Error in Date, please use the correct format! (yyyy-mm-dd)");
                        dateField.setText("");
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(confirm,"Wrong Verification Password!");
                verificationField.setText("");
            }
        }
    }
    private Label vaccine, location, date, time, appointment,status, verification, owner;
    private TextField dateField, verificationField, ownerField;
    private JComboBox vaccineC, locationC, timeC, statusC;
    private Button confirm,back;
    private Panel mainPanel, buttonPanel, v, l, d, t,s, ve, o;
    private Panel titlePanel;
    private Label title, subtitle;
    private String statusArray[]={"true","false"};

    public PageAdminAppointmentMod(){
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
        mainPanel.setLayout(new GridLayout(16,1));
        buttonPanel = new Panel();
        buttonPanel.setPreferredSize(new Dimension(800,50));
        this.add(buttonPanel,BorderLayout.SOUTH);


        confirm = new Button("Confirm");
        confirm.addActionListener(this);
        back = new Button("Back");
        back.addActionListener(this);

        appointment = new Label("Modify Appointment",Label.CENTER);
        appointment.setFont(new Font("Helvetica",Font.BOLD,20));
        appointment.setForeground(Color.WHITE);
        vaccine = new Label("Vaccine",Label.CENTER);
        vaccine.setForeground(Color.WHITE);
        location = new Label("Location",Label.CENTER);
        location.setForeground(Color.WHITE);
        date = new Label("Date (yyyy-mm-dd)",Label.CENTER);
        date.setForeground(Color.WHITE);
        time = new Label("Time",Label.CENTER);
        time.setForeground(Color.WHITE);
        status = new Label("Status",Label.CENTER);
        status.setForeground(Color.WHITE);
        verification = new Label("Admin Password (Verification)", Label.CENTER);
        verification.setForeground(Color.WHITE);
        owner = new Label("Owner", Label.CENTER);
        owner.setForeground(Color.WHITE);

        dateField = new TextField(30);
        dateField.setText(String.valueOf(Main.currentAppointment.getDate()));
        verificationField = new TextField(30);
        verificationField.setEchoChar('*');
        ownerField = new TextField(30);
        ownerField.setText(Main.currentAppointment.getOwner().getUsername());

        locationC = new JComboBox(DataIO.allLocation);
        vaccineC = new JComboBox(DataIO.allVaccineName);
        timeC = new JComboBox(DataIO.allTime);
        statusC = new JComboBox(statusArray);

        for(int i =0; i < DataIO.allStatus.length; i++){
            if(Main.currentAppointment.getLocation().equals(DataIO.allLocation[i])){
                locationC.setSelectedIndex(i);
                break;
            }
        }

        for(int i =0; i < DataIO.allVaccineName.length; i++){
            if(Main.currentAppointment.getVaccineName().equals(DataIO.allVaccineName[i])){
                vaccineC.setSelectedIndex(i);
                break;
            }
        }
        for(int i =0; i < DataIO.allTime.length; i++){
            if(Main.currentAppointment.getTimeString().equals(DataIO.allTime[i])){
                timeC.setSelectedIndex(i);
                break;
            }
        }

//        for(int i = 0;i<Main.currentCitizen.getAppointment().size();i++){
//            if(!(Main.currentCitizen.getAppointment().get(i).isStatus())){
//                vaccineField.setText(Main.currentCitizen.getAppointment().get(i).getVaccineName());
//                locationField.setText(String.valueOf(Main.currentCitizen.getAppointment().get(i).getLocation()));
//                dateField.setText(String.valueOf(Main.currentCitizen.getAppointment().get(i).getDate()));
//                timeField.setText(Main.currentCitizen.getAppointment().get(i).getTimeString());
//                register.setEnabled(false);
//                reject.setEnabled(true);
//                break;
//            }
//
//        }

        v = new Panel();
        v.add(vaccineC);
        l = new Panel();
        l.add(locationC);
        d = new Panel();
        d.add(dateField);
        t = new Panel();
        t.add(timeC);
        s = new Panel();
        s.add(statusC);
        o = new Panel();
        o.add(ownerField);
        ve = new Panel();
        ve.add(verificationField);

        mainPanel.add(appointment);
        mainPanel.add(location);
        mainPanel.add(l);
        mainPanel.add(date);
        mainPanel.add(d);
        mainPanel.add(time);
        mainPanel.add(t);
        mainPanel.add(vaccine);
        mainPanel.add(v);
        mainPanel.add(status);
        mainPanel.add(s);
        mainPanel.add(owner);
        mainPanel.add(o);
        mainPanel.add(verification);
        mainPanel.add(ve);


        //buttonPanel.add(register);
        buttonPanel.add(confirm);
        buttonPanel.add(back);
    }
}