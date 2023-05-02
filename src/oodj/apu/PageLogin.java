package oodj.apu;

import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageLogin extends JFrame implements ActionListener{

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exit){
            DataIO.write();
            System.exit(0);
        }
        else if(e.getSource() == register){
            setVisible(false);
            PageCitizenRegister citizenRegister = new PageCitizenRegister();
            citizenRegister.setVisible(true);
        }
        else if(e.getSource() == login){
            String user = usernameField.getText();
            Citizen found = DataIO.checkCitizen(user);
            if(found != null){
                String pass = passwordField.getText();
                if(found.getPassword().equals(pass)){
                    Main.currentCitizen = found;
                    usernameField.setText("");
                    passwordField.setText("");
                    setVisible(false);
                    PageCitizenHub citizenHub = new PageCitizenHub();
                    citizenHub.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(login,"Wrong Password!");
                    passwordField.setText("");
                }
            }
            else{
                JOptionPane.showMessageDialog(login,"Wrong Username!");
                usernameField.setText("");
            }
        }
        else if(e.getSource() == forgot){
            setVisible(false);
            PageCitizenForgot citizenForgot = new PageCitizenForgot();
            citizenForgot.setVisible(true);
        }
        else if (e.getSource() == admin){
            String user = usernameField.getText();
            Admin found = DataIO.checkAdmin(user);
            if(found != null){
                String pass = passwordField.getText();
                if(found.getPassword().equals(pass)){
                    Main.currentAdmin = found;
                    usernameField.setText("");
                    passwordField.setText("");
                    setVisible(false);
                    PageAdminHub adminHub = new PageAdminHub();
                    adminHub.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(login,"Wrong Password!");
                    passwordField.setText("");
                }
            }
            else{
                JOptionPane.showMessageDialog(login,"Wrong Username!");
                usernameField.setText("");
            }
        }
    }

    private TextField usernameField, passwordField;
    private Button register,login,forgot,admin,exit;
    private Panel textPanel, buttonPanel, u,p,uf,pf;
    private Label username, password;

    private Panel titlePanel;
    private Label title, subtitle;
    public PageLogin(){

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

        username = new Label("Username",Label.CENTER);
        username.setForeground(Color.WHITE);
        password = new Label("Password",Label.CENTER);
        password.setForeground(Color.WHITE);
        usernameField = new TextField(30);
        passwordField = new TextField(30);
        passwordField.setEchoChar('*');
        register = new Button("Register");
        login = new Button("Login");
        forgot = new Button("Forgot Password?");
        admin = new Button("Admin Login");
        exit = new Button("Exit");

        textPanel = new Panel();
        u = new Panel();
        p = new Panel();
        uf = new Panel();
        pf = new Panel();

        this.add(textPanel,BorderLayout.CENTER);
        textPanel.setLayout(new GridLayout(6,1));
        u.add(username);
        textPanel.add(u);
        uf.add(usernameField);
        textPanel.add(uf);
        p.add(password);
        textPanel.add(p);
        pf.add(passwordField);
        textPanel.add(pf);

        buttonPanel = new Panel();
        buttonPanel.setPreferredSize(new Dimension(800,50));
        buttonPanel.add(register);
        buttonPanel.add(login);
        buttonPanel.add(forgot);
        buttonPanel.add(admin);
        buttonPanel.add(exit);
        this.add(buttonPanel,BorderLayout.SOUTH);

        register.addActionListener(this);
        login.addActionListener(this);
        forgot.addActionListener(this);
        admin.addActionListener(this);
        exit.addActionListener(this);

        this.setVisible(true);
    }

}