public class Page {
    // Attributes
    private String pageNumber;
    private String text;
    private String englishText;
    private String image;

    // Constructor
    public Page(String pageNumber, String text, String englishText, String image) {
        this.pageNumber = pageNumber;
        this.text = text;
        this.englishText = englishText;
        this.image = image;
    }

    // Methods
    public String getText() {
        return text;
    }

    public String getEnglishText() {
        return englishText;
    }

    public int getPageNumber() {
        return this.pageNumber.charAt(0) - '0';
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = "" + pageNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void getContent() {
        System.out.println(getImage());
        System.out.println(getEnglishText());
        System.out.println(getText());
        System.out.println(getPageNumber());
    }
}
