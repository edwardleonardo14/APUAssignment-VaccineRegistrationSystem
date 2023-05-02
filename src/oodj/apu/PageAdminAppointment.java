package oodj.apu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class PageAdminAppointment extends JFrame implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            Main.currentAppointment = null;
            Main.currentCitizen = null;
            Main.currentVaccine = null;
            setVisible(false);
            dispose();
            PageAdminAppointmentHub adminAppointmentHub = new PageAdminAppointmentHub();
            adminAppointmentHub.setVisible(true);
        }
        else if(e.getSource() == reject){
            if (Main.currentCitizen.getVaccinationStatus().getDose() == 0){
                Main.currentCitizen.getAppointment().remove(Main.currentAppointment);
                DataIO.allAppointment.remove(Main.currentAppointment);
                DataIO.write();
                vaccineField.setText("");
                locationField.setText("");
                dateField.setText("");
                timeField.setText("");
                statusField.setText("");
                JOptionPane.showMessageDialog(reject,"Appointment Rejected!");
                setVisible(false);
                dispose();
                Main.currentCitizen = null;
                Main.currentAppointment = null;
                Main.currentVaccine = null;
                PageAdminAppointmentHub adminAppointmentHub = new PageAdminAppointmentHub();
                adminAppointmentHub.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(reject,"This is not the first Appointment!");
            }
        }
        else if(e.getSource() == approve){
            Main.currentAppointment.setStatus(true);
            System.out.println("approve");
            Main.currentCitizen.getVaccinationStatus().setVaccineName(Main.currentAppointment.getVaccineName());
            Main.currentCitizen.getVaccinationStatus().setStatus(Status.PartialVaccinated);
            Main.currentCitizen.getVaccinationStatus().setDose(Main.currentCitizen.getVaccinationStatus().getDose() + 1);
            if(Main.currentCitizen.getVaccinationStatus().getDose() == 1){
                if(!(Main.currentAppointment.getVaccineName().equals("Johnson&Johnson"))){
                    Main.currentVaccine.setAmount(Main.currentVaccine.getAmount() - 2);
                    Main.currentCitizen.getVaccinationStatus().setDateCompletionDose1(Main.currentAppointment.getDate());
                    LocalDate temp = Main.currentAppointment.getDate().plusDays(28);
                    String a = Main.currentAppointment.getVaccineName();
                    Location b = Main.currentAppointment.getLocation();
                    Time c = Main.currentAppointment.getTime();
                    Appointment d = new Appointment(b,temp,a,c,false,Main.currentCitizen);
                    DataIO.allAppointment.add(d);
                    Main.currentCitizen.getAppointment().add(d);
                    JOptionPane.showMessageDialog(approve,"The Vaccination has been Approved! Second Dose has been registered 28 Days from now!");
                }
                else{
                    Main.currentVaccine.setAmount(Main.currentVaccine.getAmount() - 1);
                    Main.currentCitizen.getVaccinationStatus().setDateCompletionDose1(Main.currentAppointment.getDate());
                    JOptionPane.showMessageDialog(approve,"The Vaccination has been Approved! Wait 28 Days to be Fully Vaccinated!");
                }
            }
            else{
                Main.currentCitizen.getVaccinationStatus().setDateCompletionDose2(Main.currentAppointment.getDate());
                JOptionPane.showMessageDialog(approve,"The Vaccination has been Approved! Wait 14 Days to be Fully Vaccinated!");
            }
            DataIO.write();
            Main.currentAppointment = null;
            Main.currentCitizen = null;
            Main.currentVaccine = null;
            setVisible(false);
            dispose();
            PageAdminAppointmentHub adminAppointmentHub = new PageAdminAppointmentHub();
            adminAppointmentHub.setVisible(true);
        }
        else if(e.getSource() == modify){
            setVisible(false);
            dispose();
            PageAdminAppointmentMod adminAppointmentMod = new PageAdminAppointmentMod();
            adminAppointmentMod.setVisible(true);
        }
    }
    private Label vaccine, location, date, time, appointment,status;
    private TextField vaccineField, locationField, dateField, timeField,statusField;
    private Button reject, back, modify, approve;
    private Panel mainPanel, buttonPanel, v, l, d, t,s;
    private Panel titlePanel;
    private Label title, subtitle;

    public PageAdminAppointment(){
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
        mainPanel.setLayout(new GridLayout(12,1));
        buttonPanel = new Panel();
        buttonPanel.setPreferredSize(new Dimension(800,50));
        this.add(buttonPanel,BorderLayout.SOUTH);

        reject = new Button("Reject");
        reject.addActionListener(this);
        back = new Button("Back");
        back.addActionListener(this);
        modify = new Button("Modify");
        modify.addActionListener(this);
        approve = new Button("Approve");
        approve.addActionListener(this);

        modify.setEnabled(false);

        appointment = new Label("Current Appointment",Label.CENTER);
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

        vaccineField = new TextField(30);
        vaccineField.setEditable(false);
        locationField = new TextField(30);
        locationField.setEditable(false);
        dateField = new TextField(30);
        dateField.setEditable(false);
        timeField = new TextField(30);
        timeField.setEditable(false);
        statusField = new TextField(30);
        statusField.setEditable(false);

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

        vaccineField.setText(Main.currentAppointment.getVaccineName());
        locationField.setText(String.valueOf(Main.currentAppointment.getLocation()));
        dateField.setText(String.valueOf(Main.currentAppointment.getDate()));
        timeField.setText(Main.currentAppointment.getTimeString());
        statusField.setText(String.valueOf(Main.currentAppointment.isStatus()));
        if(Main.currentAppointment.isStatus()){
            approve.setEnabled(false);
            reject.setEnabled(false);
            modify.setEnabled(true);
        }

        v = new Panel();
        v.add(vaccineField);
        l = new Panel();
        l.add(locationField);
        d = new Panel();
        d.add(dateField);
        t = new Panel();
        t.add(timeField);
        s = new Panel();
        s.add(statusField);

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


        //buttonPanel.add(register);
        buttonPanel.add(approve);
        buttonPanel.add(reject);
        buttonPanel.add(modify);
        buttonPanel.add(back);
    }
}