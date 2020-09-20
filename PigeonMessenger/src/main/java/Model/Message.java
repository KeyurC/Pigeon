package Model;

import java.util.Date;

public class Message {
    private int conversationID;
    private String conversationType;
    private String messageContent;
    private int senderID;
    private Date timestamp;

    public void setConversationID(int conversationID) {
        this.conversationID = conversationID;
    }

    public void setConversationType(String conversationType) {
        this.conversationType = conversationType;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getConversationID() {
        return conversationID;
    }

    public String getConversationType() {
        return conversationType;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public int getSenderID() {
        return senderID;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
