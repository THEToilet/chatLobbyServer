package jp.ac.shibaura.it.ie.domain.application.auth;

import com.google.inject.Inject;
import jp.ac.shibaura.it.ie.domain.model.session.SessionRepository;
import jp.ac.shibaura.it.ie.usecases.auth.login.AuthLoginOutputData;
import jp.ac.shibaura.it.ie.usecases.auth.login.AuthLoginInputData;
import jp.ac.shibaura.it.ie.usecases.auth.login.AuthLoginUseCase;

import java.util.UUID;

public class AuthLoginInteractor implements AuthLoginUseCase {

    @Inject
    private SessionRepository sessionRepository;

    @Override
    public AuthLoginOutputData handle(AuthLoginInputData inputData) {


        String uuid = UUID.randomUUID().toString();
        sessionRepository.save(inputData.getId(), uuid);
        return new AuthLoginOutputData(true, uuid);
    }
}