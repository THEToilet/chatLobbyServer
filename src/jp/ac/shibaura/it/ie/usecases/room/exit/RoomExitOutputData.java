package jp.ac.shibaura.it.ie.usecases.room.exit;

import jp.ac.shibaura.it.ie.usecases.core.OutputData;

public class RoomExitOutputData implements OutputData {
    private String userId;

    public RoomExitOutputData(String userId) {
        this.userId = userId;
    }

    private RoomExitOutputData() {}

    public String getUserId() {
        return userId;
    }
}
