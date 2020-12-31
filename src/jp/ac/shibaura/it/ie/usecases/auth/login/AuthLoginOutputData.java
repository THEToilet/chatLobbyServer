package jp.ac.shibaura.it.ie.usecases.auth.login;

import jp.ac.shibaura.it.ie.usecases.core.OutputData;

public class AuthLoginOutputData implements OutputData {
    private boolean success;
    private String session;

    public AuthLoginOutputData(boolean success, String session) {
        this.success = success;
        this.session = session;
    }

    private AuthLoginOutputData() {
    }

    public boolean isSuccess() {
        return success;
    }

    public String getSession() {
        return session;
    }
}
