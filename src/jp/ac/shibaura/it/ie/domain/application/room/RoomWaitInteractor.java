package jp.ac.shibaura.it.ie.domain.application.room;

import com.google.inject.Inject;
import jp.ac.shibaura.it.ie.domain.model.user.*;
import jp.ac.shibaura.it.ie.usecases.auth.entry.AuthEntryOutputData;
import jp.ac.shibaura.it.ie.usecases.room.wait.RoomWaitInputData;
import jp.ac.shibaura.it.ie.usecases.room.wait.RoomWaitOutputData;
import jp.ac.shibaura.it.ie.usecases.room.wait.RoomWaitUseCase;

import java.util.UUID;

public class RoomWaitInteractor implements RoomWaitUseCase {
    @Inject
    private UserRepository userRepository;

    @Override
    public RoomWaitOutputData handle(RoomWaitInputData inputData) {

        return new RoomWaitOutputData("df");
    }
}