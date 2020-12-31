package jp.ac.shibaura.it.ie.contorollers;

import com.google.inject.Inject;
import jp.ac.shibaura.it.ie.domain.application.room.RoomIdInteractor;
import jp.ac.shibaura.it.ie.domain.application.room.RoomIdJoinInteractor;
import jp.ac.shibaura.it.ie.domain.application.room.RoomIdWaitInteractor;
import jp.ac.shibaura.it.ie.domain.application.room.RoomListInteractor;
import jp.ac.shibaura.it.ie.usecases.room.list.RoomListInputData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jp.ac.shibaura.it.ie.usecases.core.OutputData;

@RestController
@RequestMapping("room")
public class RoomController {

    @Inject
    private RoomListInteractor roomListInteractor;
    @Inject
    private RoomIdInteractor roomIdInteractor;
    @Inject
    private RoomIdJoinInteractor roomIdJoinInteractor;
    @Inject
    private RoomIdWaitInteractor roomIdWaitInteractor;
    @Inject
    private RoomIdExitInteraxtor roomIdExitInteraxtor;

    @RequestMapping(value = "/:roomId", method = RequestMethod.GET)
    public OutputData roomId(@RequestParam("session") String session) {
        RoomIdInputData inputData = new RoomIdInputData(session);
        return roomIdInteractor.handle(inputData);
    }
    @RequestMapping(value = "/:roomId/join", method = RequestMethod.GET)
    public OutputData roomIdJoin(@RequestParam("session") String session) {
        RoomIdJoinInputData inputData = new RoomInJoinInputData(session);
        return roomIdJoinInteractor.handle(inputData);
    }

    @RequestMapping(value = "/:roomId/exit", method = RequestMethod.GET)
    public OutputData roomIdExit(@RequestParam("session") String session) {
        RoomIdExitInputData inputData = new RoomInExitInputData(session);
        return roomIdExitInteractor.handle(inputData);
    }

    @RequestMapping(value = "/:roomId/wait", method = RequestMethod.GET)
    public OutputData roomIdWait(@RequestParam("session") String session, RequestParam("groupSession") String groupSession) {
        RoomIdWaitInputData inputData = new RoomIdWaitInputData(session);
        return roomIdWaitInteractor.handle(inputData);
    }
}