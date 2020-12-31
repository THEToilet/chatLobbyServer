package jp.ac.shibaura.it.ie.usecases.user.add;

import jp.ac.shibaura.it.ie.usecases.core.OutputData;

public class UserAddOutputData implements OutputData {
    private String userId;

    public UserAddOutputData(String userId) {
        this.userId = userId;
    }

    private UserAddOutputData() {}

    public String getUserId() {
        return userId;
    }
}
