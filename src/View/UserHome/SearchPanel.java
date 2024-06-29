package View.UserHome;

import Dao.DocumentControl;
import Pojo.DocumentBean;

import javax.swing.*;
import java.awt.*;

public class SearchPanel {
    private DefaultListModel<String> listModel = null;
    private  JList<String> searchResultsList;
    DocumentControl documentControl = new DocumentControl();
    JPanel panel = new JPanel();


    public SearchPanel() {

        panel.setLayout(new BorderLayout());

        //搜索栏
        JPanel searchPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("请输入查询的文档名称");
        JTextField DocumnetNameField = new JTextField(20);
        JButton search = new JButton("搜索");
        search.addActionListener(e -> {
            String searchText = DocumnetNameField.getText();
            if (searchText.isEmpty()) {
                JOptionPane.showMessageDialog(UserHome.frame, "请输入查询的文档名称");
            } else {
                // 执行搜索操作，获取搜索结果
                java.util.List<DocumentBean> searchResults = documentControl.getDocumentsByName(searchText);
                listModel.clear();
                for(DocumentBean documentBean : searchResults){
                    Integer UserLevel=Integer.parseInt(UserHome.user.getLevel());
                    if(documentBean.getDocumentLevel()<=UserLevel){
                        listModel.addElement(documentBean.getDocumentName());
                    }
                }
                if(listModel.isEmpty()){
                    JOptionPane.showMessageDialog(UserHome.frame, "没有找到匹配的文档或者查询的文档您无权查看");
                }
            }
        });
        searchPanel.add(label, BorderLayout.WEST);
        searchPanel.add(DocumnetNameField, BorderLayout.CENTER);
        searchPanel.add(search, BorderLayout.EAST);
        panel.add(searchPanel, BorderLayout.NORTH);

        // 搜索结果
        listModel = new DefaultListModel<>();
        searchResultsList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(searchResultsList);
        scrollPane.setPreferredSize(new Dimension(150, 0));
        panel.add(scrollPane, BorderLayout.WEST);

        //文档展示
        JTextArea DocumentContentArea = new JTextArea();
        DocumentContentArea.setEditable(false);
        JScrollPane DocumentContentScrollPane = new JScrollPane(DocumentContentArea);
        DocumentContentScrollPane.setPreferredSize(new Dimension(500, 0));
        panel.add(DocumentContentScrollPane, BorderLayout.CENTER);

        //搜索结果点击
        searchResultsList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedValue = searchResultsList.getSelectedValue();
                if (selectedValue != null) {
                    // 根据选中的文档名称获取文档内容
                    DocumentBean documentBean = documentControl.getDocumentsByName(selectedValue).get(0);
                    DocumentContentArea.setText(documentBean.getDocumentName());
                    DocumentContentArea.append("\n");
                    DocumentContentArea.append("作者: "+documentBean.getDocumentAuthor()+"\n");
                    DocumentContentArea.append("标签: "+documentBean.getDocumentType()+"\n");
                    DocumentContentArea.append("等级限制: "+documentBean.getDocumentLevel()+"\n");
                    DocumentContentArea.append("上次修改时间: "+documentBean.getDocumentTime()+"\n");
                    DocumentContentArea.append("\n");
                    DocumentContentArea.append(documentBean.getDocumentContent());
                }
            }
        });

    }

    public static JPanel getPanel() {
        return new SearchPanel().panel;
    }
}