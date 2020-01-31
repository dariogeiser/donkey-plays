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
    private HashMap<String, Double> standing;

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

    private List<Player> getRandomizePlayersOrder() {
        PlayerService playerService = new PlayerService();
        List<Player> players = new ArrayList<>(playerService.getPlayers());
        Collections.shuffle(players);
        return players;
    }

    public boolean setCurrentPlayer() {
        if (this.allPlayers.size() == 0) {
            return false;
        }
        this.currentPlayer = this.allPlayers.get(0);
        this.allPlayers.remove(0);
        return true;
    }

    public HashMap.Entry<String, Double> getWinner() {
        HashMap.Entry<String, Double> maxEntry = null;
        for (HashMap.Entry<String, Double> entry : standing.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }
        return maxEntry;
    }

    public void addStanding(String name, Double value) {
        this.standing.put(name, value);
    }

    public Class getGameActivity() {
        return gameActivity;
    }

    public int getResultRef() {
        return resultRef;
    }

    public HashMap<String, Double> getStanding() {
        return standing;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
