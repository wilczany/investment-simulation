package pl.wipb;
import java.util.List;
import pl.wipb.Investments.Investment;
class Game {

    private  List<Investment> Investment; 
    private int day;
    private Game instance = null;
    private Player player;
    private List<Double> high_score;
    public Game GetInstance() {
        return instance;
    }
    public void StartGame()
    {

    }
    public void EndGame()
    {

    }
    public void AddPlayer(Player player)
    {

    }
    public void NextDay()
    {
        day++;
    }
    public void skipDays(int number)
    {
        day+=number;
    }

}