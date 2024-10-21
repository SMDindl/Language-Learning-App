package com.languageLearner.narration;

public class NarrateTest {
    public static void main(String[] args){

        String s = "Kumusta ka na?";
        
        Narrator.playSound(s);
        System.out.println(s);
    }
}