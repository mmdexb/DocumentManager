package Pojo;

public class User {
    private Integer userId;
    private String userName;
    private String password;
    private String level;

    public User(Integer userId, String userName, String password, String level) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.level = level;
    }

    public User(String password, String userName, String level) {
        this.password = password;
        this.userName = userName;
        this.level = level;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
