package com.learner.game;

import java.util.UUID;

public class Language {

    private final UUID uuid;
    private final String languageName;

    public Language(UUID uuid, String languageName) {
        this.uuid = uuid;
        this.languageName = languageName;
    }

    // Getters
    public Language getLanguage() {
        return this;
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getLanguageName() {
        return languageName;
    }

    @Override
    public String toString() {
        return "\u001B[34m- Language: " + languageName + "\u001B[0m\n  UUID: " + uuid + "\n";
    }

}
