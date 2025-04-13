@DirectiveMeta(name = "aggregateSizeAndTime", usage = "Aggregates byte size and time duration columns", type = DirectiveType.AGGREGATE)
public class AggregateSizeAndTimeDirective implements Directive, Aggregate {

    private String sizeColumn;
    private String timeColumn;
    private String targetSizeColumn;
    private String targetTimeColumn;
    private String sizeUnit = "bytes";  // optional
    private String timeUnit = "nanoseconds"; // optional
    private String aggType = "total"; // optional: total or average

    // Used for storing aggregates
    private long totalBytes = 0;
    private long totalNanos = 0;
    private int rowCount = 0;

    @Override
    public UsageDefinition define() {
        return UsageDefinition.builder()
            .addRequiredArg("source_size_col")
            .addRequiredArg("source_time_col")
            .addRequiredArg("target_size_col")
            .addRequiredArg("target_time_col")
            .addOptionalArg("size_unit", "bytes|kb|mb|gb")
            .addOptionalArg("time_unit", "nanoseconds|ms|s|min")
            .addOptionalArg("agg_type", "total|average")
            .build();
    }

    @Override
    public void initialize(Arguments arguments) throws DirectiveParseException {
        this.sizeColumn = arguments.value("source_size_col");
        this.timeColumn = arguments.value("source_time_col");
        this.targetSizeColumn = arguments.value("target_size_col");
        this.targetTimeColumn = arguments.value("target_time_col");

        if (arguments.has("size_unit")) this.sizeUnit = arguments.value("size_unit").toLowerCase();
        if (arguments.has("time_unit")) this.timeUnit = arguments.value("time_unit").toLowerCase();
        if (arguments.has("agg_type")) this.aggType = arguments.value("agg_type").toLowerCase();
    }
