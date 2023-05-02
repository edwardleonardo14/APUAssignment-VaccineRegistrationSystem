package oodj.apu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageCitizenProfileMod extends JFrame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cancel){
            setVisible(false);
            PageCitizenProfileView citizenProfileView = new PageCitizenProfileView();
            citizenProfileView.setVisible(true);
        }
        else if(e.getSource() == confirm){
            if(usernameField.getText().isEmpty()){
                JOptionPane.showMessageDialog(confirm,"Username cannot be empty!");
            }
            else if(passwordField.getText().isEmpty()){
                JOptionPane.showMessageDialog(confirm,"Password cannot be empty!");
            }
            else if(firstNameField.getText().isEmpty()){
                JOptionPane.showMessageDialog(confirm,"First Name cannot be empty!");
            }
            else if(ageField.getText().isEmpty()){
                JOptionPane.showMessageDialog(confirm,"Age cannot be empty!");
            }
            else if(emailField.getText().isEmpty()){
                JOptionPane.showMessageDialog(confirm,"Email cannot be empty!");
            }
            else if(phoneField.getText().isEmpty()){
                JOptionPane.showMessageDialog(confirm,"Phone Number cannot be empty!");
            }
            else if(!(male.isSelected()) && !(female.isSelected())){
                JOptionPane.showMessageDialog(confirm,"Gender cannot be empty!");
            }
            else if(!(DataIO.isNumeric(ageField.getText()))){
                JOptionPane.showMessageDialog(confirm,"Age must be numeric!");
            }
            else if(Integer.parseInt(ageField.getText()) < 0 || Integer.parseInt(ageField.getText())>120){
                JOptionPane.showMessageDialog(confirm,"Age must be valid (Between 0 - 120)!");
            }
            else if(!(DataIO.isNumeric(phoneField.getText()))){
                JOptionPane.showMessageDialog(confirm,"Phone number must be numeric!");
            }
            else if(!(DataIO.phoneCheck(phoneField.getText()))){
                JOptionPane.showMessageDialog(confirm,"Phone number must be valid length (10-14 Digits)!");
            }
            else{
                String a = usernameField.getText();
                Citizen check = DataIO.checkCitizen(a);
                if(check == null || check.getUsername().equals(a)){
                    String b = passwordField.getText();
                    String c = firstNameField.getText();
                    String d = lastNameField.getText();
                    int f = Integer.parseInt(ageField.getText());
                    String g = emailField.getText();
                    String h = phoneField.getText();
                    String i;
                    if(male.isSelected()){
                        i = "Male";
                    }
                    else{
                        i = "Female";
                    }
                    Main.currentCitizen.setUsername(a);
                    Main.currentCitizen.setPassword(b);
                    Main.currentCitizen.setFirstName(c);
                    Main.currentCitizen.setLastName(d);
                    Main.currentCitizen.setAge(f);
                    Main.currentCitizen.setEmail(g);
                    Main.currentCitizen.setPhoneNumber(h);
                    Main.currentCitizen.setGender(i);
                    DataIO.write();
                    JOptionPane.showMessageDialog(confirm,"Modify Successful! You will be logged out now!");
                    setVisible(false);
                    dispose();
                    Main.currentCitizen = null;
                    Main.login.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(confirm,"Username has been Used!");
                    usernameField.setText("");
                }
            }

        }
    }

    private TextField usernameField, passwordField, firstNameField, lastNameField, emailField, phoneField, ageField;
    private Label username, password, firstName, lastName, email, phone, age;
    private Panel mainPanel, buttonPanel, u, p, f, l, e, ph, g, a;
    private Button confirm, cancel;
    private JRadioButton male, female;
    private ButtonGroup gender;
    private Panel titlePanel;
    private Label title, subtitle;

    public PageCitizenProfileMod(){
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
        mainPanel.setLayout(new GridLayout(15,1));
        buttonPanel = new Panel();
        buttonPanel.setPreferredSize(new Dimension(800,50));
        this.add(buttonPanel,BorderLayout.SOUTH);

        confirm = new Button("Confirm");
        confirm.addActionListener(this);
        cancel = new Button("Cancel");
        cancel.addActionListener(this);

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

        if(Main.currentCitizen.getGender().equals("Male")){
            male = new JRadioButton("Male",true);
            male.setForeground(Color.WHITE);
            male.setBackground(Color.DARK_GRAY);
            female = new JRadioButton("Female");
            female.setForeground(Color.WHITE);
            female.setBackground(Color.DARK_GRAY);
        }
        else{
            male = new JRadioButton("Male");
            male.setForeground(Color.WHITE);
            male.setBackground(Color.DARK_GRAY);
            female = new JRadioButton("Female",true);
            female.setForeground(Color.WHITE);
            female.setBackground(Color.DARK_GRAY);
        }

        gender = new ButtonGroup();
        gender.add(male);
        gender.add(female);

        usernameField = new TextField(30);
        usernameField.setText(Main.currentCitizen.getUsername());
        passwordField = new TextField(30);
        passwordField.setText(Main.currentCitizen.getPassword());
        firstNameField = new TextField(30);
        firstNameField.setText(Main.currentCitizen.getFirstName());
        lastNameField = new TextField(30);
        lastNameField.setText(Main.currentCitizen.getLastName());
        emailField = new TextField(30);
        emailField.setText(Main.currentCitizen.getEmail());
        phoneField = new TextField(30);
        phoneField.setText(Main.currentCitizen.getPhoneNumber());
        ageField = new TextField(30);
        ageField.setText(String.valueOf(Main.currentCitizen.getAge()));

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
        g = new Panel();
        g.setLayout(new GridLayout(1,6));
        g.add(new Panel());
        g.add(new Panel());
        g.add(male);
        g.add(female);
        g.add(new Panel());
        g.add(new Panel());

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

        buttonPanel.add(confirm);
        buttonPanel.add(cancel);

    }
}