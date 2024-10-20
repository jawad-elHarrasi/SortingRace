package atl.model;

public enum Level {
    VERYEASY(100), EASY(1000), NORMAL(10000), HARD(100000), VERYHARD(1000000);

    private final int level;

    Level(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

}
