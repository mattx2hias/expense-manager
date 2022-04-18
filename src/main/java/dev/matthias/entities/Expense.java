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
    private int cost;
    private int issuerId;

}
