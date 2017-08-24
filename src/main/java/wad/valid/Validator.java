package wad.valid;

public abstract class Validator {

    protected static boolean isInteger(Object obj) {
        try {
            String input = (String)obj;
            int i = Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    protected static boolean isLong(Object obj) {
        try {
            String input = (String)obj;
            Long l = Long.parseLong(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
