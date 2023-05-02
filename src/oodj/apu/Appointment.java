package oodj.apu;

import java.time.LocalDate;
import java.util.Date;

public class Appointment {
    private Location location;
    private LocalDate date;
    private String vaccineName;
    private Time time;
    private boolean status;
    private Citizen owner;

    public Appointment(Location location, LocalDate date, String vaccineName, Time time, boolean status, Citizen owner) {
        this.location = location;
        this.date = date;
        this.vaccineName = vaccineName;
        this.time = time;
        this.status = status;
        this.owner = owner;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getTimeString() {
        if(time.equals(Time.eight)){
            return "08:00";
        }
        else if(time.equals(Time.ten)){
            return "10:00";
        }
        else if(time.equals(Time.twelve)){
            return "12:00";
        }
        else if(time.equals(Time.two)){
            return "14:00";
        }
        else{
            return "16:00";
        }
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Citizen getOwner() {
        return owner;
    }

    public void setOwner(Citizen owner) {
        this.owner = owner;
    }
}
