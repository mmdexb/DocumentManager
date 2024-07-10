package View.UserHome;

import Pojo.User;
import View.LoginUI.Login;

import javax.swing.*;
import java.awt.*;

public class UserControl {
    JPanel UsersControlPanel=new JPanel(new BorderLayout(0,20));
    private DefaultListModel<String> listModel = null;
    private  JList<String> searchResultsList;
    Dao.UserControl userControl = new Dao.UserControl();
    Dao.DocumentControl documentControl = new Dao.DocumentControl();
    int SelectuserId=-1;
    User SelectedUser=new User();

    public UserControl()
    {
        listModel = new DefaultListModel<>();
        searchResultsList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(searchResultsList);
        scrollPane.setPreferredSize(new Dimension(150,0));
        UsersControlPanel.add(scrollPane,BorderLayout.WEST);

        JPanel InfoPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel userName=new JLabel("用户名:");
        JTextField userNameText=new JTextField(10);
        JLabel level=new JLabel("用户等级:");
        JComboBox userlevelText = new JComboBox();
        userlevelText.setEditable(false);
        int maxlevel= 3;
        for(int i=1;i<=maxlevel;i++)
        {
            userlevelText.addItem(i);
        }
        JTextArea userDocments=new JTextArea();
        userDocments.setEditable(false);
        JLabel userPwd=new JLabel("用户密码:");
        JTextField userPwdText=new JTextField(10);
        InfoPanel.add(userName);
        InfoPanel.add(userNameText);
        InfoPanel.add(level);
        InfoPanel.add(userlevelText);
        InfoPanel.add(userPwd);
        InfoPanel.add(userPwdText);
        UsersControlPanel.add(InfoPanel,BorderLayout.NORTH);
        initList();
        UsersControlPanel.add(userDocments,BorderLayout.CENTER);

        searchResultsList.addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()){
                String selectedItem = searchResultsList.getSelectedValue();
                if(selectedItem!=null)
                {
                    SelectedUser=userControl.getUsersByName(selectedItem).get(0);
                    SelectuserId= SelectedUser.getUserId();
                    userNameText.setText(SelectedUser.getUserName());
                    userlevelText.setSelectedItem(Integer.parseInt(SelectedUser.getLevel()));
                    userDocments.setText("");
                    userDocments.append("用户文章:\n");
                    documentControl.getDocumentsByUserName(SelectedUser.getUserName()).forEach(documentBean -> {
                        userDocments.append(documentBean.getDocumentName()+" "+documentBean.getDocumentType()+" "+documentBean.getDocumentAuthor()+" "+documentBean.getDocumentTime()+"\n");
                    });
                }
            }
        });

        JPanel ButtonPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton del=new JButton("删除用户");
        JButton add = new JButton("添加用户");
        JButton update = new JButton("修改用户");
        JButton cancel = new JButton("撤销修改");
        ButtonPanel.add(add);
        ButtonPanel.add(del);
        ButtonPanel.add(update);
        ButtonPanel.add(cancel);


        add.addActionListener(e -> {
            JFrame addFrame=new JFrame("添加用户");
            addFrame.setSize(300,300);
            addFrame.setLocationRelativeTo(null);
            addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            addFrame.setVisible(true);
            JPanel panel01=new JPanel(new FlowLayout(FlowLayout.CENTER));
            JPanel panel02=new JPanel(new FlowLayout(FlowLayout.CENTER));
            JPanel panel03=new JPanel(new FlowLayout(FlowLayout.CENTER));
            JLabel userName1=new JLabel("用户名:");
            JTextField userNameText1=new JTextField(10);
            JLabel password1=new JLabel("密码:");
            JTextField passwordText1 = new JTextField(10);
            JLabel level1=new JLabel("用户等级:");
            JComboBox levelText1 = new JComboBox();
            levelText1.setEditable(false);
            for(int i=1;i<=3;i++)
            {
                levelText1.addItem(i);
            }
            panel01.add(userName1);
            panel01.add(userNameText1);
            panel02.add(password1);
            panel02.add(passwordText1);
            panel03.add(level1);
            panel03.add(levelText1);
            JPanel panel04 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton addUser = new JButton("添加用户");
            panel04.add(addUser);

            Box vbox=Box.createVerticalBox();
            vbox.add(panel01);
            vbox.add(panel02);
            vbox.add(panel03);
            vbox.add(panel04);

            addFrame.setContentPane(vbox);

            addUser.addActionListener(e1 -> {
                if(userNameText1.getText().equals("")||passwordText1.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null,"用户名或密码不能为空");
                }
                else
                {
                    String pwd2= Login.generateMD5(passwordText1.getText());
                    if(!userControl.getUsersByNameAndPassword(userNameText1.getText(), pwd2).isEmpty())
                    {
                        JOptionPane.showMessageDialog(null,"用户名已存在");
                        return;
                    }
                    User user=new User(userNameText1.getText(),pwd2,levelText1.getSelectedItem().toString());
                    userControl.addUser(user);
                    initList();
                    addFrame.dispose();
               }
            });




        });
        del.addActionListener(e -> {
            if(SelectuserId==-1)
            {
                JOptionPane.showMessageDialog(null,"请选择用户");
            }
            else
            {
                userControl.deleteUser(SelectuserId);
                initList();
            }
        });
        update.addActionListener(e -> {
            if(SelectuserId==-1)
            {
                JOptionPane.showMessageDialog(null,"请选择用户");
            }
            else
            {
                if(userNameText.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null,"用户名不能为空");
                }
                else
                {
                    User user=new User();
                    user.setUserId(SelectuserId);
                    user.setUserName(userNameText.getText());
                    user.setLevel(userlevelText.getSelectedItem().toString());
                    if(userPwdText.getText().equals("")){
                        user.setPassword(SelectedUser.getPassword());
                    }else {
                        user.setPassword(Login.generateMD5(userPwdText.getText()));
                    }
                    userControl.updateUser(user);
                    initList();
                }

            }
        });
        cancel.addActionListener(e -> {
            userNameText.setText(SelectedUser.getUserName());
            userlevelText.setSelectedItem(SelectedUser.getLevel());
            userPwdText.setText("");
        });
        UsersControlPanel.add(ButtonPanel,BorderLayout.SOUTH);


    }

    private void initList() {
        listModel.clear();
        userControl.getAllUsers().forEach(user -> {
            listModel.addElement(user.getUserName());
        });
    }

    public static JPanel getPanel()
    {
        return new UserControl().UsersControlPanel;
    }


}
