package jp.ac.shibaura.it.ie.domain.application.user;

import com.google.inject.Inject;
import jp.ac.shibaura.it.ie.domain.model.user.*;
import jp.ac.shibaura.it.ie.usecases.user.add.UserAddOutputData;
import jp.ac.shibaura.it.ie.usecases.user.update.UserUpdateInputData;
import jp.ac.shibaura.it.ie.usecases.user.update.UserUpdateOutputData;
import jp.ac.shibaura.it.ie.usecases.user.update.UserUpdateUseCase;

import java.util.UUID;

public class UserUpdateInteractor implements UserUpdateUseCase {

    @Inject
    private UserRepository userRepository;

    @Override
    public UserUpdateOutputData handle(UserUpdateInputData inputData) {
        String uuid = UUID.randomUUID().toString();

        User user = new User(
                new UserId(uuid),
                new UserName(inputData.getUserName()),
                new UserPassword("dummy")
        );

        userRepository.update(user);

        return new UserUpdateOutputData();
    }
}
