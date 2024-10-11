public class Login_logic {
    private String username;
    private String password;

    public Login_logic(String username, String password) {
        this.username = username;
        this.password = password;
    }
    //Lógica de login(Melhorar segurança depois)
    public boolean validateLogin() {
        String defaultUsername = "Projeto";
        String defaultPassword = "1234";

        if (username.equals(defaultUsername) && password.equals(defaultPassword)) {
            return true; 
        } else {
            return false; 
        }


    }
}