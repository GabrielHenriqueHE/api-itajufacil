package io.github.gabrielhenriquehe.itajufacil.utils;

public class AuthValidator {

    public static boolean isValidName(String name) {
        return name != null &&
                !name.isEmpty() &&
                !name.isBlank() &&
                name.trim().length() > 3;
    }

    public static boolean isValidUsername(String username) {
        return username != null &&
                !username.isEmpty() &&
                !username.isBlank() &&
                username.trim().length() > 3;
    }

    public static boolean isValidPhoneNumber(String phone) {
        return phone != null && phone.matches("\\d{11}");
    }

    public static boolean isValidPassword(String password) {
        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

        return password != null && password.matches(pattern);
    }

    public static boolean isValidEmail(String email) {
        String pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        return email != null && email.matches(pattern);
    }

}
