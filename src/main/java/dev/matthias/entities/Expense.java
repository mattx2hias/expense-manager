package dev.matthias.entities;

import dev.matthias.utilities.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {

    private int id;
    private String name;
    private Status status = Status.PENDING;
    private double cost;
    private int issuerId;

    public Expense(int id, String name, double cost, int issuerId) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.issuerId = issuerId;
    }

    public Expense(String name, double cost, int id) {
        this.name = name;
        this.cost = cost;
        this.issuerId = id;
    }
}
