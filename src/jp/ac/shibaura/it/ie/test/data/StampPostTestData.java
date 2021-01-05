package jp.ac.shibaura.it.ie.test.data;

public class StampPostTestData {
    private String userName;
    private String stampId;

    public StampPostTestData(String userName, String stampId){
        this.userName = userName;
        this.stampId = stampId;
    }
    public String getStampId() {
        return stampId;
    }

    public String getUserName() {
        return userName;
    }
}
