
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }
/* */
    public void start(Stage primary) throws Exception {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene tela = new Scene(root);
            primary.initStyle(StageStyle.UNDECORATED);
            primary.setScene(tela);
            primary.show();
        } catch (Exception e) {
            System.out.println("Erro ao carregar o ficheiro: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
