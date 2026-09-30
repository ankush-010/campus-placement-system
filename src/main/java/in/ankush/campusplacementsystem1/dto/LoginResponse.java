package in.ankush.campusplacementsystem1.dto;

public class LoginResponse {

    private String message;
    private String username;
    private String role;

    public LoginResponse() {

    }

    public LoginResponse(String message, String username, String role) {
        this.username = username;
        this.message = message;
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
