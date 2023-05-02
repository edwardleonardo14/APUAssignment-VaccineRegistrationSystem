package oodj.apu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageCitizenVaccinationStatus extends JFrame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back){
            setVisible(false);
            PageCitizenHub citizenHub = new PageCitizenHub();
            citizenHub.setVisible(true);
        }

    }

    private TextField vaccineField, completedField, date1Field, date2Field,statusField;
    private Label vaccine, completed, date1, date2,status;
    private Panel mainPanel, u, p, f, l,a,b;
    private Button back;

    private Panel titlePanel;
    private Label title, subtitle;

    public PageCitizenVaccinationStatus(){
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


        vaccineField = new TextField(30);
        vaccineField.setText(Main.currentCitizen.getVaccinationStatus().getVaccineName());
        vaccineField.setEditable(false);
        completedField = new TextField(30);
        completedField.setText(String.valueOf(Main.currentCitizen.getVaccinationStatus().getDose()));
        completedField.setEditable(false);
        date1Field = new TextField(30);
        date1Field.setText(String.valueOf(Main.currentCitizen.getVaccinationStatus().getDateCompletionDose1()));
        date1Field.setEditable(false);
        date2Field = new TextField(30);
        date2Field.setText(String.valueOf(Main.currentCitizen.getVaccinationStatus().getDateCompletionDose2()));
        date2Field.setEditable(false);
        statusField = new TextField(30);
        statusField.setText(String.valueOf(Main.currentCitizen.getVaccinationStatus().getStatus()));
        statusField.setEditable(false);

        u = new Panel();
        u.add(vaccineField);
        p = new Panel();
        p.add(completedField);
        f = new Panel();
        f.add(date1Field);
        l = new Panel();
        l.add(date2Field);
        a = new Panel();
        a.add(statusField);


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
        mainPanel.add(back);


        b = new Panel();

        b.add(back);
        mainPanel.add(b);

    }
}