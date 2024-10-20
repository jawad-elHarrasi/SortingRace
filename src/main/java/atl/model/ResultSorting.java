package atl.model;

import java.time.Duration;

public class ResultSorting {
    private final SortingType type;
    private final int size;
    private final int operations;
    private final Duration duration;

    public ResultSorting(SortingType type, int size, int operations, Duration duration) {
        this.type = type;
        this.size = size;
        this.operations = operations;
        this.duration = duration;
    }

    public String getSortingType() {
        return this.type.getDisplayName();
    }


    public int getSize() {
        return size;
    }

    public int getOperations() {
        return operations;
    }

    public long getDuration() {
        return duration.toMillis();
    }
}
