package jp.ac.shibaura.it.ie.usecases.user.add;

import jp.ac.shibaura.it.ie.usecases.core.InputData;
import jp.ac.shibaura.it.ie.domain.model.user.UserPassword;

public class UserAddInputData implements InputData<UserAddOutputData> {
    private final String userName;
    private final UserPassword role;

    public UserAddInputData(String userName, UserPassword role) {
        this.userName = userName;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public UserPassword getRole() {
        return role;
    }

    public String getPassword() {return "sdf"; }
}
