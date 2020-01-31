package com.example.donkey_plays.com.example.donkey_plays.models;

import com.example.donkey_plays.R;
import com.example.donkey_plays.com.example.donkey_plays.activities.StepCounterActivity;
import com.example.donkey_plays.com.example.donkey_plays.activities.VoiceBattlerActivity;
import com.example.donkey_plays.com.example.donkey_plays.services.PlayerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private List<Player> players;
    private List<Minigame> minigames;
    private Minigame currentMinigame;

    public Game() {
        this.players = new ArrayList<>();
        this.minigames = new ArrayList<>();
    }

    public void loadMinigames() {
        minigames.add(new Minigame(R.layout.voice_battler_introduction, VoiceBattlerActivity.class, R.layout.voice_battler_result));
        minigames.add(new Minigame(R.layout.step_counter_introduction, StepCounterActivity.class, R.layout.step_counter_result));
    }

    public Minigame getNewMinigame() {
        Random rand = new Random();
        Minigame randomMiniGame = minigames.get(rand.nextInt(minigames.size()));
        this.currentMinigame = randomMiniGame;
        minigames.remove(randomMiniGame);
        return randomMiniGame;
    }

    public boolean addPlayer(Player newPlayer) {
        newPlayer.setName(newPlayer.getName().trim());
        boolean playerAlreadyExistsis = false;
        for (Player player : players) {
            if (player.getName().equals(newPlayer.getName())) {
                playerAlreadyExistsis = true;
            }
        }

        if (!playerAlreadyExistsis && players.size() < 2 && !newPlayer.getName().equals("")) {
            return players.add(newPlayer);
        }
        return false;
    }

    public boolean hasNewMiniGame() {
        if (this.minigames.size() > 0) {
            return true;
        }
        return false;
    }

    public Minigame getCurrentMinigame() {
        return currentMinigame;
    }

    public boolean deletePlayer(Player player) {
        return players.remove(player);
    }

    public List<Player> getPlayers() {
        return players;
    }


}
