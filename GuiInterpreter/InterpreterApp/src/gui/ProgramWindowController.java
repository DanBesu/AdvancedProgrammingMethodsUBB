package gui;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.ProgramState;

public class ProgramWindowController {

    private Controller controller;
    private ProgramState selectedProgram;

    @FXML
    private final TextField nrProgramStatesField = new TextField("");

    public void setController(Controller controller) {
        this.controller = controller;
        selectedProgram = controller.getRepository().getProgramStateList().get(0);
        loadData();
    }

    private void loadData(){
        if (selectedProgram != null) {
            nrProgramStatesField.setText(Integer.toString(controller.getRepository().getProgramStateList().size()));
        }
    }
}
