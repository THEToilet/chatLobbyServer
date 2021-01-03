package jp.ac.shibaura.it.ie.usecases.auth.login;

import jp.ac.shibaura.it.ie.usecases.core.InputData;

public class AuthLoginInputData implements InputData<AuthLoginOutputData> {
    private final String userId;
    private final String userPassword;

    public AuthLoginInputData(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }
}