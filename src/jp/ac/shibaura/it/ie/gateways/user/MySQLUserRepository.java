package jp.ac.shibaura.it.ie.gateways.user;

import jp.ac.shibaura.it.ie.domain.model.user.*;
import jp.ac.shibaura.it.ie.gateways.databese.MySQLComm;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySQLUserRepository implements UserRepository {
    @Override
    public void save(User user) {
        MySQLComm comm = MySQLComm.getInstance();
        comm.sqlExecuteUpdate(String.format("insert into user (id, name, password) values (%s,%s,%s)", user.getId(), user.getName(), user.getPass()));
    }

    @Override
    public void update(User user) {
        MySQLComm comm = MySQLComm.getInstance();
        comm.sqlExecuteUpdate(String.format("update user set name = %s where id = %s", user.getName(), user.getId()));
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        MySQLComm comm = MySQLComm.getInstance();
        ResultSet rs = comm.sqlExecuteQuery("select * from user");
        try {
            while (rs.next()) {
                User user = new User(
                        new UserId(rs.getString("id")),
                        new UserName(rs.getString("name")),
                        new UserPassword(rs.getString("password"))
                );
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public Optional<User> find(String userId) {
        MySQLComm comm = MySQLComm.getInstance();
        ResultSet rs = comm.sqlExecuteQuery(String.format("select * from user where id = %s", userId));
        User user = null;
        try {
            user = new User(
                    new UserId(rs.getString("id")),
                    new UserName(rs.getString("name")),
                    new UserPassword(rs.getString("password"))
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(user);
    }

}
