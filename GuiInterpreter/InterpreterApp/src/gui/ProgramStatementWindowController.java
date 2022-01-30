package gui;

import controller.Controller;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.ADTs.IDict;
import model.ProgramState;
import model.statements.IStatement;
import model.values.IValue;

import java.util.*;
import java.util.stream.Collectors;

public class ProgramStatementWindowController {

    Controller controller;
    private ProgramState selectedProgram;

    @FXML
    private TextField nrPrograms = new TextField("33");

    @FXML
    private TableView<Map.Entry<Integer, String>> heapTableView = new TableView<>();

    @FXML
    private ListView<Integer> programStatesView =new ListView<>();

    @FXML
    private ListView<String> outputListView = new ListView<>();

    @FXML
    private ListView<String> fileListView= new ListView<>();

    @FXML
    private ListView<String> exeStackView=new ListView<>();

    @FXML
    private TableColumn<HashMap.Entry<Integer, String>, Integer> heapAddressColumn=new TableColumn<>();

    @FXML
    private TableColumn<HashMap.Entry<Integer, String>, String> heapValueColumn=new TableColumn<>();

    @FXML
    private TableView<Map.Entry<String, String>> symTableView=new TableView<>();

    @FXML
    private TableColumn<Map.Entry<String, String>, String> symVarNameColumn=new TableColumn<>();

    @FXML
    private TableColumn<Map.Entry<String, String>, String> symValueColumn=new TableColumn<>();

    public void setController(Controller controller) {
        this.controller = controller;
        selectedProgram = controller.getRepository().getProgramStateList().get(0);
        loadData();
    }

    private void loadData(){
        programStatesView.getItems().setAll(controller.getRepository().getProgramStateList().stream()
                .map(ProgramState::getThreadID).collect(Collectors.toList()));

        if(selectedProgram != null) {
            nrPrograms.setText(Integer.toString(controller.getRepository().getProgramStateList().size()));

            outputListView.getItems().setAll(selectedProgram.getOutput().getData().stream()
                    .map(Object::toString).collect(Collectors.toList()));

            fileListView.getItems().setAll(String.valueOf(selectedProgram.getFileTable().getContent().keySet()));

            exeStackView.getItems().setAll(selectedProgram.getExecutionStack().getStack().stream()          // we can use directly .setItems() only when we have an ObservableList
                    .map(IStatement::toString).collect(Collectors.toList()));

            IDict<Integer, IValue> heapTable = selectedProgram.getHeap();
            List<Map.Entry<Integer, String>> heapTableList = new ArrayList<>();
            for (Map.Entry<Integer, IValue> elem : heapTable.getContent().entrySet()) {
                Map.Entry<Integer, String> el = new AbstractMap.SimpleEntry<>(elem.getKey(), elem.getValue().toString());
                heapTableList.add(el);
            }
            heapTableView.setItems(FXCollections.observableList(heapTableList));
            heapTableView.refresh();

            heapAddressColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
            heapValueColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue()));

            IDict<String, IValue> symbolTable = selectedProgram.getSymbolsDict();
            List<Map.Entry<String, String>> symbolTableList = new ArrayList<>();
            for (Map.Entry<String, IValue> elem : symbolTable.getContent().entrySet()) {
                Map.Entry<String, String> el = new AbstractMap.SimpleEntry<>(elem.getKey(), elem.getValue().toString());
                symbolTableList.add(el);
            }
            symTableView.setItems(FXCollections.observableList(symbolTableList));
            symTableView.refresh();


            symVarNameColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey()));
            symValueColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue()));
        }
    }

    @FXML
    public void onRunOneStepButton() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");

        if (controller == null) {
            alert.setHeaderText("No program selected");
            alert.setContentText("Please select a program!");
            alert.showAndWait();
        }

        if (selectedProgram.getExecutionStack().size() == 0) {
            alert.setHeaderText("The program is over");
            alert.setContentText("Select a new program to execute!");
            alert.showAndWait();
        }

        controller.oneStepExecution();
        System.out.println(selectedProgram.getExecutionStack());
        loadData();
    }

    @FXML
    public void setSelectedProgram() {
        if (programStatesView.getSelectionModel().getSelectedIndex() >= 0) {
            selectedProgram = controller.getRepository().getProgramStateList().get(programStatesView.getSelectionModel().getSelectedIndex());
            loadData();
        }
    }

}
