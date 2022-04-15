package dev.matthias.entities;

import dev.matthias.utilities.Status;

public class Expense {

    private int id;
    private String name;
    private Status status = Status.PENDING;
    private int cost;
    private int issuerId;

    public Expense(){}

    public Expense(int id, String name, Status status, int cost, int issuerId) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.cost = cost;
        this.issuerId = issuerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(int issuerId) {
        this.issuerId = issuerId;
    }
}
