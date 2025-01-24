package org.lms;

/* File meant to represent a single patron */

public class Patron
{
    // Patron id attribute
    private String id;

    // Patron name attribute
    private String name;

    // Patron address attribute
    private String address;

    // Patron overdueFine attribute
    private double overdueFine;

    // Patron empty constructor
    public Patron() {}

    // Patron all args constructor
    public Patron(String id, String name, String address, double overdueFine) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.overdueFine = overdueFine;
    }

    public Patron(String name, String address, double overdueFine)
    {
        this.name = name;
        this.address = address;
        this.overdueFine = overdueFine;
    }

    // Getters and setters for each attribute

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getOverdueFine() {
        return overdueFine;
    }

    public void setOverdueFine(double overdueFine) {
        this.overdueFine = overdueFine;
    }

    @Override
    public String toString() {
        return "Patron(" +
                "\n\t\tid='" + this.id + "'," +
                "\n\t\t name='" + this.name + "'," +
                "\n\t\t address='" + this.address + "'," +
                "\n\t\t overdueFine=" + this.overdueFine + "\n\t\t" +
                ')';
    }
}
