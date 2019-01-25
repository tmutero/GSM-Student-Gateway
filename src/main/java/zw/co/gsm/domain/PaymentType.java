package zw.co.gsm.domain;


import java.util.Arrays;
import java.util.List;

public enum PaymentType {

    ECOCASH("Ecocash"),
    CASH("Cash"),
    ZIPT("ZIPT"),
    SWIPE("Swipe");

    private final String name;

    PaymentType(String name) {
        this.name = name;
    }

    public static List<PaymentType> asList() {
        return Arrays.asList(PaymentType.values());
    }

    public String getName() {
        return name;
    }

}
