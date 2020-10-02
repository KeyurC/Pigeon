package Model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FriendsWith")
public class FriendsWith {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "UserID")
    private int userID;

    @Column(name = "FriendID")
    private int friendID;

    @Column(name = "status")
    private int friendStatus;

    public int getId() {
        return id;
    }

    public int getFriendID() {
        return friendID;
    }

    public void setFriendID(int friendID) {
        this.friendID = friendID;
    }

    public int getFriendStatus() {
        return friendStatus;
    }

    public void setFriendStatus(int friendStatus) {
        this.friendStatus = friendStatus;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
