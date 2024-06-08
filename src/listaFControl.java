import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.List;

public class listaFControl {

    @FXML
    private TableView<Funcionario> tableFuncionarios;

    @FXML
    private TableColumn<Funcionario, Integer> colId;

    @FXML
    private TableColumn<Funcionario, String> colNome;

    @FXML
    private TableColumn<Funcionario, String> colBi;

    @FXML
    private TableColumn<Funcionario, String> colCargo;

    @FXML
    private TableColumn<Funcionario, Character> colSexo;

    @FXML
    private TableColumn<Funcionario, Character> colEstadoCivil;

    @FXML
    private TableColumn<Funcionario, LocalDate> colDataNascimento;

    @FXML
    private TableColumn<Funcionario, String> colTelefone;

    @FXML
    public void initialize() {
        // Inicializar as colunas da tabela
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colBi.setCellValueFactory(new PropertyValueFactory<>("bi"));
        colCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        colEstadoCivil.setCellValueFactory(new PropertyValueFactory<>("estadoCivil"));
        colDataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataN"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telFone"));

        // Carregar dados na tabela
        carregarDados();
    }

    private void carregarDados() {
        List<Funcionario> listaFuncionarios = Funcionario.listarFuncionarios();
        ObservableList<Funcionario> funcionarios = FXCollections.observableArrayList(listaFuncionarios);
        tableFuncionarios.setItems(funcionarios);
    }
}
