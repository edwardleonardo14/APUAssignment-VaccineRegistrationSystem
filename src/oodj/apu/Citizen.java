package oodj.apu;

import java.time.LocalDate;
import java.util.ArrayList;

public class Citizen extends Admin{
    private String firstName;
    private String lastName;
    private int age;
    private VaccinationStatus vaccinationStatus;
    private String email;
    private String phoneNumber;
    private String gender;
    private ArrayList<Appointment> appointment = new ArrayList<Appointment>();

    public Citizen(String username, String password, String firstName, String lastName, int age, VaccinationStatus vaccinationStatus, String email, String phoneNumber, String gender) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.vaccinationStatus = vaccinationStatus;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public Citizen(String username, String password, String firstName, int age, VaccinationStatus vaccinationStatus, String email, String phoneNumber, String gender) {
        super(username, password);
        this.firstName = firstName;
        this.age = age;
        this.vaccinationStatus = vaccinationStatus;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public Citizen(String username, String password, String firstName, String lastName, int age, String email, String phoneNumber, String gender) {
        super(username, password);
        this.firstName = firstName;
        if(lastName.isEmpty()){
            this.lastName = "";
        }
        else{
            this.lastName = lastName;
        }
        this.age = age;
        this.vaccinationStatus = newAccount;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public VaccinationStatus getVaccinationStatus() {
        return vaccinationStatus;
    }

    public void setVaccinationStatus(VaccinationStatus vaccinationStatus) {
        this.vaccinationStatus = vaccinationStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ArrayList<Appointment> getAppointment() {
        return appointment;
    }

    public void setAppointment(ArrayList<Appointment> appointment) {
        this.appointment = appointment;
    }

    VaccinationStatus newAccount = new VaccinationStatus("-",0, LocalDate.of(1900,01,01),LocalDate.of(1900,01,01), Status.NotVaccinated);
}
