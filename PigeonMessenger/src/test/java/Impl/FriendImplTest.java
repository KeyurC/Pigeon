package Impl;

import Hibernate.DBConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FriendImplTest {

    @BeforeEach
    void setUp() {
        new DBConnection();
    }

    @Test
    void sendRequest() {
        FriendImpl.sendRequest(1,2);
    }

    @Test
    void acceptRequest() {
        FriendImpl.acceptRequest(1,2);
    }
}