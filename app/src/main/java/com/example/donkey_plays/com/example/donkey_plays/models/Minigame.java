package com.example.donkey_plays.com.example.donkey_plays.models;


import com.example.donkey_plays.com.example.donkey_plays.activities.MainActivity;
import com.example.donkey_plays.com.example.donkey_plays.services.PlayerService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Minigame {
    //Ref of Introduction View
    private int id;

    private Class gameActivity;
    // Ref of Result View
    private int resultRef;


    private Player currentPlayer;
    private List<Player> allPlayers;
    private HashMap<String, Integer> standing;

    public Minigame(int id, Class gameActivity, int resultRef) {
        this.id = id;
        this.gameActivity = gameActivity;
        this.resultRef = resultRef;
        this.standing = new HashMap<>();
        this.allPlayers = getRandomizePlayersOrder();
        setCurrentPlayer();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private List<Player> getRandomizePlayersOrder(){
        PlayerService playerService = new PlayerService();
        List<Player> players = new ArrayList<>(playerService.getPlayers());
        Collections.shuffle(players);
        return players;
    }

    public boolean setCurrentPlayer() {
        if (this.allPlayers.size() == 0){
            return false;
        }
            this.currentPlayer = this.allPlayers.get(0);
            this.allPlayers.remove(0);
            return true;
    }

    public void addStanding(String name, int value){
        this.standing.put("name", value);
    }

    public Class getGameActivity() {
        return gameActivity;
    }

    public int getResultRef() {
        return resultRef;
    }

    public HashMap<String, Integer> getStanding() {
        return standing;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
