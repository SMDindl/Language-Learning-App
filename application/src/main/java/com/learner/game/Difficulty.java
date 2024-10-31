package com.learner.game;

/**
 * 
 */
public enum Difficulty {

    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard");

    private final String label;

    /**
     * 
     * @param label
     */
    Difficulty(String label) {
        this.label = label;
    }
    
    /**
     * 
     * @return
     */
    public String getLabel() {
        return label;
    }
}
