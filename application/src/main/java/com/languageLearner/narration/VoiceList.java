package com.languageLearner.narration;



// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: Apache-2.0

import java.util.Iterator;
import java.util.stream.Stream;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.polly.PollyClient;
import software.amazon.awssdk.services.polly.model.DescribeVoicesRequest;
import software.amazon.awssdk.services.polly.model.DescribeVoicesResponse;
import software.amazon.awssdk.services.polly.model.PollyException;
import software.amazon.awssdk.services.polly.model.Voice;

public class VoiceList {
    private VoiceList(){};

    public static void showVoices(Region region){
        PollyClient polly = PollyClient.builder().region(region).build();

        displayVoices(polly);
        polly.close();
    }

    private static void displayVoices(PollyClient polly) {
        try {
            DescribeVoicesRequest describeVoiceRequest = DescribeVoicesRequest.builder()
                    .engine("standard")
                    .build();

            DescribeVoicesResponse describeVoicesResult = polly.describeVoices(describeVoiceRequest);
            Stream<Voice> voiceStream = describeVoicesResult.voices().stream();

            Iterator<Voice> voices = voiceStream.iterator();

            while(voices.hasNext()){
                Voice voice = voices.next();
                
                System.out.println(voice.name() + ": " + voice.genderAsString() + " " + voice.languageName());
                
            }

        } catch (PollyException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public static void main(String[] args){
        VoiceList.showVoices(Region.EU_WEST_3);
    }
    
}
