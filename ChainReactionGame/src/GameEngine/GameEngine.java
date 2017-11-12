package GameEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameEngine {

    private int _numPlayers;
    private int _gridSize;
    private int _choice;
    private GameController _gc;
    private Map player_colors;

    public GameEngine(int _numPlayers, int _gridSize) {
        this._numPlayers = _numPlayers;
        this._gridSize = _gridSize;
    }


    public int get_numPlayers() {
        return _numPlayers;
    }

    public void set_numPlayers(int _numPlayers) {
        this._numPlayers = _numPlayers;
    }

    public int get_gridSize() {
        return _gridSize;
    }

    public void set_gridSize(int _gs) {
        _gridSize = _gs;
    }

    public int get_choice() {
        return _choice;
    }

    public void set_choice(int _choice) {
        this._choice = _choice;
    }

    public GameController get_gc() {
        return _gc;
    }

    public void set_gc(GameController _gc) {
        this._gc = _gc;
    }

    public void setplayer_colors(HashMap p){
        player_colors=p;
    }

    /*

    Will check if "gamestate.ser" is empty or doesn't exist. If it exists, load the state of
    game and continue, else create a new file

     */

    public boolean checkResume() throws Exception {

        File file = new File("gamestate.ser");

        boolean empty = !file.exists() || file.length() == 0;

        String checkEmpty = empty ? "File is empty or doesn't exist" : "File is not empty";
        System.out.println(checkEmpty);

        return !empty;

    }

    public void startGame() throws IOException, ClassNotFoundException, Exception {

        if(!checkResume()) {

            for (Object key: player_colors.keySet()){
                System.out.println(key);
                System.out.println(player_colors.get(key));
            }
            ArrayList<Player> players = new ArrayList<Player>();

            for (int i = 0; i < _numPlayers; i++) {
                players.add(new Player(player_colors.get(i+1).toString(), true, false));
                System.out.println(player_colors.get(i+1).toString());
            }

            players.get(0).set_isKillable(true);


            Grid grid = new Grid(_gridSize);

            GameState gs = new GameState(players, grid);

            _gc = new GameController(players, grid, gs, false);

        }

        else {

            // resume button handler will set some things here

            _gc = new GameController();
            _gc.loadGameState();
            this.set_numPlayers(_gc.get_players().size());


        }

    }



}
