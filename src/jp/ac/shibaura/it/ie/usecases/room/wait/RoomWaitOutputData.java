package jp.ac.shibaura.it.ie.usecases.room.wait;

import jp.ac.shibaura.it.ie.usecases.core.OutputData;

public class RoomWaitOutputData implements OutputData {
    private String userId;

    public RoomWaitOutputData(String userId) {
        this.userId = userId;
    }

    private RoomWaitOutputData() {}

}
