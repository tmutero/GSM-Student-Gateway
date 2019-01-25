package zw.co.gsm.domain;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zinzombe and tafadzwa
 */

public enum Level {



    LEVEL_1_1("1.1"),
    LEVEL_1_2("1.2"),
    LEVEL_2_1("1.1"),
    LEVEL_2_2("2.2"),
    LEVEL_3_1("3.1"),
    LEVEL_3_2("3.2"),
    LEVEL_4_1("4.1"),
    LEVEL_4_2("4.2");


    private final String name;

    Level(String name) {
        this.name = name;
    }

    public static List<Level> asList() {
        return Arrays.asList(Level.values());
    }

    public String getName() {
        return name;
    }

}
