package jp.ac.shibaura.it.ie.gateways.session;

import jp.ac.shibaura.it.ie.domain.model.session.SessionRepository;

import java.util.HashMap;

public class InMemorySessionRepository implements SessionRepository {
    private HashMap<String, String> sessions = new HashMap<>();
    @Override
    public void save(String userId, String session) {
        sessions.put(userId,session);
    }

    @Override
    public void remove(String userId) {
        sessions.remove(userId);
    }

    @Override
    public String find(String userId) {
        return sessions.get(userId);
    }

}
