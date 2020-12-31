package jp.ac.shibaura.it.ie.domain.application.user;

import com.google.inject.Inject;
import jp.ac.shibaura.it.ie.domain.model.user.User;
import jp.ac.shibaura.it.ie.domain.model.user.UserId;
import jp.ac.shibaura.it.ie.domain.model.user.UserName;
import jp.ac.shibaura.it.ie.domain.model.user.UserPassword;
import jp.ac.shibaura.it.ie.usecases.user.add.UserAddInputData;
import jp.ac.shibaura.it.ie.usecases.user.add.UserAddOutputData;
import jp.ac.shibaura.it.ie.usecases.user.add.UserAddUseCase;
import jp.ac.shibaura.it.ie.domain.model.user.UserRepository;

import java.util.UUID;

public class UserAddInteractor implements UserAddUseCase {

    @Inject
    private UserRepository userRepository;

    @Override
    public UserAddOutputData handle(UserAddInputData inputData) {
        String uuid = UUID.randomUUID().toString();

        User user = new User(
                new UserId(uuid),
                new UserName(inputData.getUserName()),
                new UserPassword(inputData.getPassword())
        );

        userRepository.save(user);

        return new UserAddOutputData(uuid);
    }
}
