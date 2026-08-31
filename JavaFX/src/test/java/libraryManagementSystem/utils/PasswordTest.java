package librarymanagementsystem.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {


    @Test
    public void passwordShouldMeetTheRequirements() {
        // Test case 1: Correct password
        String password1 = "Abcde123!";
        assertTrue(Password.passwordRequirements(password1), "Password should meet the requirements");
    }

    @Test
    public void passwordShouldBeLonger() {
        // Test case 2: Password too short
        String password2 = "Ab12!";
        assertFalse(Password.passwordRequirements(password2), "Password should be too short");
    }

    @Test
    public void passwordShouldContainAnyDigit() {
        // Test case 3: Password without a digit
        String password3 = "Abcdefg!";
        assertFalse(Password.passwordRequirements(password3), "Password should contain a digit");
    }

    @Test
    public void passwordShouldContainAnyLowercaseLetter() {
        // Test case 4: Password without a lowercase letter
        String password4 = "ABCDE123!";
        assertFalse(Password.passwordRequirements(password4), "Password should contain a lowercase letter");
    }

    @Test
    public void passwordShouldContainAnyUppercaseLetter() {
        // Test case 5: Password without an uppercase letter
        String password5 = "abcde123!";
        assertFalse(Password.passwordRequirements(password5), "Password should contain an uppercase letter");
    }

    @Test
    public void passwordShouldContainAnySpecialCharacter() {
        // Test case 6: Password without a special character
        String password6 = "Abcde123";
        assertFalse(Password.passwordRequirements(password6), "Password should contain a special character");
    }
}

