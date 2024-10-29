package com.languageLearner.Game;

public class TextEntry {
    private String text;
    private String englishText;
    private String linkedText;
    private String englishLinkedText;
    private String helperText;
    private UUID uuid;

    public TextEntry(String text, String englishText, String linkedText, String englishLinkedText, String helperText, UUID uuid) {
        this.text = text;
        this.englishText = englishText;
        this.linkedText = linkedText;
        this.englishLinkedText = englishLinkedText;
        this.helperText = helperText;
        this.uuid = uuid;
    }

    public static TextEntry fromJson(JSONObject textJson) {
        String text = (String) textJson.get("text");
        String englishText = (String) textJson.get("englishText");
        String linkedText = (String) textJson.get("linkedText");
        String englishLinkedText = (String) textJson.get("englishLinkedText");
        String helperText = (String) textJson.get("helperText");
        UUID uuid = UUID.fromString((String) textJson.get("UUID"));

        return new TextEntry(text, englishText, linkedText, englishLinkedText, helperText, uuid);
    }
}
