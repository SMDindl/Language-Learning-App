package com.languageLearner.data;

import java.util.UUID;

public class MissedQuestion {
    
    private DataKey dataKey;  
    private UUID uuid;      

    // Constructor
    public MissedQuestion(DataKey dataKey, UUID uuid) {
        this.dataKey = dataKey;
        this.uuid = uuid;
    }

    public UUID getQuestionUUID() {
        return uuid;
    }

    public DataKey getQuestionDataKey() {
        return dataKey;
    }

}
