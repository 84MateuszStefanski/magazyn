package entities;

public enum OrderStatus {

    ACCEPTED_NOT_PAID("Accepted not paid"),
    ACCEPTED_PAID("Accepted paid"),
    IN_PROGRESS("In progress"),
    DURING_DELIVERY("During delivery"),
    COMPLETED("Completed"),
    CANCELED("Canceled");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
