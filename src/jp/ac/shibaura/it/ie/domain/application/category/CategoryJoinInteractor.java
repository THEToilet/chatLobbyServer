package jp.ac.shibaura.it.ie.domain.application.category;

import com.google.inject.Inject;
import jp.ac.shibaura.it.ie.domain.model.room.Room;
import jp.ac.shibaura.it.ie.domain.model.room.RoomRepository;
import jp.ac.shibaura.it.ie.domain.model.session.SessionRepository;
import jp.ac.shibaura.it.ie.domain.model.user.UserRepository;
import jp.ac.shibaura.it.ie.log.LogUtils;
import jp.ac.shibaura.it.ie.usecases.category.join.CategoryJoinInputData;
import jp.ac.shibaura.it.ie.usecases.category.join.CategoryJoinOutputData;
import jp.ac.shibaura.it.ie.usecases.category.join.CategoryJoinUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CategoryJoinInteractor implements CategoryJoinUseCase {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LogUtils logger;

    @Override
    public CategoryJoinOutputData handle(CategoryJoinInputData inputData) {
        String uuid = "";
        logger.info("categoryIdは" + inputData.getCategoryId() + "デス");
        List<Room> rooms = roomRepository.findAll(inputData.getCategoryId());
        logger.info("Roomの数は:" + rooms.size());
        // ルームが存在する場合
        if (rooms.size() > 0) {
            // カテゴリーのユーザ数でソート
            //Collections.sort(rooms, Comparator.comparing(room -> room.getNumberOfUser()));
            for (int i = 0; i < rooms.size(); i++) {
                Room room = rooms.get(i);
                if (room.getNumberOfUser() < 4) {
                    room.joinRoom(inputData.getSession());
                    uuid = room.getRoomId();
                    break;
                }
            }
        }
        // ルームが存在しない場合
        if (uuid.isEmpty()) {
            logger.info("Roomを新しく作ります");
            uuid = UUID.randomUUID().toString();
            Room room = new Room(uuid, inputData.getCategoryId());
            room.joinRoom(inputData.getSession());
            roomRepository.save(room);
            logger.info("現在のRoom数は" + roomRepository.find(uuid).get().getCategoryId());
        }

        return new CategoryJoinOutputData(uuid);
    }
}
