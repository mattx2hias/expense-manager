package dev.matthias.utilities;

public class ParseStatus {
    private ParseStatus(){}

    public static Status getStatus(String status) {
        switch (status) {
            case "APPROVED":
                return Status.APPROVED;
            case "DENIED":
                return Status.DENIED;
            default:
                return Status.PENDING;
        }
    }
}
