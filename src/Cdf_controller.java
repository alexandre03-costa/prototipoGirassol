import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Cdf_controller {
    Funcionario f=new Funcionario();
    @FXML
    private Button btn_enviar;

    @FXML
    private Label labelSair1;

    @FXML
    private TextField lb_nome;

    @FXML
    private TextField bi;

    @FXML
    private TextField cargo;

    @FXML
    private DatePicker dataN;

    @FXML
    private ComboBox<String> comboBoxSexo;

    @FXML
    private ComboBox<String> comboBoxcCivil;

    @FXML
    private TextField tel;

    @FXML
    void close(MouseEvent event) {
        Stage tela = (Stage) labelSair1.getScene().getWindow();
        tela.close();
    }
    public void receber(Funcionario f){
        this.f=f;
        System.out.println(f.getBi());
    }
    public void mostrar(){
        bi.setText(f.getBi());
        cargo.setText(f.getCargo());
         // Inicializa o ComboBox para o sexo
         ObservableList<String> sexoItems = FXCollections.observableArrayList("M", "F");
         comboBoxSexo.setItems(sexoItems);
         comboBoxSexo.setValue("M"); // Define um valor padrão, se necessário
 
         // Inicializa o ComboBox para o estado civil
         ObservableList<String> estadoCivilItems = FXCollections.observableArrayList("C", "S", "V");
         comboBoxcCivil.setItems(estadoCivilItems);
         comboBoxcCivil.setValue("C");  
    }

    @FXML
    public void initialize() {
        //inicializa o cdfCom dados para editar;
       bi.setText(f.getBi());
       cargo.setText(f.getCargo());
        // Inicializa o ComboBox para o sexo
        ObservableList<String> sexoItems = FXCollections.observableArrayList("M", "F");
        comboBoxSexo.setItems(sexoItems);
        comboBoxSexo.setValue("M"); // Define um valor padrão, se necessário

        // Inicializa o ComboBox para o estado civil
        ObservableList<String> estadoCivilItems = FXCollections.observableArrayList("C", "S", "V");
        comboBoxcCivil.setItems(estadoCivilItems);
        comboBoxcCivil.setValue("C"); // Define um valor padrão, se necessário
    }

    @FXML
    void Enviar() {
        String nome = lb_nome.getText();
        String biText = bi.getText();
        String cargoText = cargo.getText();
        LocalDate dataNascimento = dataN.getValue();
        String sexo = comboBoxSexo.getValue();
        String estadoCivil = comboBoxcCivil.getValue();
        String telefone = tel.getText();

        if (nome.isEmpty() || biText.isEmpty() || cargoText.isEmpty() || dataNascimento == null || sexo == null
                || estadoCivil == null || telefone.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Preencha todos os campos.");
            alert.showAndWait();
            return;
        }

        Funcionario funcionario = new Funcionario();
        boolean success = false;

        try {
            success = funcionario.cadastroFuncionario(
                    nome,
                    biText,
                    cargoText,
                    sexo.charAt(0),
                    estadoCivil.charAt(0),
                    dataNascimento,
                    telefone);

            if (success) {
                lb_nome.clear();
                bi.clear();
                cargo.clear();
                dataN.setValue(null);
                comboBoxSexo.setValue(null);
                comboBoxcCivil.setValue(null);
                tel.clear();
            }

            Alert alert = new Alert(success ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
            alert.setTitle(success ? "Sucesso" : "Erro");
            alert.setHeaderText(null);
            alert.setContentText(success ? "Cadastro feito com sucesso." : "Cadastro não realizado.");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Conexão");
            alert.setHeaderText(null);
            alert.setContentText("Ocorreu um erro ao tentar cadastrar o funcionário: " + e.getMessage());
            alert.showAndWait();
        }
    }
}
