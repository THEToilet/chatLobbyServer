package jp.ac.shibaura.it.ie.test;

import jp.ac.shibaura.it.ie.domain.application.auth.AuthEntryInteractor;
import jp.ac.shibaura.it.ie.domain.application.auth.AuthLoginInteractor;
import jp.ac.shibaura.it.ie.domain.application.auth.AuthLogoutInteractor;
import jp.ac.shibaura.it.ie.domain.model.session.SessionRepository;
import jp.ac.shibaura.it.ie.usecases.auth.entry.AuthEntryInputData;
import jp.ac.shibaura.it.ie.usecases.auth.entry.AuthEntryOutputData;
import jp.ac.shibaura.it.ie.usecases.auth.login.AuthLoginInputData;
import jp.ac.shibaura.it.ie.usecases.auth.login.AuthLoginOutputData;
import jp.ac.shibaura.it.ie.usecases.auth.logout.AuthLogoutInputData;
import jp.ac.shibaura.it.ie.usecases.auth.logout.AuthLogoutOutputData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AuthTest {

    @Autowired
    private AuthLoginInteractor authLoginInteractor;

    @Autowired
    private AuthLogoutInteractor authLogoutInteractor;

    @Autowired
    private AuthEntryInteractor authEntryInteractor;

    @Autowired
    private SessionRepository sessionRepository;

    @Test
    public void testEntryInteractor() throws Exception {
        String uuid = UUID.randomUUID().toString();
        AuthEntryOutputData outputData = authEntryInteractor.handle(new AuthEntryInputData(uuid, uuid, uuid));
    }

    @Test
    public void testLoginInteractor() throws Exception {
        AuthLoginOutputData outputData = authLoginInteractor.handle(new AuthLoginInputData("22", "test"));
        System.out.println(outputData.getSession());
        System.out.println(sessionRepository.find(outputData.getSession()).get());
    }

    @Test
    public void testLogoutInteractor() throws Exception {
        AuthLogoutOutputData outputData = authLogoutInteractor.handle(new AuthLogoutInputData("82ab97dd-d744-4953-aa96-3f13bc430da0"));
    }


}
