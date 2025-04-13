public class TimeDuration extends Token {
    private final double value;
    private final String unit;

    public TimeDuration(String tokenText) {
        super(tokenText);
        String[] parts = tokenText.split("(?<=\\d)(?=[A-Za-z])");
        this.value = Double.parseDouble(parts[0]);
        this.unit = parts[1].toLowerCase(Locale.ENGLISH);
    }

    // Converts time to a canonical unit (e.g., milliseconds)
    public long getMilliseconds() {
        switch(unit) {
            case "ms": return (long) value;
            case "s": return (long) (value * 1000);
            case "m": return (long) (value * 60 * 1000);
            case "h": return (long) (value * 3600 * 1000);
            default: throw new IllegalArgumentException("Unknown time unit: " + unit);
        }
    }
}
