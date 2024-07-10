package View.UserHome;

import Pojo.User;

import javax.swing.*;
import java.awt.*;

public class UserHome {
    static JFrame frame=new JFrame("用户界面");
    static JButton search=new JButton("查阅文档");
    static JButton add=new JButton("添加文档");
    static JButton update=new JButton("修改文档");
    static JButton userControl1 = new JButton("成员管理");

    public static User user;
    public static User user2;

    public UserHome(User user)
    {
        UserHome.user = user;
        UserHome.user2 = user;
        System.out.println(user);
        frame.setSize(900,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initFrame();
        frame.setVisible(true);


    }

    private void initFrame() {

        JPanel panel = new JPanel(new CardLayout(5,5));

        SearchPanel searchPanel = new SearchPanel();
        JPanel panel1 = searchPanel.getPanel();

        AddPanel addPanel = new AddPanel();
        JPanel panel2 = addPanel.getPanel();

        UpdatePanel updatePanel = new UpdatePanel();
        JPanel panel3 = updatePanel.getPanel();

        UserControl userControl = new UserControl();
        JPanel panel4 = userControl.getPanel();



        panel.add(panel1,"查阅");
        panel.add(panel2,"添加");
        panel.add(panel3,"修改");
        panel.add(panel4,"成员管理");

        JPanel ButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ButtonPanel.add(search);
        ButtonPanel.add(add);
        ButtonPanel.add(update);

        if(user.getLevel().equals("3")){
            ButtonPanel.add(userControl1);
        }

        search.addActionListener(
                e -> {
                    CardLayout cl = (CardLayout) (panel.getLayout());
                    cl.show(panel,"查阅");
                    panel.repaint();
                }
        );
        add.addActionListener(
                e -> {
                    CardLayout cl = (CardLayout) (panel.getLayout());
                    cl.show(panel,"添加");
                    panel.repaint();
                }
        );
        update.addActionListener(
                e -> {
                    CardLayout cl = (CardLayout) (panel.getLayout());
                    cl.show(panel,"修改");
                    panel.repaint();
                }
        );
        userControl1.addActionListener(
                e -> {
                    CardLayout cl = (CardLayout) (panel.getLayout());
                    cl.show(panel,"成员管理");
                    panel.repaint();
                }
        );

        frame.add(panel);
        frame.add(ButtonPanel,BorderLayout.SOUTH);

    }




}
