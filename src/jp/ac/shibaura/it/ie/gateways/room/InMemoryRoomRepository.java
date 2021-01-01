package jp.ac.shibaura.it.ie.gateways.room;

import jp.ac.shibaura.it.ie.domain.model.room.Room;
import jp.ac.shibaura.it.ie.domain.model.room.RoomRepository;
import jp.ac.shibaura.it.ie.domain.model.session.SessionRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class InMemoryRoomRepository implements RoomRepository {
    private HashMap<String, Room> rooms = new HashMap<>();
    @Override
    public void save(Room room) {
        rooms.put(room.getId(),room);
    }

    @Override
    public void remove(Room room) {
        rooms.remove(room.getId());
    }


    @Override
    public Room find(String categoryId){
        return rooms.get(categoryId);
    }

}
