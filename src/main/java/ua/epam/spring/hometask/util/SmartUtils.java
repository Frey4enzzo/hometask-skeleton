package ua.epam.spring.hometask.util;

import java.util.Collection;

public class SmartUtils {

    public static boolean isEmpty(Object o) {
        return o == null;
    }

    public static boolean isEmpty(String str) {
        if ( str==null || str.equals("")) return true;
        else return false;
    }

    public static boolean isEmpty(Collection collection) {
        return collection.size() == 0;
    }
}
