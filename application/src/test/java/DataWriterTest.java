package com.languageLearner.data;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.UUID;

public class DataWriterTest {

    @Test
    public void testWriteAllUsers() {
        DataWriter dataWriter = new DataWriter();
        UserList userList = UserList.getInstance();

        // Create test users
        User testUser = new User("test@example.com", "testUser", "Test User", "password123", UUID.randomUUID());
        userList.addUser(testUser);

        // Call the method to write users to JSON
        dataWriter.writeAllUsers();

        // Verify the file exists and contains the correct data
        File file = new File(DataWriter.USER_FILE);
        assertTrue(file.exists(), "User file should exist");

        // Optionally, read the file back and verify its contents (you might need a method to read back the JSON)
        // This could involve using DataLoader to ensure the written data matches what is read
    }
}
