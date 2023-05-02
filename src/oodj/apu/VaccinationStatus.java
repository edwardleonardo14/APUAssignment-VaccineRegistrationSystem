package oodj.apu;

import java.time.LocalDate;
import java.util.Date;

public class VaccinationStatus {
    private String vaccineName;
    private int dose;
    private LocalDate dateCompletionDose1;
    private LocalDate dateCompletionDose2;
    private Status status;

    public VaccinationStatus(String vaccineName, int dose, LocalDate dateCompletionDose1, LocalDate dateCompletionDose2, Status status) {
        this.vaccineName = vaccineName;
        this.dose = dose;
        this.dateCompletionDose1 = dateCompletionDose1;
        this.dateCompletionDose2 = dateCompletionDose2;
        this.status = status;
    }

    public VaccinationStatus() {
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public LocalDate getDateCompletionDose1() {
        return dateCompletionDose1;
    }

    public void setDateCompletionDose1(LocalDate dateCompletionDose1) {
        this.dateCompletionDose1 = dateCompletionDose1;
    }

    public LocalDate getDateCompletionDose2() {
        return dateCompletionDose2;
    }

    public void setDateCompletionDose2(LocalDate dateCompletionDose2) {
        this.dateCompletionDose2 = dateCompletionDose2;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
