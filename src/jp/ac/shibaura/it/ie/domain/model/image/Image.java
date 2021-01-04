package jp.ac.shibaura.it.ie.domain.model.image;

public class Image {
    private String imageUrl;
    private String fileName;
    private String fileExtension;

    public Image(String imageUrl, String fileName, String fileExtension) {
        this.imageUrl = imageUrl;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public String getFileName() {
        return fileName;
    }
}
