package Slick;

import Engine.Player;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @since 05 July 2012
 * @author Shane Kelly
 */
public class Game extends StateBasedGame
{

    // state list
    public static int MAINMENUSTATE = 0;
    public static int WORLDSTATE = 1;
    public static int FIGHTSTATE = 2;
    public static int REWARDSSTATE = 3;
    public static int LOSESTATE = 4;



    // game configuration
    static int height = 600;
    static int width = 800;
    static boolean fullscreen = false;
    static boolean showFPS = false;
    static String title = "Stick RPG!";
    static int fpslimit = 60;

    // player
    private static Player player;
    private boolean isPlayerLoaded = false;
    private static int expReward;



    public static void setExpReward(int expReward) {
        Game.expReward = expReward;
    }

    public static int getExpReward() {
        return expReward;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player _player) {
        player = _player;
    }



    public Game(String title) {
          super(title);

          this.addState(new FightState(FIGHTSTATE));
          this.addState(new MainMenuState(MAINMENUSTATE));
          this.addState(new WorldState(WORLDSTATE));
          this.addState(new RewardsState(REWARDSSTATE));
          this.addState(new LoseState(LOSESTATE));
          if (!isPlayerLoaded) {
              player = new Player(1000, 900, 15, 1, 0, 100, 90);
              isPlayerLoaded = true;
          }
          this.enterState(WORLDSTATE);
    }  

     public static void main(String[] args) throws SlickException {
          AppGameContainer app = new AppGameContainer(new Game(title));
          app.setDisplayMode(width, height, fullscreen);
          app.setSmoothDeltas(true);
          app.setTargetFrameRate(fpslimit);
          app.setShowFPS(showFPS);
          app.start();
     }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {

    }


}
