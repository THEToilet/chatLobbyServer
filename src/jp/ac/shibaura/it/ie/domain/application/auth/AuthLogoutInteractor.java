package jp.ac.shibaura.it.ie.domain.application.auth;

import com.google.inject.Inject;
import jp.ac.shibaura.it.ie.domain.model.session.SessionRepository;
import jp.ac.shibaura.it.ie.usecases.auth.logout.AuthLogoutInputData;
import jp.ac.shibaura.it.ie.usecases.auth.logout.AuthLogoutOutputData;
import jp.ac.shibaura.it.ie.usecases.auth.logout.AuthLogoutUseCase;

public class AuthLogoutInteractor implements AuthLogoutUseCase {
    @Inject
    private SessionRepository sessionRepository;

    @Override
    public AuthLogoutOutputData handle(AuthLogoutInputData inputData) {
        sessionRepository.remove(inputData.getId());
        return new AuthLogoutOutputData(true);
    }
}
