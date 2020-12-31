package jp.ac.shibaura.it.ie.domain.model.session;

import jp.ac.shibaura.it.ie.domain.model.user.User;

public interface SessionRepository {
    public void save(String userId, String session);
    public void remove(String userId);
    public String find(String userId);
}
