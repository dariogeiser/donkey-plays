package com.example.donkey_plays.com.example.donkey_plays.models;


import com.example.donkey_plays.com.example.donkey_plays.services.PlayerService;

import java.util.Collections;
import java.util.List;

public class Minigame {
    private int id;
    private boolean alreadyPlayed;
    private List<Player> players;

    public Minigame(int id) {
        this.id = id;
        this.alreadyPlayed = false;
        this.players = getRandomizePlayersOrder();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAlreadyPlayed() {
        return alreadyPlayed;
    }

    public void setAlreadyPlayed(boolean alreadyPlayed) {
        this.alreadyPlayed = alreadyPlayed;
    }

    private List<Player> getRandomizePlayersOrder(){
       // PlayerService playerService = new PlayerService();
       // List<Player> players = playerService.getPlayers();
       // Collections.shuffle(players);
        return null;
    }
}
