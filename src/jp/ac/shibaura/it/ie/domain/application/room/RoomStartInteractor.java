package jp.ac.shibaura.it.ie.domain.application.room;

import com.google.inject.Inject;
import jp.ac.shibaura.it.ie.domain.model.user.*;
import jp.ac.shibaura.it.ie.usecases.room.start.RoomStartInputData;
import jp.ac.shibaura.it.ie.usecases.room.start.RoomStartOutputData;
import jp.ac.shibaura.it.ie.usecases.room.start.RoomStartUseCase;

import java.util.UUID;

public class RoomStartInteractor implements RoomStartUseCase {
    @Inject
    private UserRepository userRepository;

    @Override
    public RoomStartOutputData handle(RoomStartInputData inputData) {

        return new RoomStartOutputData("f");
    }
}