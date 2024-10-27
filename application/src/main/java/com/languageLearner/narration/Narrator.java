package com.languageLearner.narration;

import java.io.IOException;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackListener;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.polly.PollyClient;
import software.amazon.awssdk.services.polly.model.DescribeVoicesRequest;
import software.amazon.awssdk.services.polly.model.DescribeVoicesResponse;
import software.amazon.awssdk.services.polly.model.OutputFormat;
import software.amazon.awssdk.services.polly.model.PollyException;
import software.amazon.awssdk.services.polly.model.SynthesizeSpeechRequest;
import software.amazon.awssdk.services.polly.model.SynthesizeSpeechResponse;
import software.amazon.awssdk.services.polly.model.Voice;

public class Narrator {
    private Narrator() {};

    // Play sound using Miguel's voice
    public static void playSoundMiguel(String text) {
        playSound(text, "Miguel");
    }

    // Play sound using Russell's voice
    public static void playSoundRussell(String text) {
        playSound(text, "Russell");
    }

    public static void playSoundZayd(String text) {
        playSound(text, "Zayd");
    }




    // Main playSound method that takes text and voice name as parameters
    private static void playSound(String text, String voiceName) {
        PollyClient polly = PollyClient.builder().region(Region.EU_WEST_3).build();

        talkPolly(polly, text, voiceName);
        polly.close();
    }

    private static void talkPolly(PollyClient polly, String text, String voiceName) {
        try {
            DescribeVoicesRequest describeVoiceRequest = DescribeVoicesRequest.builder()
                    .engine("standard")
                    .build();

            DescribeVoicesResponse describeVoicesResult = polly.describeVoices(describeVoiceRequest);
            Voice voice = describeVoicesResult.voices().stream()
                    .filter(v -> v.name().equals(voiceName))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Voice not found"));

            InputStream stream = synthesize(polly, text, voice, OutputFormat.MP3);
            AdvancedPlayer player = new AdvancedPlayer(stream,
                    javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice());
                    
            player.setPlayBackListener(new PlaybackListener(){});

            player.play();

        } catch (PollyException | JavaLayerException | IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public static InputStream synthesize(PollyClient polly, String text, Voice voice, OutputFormat format)
            throws IOException {
        SynthesizeSpeechRequest synthReq = SynthesizeSpeechRequest.builder()
                .text(text)
                .voiceId(voice.id())
                .outputFormat(format)
                .build();

        ResponseInputStream<SynthesizeSpeechResponse> synthRes = polly.synthesizeSpeech(synthReq);
        return synthRes;
    }
}
