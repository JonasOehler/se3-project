package librarymanagementsystem.utils;

public class Password {
    public static boolean passwordRequirements(String password) {
        if (password.length() < 8) {
            return false;
        }

        if (!password.matches(".*\\d.*")) {
            return false;
        }

        if (!password.matches(".*[a-z].*")) {
            return false;
        }

        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        if (!password.matches(".*[!@#$%^&*()].*")) {
            return false;
        }
        return true;
    }
}
