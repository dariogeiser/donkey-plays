package com.example.donkey_plays.com.example.donkey_plays.models;

public class GameState {
    private static Game game = new Game();

    public GameState() {

    }

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        GameState.game = game;
    }
}
