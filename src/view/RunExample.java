package view;

import controller.Controller;
import model.ProgramState;

public class RunExample extends Command{
    private Controller controller;

    public RunExample(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    public void execute() {
        try{
            ProgramState programState =  controller.allStep();
            System.out.println("Output: ");
            for(int i = 0; i < programState.getOutput().size(); ++i){
                System.out.println(programState.getOutput().getData().get(i));
            }
        }
        catch (Exception e){
            // treat the exceptions that can't be solved in the controller
            System.out.println(e.toString());
        }
    }
}
