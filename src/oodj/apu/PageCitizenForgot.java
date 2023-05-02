package oodj.apu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageCitizenForgot extends JFrame implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cancel){
            setVisible(false);
            Main.login.setVisible(true);
        }
        else if(e.getSource() == resetpassword){
            String user = usernameField.getText();
            Citizen found = DataIO.checkCitizen(user);
            if(found != null){
                String email = emailField.getText();
                if(found.getEmail().equals(email)){
                    found.setPassword(newPasswordField.getText());
                    DataIO.write();
                    JOptionPane.showMessageDialog(resetpassword,"Password Reset!");
                    setVisible(false);
                    Main.login.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(resetpassword,"Wrong Email!");
                    emailField.setText("");
                }
            }
            else{
                JOptionPane.showMessageDialog(resetpassword,"Username not found!");
                usernameField.setText("");
            }
        }
    }

    private Panel titlePanel;
    private Label title, subtitle;

    //page 3
    private Label username, email, newPassword;
    private TextField usernameField, emailField, newPasswordField;
    private Button resetpassword, cancel;
    private Panel textPanel,p1,p2,p3,p4,p5,p6,p7,p8;

    public PageCitizenForgot() {

        this.setTitle("COVID-19 VACCINATION SYSTEM");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10, 6));
        this.setLocation(300, 300);
        this.setSize(800, 400);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        titlePanel = new Panel();
        titlePanel.setBackground(new Color(0xFF781F));
        titlePanel.setPreferredSize(new Dimension(1000, 80));
        titlePanel.setLayout(new BorderLayout());
        this.add(titlePanel, BorderLayout.NORTH);
        title = new Label("Malaysia Vaccination Center Program", Label.CENTER);
        title.setFont(new Font("Helvetica", Font.BOLD, 35));
        titlePanel.add(title, BorderLayout.NORTH);
        subtitle = new Label("Healthy Me, Healthy You", Label.CENTER);
        subtitle.setFont(new Font("Helvetica", Font.BOLD, 20));
        titlePanel.add(subtitle, BorderLayout.CENTER);

        //Label
        username = new Label("Username",Label.CENTER);
        username.setForeground(Color.WHITE);

        email = new Label("Email",Label.CENTER);
        email.setForeground(Color.WHITE);

        newPassword = new Label("New Password",Label.CENTER);
        newPassword.setForeground(Color.WHITE);

        //TextField
        usernameField = new TextField(30);
        emailField= new TextField(30);
        newPasswordField = new TextField(30);

        //Button
        resetpassword = new Button("Reset Password");
        cancel = new Button("Cancel");
        resetpassword.addActionListener(this);
        cancel.addActionListener(this);

        //Pannel
        textPanel = new Panel();
        p1 = new Panel();
        p2 = new Panel();
        p3 = new Panel();
        p4 = new Panel();
        p5 = new Panel();
        p6 = new Panel();
        p7 = new Panel();
        p8 = new Panel();

        this.add(textPanel,BorderLayout.CENTER);
        textPanel.setLayout(new GridLayout(8,1));
        p1.add(username);
        textPanel.add(p1);
        p2.add(usernameField);
        textPanel.add(p2);
        p3.add(email);
        textPanel.add(p3);
        p4.add(emailField);
        textPanel.add(p4);
        p5.add(newPassword);
        textPanel.add(p5);
        p6.add(newPasswordField);
        textPanel.add(p6);
        p7.add(resetpassword);
        textPanel.add(p7);
        p8.add(cancel);
        textPanel.add(p8);
    }
}