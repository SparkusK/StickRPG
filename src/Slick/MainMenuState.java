package Slick;

/**
 * @filename MainMenuState.java
 * @author Shane
 * @date 09 Jan 2013
 */

// -----------------------------------------------------------------------------
// Imports & Definition
// -----------------------------------------------------------------------------

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuState extends BasicGameState {

// -----------------------------------------------------------------------------
// Fields
// -----------------------------------------------------------------------------

    private int stateID = -1;

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

    MainMenuState( int stateID )
    {
       this.stateID = stateID;
    }

// -----------------------------------------------------------------------------
// Methods
// -----------------------------------------------------------------------------

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
 
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
 
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
 
    }

}
