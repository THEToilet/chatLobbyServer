package jp.ac.shibaura.it.ie.domain.application.category;

import com.google.inject.Inject;
import jp.ac.shibaura.it.ie.domain.model.user.*;
import jp.ac.shibaura.it.ie.usecases.category.join.CategoryJoinInputData;
import jp.ac.shibaura.it.ie.usecases.category.join.CategoryJoinOutputData;
import jp.ac.shibaura.it.ie.usecases.category.join.CategoryJoinUseCase;
import jp.ac.shibaura.it.ie.usecases.user.add.UserAddInputData;
import jp.ac.shibaura.it.ie.usecases.user.add.UserAddOutputData;

import java.util.UUID;

public class CategoryJoinInteractor implements CategoryJoinUseCase {

    @Inject
    private UserRepository userRepository;

    @Override
    public CategoryJoinOutputData handle(CategoryJoinInputData inputData) {
        String uuid = UUID.randomUUID().toString();

        User user = new User(
                new UserId(uuid),
                new UserName(inputData.getUserName()),
                new UserPassword(inputData.getPassword())
        );

        userRepository.save(user);

        return new CategoryJoinOutputData(uuid);
    }
}
