package de.kohlerjens.springboot.jpa.tools;

import java.sql.Date;

public class Utils {
    public static boolean checkNullString(String stringToCheck) {
        return stringToCheck != null;
    }
    public static boolean checkNullString(Date stringToCheck) {
        return stringToCheck != null;
    }
}
