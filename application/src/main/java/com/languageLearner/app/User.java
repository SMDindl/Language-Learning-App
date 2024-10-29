package com.languageLearner.app;

import java.util.HashMap;
import java.util.UUID;

public class User {

    private String username;
    private String email;
    private HashMap<UUID, Progress> progressMap;


    /**
     * Inner nestest class used for traking user progress
     */
    public class Progress {

        public Progress(String langauge) {
            
        }

    }

    
}
