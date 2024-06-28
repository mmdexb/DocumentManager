package Test;

import Dao.DocumentControl;
import Dao.UserControl;
import Pojo.DocumentBean;
import Pojo.User;
import Utils.DBtool;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    private DBtool<User> dbtool = new DBtool<>();
    private UserControl userControl = new UserControl();
    public List<User> getUsers(){
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
        },"admin1","admin");
    }

    public static void main(String[] args)
    {
        UserControl userControl = new UserControl();
//        List<User> users = new ArrayList<User>();
//        users=userControl.getUsersByNameAndPassword("张三","123456");
//        if(users.isEmpty()){
//            System.out.println("没有找到该用户");
//        }
//        for(User user:users)
//        {
//            System.out.println(user);
//        }
//        User user = new User();
//        user.setLevel("5");
//        user.setPassword("123456");
//        user.setUserName("陈薏帆");
//        if(userControl.addUser(user)==1){
//            System.out.println("添加成功");
//        }
//
        DocumentControl documentControl = new DocumentControl();
        List<DocumentBean> documentList = new ArrayList<DocumentBean>();
        documentList = documentControl.getDocumentsByName("数据库");
        for(DocumentBean documentBean:documentList){
            System.out.println(documentBean);
        }
    }
}
