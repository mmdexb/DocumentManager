package View.LoginUI;

import Dao.UserControl;
import Pojo.User;
import View.UserHome.UserHome;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Login {
    public static JFrame frame=new JFrame("登录");
    public static JLabel userName=new JLabel("用户名");
    public static JLabel password=new JLabel("密  码");
    public static JTextField userNameText=new JTextField(20);
    public static JPasswordField passwordText=new JPasswordField(20);
    public static JButton login=new JButton("登录");
    public static JButton register=new JButton("注册");

    public Login()
    {
        frame.setSize(400,300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initFrame();
        frame.setVisible(true);
    }

    private void initFrame(){
        JPanel panel01=new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel01.add(userName);
        userNameText.setDocument(new TxDocument(20));
        panel01.add(userNameText);

        JPanel panel02=new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel02.add(password);
        passwordText.setDocument(new TxDocument(20));
        panel02.add(passwordText);

        JPanel panel03=new JPanel(new FlowLayout(FlowLayout.CENTER));
        login.addActionListener(e -> {
            if(userNameText.getText().isEmpty()||new String(passwordText.getPassword()).isEmpty()){
                JOptionPane.showMessageDialog(frame,"用户名或密码为空","错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
            UserControl userControl=new UserControl();
            ArrayList<User> users= (ArrayList<User>) userControl.getUsersByNameAndPassword(userNameText.getText(),new String(passwordText.getPassword()));
            if(users.isEmpty()){
                JOptionPane.showMessageDialog(frame,"用户名或密码错误","错误",JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(frame,"登陆成功","成功",JOptionPane.INFORMATION_MESSAGE);
                System.out.println(users.get(0));
                new UserHome(users.get(0));
                frame.dispose();

            }

        });
        panel03.add(login);

        register.addActionListener(e -> {
            if(userNameText.getText().isEmpty()||new String(passwordText.getPassword()).isEmpty()){
                JOptionPane.showMessageDialog(frame,"用户名或密码为空","错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
            UserControl userControl=new UserControl();
            if(userControl.getUsersByName(userNameText.getText()).isEmpty()){
                User user=new User(userNameText.getText(),new String(passwordText.getPassword()),"1");
                userControl.addUser(user);
                JOptionPane.showMessageDialog(frame,"注册成功","成功",JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(frame,"用户名已存在","错误",JOptionPane.ERROR_MESSAGE);
            }
        });
        panel03.add(register);

        Box vbox=Box.createVerticalBox();
        vbox.add(panel01);
        vbox.add(panel02);
        vbox.add(panel03);

        frame.setContentPane(vbox);

    }

    public static void main(String[] args) {
        new Login();
    }


}
