package jp.ac.shibaura.it.ie.usecases.room.wait;

import jp.ac.shibaura.it.ie.domain.model.user.UserPassword;
import jp.ac.shibaura.it.ie.usecases.core.InputData;

public class RoomWaitInputData implements InputData<RoomWaitOutputData> {
    private final String session;
    private final String userId;
    private final String roomId;

    public RoomWaitInputData(String session, String userId, String roomId) {
        this.session = session;
        this.userId = userId;
        this.roomId = roomId;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getSession() {
        return session;
    }

    public String getUserId() {
        return userId;
    }
}
