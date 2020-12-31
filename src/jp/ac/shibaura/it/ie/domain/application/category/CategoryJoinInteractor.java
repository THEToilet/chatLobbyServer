package jp.ac.shibaura.it.ie.domain.application.category;

import com.google.inject.Inject;
import jp.ac.shibaura.it.ie.domain.model.room.Room;
import jp.ac.shibaura.it.ie.domain.model.room.RoomRepository;
import jp.ac.shibaura.it.ie.domain.model.session.SessionRepository;
import jp.ac.shibaura.it.ie.domain.model.user.UserRepository;
import jp.ac.shibaura.it.ie.usecases.category.join.CategoryJoinInputData;
import jp.ac.shibaura.it.ie.usecases.category.join.CategoryJoinOutputData;
import jp.ac.shibaura.it.ie.usecases.category.join.CategoryJoinUseCase;

import java.util.UUID;

public class CategoryJoinInteractor implements CategoryJoinUseCase {

    @Inject
    private RoomRepository roomRepository;

    @Inject
    private SessionRepository sessionRepository;

    @Inject
    private UserRepository userRepository;

    @Override
    public CategoryJoinOutputData handle(CategoryJoinInputData inputData) {
        String uuid;
        Room room = roomRepository.find(inputData.getCategoryId());
        // 未完成グループがなかったら
        if(room == null){
            uuid = UUID.randomUUID().toString();
            room = new Room(uuid, inputData.getCategoryId());
            roomRepository.save(room);
        }
        else{
            room.joinRoom(userRepository.find(sessionRepository.find(inputData.getUserId())).get());
            uuid = room.getId();
        }

        return new CategoryJoinOutputData(uuid);
    }
}
