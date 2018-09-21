package utils;

public class Padder {
    private final int STRLEN = 10;

    public Padder() {
    }
    public String padString(String str){
        StringBuilder paddedName = new StringBuilder(str);
        while(paddedName.length()<STRLEN){
            paddedName.append(" ");
        }
        return String.valueOf(paddedName);
    }
}
