package jp.ac.shibaura.it.ie.domain.application.auth;

import com.google.inject.Inject;
import jp.ac.shibaura.it.ie.domain.model.user.*;
import jp.ac.shibaura.it.ie.usecases.auth.entry.AuthEntryInputData;
import jp.ac.shibaura.it.ie.usecases.auth.entry.AuthEntryOutputData;
import jp.ac.shibaura.it.ie.usecases.auth.entry.AuthEntryUseCase;

import java.util.UUID;

public class AuthEntryInteractor implements AuthEntryUseCase {
    @Inject
    private UserRepository userRepository;

    @Override
    public AuthEntryOutputData handle(AuthEntryInputData inputData) {

        User user = new User(
                new UserId(inputData.getId()),
                new UserName(inputData.getName()),
                new UserPassword(inputData.getPassword())
        );

        userRepository.save(user);

        return new AuthEntryOutputData(true);
    }
}