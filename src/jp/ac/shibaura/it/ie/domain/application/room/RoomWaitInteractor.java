package jp.ac.shibaura.it.ie.domain.application.room;

import com.google.inject.Inject;
import jp.ac.shibaura.it.ie.domain.model.user.*;
import jp.ac.shibaura.it.ie.usecases.auth.entry.AuthEntryOutputData;
import jp.ac.shibaura.it.ie.usecases.room.wait.RoomWaitInputData;
import jp.ac.shibaura.it.ie.usecases.room.wait.RoomWaitOutputData;
import jp.ac.shibaura.it.ie.usecases.room.wait.RoomWaitUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RoomWaitInteractor implements RoomWaitUseCase {
    @Autowired
    private UserRepository userRepository;

    @Override
    public RoomWaitOutputData handle(RoomWaitInputData inputData) {

        return new RoomWaitOutputData("df");
    }
}