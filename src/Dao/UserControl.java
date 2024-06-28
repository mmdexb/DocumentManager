package Dao;

import Pojo.User;
import Utils.DBtool;

import java.sql.SQLException;
import java.util.List;

public class UserControl {
    private DBtool<User> dbtool = new DBtool<>();
    public List<User> getAllUsers(){
        String sql="SELECT * FROM user";
        return dbtool.executeQuery(sql, rs -> {
            User user = new User();
            try {
                user.setUserId(rs.getInt("userId"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setLevel(rs.getString("level"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return user;
        });
    }

    public List<User> getUsersById(Integer userId){
        String sql="SELECT * FROM user WHERE userId = ?";
        return dbtool.executeQuery(sql, rs -> {
            User user = new User();
            try {
                user.setUserId(rs.getInt("userId"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setLevel(rs.getString("level"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return user;
        }, userId);
    }

    public List<User> getUsersByName(String userName){
        String sql="SELECT * FROM user WHERE userName = ?";
        return dbtool.executeQuery(sql, rs -> {
            User user = new User();
            try {
                user.setUserId(rs.getInt("userId"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setLevel(rs.getString("level"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return user;
        }, userName);
    }

    public List<User> getUsersByNameAndPassword(String userName,String password){
        String sql="SELECT * FROM user WHERE userName = ? AND password = ?";
        return dbtool.executeQuery(sql, rs -> {
            User user = new User();
            try {
                user.setUserId(rs.getInt("userId"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setLevel(rs.getString("level"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return user;
        }, userName, password);
    }

    public int addUser(User user){
        return dbtool.executeUpdate("INSERT INTO user(userName, password, level) VALUES (?, ?, ?)", user.getUserName(), user.getPassword(), user.getLevel());
    }

    public int deleteUser(Integer userId){
        return dbtool.executeUpdate("DELETE FROM user WHERE userId = ?", userId);
    }

    public int updateUser(User user){
        return dbtool.executeUpdate("UPDATE user SET userName = ?, password = ?, level = ? WHERE userId = ?", user.getUserName(), user.getPassword(), user.getLevel(), user.getUserId());
    }

    public int updateUserPassword(String password,Integer userId){
        return dbtool.executeUpdate("UPDATE user SET password = ? WHERE userId = ?", password, userId);
    }









}
