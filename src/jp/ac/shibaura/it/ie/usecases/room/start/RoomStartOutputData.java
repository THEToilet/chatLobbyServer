package jp.ac.shibaura.it.ie.usecases.room.start;

import jp.ac.shibaura.it.ie.usecases.core.OutputData;

import java.util.ArrayList;
import java.util.List;

public class RoomStartOutputData implements OutputData {
    private List<RoomStartUserData> users = new ArrayList<>();
    private String categoryId;

    public RoomStartOutputData(List<RoomStartUserData> users, String categoryId) {
        this.users = users;
        this.categoryId = categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public List<RoomStartUserData> getUsers() {
        return users;
    }

    public void setUsers(List<RoomStartUserData> users) {
        this.users = users;
    }
}
