package oodj.apu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageAdminHub extends JFrame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == logout){
            Main.currentAdmin = null;
            setVisible(false);
            dispose();
            Main.login.setVisible(true);
        }
        else if(e.getSource() == profile){
            setVisible(false);
            dispose();
            PageAdminCitizenHub adminCitizenHub = new PageAdminCitizenHub();
            adminCitizenHub.setVisible(true);
        }
        else if(e.getSource() == appointment){
            setVisible(false);
            dispose();
            PageAdminAppointmentHub adminAppointmentHub = new PageAdminAppointmentHub();
            adminAppointmentHub.setVisible(true);
        }
        else if(e.getSource() == vaccine){
            setVisible(false);
            dispose();
            PageAdminVaccineHub adminSupplyHub = new PageAdminVaccineHub();
            adminSupplyHub.setVisible(true);
        }
        else if(e.getSource() == admin){
            setVisible(false);
            dispose();
            PageAdminProfileHub adminProfileHub = new PageAdminProfileHub();
            adminProfileHub.setVisible(true);
        }
        else if(e.getSource() == report){
            setVisible(false);
            dispose();
            PageAdminReportHub adminReport = new PageAdminReportHub();
            adminReport.setVisible(true);
        }
    }

    private Label hellofn;
    private Button profile, appointment, vaccine, logout, admin, report;
    private Panel p1,p2,p3,p4,p5,textPanel2,p6, p7;

    private Panel titlePanel;
    private Label title, subtitle;

    public PageAdminHub(){
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
        hellofn = new Label("Hello, Admin "+Main.currentAdmin.getUsername()+"!",Label.CENTER);
        hellofn.setFont(new Font("Helvetica",Font.BOLD,20));
        hellofn.setForeground(Color.WHITE);

        //button
        profile = new Button("Citizen");
        appointment = new Button("Appointment");
        vaccine = new Button("Vaccine");
        logout = new Button("Logout");
        admin = new Button("Admin");
        report = new Button("Report");

        profile.addActionListener(this);
        appointment.addActionListener(this);
        vaccine.addActionListener(this);
        logout.addActionListener(this);
        admin.addActionListener(this);
        report.addActionListener(this);

        //panel
        textPanel2 = new Panel();
        p1 = new Panel();
        p2 = new Panel();
        p3 = new Panel();
        p4 = new Panel();
        p5 = new Panel();
        p6 = new Panel();
        p7 = new Panel();

        //setpanel
        this.add(textPanel2,BorderLayout.CENTER);
        textPanel2.setLayout(new GridLayout(8,1));
        p1.add(hellofn);
        textPanel2.add(p1);
        p2.add(profile);
        textPanel2.add(p2);
        p3.add(appointment);
        textPanel2.add(p3);
        p4.add(vaccine);
        textPanel2.add(p4);
        p7.add(report);
        textPanel2.add(p7);
        p6.add(admin);
        textPanel2.add(p6);
        p5.add(logout);
        textPanel2.add(p5);
    }
}
