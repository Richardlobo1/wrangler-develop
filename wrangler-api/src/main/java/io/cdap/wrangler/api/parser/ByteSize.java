public class ByteSize extends Token {
    private final double value;
    private final String unit;

    public ByteSize(String tokenText) {
        super(tokenText);
        // A simple parser: split numeric value from unit (e.g., "10KB")
        // Use regex or a simple loop for more robust parsing.
        String[] parts = tokenText.split("(?<=\\d)(?=[A-Za-z])");
        this.value = Double.parseDouble(parts[0]);
        this.unit = parts[1].toUpperCase(Locale.ENGLISH);
    }

    // Converts to canonical unit (bytes); consider using 1024 or 1000 as your base.
    public long getBytes() {
        switch(unit) {
            case "B": return (long) value;
            case "KB": return (long) (value * 1024);
            case "MB": return (long) (value * 1024 * 1024);
            case "GB": return (long) (value * 1024 * 1024 * 1024);
            default: throw new IllegalArgumentException("Unknown byte unit: " + unit);
        }
    }
}
