package atl.model;

public enum SortingType {
    BUBBLE_SORT("Bubble Sort"),
    MERGE_SORT("Merge Sort");
    private final String type;

    SortingType(String displayName) {
        this.type = displayName;
    }

    public String getDisplayName() {
        return type;
    }

}
