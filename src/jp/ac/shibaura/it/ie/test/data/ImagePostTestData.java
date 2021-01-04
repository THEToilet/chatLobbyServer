package jp.ac.shibaura.it.ie.test.data;

import jp.ac.shibaura.it.ie.usecases.image.ImageListOutputData;

public class ImagePostTestData {
    private String imageSource;
    private String fileName;
    private String fileExtension;
    public ImagePostTestData(String imageSource, String fileName, String fileExtension){
        this.imageSource = imageSource;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }
}

