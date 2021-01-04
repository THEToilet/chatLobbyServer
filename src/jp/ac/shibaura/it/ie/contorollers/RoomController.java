package jp.ac.shibaura.it.ie.contorollers;

import jp.ac.shibaura.it.ie.domain.application.room.RoomExitInteractor;
import jp.ac.shibaura.it.ie.domain.application.room.RoomWaitInteractor;
import jp.ac.shibaura.it.ie.domain.application.session.SessionInteractor;
import jp.ac.shibaura.it.ie.log.LogUtils;
import jp.ac.shibaura.it.ie.usecases.room.exit.RoomExitInputData;
import jp.ac.shibaura.it.ie.usecases.room.wait.RoomWaitInputData;
import jp.ac.shibaura.it.ie.usecases.session.SessionInputData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jp.ac.shibaura.it.ie.usecases.core.OutputData;

@RestController
@RequestMapping("room")
public class RoomController {

    @Autowired
    private RoomWaitInteractor roomWaitInteractor;
    @Autowired
    private RoomExitInteractor roomExitInteractor;

    @Autowired
    private SessionInteractor sessionInteractor;

    @Autowired
    private LogUtils logger;


    @RequestMapping(value = "/{roomId}/exit", method = RequestMethod.GET)
    public ResponseEntity<String> roomExit(@RequestHeader("session") String session, @PathVariable("roomId") String roomId) {
        if (!sessionInteractor.handle(new SessionInputData(session)).isSuccess()) {
            logger.warn("認証エラー");
            throw new RuntimeException();
        }
        logger.info("room/exit:" + roomId);
        roomExitInteractor.handle(new RoomExitInputData(session, roomId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{roomId}/wait", method = RequestMethod.GET)
    public OutputData roomWait(@RequestHeader("session") String session, @PathVariable("roomId") String roomId) {
        if (!sessionInteractor.handle(new SessionInputData(session)).isSuccess()) {
            logger.warn("認証エラー");
            throw new RuntimeException();
        }
        logger.info("room/wait:" + roomId);
        return roomWaitInteractor.handle(new RoomWaitInputData(session, roomId));
    }
}