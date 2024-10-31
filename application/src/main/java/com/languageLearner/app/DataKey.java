package com.languageLearner.app;

import java.util.UUID;

/**
 * For objects storing mutiple uuids
 */
public class DataKey {
    
    private UUID languageUUID;
    private UUID gameUUID;
    private UUID generalUUID;

    DataKey(UUID languageUUID, UUID gameUUID, UUID generalUUID) {
        this.languageUUID = languageUUID;
        this.gameUUID = gameUUID;
        this.generalUUID = generalUUID;
    }

    public UUID getLanguageUUID() {
        return languageUUID;
    }
    
    public UUID getGameUUID() {
        return gameUUID;
    }
    
    public UUID getGeneralUUID() {
        return generalUUID;
    }
    

}
