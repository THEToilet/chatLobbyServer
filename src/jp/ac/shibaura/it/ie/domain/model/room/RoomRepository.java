package jp.ac.shibaura.it.ie.domain.model.room;

import jp.ac.shibaura.it.ie.domain.model.user.User;
import jp.ac.shibaura.it.ie.domain.model.user.UserId;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    public void save(User user);

    public void remove(User user);

    public List<User> findAll();

    public Optional<User> find(UserId id);
}

