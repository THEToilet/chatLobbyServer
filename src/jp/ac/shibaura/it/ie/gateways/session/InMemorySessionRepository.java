package jp.ac.shibaura.it.ie.gateways.session;

import jp.ac.shibaura.it.ie.domain.model.session.SessionRepository;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemorySessionRepository implements SessionRepository {
    private HashMap<String, String> sessions = new HashMap<>();

    @Override
    public void save(String session, String userId) {
        sessions.put(session, userId);
    }

    @Override
    public void remove(String session) {
        sessions.remove(session);
    }

    @Override
    public Optional<String> find(String session) {
        /**
        for (Map.Entry<String, String> entry : sessions.entrySet()) {
            System.out.println("Sessionの中身" + entry.getKey() + "：" + entry.getValue());
        }**/
        return Optional.ofNullable(sessions.get(session));
    }

}
