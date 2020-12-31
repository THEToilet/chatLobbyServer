package jp.ac.shibaura.it.ie.usecases.auth.logout;

import jp.ac.shibaura.it.ie.usecases.core.InputData;
import jp.ac.shibaura.it.ie.usecases.auth.logout.AuthLogoutOutputData;

public class AuthLogoutInputData implements InputData<AuthLogoutOutputData> {
    private final String id;
    private final String session;

    public AuthLogoutInputData(String id, String session) {
        this.id = id;
        this.session = session;
    }

    public String getId() {
        return id;
    }

    public String getSession() {
        return session;
    }
}