package oodj.apu;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class DataIO {
    public static ArrayList<Citizen> allCitizen =
            new ArrayList<Citizen>();
    public static ArrayList<Admin> allAdmin =
            new ArrayList<Admin>();
    public static ArrayList<Vaccine> allVaccine =
            new ArrayList<Vaccine>();
    public static ArrayList<Appointment> allAppointment =
            new ArrayList<Appointment>();
    public static String[] allVaccineName;
    public static String[] allTime = new String[Time.values().length];
    public static Location[] allLocation = Location.values();
    public static Status[] allStatus = Status.values();

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

    public static void read(){
        try{
            Scanner a1 = new Scanner(new File("admin.txt"));
            while(a1.hasNext()){
                String b1 = a1.nextLine();
                String c1 = a1.nextLine();
                a1.nextLine();
                Admin d1 = new Admin(b1,c1);
                allAdmin.add(d1);
            }
            Scanner a2 = new Scanner(new File("citizen.txt"));
            while(a2.hasNext()){
                String b2 = a2.nextLine(); //username
                String c2 = a2.nextLine(); //password
                String d2 = a2.nextLine(); //first name
                String e2 = a2.nextLine(); //last name
                int p2 = Integer.parseInt(a2.nextLine()); //age
                String f2 = a2.nextLine(); //vaccstatus.vaccineName
                int g2 = Integer.parseInt(a2.nextLine()); //vaccstatus.dose
                LocalDate h2 = LocalDate.parse(a2.nextLine(), formatter); //vacstat.datedose1
                LocalDate i2 = LocalDate.parse(a2.nextLine(), formatter); //vacstat.datedose2
                Status j2 = Status.valueOf(a2.nextLine());
                String k2 = a2.nextLine(); //email
                String l2 = a2.nextLine(); //phoneNumber
                String m2 = a2.nextLine(); //gender
                a2.nextLine();
                VaccinationStatus n2 = new VaccinationStatus(f2,g2,h2,i2,j2);
                Citizen o2 = new Citizen(b2, c2, d2, e2, p2, n2, k2, l2, m2);
                allCitizen.add(o2);
            }
            Scanner a3 = new Scanner(new File("vaccine.txt"));
            while(a3.hasNext()){
                String b3 = a3.nextLine();
                int c3 = Integer.parseInt(a3.nextLine());
                int d3 = Integer.parseInt(a3.nextLine());
                int e3 = Integer.parseInt(a3.nextLine());
                a3.nextLine();
                Vaccine f3 = new Vaccine(b3,c3,d3,e3);
                allVaccine.add(f3);
            }
            Scanner a4 = new Scanner(new File("appointment.txt"));
            while(a4.hasNext()){
                Location b4 = Location.valueOf(a4.nextLine()); //location
                LocalDate c4 = LocalDate.parse(a4.nextLine(),formatter); //date
                String d4 = a4.nextLine(); //vaccine name
                Time e4 = Time.valueOf(a4.nextLine());
                boolean f4 = Boolean.parseBoolean(a4.nextLine());
                Citizen g4 = DataIO.checkCitizen(a4.nextLine());
                a4.nextLine();
                Appointment h4 = new Appointment(b4,c4,d4,e4,f4,g4);
                allAppointment.add(h4);
                g4.getAppointment().add(h4);
            }
            allVaccineName = new String[allVaccine.size()];
            fillAllVaccineName();
            int i = 0;
            for (Time s : Time.values()){
                allTime[i] = getTimeString(s);
                i++;
            }
        }
        catch(Exception e){
            System.out.println("Error in Read!");
        }
    }

    public static void write(){
        try{
            PrintWriter a = new PrintWriter("admin.txt");
            for(int i=0;i<allAdmin.size(); i++){
                a.println(allAdmin.get(i).getUsername());
                a.println(allAdmin.get(i).getPassword());
                a.println();
            }
            a.close();

            PrintWriter b = new PrintWriter("citizen.txt");
            for(int i=0;i<allCitizen.size();i++){
                b.println(allCitizen.get(i).getUsername());
                b.println(allCitizen.get(i).getPassword());
                b.println(allCitizen.get(i).getFirstName());
                b.println(allCitizen.get(i).getLastName());
                b.println(allCitizen.get(i).getAge());
                b.println(allCitizen.get(i).getVaccinationStatus().getVaccineName());
                b.println(allCitizen.get(i).getVaccinationStatus().getDose());
                b.println(allCitizen.get(i).getVaccinationStatus().getDateCompletionDose1());
                b.println(allCitizen.get(i).getVaccinationStatus().getDateCompletionDose2());
                b.println(allCitizen.get(i).getVaccinationStatus().getStatus());
                b.println(allCitizen.get(i).getEmail());
                b.println(allCitizen.get(i).getPhoneNumber());
                b.println(allCitizen.get(i).getGender());
                b.println();
            }
            b.close();

            PrintWriter c = new PrintWriter("vaccine.txt");
            for(int i=0;i<allVaccine.size();i++){
                c.println(allVaccine.get(i).getName());
                c.println(allVaccine.get(i).getDoseRequired());
                c.println(allVaccine.get(i).getDayRequired());
                c.println(allVaccine.get(i).getAmount());
                c.println();
            }
            c.close();

            PrintWriter d = new PrintWriter("appointment.txt");
            for(int i=0;i<allAppointment.size();i++){
                d.println(allAppointment.get(i).getLocation());
                d.println(allAppointment.get(i).getDate());
                d.println(allAppointment.get(i).getVaccineName());
                d.println(allAppointment.get(i).getTime());
                d.println(allAppointment.get(i).isStatus());
                d.println(allAppointment.get(i).getOwner().getUsername());
                d.println();
            }
            d.close();

        }
        catch(Exception e){
            System.out.println("Error in Write!");
        }
    }

    public static Citizen checkCitizen(String x){
        for(Citizen c : allCitizen){
            if(x.equals(c.getUsername())){
                return c;
            }
        }
        return null;
    }

    public static Admin checkAdmin(String x){
        for(Admin a : allAdmin){
            if(x.equals(a.getUsername())){
                return a;
            }
        }
        return null;
    }

    public static Appointment checkAppointment(String x, LocalDate y){
        for(Appointment a : allAppointment){
            if(x.equals(a.getOwner().getUsername()) && y.equals(a.getDate())){
                return a;
            }
        }
        return null;
    }

    public static Vaccine checkVaccine(String x){
        for(Vaccine v : allVaccine){
            if(x.equals(v.getName())){
                return v;
            }
        }
        return null;
    }

    public static boolean isNumeric(String x){
        try{
            Long.parseLong(x);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public static boolean phoneCheck(String x){
        long n = Long.parseLong(x);
        int check = (int)(Math.log10(n)+1);
        if (check >= 10 || check <= 14){
            return true;
        }
        else{
            return false;
        }
    }

    public static void fillAllVaccineName(){
        for(int i = 0; i<allVaccine.size();i++){
            allVaccineName[i] = allVaccine.get(i).getName();
        }
    }

    public static String getTimeString(Time x) {
        if(x.equals(Time.eight)){
            return "08:00";
        }
        else if(x.equals(Time.ten)){
            return "10:00";
        }
        else if(x.equals(Time.twelve)){
            return "12:00";
        }
        else if(x.equals(Time.two)){
            return "14:00";
        }
        else{
            return "16:00";
        }
    }

    public static Time getTimeValue(String x) {
        if(x.equals("08:00")){
            return Time.eight;
        }
        else if(x.equals("10:00")){
            return Time.ten;
        }
        else if(x.equals("12:00")){
            return Time.twelve;
        }
        else if(x.equals("14:00")){
            return Time.two;
        }
        else{
            return Time.four;
        }
    }

    public static void statusUpdater(){
        for(Citizen c : allCitizen){
            if(c.getVaccinationStatus().getStatus().equals(Status.PartialVaccinated)){
                if(checkVaccine(c.getVaccinationStatus().getVaccineName()).getDoseRequired() == c.getVaccinationStatus().getDose()) {
//                    System.out.println(c.getFirstName());
//                    System.out.println(c.getVaccinationStatus().getVaccineName());
                    int a = checkVaccine(c.getVaccinationStatus().getVaccineName()).getDayRequired();
//                    System.out.println(a);
                    LocalDate b;
                    if(checkVaccine(c.getVaccinationStatus().getVaccineName()).getDoseRequired() == 1){
                        b = c.getVaccinationStatus().getDateCompletionDose1();
                    }
                    else{
                        b = c.getVaccinationStatus().getDateCompletionDose2();
                    }
//                    System.out.println(b);
//                    System.out.println(b.plusDays(a));
//                    System.out.println(LocalDate.now());
                    if (b.plusDays(a).compareTo(LocalDate.now()) < 0) {
                        System.out.println("UPGRADED");
                        c.getVaccinationStatus().setStatus(Status.FullVaccinated);
                        DataIO.write();
                    }
                }
            }
        }
    }
}
