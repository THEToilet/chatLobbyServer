package jp.ac.shibaura.it.ie.domain.model.room;

import jp.ac.shibaura.it.ie.domain.model.user.User;
import jp.ac.shibaura.it.ie.domain.model.user.UserId;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    public void save(Room room);

    public void remove(Room room);


    public Room find(String categoryId);
}

