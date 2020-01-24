package com.example.donkey_plays.com.example.donkey_plays.services;

import android.widget.TextView;

import com.example.donkey_plays.com.example.donkey_plays.models.Game;
import com.example.donkey_plays.com.example.donkey_plays.models.GameState;
import com.example.donkey_plays.com.example.donkey_plays.models.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerService {


    private Game game;


    public PlayerService() {
        this.game = GameState.getGame();
    }

    public boolean addPlayer(Player player) {
        if (this.game.addPlayer(player)) {
            GameState.setGame(game);
            return true;
        }
        return false;
    }


    public boolean deletePlayer(Player player){
        boolean isDeleted = this.game.deletePlayer(player);
        GameState.setGame(game);
        return isDeleted;
    }

    public List<Player> getPlayers() {
        return game.getPlayers();
    }
}
