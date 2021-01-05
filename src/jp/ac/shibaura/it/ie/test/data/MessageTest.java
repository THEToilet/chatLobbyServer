package jp.ac.shibaura.it.ie.test.data;

import java.util.ArrayList;
import java.util.List;

public class MessageTest {
    private String messageId;
    private String postedUser;
    private String imageUrl;
    private String fileName;
    private String fileExtension;
    private List<Stamp> stampList = new ArrayList<Stamp>();

    public String getMessageId() {
        return messageId;
    }

    public String getPostedUser() {
        return postedUser;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public List<Stamp> getStampList() {
        return stampList;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setPostedUser(String postedUser) {
        this.postedUser = postedUser;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public void setStampList(List<Stamp> stampList) {
        this.stampList = stampList;
    }
}
