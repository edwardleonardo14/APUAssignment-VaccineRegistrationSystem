package oodj.apu;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class PageAdminReportHub extends JFrame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            setVisible(false);
            dispose();
            PageAdminHub adminHub = new PageAdminHub();
            adminHub.setVisible(true);
        }
        else if(e.getSource() == admin){
            JOptionPane.showMessageDialog(this,"We have "+ DataIO.allAdmin.size()+" Active Admin!");
        }
        else if(e.getSource() == age){
            String[] allAge = new String[DataIO.allCitizen.size()];
            for (int i = 0; i<DataIO.allCitizen.size(); i++){
                System.out.println(DataIO.allCitizen.get(i).getAge());
                allAge[i] = String.valueOf(DataIO.allCitizen.get(i).getAge());
            }
            System.out.println(allAge.length);
            //to get all the unique age
            Set<String> uniqueAge = new HashSet<String>(java.util.List.of(allAge));
            System.out.println(uniqueAge.size());
            for(String s : uniqueAge){
                int count = 0;
                for(int i = 0; i< allAge.length; i++){
                    if(s.equals(allAge[i])){
                        count++;
                    }
                }
                JOptionPane.showMessageDialog(this,"We have "+ count+" Active Citizen that are aged "+s+"!");
            }
        }
        else if(e.getSource() == appointment){
            int sizet = 0;
            int sizef = 0;
            for(Appointment a : DataIO.allAppointment){
                if(a.isStatus()){
                    sizet++;
                }
                else{
                    sizef++;
                }
            }
            JOptionPane.showMessageDialog(this,"We have "+ sizet+" Approved Appointment!\n" +
                    "We have "+ sizef+" Pending Appointment!");
        }
        else if(e.getSource() == citizen){
            JOptionPane.showMessageDialog(this,"We have "+ DataIO.allCitizen.size()+" Active Citizen!");
        }
        else if(e.getSource() == gender){
            int sizef = 0;
            int sizem = 0;
            for(Citizen c : DataIO.allCitizen){
                if(c.getGender().equals("Male")){
                    sizem++;
                }
                else{
                    sizef++;
                }
            }
            JOptionPane.showMessageDialog(this,"We have "+ sizem+" Male Citizen!\n" +
                    "We have "+ sizef+" Female Citizen!");
        }
        else if(e.getSource() == vaccine){
            JOptionPane.showMessageDialog(this,"We have "+ (DataIO.allVaccine.size() - 1)+" Active Vaccine!");
        }
        else if(e.getSource() == centerU){
            int count = 0;
            int[] centerCount = new int[DataIO.allLocation.length];
            for(int i = 0; i<DataIO.allLocation.length;i++){
//                System.out.println(DataIO.allLocation[i]);
                for(Appointment a : DataIO.allAppointment){
                    if(a.getLocation().equals(DataIO.allLocation[i])){
                        count++;
                    }
                }
//                System.out.println(count);
                centerCount[i] = count;
                count = 0;
                JOptionPane.showMessageDialog(this,"Center "+ DataIO.allLocation[i]+" Have "+centerCount[i]+" Appointment Count!");
            }
        }
        else if(e.getSource() == vaccineU){
            int count = 0;
            int[] vaccineCount = new int[DataIO.allVaccine.size()];
            for(int i = 0; i<DataIO.allVaccineName.length;i++){
                if(DataIO.allVaccineName[i].equals("-")){
                    continue;
                }
//                System.out.println(DataIO.allLocation[i]);
                for(Citizen c : DataIO.allCitizen){
                    if(c.getVaccinationStatus().getVaccineName().equals(DataIO.allVaccineName[i])){
                        count++;
                    }
                }
//                System.out.println(count);
                vaccineCount[i] = count;
                count = 0;
                JOptionPane.showMessageDialog(this,"Vaccine "+ DataIO.allVaccineName[i]+" Have been used by "+vaccineCount[i]+" Citizens!");
            }
        }
    }

    private Label report;
    private Button gender, age, vaccine, centerU, appointment, citizen, vaccineU, back, admin;
    private Panel p1,p2,p3,p4,p5,textPanel2,p6, p7,p8,p9,p10;

    private Panel titlePanel;
    private Label title, subtitle;

    public PageAdminReportHub(){
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


        //label
        report = new Label("REPORT",Label.CENTER);
        report.setFont(new Font("Helvetica",Font.BOLD,20));
        report.setForeground(Color.WHITE);

        //button
        admin = new Button("Admin Count");
        age = new Button("Age Count");
        appointment = new Button("Appointment Count");
        citizen = new Button("Citizen Count");
        gender = new Button("Gender Count");
        vaccine = new Button("Vaccine Count");
        centerU = new Button("Center Usage");
        vaccineU = new Button("Vaccine Usage");
        back = new Button("Back");

        admin.addActionListener(this);
        age.addActionListener(this);
        appointment.addActionListener(this);
        citizen.addActionListener(this);
        gender.addActionListener(this);
        vaccine.addActionListener(this);
        centerU.addActionListener(this);
        vaccineU.addActionListener(this);
        back.addActionListener(this);


        //panel
        textPanel2 = new Panel();
        p1 = new Panel();
        p2 = new Panel();
        p3 = new Panel();
        p4 = new Panel();
        p5 = new Panel();
        p6 = new Panel();
        p7 = new Panel();
        p8 = new Panel();
        p9 = new Panel();
        p10 = new Panel();

        //setpanel
        this.add(textPanel2,BorderLayout.CENTER);
        textPanel2.setLayout(new GridLayout(11,1));
        textPanel2.add(report);
        p1.add(admin);
        textPanel2.add(p1);
        p2.add(age);
        textPanel2.add(p2);
        p3.add(appointment);
        textPanel2.add(p3);
        p4.add(citizen);
        textPanel2.add(p4);
        p5.add(gender);
        textPanel2.add(p5);
        p6.add(vaccine);
        textPanel2.add(p6);
        p7.add(centerU);
        textPanel2.add(p7);
//        p8.add(time);
//        textPanel2.add(p8);
        p9.add(vaccineU);
        textPanel2.add(p9);
        p10.add(back);
        textPanel2.add(p10);
    }
}
