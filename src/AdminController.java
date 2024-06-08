import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminController {
    Integer linha=0;
    Funcionario guardar=new Funcionario();
    Cdf_controller form= new Cdf_controller();
    @FXML
    private Label lb_new;

    @FXML
    private Label lb_sair;
    @FXML
    private Label totFunci;
    @FXML
    private TableView<Funcionario> tableView;



    @FXML
    private TableColumn<Funcionario, Integer> id;

    @FXML
    private TableColumn<Funcionario, String> nome;

    @FXML
    private TableColumn<Funcionario, String> bi;

    @FXML
    private TableColumn<Funcionario, String> cargo;

    @FXML
    private TableColumn<Funcionario, LocalDate> dataNascimento;

    @FXML
    private TableColumn<Funcionario, Character> estadoCivil;

    @FXML
    private TableColumn<Funcionario, Character> sexo;

    @FXML
    private TableColumn<Funcionario, String> telefone;
        @FXML
    private Button btneditar;
  
    @FXML
    public void initialize() throws SQLException {
        Funcionario f= new Funcionario();
        String s=f.contagem();
         totFunci.setText(s);
       
        // Configurar as colunas da tabela
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        bi.setCellValueFactory(new PropertyValueFactory<>("bi"));
        cargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        dataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataN"));
        estadoCivil.setCellValueFactory(new PropertyValueFactory<>("estadoCivil"));
        sexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        telefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        // Carregar dados na tabela
        ObservableList<Funcionario> funcionarios = FXCollections.observableArrayList(Funcionario.listarFuncionarios());
        tableView.setItems(funcionarios);

    }

    @FXML
    void abrir_cadastro(MouseEvent event) {
        try {
           
            Stage primary = (Stage) lb_sair.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cdfuncionario.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao carregar FXML");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao carregar o arquivo cadastro.fxml: " + e.getMessage());
            alert.showAndWait();
        }
    }
    public Funcionario guardarLinha(){
        
        linha=tableView.getSelectionModel().getSelectedIndex();
        if(linha>-1){
            guardar.setBi(bi.getCellData(linha));
            guardar.setBi(cargo.getCellData(linha));
        }
        return guardar;
    }
    @FXML
    void PegarDados(MouseEvent event) {
      guardarLinha();

       }
           @FXML
    void editar(ActionEvent event) {
        try {
           form.receber(guardarLinha());
            Stage primary = (Stage) lb_sair.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cdfuncionario.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.show();
            form.mostrar();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao carregar FXML");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao carregar o arquivo cadastro.fxml: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void close(MouseEvent event) {
        Stage tela = (Stage) lb_sair.getScene().getWindow();
        tela.close();
    }
}
