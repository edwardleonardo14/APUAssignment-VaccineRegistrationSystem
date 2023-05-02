package oodj.apu;

public class Main {
    public static PageLogin login = new PageLogin();
    public static Citizen currentCitizen;
    public static Admin currentAdmin;
    public static Vaccine currentVaccine;
    public static Appointment currentAppointment;
    public static void main(String[] args) {
        DataIO.read();
        DataIO.statusUpdater();
    }


}
