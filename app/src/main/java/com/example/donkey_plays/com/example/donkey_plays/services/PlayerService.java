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

    public void increaseScore(String playerName){
        for(Player player: this.getPlayers()){
            if(player.getName().equals(playerName)){
                player.increaseScore();
            }
        }
        GameState.setGame(game);
    }

    public Player getWinner(){
        Player winner = null;
        for(Player player: getPlayers() ){
            if (winner == null || player.getScore() > winner.getScore()) {
                winner = player;
            }
        }
        return winner;
    }

    public List<Player> getPlayers() {
        return game.getPlayers();
    }
}
