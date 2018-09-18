
import Controller.LoginController;
import Controller.MainController;
import View.Login;

public class MainProgram {
    public static void main(String[] args) {
        //new MainController();
        Login login = new Login();
        LoginController ctrl = new LoginController(login);
        login.setVisible(true);
    }
}