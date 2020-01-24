package com.example.donkey_plays.com.example.donkey_plays.models;

import com.example.donkey_plays.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private List<Player> players;
    private List<Minigame> minigames;

    public Game () {
        this.players = new ArrayList<Player>();
        this.minigames = new ArrayList<Minigame>();
        loadMinigames();
    }

    private void loadMinigames() {
        minigames.add(new Minigame(R.layout.voice_battler_introduction));
    }

    public Minigame getNewMinigame() {
        Random rand = new Random();
        Minigame randomMiniGame = minigames.get(rand.nextInt(minigames.size()));
        minigames.remove(randomMiniGame);
        return randomMiniGame;
    }

    public boolean addPlayer(Player newPlayer){
        newPlayer.setName(newPlayer.getName().trim());
        boolean playerAlreadyExistsis = false;
        for(Player player : players){
            if(player.getName().equals(newPlayer.getName())){
                playerAlreadyExistsis = true;
            }
        }

        if(!playerAlreadyExistsis && players.size() < 2 && !newPlayer.getName().equals("")){
            return players.add(newPlayer);
        }
        return false;
    }

    public boolean deletePlayer(Player player){
        return players.remove(player);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
