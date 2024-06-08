import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.geometry.Rectangle2D;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private Button btnLogin;

    @FXML
    private Label labelSair;

    @FXML
    private PasswordField pass;

    @FXML
    private TextField user;

    @FXML
    void close(MouseEvent event) {
        Stage tela = (Stage) labelSair.getScene().getWindow();
        tela.close();
    }

    @FXML
    void entrar(ActionEvent event) throws IOException {
        String usuario = user.getText().trim();
        String senha = pass.getText().trim();

        // Verifica se os campos estão vazios
        if (usuario.isEmpty() || senha.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Usuário e senha são obrigatórios.");
            alert.showAndWait();
            return; // Sai do método se os campos estiverem vazios
        }

        Connection conectar = null;
        PreparedStatement pr = null;
        ResultSet resultado = null;

        try {
            String sql = "SELECT * FROM admin WHERE user_name=? AND password=?";
            conectar = Conection.getConnection(); // Obter a conexão
            pr = conectar.prepareStatement(sql);
            pr.setString(1, usuario);
            pr.setString(2, senha);
            resultado = pr.executeQuery();

            if (resultado.next()) {
                Stage primary = (Stage) btnLogin.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("AdminTela.fxml"));
                Scene scene = new Scene(root);
                Stage adminStage = new Stage();
                adminStage.initStyle(StageStyle.UNDECORATED);
                adminStage.setScene(scene);
                adminStage.show();

                // Centralizar a janela Admin
                centralizarJanela(adminStage);

                // Fechar a janela de login
                primary.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Usuário ou senha incorretos.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de SQL");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao conectar ao banco de dados: " + e.getMessage());
            alert.showAndWait();
        } finally {
            // Fechar recursos
            try {
                if (resultado != null)
                    resultado.close();
                if (pr != null)
                    pr.close();
                if (conectar != null && !conectar.isClosed())
                    conectar.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void centralizarJanela(Stage stage) {
        // Obter a tela primária
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // Calcular a posição central
        double x = (bounds.getWidth() - stage.getWidth()) / 2;
        double y = (bounds.getHeight() - stage.getHeight()) / 2;

        // Definir a posição da janela
        stage.setX(x);
        stage.setY(y);
    }
}
