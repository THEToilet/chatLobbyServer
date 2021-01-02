package jp.ac.shibaura.it.ie.test;

import jp.ac.shibaura.it.ie.domain.application.user.UserUpdateInteractor;
import jp.ac.shibaura.it.ie.domain.model.session.SessionRepository;
import jp.ac.shibaura.it.ie.usecases.user.update.UserUpdateInputData;
import jp.ac.shibaura.it.ie.usecases.user.update.UserUpdateOutputData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserTest {

    @Autowired
    private UserUpdateInteractor userUpdateInteractor;

    @Autowired
    private SessionRepository sessionRepository;

    @Test
    public void testUserUpdateInteractor() throws Exception {
        UserUpdateOutputData outputData = userUpdateInteractor.handle(new UserUpdateInputData("22", "ok"));
    }
}
