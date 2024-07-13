package View.LoginUI;

import Dao.UserControl;
import Pojo.User;
import View.UserHome.UserHome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Login {
    public static JFrame frame=new JFrame("登录");
    public static JLabel userName=new JLabel("用户名");
    public static JLabel password=new JLabel("密  码");
    public static JTextField userNameText=new JTextField(20);
    public static JPasswordField passwordText=new JPasswordField(20);
    public static JButton login=new JButton("登录");
    public static JButton register=new JButton("注册");
    String code="";
    JTextField tf1;

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

        JPanel panel04=new JPanel();

        Object[] obj = Yanzheng.createImage();
        code = obj[0].toString();
        System.out.println(code);
        //验证码图片
        JLabel label1 = new JLabel();

        ImageIcon img = new ImageIcon((BufferedImage)obj[1]);
        label1.setIcon((Icon)img);
        panel04.add(label1);
        tf1 = new JTextField(6);
        panel04.add(tf1);


        panel04.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount() == 1){
                    //重新获取验证码
                    getPicture(label1,panel04);
                }
            }
        });


        login.addActionListener(e -> {
            if(userNameText.getText().isEmpty()||new String(passwordText.getPassword()).isEmpty()){
                JOptionPane.showMessageDialog(frame,"用户名或密码为空","错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
            UserControl userControl=new UserControl();
            String pwd=new String(passwordText.getPassword());
            pwd=generateMD5(pwd);
            ArrayList<User> users= (ArrayList<User>) userControl.getUsersByNameAndPassword(userNameText.getText(),pwd);
            if(users.isEmpty()){
                JOptionPane.showMessageDialog(frame,"用户名或密码错误","错误",JOptionPane.ERROR_MESSAGE);
            }else{
                System.out.println(tf1.getText());
                if(!tf1.getText().equals(code)){
                    JOptionPane.showMessageDialog(frame,"验证码错误","错误",JOptionPane.ERROR_MESSAGE);
                    return;
                }
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
            if(userNameText.getText().isEmpty()||new String(passwordText.getPassword()).isEmpty()){
                JOptionPane.showMessageDialog(frame,"用户名或密码为空","错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
            UserControl userControl=new UserControl();
            if(userControl.getUsersByName(userNameText.getText()).isEmpty()){
                String pwd=new String(passwordText.getPassword());
                pwd=generateMD5(pwd);
                User user=new User(userNameText.getText(),pwd,"1");
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
        vbox.add(panel04);
        vbox.add(panel03);


        frame.setContentPane(vbox);

    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.noddraw", "true");
        new Login();
    }
    public static String generateMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);

        }


    }

    public void getPicture(JLabel label,JPanel panel){
        Object[] obj = Yanzheng.createImage();
        code = obj[0].toString();
        System.out.println(code);
        ImageIcon img = new ImageIcon((BufferedImage)obj[1]);//创建图片对象
        label.setIcon((Icon)img);
        panel.add(label);
    }


}
