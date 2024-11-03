package com.languageLearner.data;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.UUID;

public class DataLoaderTest {

    @BeforeEach
    public void setup() {
        // Prepare test data, e.g., creating a test JSON file
        DataWriter dataWriter = new DataWriter();
        UserList userList = UserList.getInstance();
        userList.clearUsers(); // Clear any existing users

        // Create test users
        User testUser = new User("test@example.com", "testUser", "Test User", "password123", UUID.randomUUID());
        userList.addUser(testUser);
        dataWriter.writeAllUsers(); // Write the test users to JSON
    }

    @Test
    public void testLoadUsers() {
        DataLoader dataLoader = new DataLoader();
        dataLoader.loadUsers(); // Load the users from the JSON file

        UserList userList = UserList.getInstance();
        assertEquals(1, userList.getUsers().size(), "User list should contain one user");

        User loadedUser = userList.getUsers().get(0);
        assertEquals("testUser", loadedUser.getUsername(), "Loaded user should have the correct username");
        assertEquals("test@example.com", loadedUser.getEmail(), "Loaded user should have the correct email");
        // Add more assertions as needed for other fields
    }
}
