public class Akun {
    private String username;
    private String password;

    protected String role;

    public Akun(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setUsername(String username) {
        if (username != null && !username.trim().isEmpty()) {
            this.username = username;
        } else {
            System.out.println("Username tidak boleh kosong!");
        }
    }

    public void setPassword(String password) {
        if (password != null && password.length() >= 6) {
            this.password = password;
        } else {
            System.out.println("Password minimal 6 karakter!");
        }
    }

}