package jp.ac.shibaura.it.ie.gateways.databese;

import jp.ac.shibaura.it.ie.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLComm {
    private static MySQLComm mySQLComm = new MySQLComm();
    private Connection conn;

    private MySQLComm() {
        try {
            String target = Config.get().getDBUrl() + ":" + Config.get().getDBPort() + "/" + Config.get().getDBName();
            conn = DriverManager.getConnection(target, Config.get().getDBUserId(), Config.get().getDBPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MySQLComm getInstance() {
        return mySQLComm;
    }

    public Connection getConn(){
        return this.conn;
    }

    public int sqlExecuteUpdate(String sql){
        int result = 0;
        try {
            Statement st = this.getConn().createStatement();
            result = st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ResultSet sqlExecuteQuery(String sql){
        ResultSet rs = null;
        try {
            Statement st = this.getConn().createStatement();
            rs = st.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void closeDB() {
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
