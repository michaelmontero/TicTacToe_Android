package com.example.elleu.tictactoe_android;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import com.example.elleu.tictactoe_android.Game;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    Game game;
    @Before
    public void setUp(){
        game = new Game(2);
        game.TILE[0] = 1;
        game.TILE[4] = 1;
        game.TILE[8] = 2;
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void tileToPlay_isCorrect()throws Exception{

        assertEquals(2, game.getRondomTile());
    }
}