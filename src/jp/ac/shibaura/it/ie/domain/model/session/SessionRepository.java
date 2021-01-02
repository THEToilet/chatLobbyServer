package jp.ac.shibaura.it.ie.domain.model.session;

import jp.ac.shibaura.it.ie.domain.model.user.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
public interface SessionRepository {
    public void save(String session, String userId);
    public void remove(String session);
    public Optional<String> find(String session);
}
