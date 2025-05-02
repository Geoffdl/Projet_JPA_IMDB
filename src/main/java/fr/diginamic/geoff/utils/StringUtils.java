package fr.diginamic.geoff.utils;

import static java.lang.Float.parseFloat;

public class StringUtils {


    public static String[] stringToArrayOfStrings(String string, String splitTarget) {
        if (string == null || string.isEmpty()) return null;
        try{
            return string.split(splitTarget);
        } catch (Exception e){
            return null;
        }

    }

    public static Float stringToFloat(String string) {
        if (string == null || string.isEmpty()) return null;
        String[] stringArr = string.split(" ");
        try {
            return parseFloat(stringArr[0].replace(",", "."));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}