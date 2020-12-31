package jp.ac.shibaura.it.ie.contorollers;

import com.google.inject.Inject;
import jp.ac.shibaura.it.ie.domain.application.user.UserUpdateInteractor;
import jp.ac.shibaura.it.ie.usecases.core.OutputData;
import jp.ac.shibaura.it.ie.usecases.user.update.UserUpdateInputData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Inject
    UserUpdateInteractor userUpdateInteractor;

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public OutputData userUpdate(@RequestParam("session") String session, @RequestParam("userId") String userId) {
        UserUpdateInputData inputDate = new UserUpdateInputData(userId,session);
        return userUpdateInteractor.handle(inputDate);
    }
}