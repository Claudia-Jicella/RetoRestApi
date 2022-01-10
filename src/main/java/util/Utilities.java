package util;

public class Utilities {

    private Utilities() {
    }

    public static String getUserDir(){
        return System.getProperty("user.dir");
    }

    public static String defineOS(){
        return System.getProperty("os.name");
    }
}
