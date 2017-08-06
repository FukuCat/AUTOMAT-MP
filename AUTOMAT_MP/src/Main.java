import engine.MachineProject;

public class Main {

    public static void main(String[] args) {
        new MachineProject("MP Demo", 720, 480, 60).start();

        /*
        MealyMachine machine;
        MealyState sA = new MealyState("A", true);

        String transitionMap[][] = {
                {sA.getName(),"1","0",sA.getName()},
                {sA.getName(),"0","1",sA.getName()},
        };

        machine = new MealyMachine("1100011", transitionMap);
        machine.addState(sA);
        machine.initialState(sA.getName());

        System.out.println(machine.getOutput());
        machine.nextState();
        System.out.println(machine.getOutput());
        machine.nextState();
        System.out.println(machine.getOutput());
        machine.nextState();
        System.out.println(machine.getOutput());
        machine.nextState();
        System.out.println(machine.getOutput());
        machine.nextState();
        System.out.println(machine.getOutput());
        machine.nextState();
        System.out.println(machine.getOutput());
        machine.nextState();
        System.out.println(machine.getOutput());
        machine.nextState();
        System.out.println(machine.getOutput());
        */

    }
}
