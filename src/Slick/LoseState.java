package Slick;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @filename LoseState.java
 * @author Shane
 * @date 30 Jan 2013
 */

// -----------------------------------------------------------------------------
// Imports & Definition
// -----------------------------------------------------------------------------

public class LoseState extends BasicGameState {

// -----------------------------------------------------------------------------
// Fields
// -----------------------------------------------------------------------------

    private int stateID = -1;

    private Image gameOver;

// -----------------------------------------------------------------------------
// Getters $ Setters
// -----------------------------------------------------------------------------


    @Override
    public int getID() {
        return this.stateID;
    }

// -----------------------------------------------------------------------------
// Constructors
// -----------------------------------------------------------------------------

    LoseState( int stateID )
    {
       this.stateID = stateID;
    }


// -----------------------------------------------------------------------------
// Methods
// -----------------------------------------------------------------------------

    @Override
    public void enter(GameContainer gc, StateBasedGame sb) throws SlickException{

    }

    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        gameOver = new Image("res/GameOver.png");
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        gameOver.draw(0,0);
    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        
    }


}