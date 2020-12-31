package jp.ac.shibaura.it.ie.contorollers;

import com.google.inject.Inject;
import jp.ac.shibaura.it.ie.domain.application.room.RoomExitInteractor;
import jp.ac.shibaura.it.ie.domain.application.room.RoomStartInteractor;
import jp.ac.shibaura.it.ie.domain.application.room.RoomWaitInteractor;
import jp.ac.shibaura.it.ie.usecases.room.exit.RoomExitInputData;
import jp.ac.shibaura.it.ie.usecases.room.start.RoomStartInputData;
import jp.ac.shibaura.it.ie.usecases.room.wait.RoomWaitInputData;
import org.springframework.web.bind.annotation.*;
import jp.ac.shibaura.it.ie.usecases.core.OutputData;

@RestController
@RequestMapping("room")
public class RoomController {

    @Inject
    private RoomStartInteractor roomStartInteractor;
    @Inject
    private RoomWaitInteractor roomWaitInteractor;
    @Inject
    private RoomExitInteractor roomExitInteractor;

    @RequestMapping(value = "/{roomId}/start", method = RequestMethod.GET)
    public OutputData roomJoin(@RequestParam("session") String session, @RequestParam("userId") String userId, @PathVariable("roomId") String roomId) {
        RoomStartInputData inputData = new RoomStartInputData(session, userId, roomId);
        return roomStartInteractor.handle(inputData);
    }

    @RequestMapping(value = "/{roomId}/exit", method = RequestMethod.GET)
    public OutputData roomExit(@RequestParam("session") String session, @RequestParam("userId") String userId, @PathVariable("roomId") String roomId) {
        RoomExitInputData inputData = new RoomExitInputData(session, userId, roomId);
        return roomExitInteractor.handle(inputData);
    }

    @RequestMapping(value = "/{roomId}/wait", method = RequestMethod.GET)
    public OutputData roomWait(@RequestParam("session") String session, @RequestParam("userId") String userId, @PathVariable("roomId") String roomId) {
        RoomWaitInputData inputData = new RoomWaitInputData(session, userId, roomId);
        return roomWaitInteractor.handle(inputData);
    }
}