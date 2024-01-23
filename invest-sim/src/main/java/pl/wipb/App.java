package pl.wipb;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Game game = new Game();
        game.StartGame();
        game.printStockValues();
        game.NextDay();
        System.out.println("Next day");
        game.printStockValues();
    }
}
