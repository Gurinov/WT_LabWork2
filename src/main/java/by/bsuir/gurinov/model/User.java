package by.bsuir.gurinov.model;

public class User {
    private String username;
    private String password;
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if(isValidValue(username)){
            this.username = username;
        }else {
            this.username = "username";
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role.equals("Admin") || role.equals("User")){
            this.role = role;
        }
        this.role = "User";
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public boolean isAdmin(){
        return role.equals("Admin");
    }

    private boolean isValidValue(String str){
        if ((str.length() > 4) && (str.length() < 50)){
            return true;
        }
        return false;
    }
}
