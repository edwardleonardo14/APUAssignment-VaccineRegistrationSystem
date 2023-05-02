package oodj.apu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class PageAdminCitizenVaccinationStatusMod extends JFrame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            setVisible(false);
            dispose();
            PageAdminCitizenVaccinationStatus adminCitizenProfileVaccinationStatus = new PageAdminCitizenVaccinationStatus();
            adminCitizenProfileVaccinationStatus.setVisible(true);
        }
        else if (e.getSource() == confirm){
            LocalDate fc = null;
            LocalDate fd = null;
            try{
                String fa = String.valueOf(vaccineC.getSelectedItem());
                int fb = Integer.parseInt(String.valueOf(completedC.getSelectedItem()));
                fc = LocalDate.parse(date1Field.getText(), DataIO.formatter);
                fd = LocalDate.parse(date2Field.getText(), DataIO.formatter);
                Status fe = Status.valueOf(String.valueOf(statusC.getSelectedItem()));
                if(verificationField.getText().equals(Main.currentAdmin.getPassword())){
                    Main.currentCitizen.getVaccinationStatus().setVaccineName(fa);
                    Main.currentCitizen.getVaccinationStatus().setDose(fb);
                    Main.currentCitizen.getVaccinationStatus().setDateCompletionDose1(fc);
                    Main.currentCitizen.getVaccinationStatus().setDateCompletionDose2(fd);
                    Main.currentCitizen.getVaccinationStatus().setStatus(fe);
                    DataIO.write();
                    JOptionPane.showMessageDialog(confirm, "Modify Successful!");
                    setVisible(false);
                    dispose();
                    PageAdminCitizenProfileView adminCitizenProfileView = new PageAdminCitizenProfileView();
                    adminCitizenProfileView.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(confirm,"Wrong Verification Password!");
                    verificationField.setText("");
                }
            }
            catch (Exception ee){
                JOptionPane.showMessageDialog(confirm,"Error in Date, please use the correct format! (yyyy-mm-dd)");
                date1Field.setText("");
                date2Field.setText("");
            }


        }
    }

    private TextField date1Field, date2Field, verificationField;
    private JComboBox statusC, vaccineC, completedC;
    private Label vaccine, completed, date1, date2,status, verification;
    private Panel mainPanel;
    private Panel u;
    private Panel p;
    private Panel f;
    private Panel l;
    private Panel a;
    private Panel b;
    private Panel v;
    private Button back, confirm;

    private Panel titlePanel;
    private Label title, subtitle;

    private String allCompleted[] = {"0","1","2"};

    public PageAdminCitizenVaccinationStatusMod(){
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
        mainPanel.setLayout(new GridLayout(14,1));


        back = new Button("Back");
        back.addActionListener(this);
        confirm = new Button("Confirm");
        confirm.addActionListener(this);

        vaccine = new Label("Vaccine",Label.CENTER);
        vaccine.setForeground(Color.WHITE);
        completed = new Label("Dose Completed",Label.CENTER);
        completed.setForeground(Color.WHITE);
        date1 = new Label("Dose 1 Date Completion",Label.CENTER);
        date1.setForeground(Color.WHITE);
        date2 = new Label("Dose 2 Date Completion",Label.CENTER);
        date2.setForeground(Color.WHITE);
        status = new Label("Status",Label.CENTER);
        status.setForeground(Color.WHITE);
        verification = new Label("Admin Password (Verification)",Label.CENTER);
        verification.setForeground(Color.WHITE);

        date1Field = new TextField(30);
        date1Field.setText(String.valueOf(Main.currentCitizen.getVaccinationStatus().getDateCompletionDose1()));
        date2Field = new TextField(30);
        date2Field.setText(String.valueOf(Main.currentCitizen.getVaccinationStatus().getDateCompletionDose2()));
        verificationField = new TextField(30);
        verificationField.setEchoChar('*');

        statusC = new JComboBox(DataIO.allStatus);
        vaccineC = new JComboBox(DataIO.allVaccineName);
        completedC = new JComboBox(allCompleted);

        if(Main.currentCitizen.getVaccinationStatus().getDose() == 0){
            completedC.setSelectedIndex(0);
        }
        else if(Main.currentCitizen.getVaccinationStatus().getDose() == 1){
            completedC.setSelectedIndex(1);
        }
        else{
            completedC.setSelectedIndex(2);
        }

        for(int i =0; i < DataIO.allStatus.length; i++){
            if(Main.currentCitizen.getVaccinationStatus().getStatus().equals(DataIO.allStatus[i])){
                statusC.setSelectedIndex(i);
                break;
            }
        }

        for(int i =0; i < DataIO.allVaccineName.length; i++){
            if(Main.currentCitizen.getVaccinationStatus().getVaccineName().equals(DataIO.allVaccineName[i])){
                vaccineC.setSelectedIndex(i);
                break;
            }
        }

        u = new Panel();
        u.add(vaccineC);
        p = new Panel();
        p.add(completedC);
        f = new Panel();
        f.add(date1Field);
        l = new Panel();
        l.add(date2Field);
        a = new Panel();
        a.add(statusC);
        v = new Panel();
        v.add(verificationField);


        mainPanel.add(vaccine);
        mainPanel.add(u);
        mainPanel.add(completed);
        mainPanel.add(p);
        mainPanel.add(date1);
        mainPanel.add(f);
        mainPanel.add(date2);
        mainPanel.add(l);
        mainPanel.add(status);
        mainPanel.add(a);
        mainPanel.add(verification);
        mainPanel.add(v);

        b = new Panel();
        b.add(confirm);
        b.add(back);
        mainPanel.add(b);

    }
}