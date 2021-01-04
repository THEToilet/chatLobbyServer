package jp.ac.shibaura.it.ie.test.data;

public class UserUpdateTestDate {
    private String userId;
    private String userName;
    public UserUpdateTestDate(String userId, String userName){
        this.userId = userId;
        this.userName= userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}
