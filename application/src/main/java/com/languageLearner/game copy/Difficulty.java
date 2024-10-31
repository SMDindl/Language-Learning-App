package com.languageLearner.game;

/**
 * 
 */
public enum Difficulty {

    EASY(1, "Easy"),
    MEDIUM(2, "Medium"),
    HARD(3, "Hard");

    private final int position;
    private final String label;

    /**
     * 
     * @param position
     * @param label
     */
    Difficulty(int position, String label) {
        this.position = position;
        this.label = label;
    }

    /**
     * 
     * @return
     */
    public int getPosition() {
        return position;
    }

    /**
     * 
     * @return
     */
    public String getLabel() {
        return label;
    }
}
