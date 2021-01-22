package jp.ac.shibaura.it.ie.contorollers;

import jp.ac.shibaura.it.ie.domain.application.session.SessionInteractor;
import jp.ac.shibaura.it.ie.log.LogUtils;
import jp.ac.shibaura.it.ie.usecases.auth.entry.AuthEntryInputData;
import jp.ac.shibaura.it.ie.usecases.auth.login.AuthLoginInputData;
import jp.ac.shibaura.it.ie.domain.application.auth.AuthLoginInteractor;
import jp.ac.shibaura.it.ie.domain.application.auth.AuthLogoutInteractor;
import jp.ac.shibaura.it.ie.domain.application.auth.AuthEntryInteractor;
import jp.ac.shibaura.it.ie.usecases.auth.logout.AuthLogoutInputData;
import jp.ac.shibaura.it.ie.usecases.core.OutputData;

import jp.ac.shibaura.it.ie.usecases.session.SessionInputData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@EnableAutoConfiguration
@RestController
@Component
public class AuthController {

    @Autowired
    private AuthLoginInteractor authLoginInteractor;

    @Autowired
    private AuthEntryInteractor authEntryInteractor;

    @Autowired
    private AuthLogoutInteractor authLogoutInteractor;

    @Autowired
    private SessionInteractor sessionInteractor;

    @Autowired
    private LogUtils logger;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public OutputData login(@RequestBody AuthLoginInputData inputData) {
        return authLoginInteractor.handle(inputData);
    }

    @RequestMapping(value = "/entry", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity entry(@RequestBody AuthEntryInputData inputData) {
        logger.info("entry" + inputData.getId()+inputData.getName()+inputData.getPassword());
        logger.info(inputData.toString());
        authEntryInteractor.handle(inputData);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity logout(@RequestHeader("session") String session) {
        if(!sessionInteractor.handle(new SessionInputData(session)).isSuccess()){
            logger.warn("認証エラー");
            throw new RuntimeException();
        }
        authLogoutInteractor.handle(new AuthLogoutInputData(session));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}