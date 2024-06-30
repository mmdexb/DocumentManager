package Dao;

import Pojo.DocumentBean;
import Utils.DBtool;

import java.sql.SQLException;
import java.util.List;

public class DocumentControl {
    private DBtool<DocumentBean> dbtool = new DBtool<>();

    public List<DocumentBean> getAllDocuments()
    {
        String sql = "select * from document";
        return dbtool.executeQuery(sql, rs -> {
            DocumentBean documentBean = new DocumentBean();
            try {
                documentBean.setDocumentID(rs.getInt("documentID"));
                documentBean.setDocumentName(rs.getString("documentName"));
                documentBean.setDocumentAuthor(rs.getString("documentAuthor"));
                documentBean.setDocumentType(rs.getString("documentType"));
                documentBean.setDocumentContent(rs.getString("documentContent"));
                documentBean.setDocumentTime(rs.getString("documentTime"));
                documentBean.setDocumentLevel(rs.getInt("documentLevel"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return documentBean;
        });
    }

    public List<DocumentBean> getDocumentsById(Integer documentId)
    {
        String sql = "select * from document where documentID = ?";
        return dbtool.executeQuery(sql, rs -> {
            DocumentBean documentBean = new DocumentBean();
            try {
                documentBean.setDocumentID(rs.getInt("documentID"));
                documentBean.setDocumentName(rs.getString("documentName"));
                documentBean.setDocumentAuthor(rs.getString("documentAuthor"));
                documentBean.setDocumentType(rs.getString("documentType"));
                documentBean.setDocumentContent(rs.getString("documentContent"));
                documentBean.setDocumentTime(rs.getString("documentTime"));
                documentBean.setDocumentLevel(rs.getInt("documentLevel"));

            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            return documentBean;
        }, documentId);
    }
    //模糊查询
    public List<DocumentBean> getDocumentsByName(String documentName)
    {
        String sql="select * from document where documentName like ?";
        documentName="%"+documentName+"%";
        return dbtool.executeQuery(sql, rs -> {
            DocumentBean documentBean = new DocumentBean();
            try {
                documentBean.setDocumentID(rs.getInt("documentID"));
                documentBean.setDocumentName(rs.getString("documentName"));
                documentBean.setDocumentAuthor(rs.getString("documentAuthor"));
                documentBean.setDocumentType(rs.getString("documentType"));
                documentBean.setDocumentContent(rs.getString("documentContent"));
                documentBean.setDocumentTime(rs.getString("documentTime"));
                documentBean.setDocumentLevel(rs.getInt("documentLevel"));
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
            return documentBean;
        }, documentName);
    }

    public int addDocument(DocumentBean documentBean){
        return dbtool.executeUpdate("insert into document(documentName,documentAuthor,documentType,documentContent,documentTime,documentLevel) values(?,?,?,?,?,?)",
                documentBean.getDocumentName(),
                documentBean.getDocumentAuthor(),
                documentBean.getDocumentType(),
                documentBean.getDocumentContent(),
                documentBean.getDocumentTime(),
                documentBean.getDocumentLevel()
        );
    }



}
