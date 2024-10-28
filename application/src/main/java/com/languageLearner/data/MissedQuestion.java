package com.languageLearner.data;

public class MissedQuestion {
    
    private DataKey dataKey;  
    private String uuid;      

    // Constructor
    public MissedQuestion(DataKey dataKey, String uuid) {
        this.dataKey = dataKey;
        this.uuid = uuid;
    }

    public String getQuestionUUID() {
        return uuid;
    }

    public DataKey getQuestionDataKey() {
        return dataKey;
    }

}
