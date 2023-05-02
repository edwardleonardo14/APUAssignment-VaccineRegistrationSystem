package oodj.apu;

public class Vaccine {
    private String name;
    private int doseRequired;
    private int dayRequired;
    private int amount;

    //Sinovac, Pfizer, Moderna, Johnson&Johnson, AstraZeneca


    public Vaccine(String name, int doseRequired, int dayRequired, int amount) {
        this.name = name;
        this.doseRequired = doseRequired;
        this.dayRequired = dayRequired;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDoseRequired() {
        return doseRequired;
    }

    public void setDoseRequired(int doseRequired) {
        this.doseRequired = doseRequired;
    }

    public int getDayRequired() {
        return dayRequired;
    }

    public void setDayRequired(int dayRequired) {
        this.dayRequired = dayRequired;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
