package pl.wipb.Command;

//import javafx.util.Pair;
import pl.wipb.Investments.Investment;

import java.util.List;

// - Player
//invoker - Investment
public interface Command {
    // inicjator ma mieć pole private Command command;
    public void undo();

    public void redo();
}