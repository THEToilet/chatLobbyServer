package jp.ac.shibaura.it.ie.domain.model.room;

import jp.ac.shibaura.it.ie.domain.model.user.User;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private List<User> users = new ArrayList<>(4);
    private String id;
    private String categoryId;

    @Inject
    RoomRepository roomRepository;

    public Room(String id, String categoryId){
        this.id = id;
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public void joinRoom(User user){
        users.add(user);
    }
    public void exitRoom(User user){
        users.remove(user);
    }

}
