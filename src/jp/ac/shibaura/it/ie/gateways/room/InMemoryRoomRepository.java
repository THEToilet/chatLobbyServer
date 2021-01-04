package jp.ac.shibaura.it.ie.gateways.room;

import jp.ac.shibaura.it.ie.domain.model.room.Room;
import jp.ac.shibaura.it.ie.domain.model.room.RoomRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.*;

@Component
@Repository
public class InMemoryRoomRepository implements RoomRepository {
    private Map<String, Room> rooms = new LinkedHashMap<>();

    @Override
    public void save(Room room) {
        rooms.put(room.getRoomId(), room);
    }

    @Override
    public void remove(Room room) {
        rooms.remove(room.getRoomId());
    }

    @Override
    public Optional<Room> find(String roomId) {
        return Optional.ofNullable(rooms.get(roomId));
    }

    @Override
    public List<Room> findAll(String categoryId) {
        List<Room> roomList = new ArrayList<>();
        System.out.println("roomの数は？？？？" + rooms.size());
        rooms.forEach((roomId, room) -> {
            System.out.println("InMemoryRoomRepository:" + categoryId + "::::" + room.getCategoryId());
            if(room.getCategoryId().equals(categoryId)){
                System.out.println("追加しました");
                roomList.add(room);
            }
        });
        System.out.println("InmemoryRoomRepository roomlist.size:" + roomList.size());
        return roomList;

    }

}
