package view;

import controller.Controller;

public class RunExample extends Command{
    private Controller controller;

    public RunExample(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    public void execute() {
        try{
            controller.allStep();
        }
        catch (Exception e){
            // treat the exceptions that can't be solved in the controller
            System.out.println(e.getMessage());
        }
    }
}
