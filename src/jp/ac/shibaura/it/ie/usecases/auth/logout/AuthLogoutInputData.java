package jp.ac.shibaura.it.ie.usecases.auth.logout;

import jp.ac.shibaura.it.ie.usecases.core.InputData;
import jp.ac.shibaura.it.ie.usecases.auth.logout.AuthLogoutOutputData;

public class AuthLogoutInputData implements InputData<AuthLogoutOutputData> {
    private final String id;

    public AuthLogoutInputData(String id, String session) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}