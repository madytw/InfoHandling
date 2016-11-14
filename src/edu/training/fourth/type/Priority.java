package edu.training.fourth.type;

/**
 * Created by Roman on 12.11.2016.
 */
public enum Priority {
    VERY_LOW(0), LOW(1), NORMAL(2), HIGH(3), VERY_HIGH(4);

    private int priority;

    Priority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
