package jp.ac.shibaura.it.ie.usecases.auth.login;

import jp.ac.shibaura.it.ie.usecases.core.OutputData;

public class AuthLoginOutputData implements OutputData {
    private String session;
    private String userName;

    public AuthLoginOutputData(String session, String userName) {
        this.session = session;
        this.userName = userName;
    }

    public String getSession() {
        return session;
    }

    public String getUserName() {
        return userName;
    }
}
