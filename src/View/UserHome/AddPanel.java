package View.UserHome;

import Dao.DocumentControl;
import Pojo.DocumentBean;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class AddPanel {
    JPanel panel = new JPanel(new BorderLayout(0,20));

    public AddPanel()
    {
        JPanel InfoPanel = new JPanel(new BorderLayout());

        InfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel documentNameLable = new JLabel("文档名");
        JTextField documentNameText = new JTextField(20);
        JLabel documentTypeLable = new JLabel("文档标签");
        JTextField documentTypeText = new JTextField(10);
        JLabel documentLevelLable = new JLabel("文档等级");
        //下拉框
        JComboBox documentLevelText = new JComboBox();
        documentLevelText.setEditable(false);
        int maxlevel= Integer.parseInt(UserHome.user.getLevel());
        for(int i=1;i<=maxlevel;i++)
        {
            documentLevelText.addItem(i);
        }
        InfoPanel.add(documentNameLable);
        InfoPanel.add(documentNameText);
        InfoPanel.add(documentTypeLable);
        InfoPanel.add(documentTypeText);
        InfoPanel.add(documentLevelLable);
        InfoPanel.add(documentLevelText);

        JPanel ContentPanel = new JPanel(new BorderLayout());
        JTextArea documentContentText = new JTextArea();
        JScrollPane documentContentLable = new JScrollPane(documentContentText);
        ContentPanel.add(documentContentLable,BorderLayout.CENTER);

        JButton addButton = new JButton("提交");
        JButton cancelButton = new JButton("清空");
        JButton UploadButton = new JButton("从文件中读取");
        addButton.addActionListener(e -> {
            if (documentNameText.getText().equals("") || documentTypeText.getText().equals("") || documentContentText.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "请填写完整信息");
                return;
            }
            DocumentBean documentBean = new DocumentBean();
            documentBean.setDocumentName(documentNameText.getText());
            documentBean.setDocumentType(documentTypeText.getText());
            documentBean.setDocumentLevel(Integer.parseInt(documentLevelText.getSelectedItem().toString()));
            documentBean.setDocumentContent(documentContentText.getText());
            documentBean.setDocumentAuthor(UserHome.user.getUserName());
            String time = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(new java.util.Date());
            documentBean.setDocumentTime(time);

            DocumentControl documentControl = new DocumentControl();
            if (documentControl.addDocument(documentBean)==1) {
                JOptionPane.showMessageDialog(null, "添加成功");
                documentNameText.setText("");
                documentTypeText.setText("");
                documentContentText.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "添加失败");
            }


        });
        cancelButton.addActionListener(e -> {
            documentNameText.setText("");
            documentTypeText.setText("");
            documentContentText.setText("");
        });

        UploadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(null);
            if(result == JFileChooser.APPROVE_OPTION)
            {
                String filePath = fileChooser.getSelectedFile().getPath();
                //逐行读取文件
                try {
                    FileReader fileReader = new FileReader(filePath);
                    String name=filePath.substring(filePath.lastIndexOf("\\")+1);
                    //去掉拓展名
                    name=name.substring(0,name.lastIndexOf("."));
                    documentNameText.setText(name);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line;
                    while((line = bufferedReader.readLine()) != null)
                    {
                        documentContentText.append(line);
                        documentContentText.append("\n");
                    }
                    bufferedReader.close();
                    fileReader.close();
                }catch(IOException e2){
                    System.out.println("读取文件失败");
                }
            }
        });
        JPanel ButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ButtonPanel.add(addButton);
        ButtonPanel.add(cancelButton);
        ButtonPanel.add(UploadButton);

        panel.add(InfoPanel,BorderLayout.NORTH);
        panel.add(ContentPanel,BorderLayout.CENTER);
        panel.add(ButtonPanel,BorderLayout.SOUTH);


    }
    public static JPanel getPanel() {
        return new AddPanel().panel;
    }

}
