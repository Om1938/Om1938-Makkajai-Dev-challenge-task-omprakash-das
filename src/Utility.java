public class Utility {

    private Utility() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Utility function to format currency values
     * 
     * @param value
     * @return Formatted string with decimal precision of 2
     */
    public static String format(float value) {
        return String.format("%.2f", value);
    }
}
