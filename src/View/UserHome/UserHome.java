package View.UserHome;

import javax.swing.*;
import java.awt.*;

public class UserHome {
    static JFrame frame=new JFrame("用户界面");
    static JButton search=new JButton("查阅文档");
    static JButton add=new JButton("添加文档");
    static JButton update=new JButton("修改文档");
    static JButton my = new JButton("我的文档");

    public UserHome()
    {
        frame.setSize(1200,800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initFrame();
        frame.setVisible(true);

    }

    private void initFrame() {
        JPanel panel = new JPanel(new CardLayout(5,5));
        JPanel panel1 = new JPanel();
        JLabel textField = new JLabel("用户名");
        panel1.setBackground(Color.red);
        panel1.add(textField);

        JPanel panel2 = new JPanel();
        JLabel textField1 = new JLabel("用户名2");
        panel2.setBackground(Color.blue);
        panel2.add(textField1);

        panel.add(panel1,"查阅");
        panel.add(panel2,"添加");

        JPanel ButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ButtonPanel.add(search);
        ButtonPanel.add(add);

        search.addActionListener(
                e -> {
                    CardLayout cl = (CardLayout) (panel.getLayout());
                    cl.show(panel,"查阅");
                }
        );
        add.addActionListener(
                e -> {
                    CardLayout cl = (CardLayout) (panel.getLayout());
                    cl.show(panel,"添加");
                }
        );


        frame.add(panel);
        frame.add(ButtonPanel,BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
        new UserHome();
    }
}
