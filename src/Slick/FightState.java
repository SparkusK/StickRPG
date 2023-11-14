
package Slick;

import Engine.CombatCharacter;
import Engine.Player;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.*;

/**
 * @filename MainMenuState.java
 * @author Shane
 * @date 09 Jan 2013
 */

// -----------------------------------------------------------------------------
// Imports & Definition
// -----------------------------------------------------------------------------

public class FightState extends BasicGameState {
    public static final float bars_height = 40.0F;
    public static final float mana_bar_x_position = 580.0F;
    public static final float mpBarWidth = 215.0F;
    private static final float bar_text_y_position = 560.0F;
    private static final float bars_y_position = 540.0F;
    private static final float health_bar_x_position = 5.0F;



// -----------------------------------------------------------------------------
// Fields
// -----------------------------------------------------------------------------


    private enum STATES {
        
    }

    boolean enemyHasAttacked = false;

    private int stateID = -1;

    Player          player;
    CombatCharacter  enemy;

    private Image background;
    private Image playerChar;
    private Image  enemyChar;

    private int charx =  40;
    private int charY = 212;

    private int enemyX = 590;
    private int enemyY = 230;

    TrueTypeFont uiFont      = null;
    TrueTypeFont messageFont = null;

    Input input;

    // --- New fields --- //

    private Image         menu;
    private Image    menuFrame;
    private Image    attButton;
    private Image  spellButton;
    private Image   itemButton;
    private Image statusButton;

    private final int NONE      = 0;
    private final int ATTACK    = 1;
    private final int SPELL     = 2;
    private final int ITEM      = 3;
    private final int STATUS    = 4;

    private final Point attackMidpoint  = new Point(280, 540);
    private final Point spellMidpoint   = new Point(360, 540);
    private final Point itemMidpoint    = new Point(440, 540);
    private final Point statusMidpoint  = new Point(520, 540);

    private float healthWidth;
    private float hpBarWidth = 215f;
    private String HPString = "";
    private float manaWidth;
    private float manaBarWidth = 215f;
    private String MPString = "";




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

    FightState( int stateID )
    {
       this.stateID = stateID;
    }


// -----------------------------------------------------------------------------
// Methods
// -----------------------------------------------------------------------------

    @Override
    public void enter(GameContainer gc, StateBasedGame sb) throws SlickException
    {
        super.enter(gc, sb);

        // Here we should probably load the player's state from some kind of file or something
        // that keeps track of the player - things like Level, Experience, HP (max and current), Damage,
        // etc etc - all the stats. as well as status effects like Poison, Slow etc.
        //
        // we should probably also load some kind of enemy - probably choose a random one from
        // a file, suited for the level itself. I should probably write some kind of
        // enter function where you can say which enemy you want to load - for boss fights and
        // special encounters and such.
        //
        // For now, though, I'm just gonna load a default player and a default enemy character. I'll
        // implement the rest as I go along.

        player = Game.getPlayer();
        enemy = new CombatCharacter(150, 15, 150, 20);
//        System.out.println("in fightstate:" + Integer.toString(enemy.getExpReward()));

        Game.setExpReward(enemy.getExpReward());

    }

//    public void enter(GameContainer gc, StateBasedGame sb, Player _player, Level _level) throws SlickException {
//        super.enter(gc, sb);
//
//        player = _player;
//        Level level = _level;
//
//        enemy = level.getEnemy();
//        Game.setExpReward(enemy.getExpReward());
//
//    }
//
//    public void enter(GameContainer gc, StateBasedGame sb, Player _player, CombatCharacter _enemy) throws SlickException {
//        super.enter(gc, sb);
//
//        player = _player;
//        enemy = _enemy;
//
//        Game.setExpReward(enemy.getExpReward());
//
//    }
//


    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        Game.setExpReward(enemy.getExpReward());
    }


    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        background = new Image("res/fightImages/Background.png");
        menu = new Image("res/fightImages/FightMenu.png");
        menuFrame = new Image("res/fightImages/MenuBackground.png");
        attButton = new Image("res/fightImages/AttackIcon.png");
        spellButton = new Image("res/fightImages/SpellIcon.png");
        itemButton = new Image("res/fightImages/ItemsIcon.png");
        statusButton = new Image("res/fightImages/StatusIcon.png");


//        menuOverlay = new Image("res/fightImages/MenuOverlay.png");
//        attackBtn = new Image("res/fightImages/AttackButton.png");
        playerChar = new Image("res/fightImages/StickyCombat.png");
        enemyChar = new Image("res/fightImages/WolfStick.png");
        Font UIFont = new Font("Verdana", Font.BOLD, 20);
        Font normalMessageFont = new Font("Arial", Font.PLAIN, 16);
        ArrayList<String> messages = new ArrayList<String>();
        uiFont = new TrueTypeFont(UIFont, true);
        messageFont = new TrueTypeFont(normalMessageFont, true);
//        messageBox = new Image("res/fightImages/CombatMessageBox.png");
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        background.draw(0, 0);
        menuFrame.draw(0, 480);
        menu.draw(225, 490);
        drawHealthBar(g);
        drawManaBar(g);
        attButton.draw(245, 505);
        spellButton.draw(325, 505);
        itemButton.draw(405, 505);
        statusButton.draw(485, 505);

        playerChar.draw(charx, charY);
        enemyChar.draw(enemyX, enemyY);


//        String hpString = "HP: " + Integer.toString(player.getHP()) + "/" + Integer.toString(player.getMaxHP());
//        uiFont.drawString(70, 495, hpString, Color.yellow);
//        menuOverlay.draw(0, 450);
//        attackBtn.draw(btnX, btnY);

    }

    private void drawHealthBar(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(health_bar_x_position, bars_y_position, hpBarWidth, bars_height);
        g.setColor(Color.red);
        g.fillRect(health_bar_x_position, bars_y_position, healthWidth, bars_height);
        int drawWidth = g.getFont().getWidth(MPString);
        float xDrawPosition = (hpBarWidth / 2) - (drawWidth / 2) + health_bar_x_position;
        g.setColor(Color.white);
        g.drawString(HPString, xDrawPosition, bar_text_y_position);
    }

    private void drawManaBar(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(mana_bar_x_position, bars_y_position, mpBarWidth, bars_height);
        g.setColor(Color.blue);
        g.fillRect(mana_bar_x_position + (mpBarWidth - manaWidth), bars_y_position, manaWidth, bars_height);
        int drawWidth = g.getFont().getWidth(MPString);
        float xDrawPosition = (mpBarWidth / 2) - (drawWidth / 2) + mana_bar_x_position;
        g.setColor(Color.white);
        g.drawString(MPString, xDrawPosition, bar_text_y_position);
    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        HPString = Integer.toString(player.getHP())+ "/" + Integer.toString(player.getMaxHP());
        MPString = Integer.toString(player.getMP()) + "/" + Integer.toString(player.getMaxMP());
        healthWidth = (hpBarWidth / player.getMaxHP()) * player.getHP();
        manaWidth = (manaBarWidth / player.getMaxMP()) * player.getMP();

        input = container.getInput();


        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            Point clickPoint = new Point(input.getMouseX(), input.getMouseY());

            if (circleClicked(clickPoint) != 0)  {
                switch (circleClicked(clickPoint)) {
                    case 1:

                }
            }
        }

    }


    private int circleClicked(Point clickPoint) {


        if (isInsideCircle(attackMidpoint, clickPoint)) {
            return ATTACK;
        }

        if (isInsideCircle(spellMidpoint, clickPoint)) {
            return SPELL;
        }

        if (isInsideCircle(itemMidpoint, clickPoint)) {
            return ITEM;
        }

        if (isInsideCircle(statusMidpoint, clickPoint)) {
            return STATUS;
        }

        return NONE;
    }

    // if the distance between the point clicked and the circle's midpoint,
    // is greater than the circle's radius,
    // (d = sqrt( (Xclicked - Xmidpoint)^2 + (Yclicked - Ymidpoint)^2) ) > radius)
    // then the point clicked was outside the circle's bounds.

    private boolean isInsideCircle(Point circleMidpoint, Point clickPoint) {
        int radius = 35;
        double distance;
        double differenceX = (double)(circleMidpoint.x - clickPoint.x);
        double differenceY = (double)(circleMidpoint.y - clickPoint.y);
        distance = Math.sqrt( Math.pow(differenceX, 2) + Math.pow(differenceY, 2));
        if (distance < radius) return true;
//        System.out.println("isInsideCircle returning false. The distance shows: " + distance);
        return false;
    }




}

