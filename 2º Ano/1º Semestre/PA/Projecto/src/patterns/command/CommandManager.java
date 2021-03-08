package patterns.command;

import java.io.Serializable;
import java.util.Stack;

/**
 * Class that will manage the command and inplements a Serializable
 */
public class CommandManager implements Serializable {
    private Stack<Command> commandHistory;

    /**
     * Method that will inicialize the CommandManager
     */
    public CommandManager() {
        this.commandHistory = new Stack<>();
    }

    /**
     * Method that will recieve a command and will execute it and will push it to the Stack created previously
     *
     * @param cmd a command to push to Stack
     */
    public void executeCommand(Command cmd) {
        if(cmd == null) return;
        cmd.execute();
        this.commandHistory.push(cmd);
    }

    /**
     * Method that will undo a action is the Stack is not empty, will print the commandHistory and will unExecute the command that was popped from the stack
     */
    public void undo() {
        if(commandHistory.isEmpty()) return;
        Command cmd = commandHistory.pop();
        cmd.unExecute();
    }

    /**
     * Method that will return a text with the command history
     *
     * @return a text with the command history
     */
    public String listAll() {
        String str="COMMAND HISTORY: \n";
        int count=1;
        for (Command cmd : commandHistory) {
            str= str + (count++) + "-> " + cmd.toString() + "\n";
        }
        return str;
    }

}
