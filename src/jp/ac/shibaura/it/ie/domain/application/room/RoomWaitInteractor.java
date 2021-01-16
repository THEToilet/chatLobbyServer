package jp.ac.shibaura.it.ie.domain.application.room;

import jp.ac.shibaura.it.ie.domain.model.room.Room;
import jp.ac.shibaura.it.ie.domain.model.room.RoomRepository;
import jp.ac.shibaura.it.ie.domain.model.session.SessionRepository;
import jp.ac.shibaura.it.ie.domain.model.user.User;
import jp.ac.shibaura.it.ie.domain.model.user.UserRepository;
import jp.ac.shibaura.it.ie.log.LogUtils;
import jp.ac.shibaura.it.ie.usecases.room.start.RoomStartOutputData;
import jp.ac.shibaura.it.ie.usecases.room.start.RoomStartUserData;
import jp.ac.shibaura.it.ie.usecases.room.wait.RoomWaitInputData;
import jp.ac.shibaura.it.ie.usecases.room.wait.RoomWaitOutputData;
import jp.ac.shibaura.it.ie.usecases.room.wait.RoomWaitUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class RoomWaitInteractor implements RoomWaitUseCase {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LogUtils logger;

    private Map<String, Boolean> sent = new HashMap<String, Boolean>();

    @Override
    public RoomWaitOutputData handle(RoomWaitInputData inputData) {
        if (!Optional.ofNullable(sent.get(inputData.getRoomId())).isPresent()) {
            sent.put(inputData.getRoomId(), false);
        }
        Optional<Room> optionalRoom = roomRepository.find(inputData.getRoomId());
        if (!optionalRoom.isPresent()) {
            throw new RuntimeException();
        }
        Room room = optionalRoom.get();
        logger.info("room/wait : interactor" + room.getRoomId());
        logger.info("room/wait : interactor" + room.getNumberOfUser());
        // ここで人数が揃ったらアプリケーションサーバに飛ばす処理を書く
        boolean start = (room.getNumberOfUser() == room.getMaxNumberOfUser());
        // 4人揃ったらアプリケーションサーバに送信
        // だけど4人がこれにアクセスすると4回送ることになるからどうにか遷都
        if (start && !sent.get(inputData.getRoomId())) {
            logger.info("room/wait アプリケーション送信");
            // ここでroomを消してしまうと他の人がアクセスしたときにエラーがでる
            // これは一回だけ動かしたい
            // TODO : 人が残るエラー削除する
            List<String> sessions = room.getUsers();
            List<RoomStartUserData> userDataList = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                Optional<String> userId = sessionRepository.find(sessions.get(i));
                logger.info("room/Wait userId is " + userId.get());
                Optional<User> optionalUser = userRepository.find(userId.get());
                if (!optionalUser.isPresent()) {
                    throw new RuntimeException();
                }
                RoomStartUserData roomStartUserData = new RoomStartUserData(userId.get(), optionalUser.get().getName().getValue(), sessions.get(i));
                userDataList.add(roomStartUserData);
            }

            RoomStartOutputData roomStartOutputData = new RoomStartOutputData(userDataList, room.getCategoryId());
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> roomStart = restTemplate.postForEntity("http://localhost:8081/room/" + inputData.getRoomId() + "/start", roomStartOutputData, String.class);
            //roomRepository.remove(room.get());
            logger.info("RoomStart://" + roomStart);
            sent.replace(inputData.getRoomId(), true);
        }

        return new RoomWaitOutputData(room.getNumberOfUser(), sent.get(inputData.getRoomId()));
    }
}