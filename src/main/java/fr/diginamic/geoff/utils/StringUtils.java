package fr.diginamic.geoff.utils;

public class StringUtils {


    public static String[] stringToArrayOfStrings(String string, String splitTarget){
        return string.split(splitTarget);
    }
}