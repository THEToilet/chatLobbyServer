package jp.ac.shibaura.it.ie.contorollers;

import com.google.inject.Inject;
import jp.ac.shibaura.it.ie.usecases.auth.entry.AuthEntryInputData;
import jp.ac.shibaura.it.ie.usecases.auth.login.AuthLoginInputData;
import jp.ac.shibaura.it.ie.domain.application.auth.AuthLoginInteractor;
import jp.ac.shibaura.it.ie.domain.application.auth.AuthLogoutInteractor;
import jp.ac.shibaura.it.ie.domain.application.auth.AuthEntryInteractor;
import jp.ac.shibaura.it.ie.usecases.auth.logout.AuthLogoutInputData;
import jp.ac.shibaura.it.ie.usecases.core.OutputData;

import org.springframework.web.bind.annotation.*;


@RestController
public class AuthController {

    @Inject
    private AuthLoginInteractor authLoginInteractor;

    @Inject
    private AuthEntryInteractor authEntryInteractor;

    @Inject
    private AuthLogoutInteractor authLogoutInteractor;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public OutputData login(@RequestParam("id") String id, @RequestParam("password") String password) {
        AuthLoginInputData inputData = new AuthLoginInputData(id, password);
        return authLoginInteractor.handle(inputData);
    }

    @RequestMapping(value = "/entry", method = RequestMethod.POST)
    public OutputData entry(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("password") String password) {
        AuthEntryInputData inputData = new AuthEntryInputData(id, name, password);
        return authEntryInteractor.handle(inputData);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public OutputData logout(@RequestParam("id") String id, @RequestParam("session") String session) {
        AuthLogoutInputData inputData = new AuthLogoutInputData(id, session);
        return authLogoutInteractor.handle(inputData);
    }
}