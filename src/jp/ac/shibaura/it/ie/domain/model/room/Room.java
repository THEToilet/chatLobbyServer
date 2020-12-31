package jp.ac.shibaura.it.ie.domain.model.room;

import jp.ac.shibaura.it.ie.domain.model.user.User;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private List<User> users = new ArrayList<>(4);
    private String id;
    private String categoryNumber;

    @Inject
    RoomRepository roomRepository;

    public Room(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
