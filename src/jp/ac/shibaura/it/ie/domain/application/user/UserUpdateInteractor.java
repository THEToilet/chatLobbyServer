package jp.ac.shibaura.it.ie.domain.application.user;

import jp.ac.shibaura.it.ie.domain.model.user.*;
import jp.ac.shibaura.it.ie.usecases.user.update.UserUpdateInputData;
import jp.ac.shibaura.it.ie.usecases.user.update.UserUpdateOutputData;
import jp.ac.shibaura.it.ie.usecases.user.update.UserUpdateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserUpdateInteractor implements UserUpdateUseCase {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserUpdateOutputData handle(UserUpdateInputData inputData) {
        User user = new User(
                new UserId(inputData.getUserId()),
                new UserName(inputData.getUserName()),
                new UserPassword("dummy")
        );

        userRepository.update(user);

        return new UserUpdateOutputData();
    }
}
