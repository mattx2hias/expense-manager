package dev.matthias.utilities;

import java.util.Locale;

public class ParseStatus {
    private ParseStatus(){}

    public static Status getStatus(String status) {
        switch (status.toUpperCase(Locale.ROOT)) {
            case "APPROVED": case "APPROVE":
                return Status.APPROVED;
            case "DENIED": case "DENY":
                return Status.DENIED;
            default:
                return Status.PENDING;
        }
    }
}
