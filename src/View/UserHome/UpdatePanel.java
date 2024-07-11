package View.UserHome;
import Dao.DocumentControl;
import Pojo.DocumentBean;

import javax.swing.*;
import java.awt.*;

public class UpdatePanel {
    JPanel UpdatePanel=new JPanel(new BorderLayout(0,20));
    private DefaultListModel<String> listModel = null;
    private  JList<String> searchResultsList;
    DocumentControl documentControl = new DocumentControl();
    int documentId=0;
    DocumentBean SelectedDocumentBean=new DocumentBean();

    public UpdatePanel()
    {
        listModel = new DefaultListModel<>();
        searchResultsList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(searchResultsList);
        scrollPane.setPreferredSize(new Dimension(150, 0));
        UpdatePanel.add(scrollPane,BorderLayout.WEST);

        JTextArea DocumentContentArea = new JTextArea();
        DocumentContentArea.setEditable(true);
        JScrollPane DocumentContentScrollPane = new JScrollPane(DocumentContentArea);
        DocumentContentScrollPane.setPreferredSize(new Dimension(500, 0));
        UpdatePanel.add(DocumentContentScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton updateButton = new JButton("提交");
        JButton cancelButton = new JButton("撤销");
        JButton deleteButton = new JButton("删除");
        JButton refreshButton = new JButton("刷新");
        JLabel DocumentNameLabel = new JLabel("文档名称：");
        JTextField DocumentNameText = new JTextField(20);
        JLabel DocumentTypeLabel = new JLabel("文档类型：");
        JTextField DocumentTypeText = new JTextField(10);
        JLabel DocumentLevelLabel = new JLabel("文档等级：");
        JComboBox documentLevelText = new JComboBox();
        documentLevelText.setEditable(false);
        int maxlevel= Integer.parseInt(UserHome.user.getLevel());
        for(int i=1;i<=maxlevel;i++)
        {
            documentLevelText.addItem(i);
        }
        buttonPanel.add(DocumentNameLabel);
        buttonPanel.add(DocumentNameText);
        buttonPanel.add(DocumentTypeLabel);
        buttonPanel.add(DocumentTypeText);
        buttonPanel.add(DocumentLevelLabel);
        buttonPanel.add(documentLevelText);
        buttonPanel.add(updateButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        UpdatePanel.add(buttonPanel,BorderLayout.SOUTH);
        initList();

        searchResultsList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedValue = searchResultsList.getSelectedValue();
                if (selectedValue != null) {
                    // 根据选中的文档名称获取文档内容
                    DocumentBean documentBean = documentControl.getDocumentsByName(selectedValue).get(0);
                    SelectedDocumentBean=documentBean;
                    DocumentContentArea.setText(documentBean.getDocumentContent());
                    DocumentNameText.setText(documentBean.getDocumentName());
                    DocumentTypeText.setText(documentBean.getDocumentType());
                    documentLevelText.setSelectedItem(documentBean.getDocumentLevel());
                    documentId=documentBean.getDocumentID();
                }
            }
        });
        updateButton.addActionListener(e -> {
            if(documentId!=0){
                DocumentBean documentBean = new DocumentBean();
                documentBean.setDocumentID(documentId);
                documentBean.setDocumentContent(DocumentContentArea.getText());
                documentBean.setDocumentName(DocumentNameText.getText());
                documentBean.setDocumentType(DocumentTypeText.getText());
                documentBean.setDocumentLevel(Integer.parseInt(documentLevelText.getSelectedItem().toString()));
                documentBean.setDocumentAuthor(SelectedDocumentBean.getDocumentAuthor());
                String time = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(new java.util.Date());
                documentBean.setDocumentTime(time);
                documentId=0;

                documentControl.updateDocument(documentBean);
                JOptionPane.showMessageDialog(null, "更新成功");
                initList();
            }else{
                JOptionPane.showMessageDialog(null, "请选择文档");
            }
        });
        cancelButton.addActionListener(e -> {
            if(documentId!=0){
                DocumentNameText.setText(SelectedDocumentBean.getDocumentName());
                DocumentTypeText.setText(SelectedDocumentBean.getDocumentType());
                DocumentContentArea.setText(SelectedDocumentBean.getDocumentContent());
                documentLevelText.setSelectedItem(SelectedDocumentBean.getDocumentLevel());
            }else{
                JOptionPane.showMessageDialog(null, "请选择文档");
            }



        });
        deleteButton.addActionListener(e->{
            if(documentId!=0){
                int result = JOptionPane.showConfirmDialog(null, "确定删除吗？", "提示", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    documentControl.deleteDocument(documentId);
                    JOptionPane.showMessageDialog(null, "删除成功");
                    initList();
                }
            }else{
                JOptionPane.showMessageDialog(null, "请选择文档");
            }

        });
        refreshButton.addActionListener(e->{
            initList();
        });

    }

    public void initList(){
        if(UserHome.user.getLevel().equals("3")){
            listModel.clear();
            for(DocumentBean documentBean:documentControl.getAllDocuments()){
                listModel.addElement(documentBean.getDocumentName());
            }
        }else{
            listModel.clear();
            java.util.List<DocumentBean> documentBeans = documentControl.getDocumentsByUserName(UserHome.user.getUserName());
            for(DocumentBean documentBean:documentBeans){
                listModel.addElement(documentBean.getDocumentName());
            }

        }

    }

    public static JPanel getPanel() {
        return new UpdatePanel().UpdatePanel;
    }
}
