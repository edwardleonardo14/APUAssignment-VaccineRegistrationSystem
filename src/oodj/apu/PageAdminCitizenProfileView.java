package oodj.apu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class PageAdminCitizenProfileView extends JFrame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            Main.currentCitizen = null;
            setVisible(false);
            dispose();
            PageAdminCitizenHub adminCitizenHub = new PageAdminCitizenHub();
            adminCitizenHub.setVisible(true);
        }
        else if (e.getSource() == modify){
            setVisible(false);
            dispose();
            PageAdminCitizenProfileMod adminCitizenProfileMod = new PageAdminCitizenProfileMod();
            adminCitizenProfileMod.setVisible(true);
        }
        else if (e.getSource() == vaccinationStatus){
            setVisible(false);
            dispose();
            PageAdminCitizenVaccinationStatus adminCitizenVaccinationStatus = new PageAdminCitizenVaccinationStatus();
            adminCitizenVaccinationStatus.setVisible(true);
        }
        else if(e.getSource() == delete){
            String a = JOptionPane.showInputDialog("Enter Verification Password to delete the Data!");
            if(Main.currentAdmin.getPassword().equals(a)){
                DataIO.allCitizen.remove(Main.currentCitizen);

                // to delete the citizen's appointment data without throwing concurrentModificationException
                Iterator<Appointment> itr = DataIO.allAppointment.iterator();
                while(itr.hasNext()){
                    Appointment aa = itr.next();
                    if(aa.getOwner().equals(Main.currentCitizen)){
                        itr.remove();
                    }
                }

                DataIO.write();
                JOptionPane.showMessageDialog(delete,"Citizen Deleted! All Appointment from the citizen also deleted!");
                Main.currentCitizen = null;
                setVisible(false);
                dispose();
                PageAdminCitizenHub adminCitizenHub = new PageAdminCitizenHub();
                adminCitizenHub.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(delete,"Wrong Verification Password!");
            }
        }
    }

    private TextField usernameField, passwordField, firstNameField, lastNameField, emailField, phoneField, ageField, genderField;
    private Label username, password, firstName, lastName, email, phone, age, gender;
    private Panel mainPanel, buttonPanel, u, p, f, l, e, ph, g, a,gep2,v;
    private Button modify, back,vaccinationStatus, delete;
    private Panel titlePanel;
    private Label title, subtitle;


    public PageAdminCitizenProfileView(){
        this.setTitle("COVID-19 VACCINATION SYSTEM");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10,6));
        this.setLocation(300,300);
        this.setSize(800,700);
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
        mainPanel.setLayout(new GridLayout(17,1));
        buttonPanel = new Panel();
        buttonPanel.setPreferredSize(new Dimension(800,50));
        this.add(buttonPanel,BorderLayout.SOUTH);

        modify = new Button("Modify");
        modify.addActionListener(this);
        back = new Button("Back");
        back.addActionListener(this);
        vaccinationStatus = new Button("Vaccination Status");
        vaccinationStatus.addActionListener(this);
        delete = new Button("Delete Account");
        delete.addActionListener(this);

        username = new Label("Username",Label.CENTER);
        username.setForeground(Color.WHITE);
        password = new Label("Password",Label.CENTER);
        password.setForeground(Color.WHITE);
        firstName = new Label("First Name",Label.CENTER);
        firstName.setForeground(Color.WHITE);
        lastName = new Label("Last Name (Optional)",Label.CENTER);
        lastName.setForeground(Color.WHITE);
        email = new Label("Email",Label.CENTER);
        email.setForeground(Color.WHITE);
        phone = new Label("Phone Number",Label.CENTER);
        phone.setForeground(Color.WHITE);
        age = new Label("Age",Label.CENTER);
        age.setForeground(Color.WHITE);
        gender = new Label("Gender",Label.CENTER);
        gender.setForeground(Color.WHITE);

        usernameField = new TextField(30);
        usernameField.setText(Main.currentCitizen.getUsername());
        usernameField.setEditable(false);
        passwordField = new TextField(30);
        passwordField.setText(Main.currentCitizen.getPassword());
        passwordField.setEchoChar('*');
        passwordField.setEditable(false);
        firstNameField = new TextField(30);
        firstNameField.setText(Main.currentCitizen.getFirstName());
        firstNameField.setEditable(false);
        lastNameField = new TextField(30);
        lastNameField.setText(Main.currentCitizen.getLastName());
        lastNameField.setEditable(false);
        emailField = new TextField(30);
        emailField.setText(Main.currentCitizen.getEmail());
        emailField.setEditable(false);
        phoneField = new TextField(30);
        phoneField.setText(Main.currentCitizen.getPhoneNumber());
        phoneField.setEditable(false);
        ageField = new TextField(30);
        ageField.setText(String.valueOf(Main.currentCitizen.getAge()));
        ageField.setEditable(false);
        genderField = new TextField(30);
        genderField.setText(Main.currentCitizen.getGender());
        genderField.setEditable(false);

        u = new Panel();
        u.add(usernameField);
        p = new Panel();
        p.add(passwordField);
        f = new Panel();
        f.add(firstNameField);
        l = new Panel();
        l.add(lastNameField);
        a = new Panel();
        a.add(ageField);
        e = new Panel();
        e.add(emailField);
        ph = new Panel();
        ph.add(phoneField);

        //tambahan
        g = new Panel();
        gep2 = new Panel();
        v = new Panel();

        mainPanel.add(username);
        mainPanel.add(u);
        mainPanel.add(password);
        mainPanel.add(p);
        mainPanel.add(firstName);
        mainPanel.add(f);
        mainPanel.add(lastName);
        mainPanel.add(l);
        mainPanel.add(age);
        mainPanel.add(a);
        mainPanel.add(email);
        mainPanel.add(e);
        mainPanel.add(phone);
        mainPanel.add(ph);
        mainPanel.add(g);

        buttonPanel.add(modify);
        buttonPanel.add(delete);
        buttonPanel.add(back);

        g.add(gender);
        gep2.add(genderField);
        mainPanel.add(gep2);

        v.add(vaccinationStatus);
        mainPanel.add(v);

    }
}