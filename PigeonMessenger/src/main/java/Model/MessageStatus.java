package Model;

public class MessageStatus {
    private int messageStatusID;
    private int conversationID;
    private int userID;
    private Boolean isRead;

    public int getMessageStatusID() {
        return messageStatusID;
    }

    public void setMessageStatusID(int messageStatusID) {
        this.messageStatusID = messageStatusID;
    }

    public int getConversationID() {
        return conversationID;
    }

    public void setConversationID(int conversationID) {
        this.conversationID = conversationID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }
}
