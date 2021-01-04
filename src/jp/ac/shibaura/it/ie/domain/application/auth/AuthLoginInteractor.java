package jp.ac.shibaura.it.ie.domain.application.auth;

import jp.ac.shibaura.it.ie.domain.model.session.SessionRepository;
import jp.ac.shibaura.it.ie.log.LogUtils;
import jp.ac.shibaura.it.ie.usecases.auth.login.AuthLoginOutputData;
import jp.ac.shibaura.it.ie.usecases.auth.login.AuthLoginInputData;
import jp.ac.shibaura.it.ie.usecases.auth.login.AuthLoginUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Component
@Service
public class AuthLoginInteractor implements AuthLoginUseCase {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private LogUtils logger;

    @Override
    public AuthLoginOutputData handle(AuthLoginInputData inputData) {
        String uuid = UUID.randomUUID().toString();
        logger.info(uuid + ":" + inputData.getUserId());
        sessionRepository.save(uuid, inputData.getUserId());
        return new AuthLoginOutputData(uuid);
    }
}