package jp.ac.shibaura.it.ie.contorollers;

import com.google.inject.Inject;
import jp.ac.shibaura.it.ie.domain.application.session.SessionInteractor;
import jp.ac.shibaura.it.ie.domain.application.user.UserUpdateInteractor;
import jp.ac.shibaura.it.ie.log.LogUtils;
import jp.ac.shibaura.it.ie.usecases.core.OutputData;
import jp.ac.shibaura.it.ie.usecases.session.SessionInputData;
import jp.ac.shibaura.it.ie.usecases.user.update.UserUpdateInputData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserUpdateInteractor userUpdateInteractor;

    @Autowired
    private SessionInteractor sessionInteractor;

    @Autowired
    private LogUtils logger;

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<String> userUpdate(@RequestHeader("session") String session, @RequestBody UserUpdateInputData inputDate) {
        if (!sessionInteractor.handle(new SessionInputData(session)).isSuccess()) {
            logger.warn("認証エラー");
            throw new RuntimeException();
        }
        userUpdateInteractor.handle(inputDate);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}