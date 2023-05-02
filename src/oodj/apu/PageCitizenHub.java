package oodj.apu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PageCitizenHub extends JFrame implements ActionListener{

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == logout){
            Main.currentCitizen = null;
            setVisible(false);
            Main.login.setVisible(true);
        }
        else if(e.getSource() == appointment){
            this.setVisible(false);
            PageCitizenAppointment citizenAppointment = new PageCitizenAppointment();
            citizenAppointment.setVisible(true);
        }
        else if(e.getSource() == vaccinationStatus){
            setVisible(false);
            PageCitizenVaccinationStatus citizenVaccinationStatus = new PageCitizenVaccinationStatus();
            citizenVaccinationStatus.setVisible(true);
        }
        else if(e.getSource() == profile){
            setVisible(false);
            PageCitizenProfileView citizenProfileView = new PageCitizenProfileView();
            citizenProfileView.setVisible(true);
        }
    }
    private Label hellofn;
    private Button profile, appointment, vaccinationStatus, logout;
    private Panel p1,p2,p3,p4,p5,textPanel2;

    private Panel titlePanel;
    private Label title, subtitle;

    public PageCitizenHub(){

        this.setTitle("COVID-19 VACCINATION SYSTEM");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10,6));
        this.setLocation(300,300);
        this.setSize(800,400);
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

        //label
        hellofn = new Label("Hello, "+Main.currentCitizen.getFirstName()+"!");
        hellofn.setFont(new Font("Helvetica",Font.BOLD,20));
        hellofn.setForeground(Color.WHITE);

        //button
        profile = new Button("Profile");
        appointment = new Button("Appointment");
        vaccinationStatus = new Button("Vaccination Status");
        logout = new Button("Logout");

        profile.addActionListener(this);
        appointment.addActionListener(this);
        vaccinationStatus.addActionListener(this);
        logout.addActionListener(this);

        //panel
        textPanel2 = new Panel();
        p1 = new Panel();
        p2 = new Panel();
        p3 = new Panel();
        p4 = new Panel();
        p5 = new Panel();

        //setpanel
        this.add(textPanel2,BorderLayout.CENTER);
        textPanel2.setLayout(new GridLayout(7,1));
        p1.add(hellofn);
        textPanel2.add(p1);
        p2.add(profile);
        textPanel2.add(p2);
        p3.add(appointment);
        textPanel2.add(p3);
        p4.add(vaccinationStatus);
        textPanel2.add(p4);
        p5.add(logout);
        textPanel2.add(p5);
    }
}