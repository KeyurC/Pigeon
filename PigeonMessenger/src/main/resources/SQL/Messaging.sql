CREATE TABLE Message (
    conversationID int NOT NULL PRIMARY KEY,
    conversationType varchar(50),
  	message varchar(500),
    senderID int,
  	timestamps timestamp,
  	FOREIGN KEY (senderID) REFERENCES User(userID)

);
CREATE TABLE MessageRecipient (
    conversationID int NOT NULL PRIMARY KEY,
    userID int,
  	FOREIGN KEY (userID) REFERENCES User(userID)
);
CREATE TABLE MessageStatus (
    messageStatusID int NOT NULL PRIMARY KEY,
    conversationID INT,
  	userID int,
  	isRead Bool NOT NULL DEFAULT 0,
  	FOREIGN KEY (userID) REFERENCES User(userID),
  	FOREIGN KEY (conversationID) REFERENCES Messages(conversationID)
);
CREATE TABLE User (
	userID int NOT NULL PRIMARY KEY,
  	userName varchar(100) NOT NULL
);