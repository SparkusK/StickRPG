
package Slick;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @filename MainMenuState.java
 * @author Shane
 * @date 09 Jan 2013
 */

// -----------------------------------------------------------------------------
// Imports & Definition
// -----------------------------------------------------------------------------

public class RewardsState extends BasicGameState {

// -----------------------------------------------------------------------------
// Fields
// -----------------------------------------------------------------------------

    private int stateID = -1;

    private Image rewardScreen;
    private Image continueBtn;
    private Input input;
    private String startMessage = "Congratulations! You have overcome the opponent!";
    private String getExp;
    private String haveExp;
    private String needExp;
    private String newExp;
    private String leveledUp = "You have gained a level of experience!";
    boolean levelUp = false;


    TrueTypeFont trueTypeFont = null;
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

    RewardsState( int stateID )
    {
       this.stateID = stateID;
    }


// -----------------------------------------------------------------------------
// Methods
// -----------------------------------------------------------------------------


    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        System.out.println("entering rewardstate");
        System.out.println("exp reward in rewardstate: " + Integer.toString(Game.getExpReward()));
        int rewardExp = Game.getExpReward();
        int currentExp = Game.getPlayer().getExp();
        int expToNext = Game.getPlayer().determineExpToNext();
        Game.getPlayer().setExp(currentExp + rewardExp);
        int totalExp = Game.getPlayer().getExp();
        levelUp = Game.getPlayer().isLevelUp();
        getExp =  "Rewarded EXP:  " + Integer.toString(rewardExp);
        haveExp = "Previous EXP:  " + Integer.toString(currentExp);
        needExp = "Required EXP:  " + Integer.toString(expToNext);
        newExp =  "Current EXP:   " + Integer.toString(totalExp);

    }

    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        rewardScreen = new Image("res/RewardScreen.png");
        continueBtn = new Image("res/ContinueButton.png");
        trueTypeFont = new TrueTypeFont(new Font("Verdana", Font.PLAIN, 18), true);


    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        rewardScreen.draw(0,0);
        continueBtn.draw(600, 500);
        trueTypeFont.drawString(100, 50, startMessage, Color.red);
        trueTypeFont.drawString(100, 100, getExp, Color.red);
        trueTypeFont.drawString(100, 150, haveExp, Color.red);
        trueTypeFont.drawString(100, 200, needExp, Color.red);
        trueTypeFont.drawString(100, 250, newExp, Color.red);
        if (levelUp) trueTypeFont.drawString(100, 300, leveledUp, Color.red);
    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        input = container.getInput();
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            if (input.getMouseX() >= 600 && input.getMouseX() <= 800) {
                if (input.getMouseY() >= 500 && input.getMouseY() <= 560) {
                    
                    Game.getPlayer().levelUp();
                    game.enterState(Game.WORLDSTATE);
                }
            }
        }
    }


}
