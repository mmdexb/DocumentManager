package Pojo;

public class DocumentBean {
    private Integer DocumentID;
    private String DocumentName;
    private String DocumentAuthor;
    private String DocumentType;
    private String DocumentContent;
    private String DocumentTime;
    private Integer DocumentLevel;

    public DocumentBean(Integer documentID, String documentName, String documentAuthor, String documentType, String documentContent, String documentTime, Integer documentLevel) {
        DocumentID = documentID;
        DocumentName = documentName;
        DocumentAuthor = documentAuthor;
        DocumentType = documentType;
        DocumentContent = documentContent;
        DocumentTime = documentTime;
        DocumentLevel = documentLevel;
    }

    public DocumentBean(String documentName, String documentType, String documentAuthor, String documentContent, String documentTime,Integer documentLevel) {
        DocumentName = documentName;
        DocumentType = documentType;
        DocumentAuthor = documentAuthor;
        DocumentContent = documentContent;
        DocumentTime = documentTime;
        DocumentLevel = documentLevel;
    }

    public DocumentBean() {

    }

    public Integer getDocumentID() {
        return DocumentID;
    }

    public void setDocumentID(Integer documentID) {
        DocumentID = documentID;
    }

    public String getDocumentName() {
        return DocumentName;
    }

    public void setDocumentName(String documentName) {
        DocumentName = documentName;
    }

    public String getDocumentAuthor() {
        return DocumentAuthor;
    }

    public void setDocumentAuthor(String documentAuthor) {
        DocumentAuthor = documentAuthor;
    }

    public String getDocumentType() {
        return DocumentType;
    }

    public void setDocumentType(String documentType) {
        DocumentType = documentType;
    }

    public String getDocumentContent() {
        return DocumentContent;
    }

    public void setDocumentContent(String documentContent) {
        DocumentContent = documentContent;
    }

    public String getDocumentTime() {
        return DocumentTime;
    }

    public void setDocumentTime(String documentTime) {
        DocumentTime = documentTime;
    }
    public Integer getDocumentLevel() {
        return DocumentLevel;
    }
    public void setDocumentLevel(Integer documentLevel) {
        DocumentLevel = documentLevel;
    }

    @Override
    public String toString() {
        return "DocumentBean{" +
                "DocumentID=" + DocumentID +
                ", DocumentName='" + DocumentName + '\'' +
                ", DocumentAuthor='" + DocumentAuthor + '\'' +
                ", DocumentType='" + DocumentType + '\'' +
                ", DocumentContent='" + DocumentContent + '\'' +
                ", DocumentTime='" + DocumentTime + '\'' +
                ", DocumentLevel=" + DocumentLevel +
                '}';
    }
}
