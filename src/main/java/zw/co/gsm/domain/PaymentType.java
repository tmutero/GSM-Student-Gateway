package zw.co.gsm.domain;


public enum PaymentType {

    ECOCASH("Ecocash"),
    CASH("Cash"),
    ZIPT("ZIPT"),
    SWIPE("Swipe");

    private final String name;

    PaymentType(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }


}
