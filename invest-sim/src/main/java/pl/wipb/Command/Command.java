package pl.wipb.Command;

// - Player?
//invoker - Investment?

//ej kurde serio nie wiem co z tym wyzej 😨
public interface Command {
    // inicjator ma mieć pole private Command command;
    public void execute(int amount);
}