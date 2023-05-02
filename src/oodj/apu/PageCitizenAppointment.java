package oodj.apu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageCitizenAppointment extends JFrame implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            setVisible(false);
            PageCitizenHub citizenHub = new PageCitizenHub();
            citizenHub.setVisible(true);
        }
        else if(e.getSource() == cancel){
            Appointment temp = null;
            for(int i = 0;i<Main.currentCitizen.getAppointment().size();i++) {
                if (!(Main.currentCitizen.getAppointment().get(i).isStatus())) {
                    temp = Main.currentCitizen.getAppointment().get(i);
                    Main.currentCitizen.getAppointment().remove(temp);
                    DataIO.allAppointment.remove(temp);
                    DataIO.write();
                    vaccineField.setText("");
                    locationField.setText("");
                    dateField.setText("");
                    timeField.setText("");
                    JOptionPane.showMessageDialog(cancel,"Appointment Cancelled!");
                    setVisible(false);
                    PageCitizenHub citizenHub = new PageCitizenHub();
                    citizenHub.setVisible(true);
                    break;
                }
            }
        }
        else if(e.getSource() == register){
            if(Main.currentCitizen.getVaccinationStatus().getVaccineName().equals("Johnson&Johnson") && Main.currentCitizen.getVaccinationStatus().getDose() < 1){
                setVisible(false);
                PageCitizenAppointmentRegister citizenAppointmentRegister = new PageCitizenAppointmentRegister();
                citizenAppointmentRegister.setVisible(true);
            }
            else if(!(Main.currentCitizen.getVaccinationStatus().getVaccineName().equals("Johnson&Johnson")) && Main.currentCitizen.getVaccinationStatus().getDose() < 2){
                setVisible(false);
                PageCitizenAppointmentRegister citizenAppointmentRegister = new PageCitizenAppointmentRegister();
                citizenAppointmentRegister.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(cancel,"You already got Enough Dose!");
            }
        }
    }
    private Label vaccine, location, date, time, appointment;
    private TextField vaccineField, locationField, dateField, timeField;
    private Button register, cancel, back;
    private Panel mainPanel, buttonPanel, v, l, d, t;
    private Panel titlePanel;
    private Label title, subtitle;

    public PageCitizenAppointment(){
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
        cancel = new Button("Cancel Appointment");
        cancel.addActionListener(this);
        cancel.setEnabled(false);
        back = new Button("Back");
        back.addActionListener(this);

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

        vaccineField = new TextField(30);
        vaccineField.setEditable(false);
        locationField = new TextField(30);
        locationField.setEditable(false);
        dateField = new TextField(30);
        dateField.setEditable(false);
        timeField = new TextField(30);
        timeField.setEditable(false);



        for(int i = 0;i<Main.currentCitizen.getAppointment().size();i++){
            if(!(Main.currentCitizen.getAppointment().get(i).isStatus())){
                vaccineField.setText(Main.currentCitizen.getAppointment().get(i).getVaccineName());
                locationField.setText(String.valueOf(Main.currentCitizen.getAppointment().get(i).getLocation()));
                dateField.setText(String.valueOf(Main.currentCitizen.getAppointment().get(i).getDate()));
                timeField.setText(Main.currentCitizen.getAppointment().get(i).getTimeString());
                register.setEnabled(false);
                cancel.setEnabled(true);
                if(!(Main.currentCitizen.getVaccinationStatus().getStatus().equals(Status.NotVaccinated))){
                    cancel.setEnabled(false);
                }
                break;
            }

        }

        v = new Panel();
        v.add(vaccineField);
        l = new Panel();
        l.add(locationField);
        d = new Panel();
        d.add(dateField);
        t = new Panel();
        t.add(timeField);

        mainPanel.add(appointment);
        mainPanel.add(location);
        mainPanel.add(l);
        mainPanel.add(date);
        mainPanel.add(d);
        mainPanel.add(time);
        mainPanel.add(t);
        mainPanel.add(vaccine);
        mainPanel.add(v);

        buttonPanel.add(register);
        buttonPanel.add(cancel);
        buttonPanel.add(back);
    }
}
