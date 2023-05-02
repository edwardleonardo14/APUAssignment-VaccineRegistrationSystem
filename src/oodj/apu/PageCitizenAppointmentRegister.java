package oodj.apu;

import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class PageCitizenAppointmentRegister extends JFrame implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            setVisible(false);
            PageCitizenAppointment citizenAppointment = new PageCitizenAppointment();
            citizenAppointment.setVisible(true);
        }

        else if(e.getSource() == register){
            try{
                LocalDate a = null;
                if(LocalDate.parse(dateField.getText(), DataIO.formatter).compareTo(LocalDate.now())<0){
                    JOptionPane.showMessageDialog(register,"Date cannot be earlier than today!");
                }
                else{
                    a = LocalDate.parse(dateField.getText(), DataIO.formatter);
                    Location b = Location.valueOf(String.valueOf(locationC.getSelectedItem()));
                    String c = String.valueOf(vaccineC.getSelectedItem());
                    Time d = DataIO.getTimeValue(String.valueOf(timeC.getSelectedItem()));
                    Appointment f = new Appointment(b,a,c,d,false,Main.currentCitizen);
                    DataIO.allAppointment.add(f);
                    Main.currentCitizen.getAppointment().add(f);
                    DataIO.write();
                    JOptionPane.showMessageDialog(register,"Register Successful!");
                    setVisible(false);
                    dispose();
                    PageCitizenAppointment citizenAppointment = new PageCitizenAppointment();
                    citizenAppointment.setVisible(true);
                }
            }
            catch(Exception ee){
                JOptionPane.showMessageDialog(register,"Error in Date, please use the correct format! (yyyy-mm-dd)");
                dateField.setText("");
            }
        }
    }
    private Label vaccine, location, date, time, appointment;
    private JComboBox vaccineC, locationC, timeC;
    private TextField dateField;
    private Button register, back;
    private Panel mainPanel, buttonPanel, v, l, d, t;
    private Panel titlePanel;
    private Label title, subtitle;
//    private String locationArray[]={"Bukit Jalil Stadium", "Axiata Arena", "Universiti Malaya", "UCSI", "KLCC", "IOI City"};
//    private String timeArray[]={"08:00", "10:00", "12.00", "14.00", "16.00"};

    public PageCitizenAppointmentRegister(){
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

        register = new Button("Submit Appointment");
        register.addActionListener(this);
        back = new Button("Back");
        back.addActionListener(this);

        appointment = new Label("Register Appointment",Label.CENTER);
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

        dateField = new TextField(30);

        locationC = new JComboBox(DataIO.allLocation);
        vaccineC = new JComboBox(DataIO.allVaccineName);
        timeC = new JComboBox(DataIO.allTime);

        v = new Panel();
        v.add(vaccineC);
        l = new Panel();
        l.add(locationC);
        d = new Panel();
        d.add(dateField);
        t = new Panel();
        t.add(timeC);

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
        buttonPanel.add(back);
    }
}
