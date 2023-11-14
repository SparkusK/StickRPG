/**
 * @filename WorldState.java
 * @author Shane
 * @date 09 Jan 2013
 */

package Slick;

import Engine.LevelLoader;
import Engine.Camera;
import Engine.TextureManager;
import Engine.Level;
import java.awt.Point;
import java.io.FileNotFoundException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class WorldState extends BasicGameState {

// -----------------------------------------------------------------------------
// Fields
// -----------------------------------------------------------------------------

    // game configuration
    private int stateID = -1;

    // state specific fields
    private Level map;
    private Camera camera;
    private Image black;
    private Image sticky;
    private TextureManager textures;

    // -- game logic fields --
    //      direction config
    private final int NORTH = 0;
    private final int SOUTH = 1;
    private final int EAST = 2;
    private final int WEST = 3;

    //      rate of battle encounters
    private final double encounterRate = 0.15;
    //      character direction
    private int characterDirection = 0;

    //      input handling
    private Input input;
    private Point target;
    private boolean didMove;
    
    //      portal handling
    private boolean onPortalSquare;

    // -- end of game logic fields --

    //  Scrolling fields
    private boolean scrolling = false;
    private int xInset = 0;
    private int yInset = 0;
    private int timePassed = 0;
    private int frameNumber = 0;
    private boolean scrollingFinished = false;

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

    WorldState( int stateID )  {
       this.stateID = stateID;
    }

// -----------------------------------------------------------------------------
// Methods
// -----------------------------------------------------------------------------

    // -------------------------------------------------------------------------

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        textures = new TextureManager();
        try {
            map = LevelLoader.readLevel("level001.sticklvl");
        } catch (FileNotFoundException ex) {
            System.out.println("Tried to load level data, but could not find level");
        }
        try {
            LevelLoader.addTextures("level001.sticklvl", textures );
        } catch (FileNotFoundException ex) {
            System.out.println("Tried to load texture data, but could not find level");
        }
//        map = new Level(new Point(50, 50), new Point(6,7));
//        map.createLevel();
        camera = new Camera(map.getStartPosition(), map);
        black = new Image("res/tileImages/Black.png");
        sticky = new Image("res/Sticky.png");
//        map.printTiles();
//        map.printObjects();


    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        for (int y = 0; y < 17; y++) {
            for (int x = 0; x < 22; x++) {
                textures.getImageByName(camera.getView()[x][y].getImageName()).draw(((x - 1) * 40) + xInset, ((y - 1) * 40) + yInset);
                try {
                    textures.getImageByName(camera.getLootables()[x][y].getImageName()).draw(((x - 1) * 40) + xInset, ((y - 1) * 40) + yInset);
                } catch (NullPointerException e) {}
            }
        }
        sticky.draw(9 * 40, 7 * 40);

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        if (scrolling) {
            scrollCamera(delta, sbg);
        }
        else {
            input = gc.getInput();
            target = new Point(camera.getTopLeft().x, camera.getTopLeft().y);

            move(input, target);
            if (didMove) {
                if (map.isWalkable(target.x + 9, target.y + 7)) {
                    scrolling = true;
                    didMove = false;
//                    isOnPortalSquare(new Point(target.x + 9, target.y + 7));
//                    if (onPortalSquare) {
//                        Point portalTarget = new Point(getCorrespondingPosition(new Point(target.x + 9, target.y + 7)));
//                        portalTarget.x -= 9;
//                        portalTarget.y -= 7;
//                        camera.setTopLeft(portalTarget);
//                        return;
//                    }
//                    else {
//                        encounter(sbg);
//                    }
                }
                else {
                    scrolling = false;
                }
            interact(input, target);
            }
        }
    }




    // --------------------

    private void interact(Input input, Point target) {
        if (input.isKeyPressed(Input.KEY_E)) {
            Point interactTarget = new Point(target.x + 9, target.y + 7);

            if (characterDirection == WEST) {
                interactTarget.x -= 1;
            }
            if (characterDirection == EAST) {
                interactTarget.x += 1;
            }
            if (characterDirection == NORTH) {
                interactTarget.y -= 1;
            }
            if (characterDirection == SOUTH) {
                interactTarget.y += 1;
            }
            map.interact(interactTarget);
        }
    }

    private void move(Input input, Point target) {
        // move left
        if (input.isKeyDown(Input.KEY_A)) {

            target.x -= 1;
            characterDirection = WEST;
            didMove = true;
        }
        // move right
        else if(input.isKeyDown(Input.KEY_D)) {
            target.x += 1;
            characterDirection = EAST;
            didMove = true;
        }
        // move up
        else if(input.isKeyDown(Input.KEY_W)) {
            target.y -= 1;
            characterDirection = NORTH;
            didMove = true;
        }
        // move down
        else if(input.isKeyDown(Input.KEY_S)) {
            target.y += 1;
            characterDirection = SOUTH;
            didMove = true;
        }
        else {
            didMove = false;
        }

    }
    
    private void teleport(Point fromPortal) {
        
    }
    
    private void isOnPortalSquare(Point standingPos) {

        int standx, standy, portalx, portaly;
        standx = standingPos.x;
        standy = standingPos.y;

//        System.out.println("You are on: " + Integer.toString(standingPos.x) + ";" + Integer.toString(standingPos.y));
        for (int i = 0; i < map.getPortalSquares().size(); i++) {
            portalx = map.getPortalSquares().get(i).getPortalFromPoint().x;
            portaly = map.getPortalSquares().get(i).getPortalFromPoint().y;
            if (standx == portalx && standy == portaly) {
                onPortalSquare = true;
//                System.out.println("You are on a portal square.");
//                System.out.println("Does the game think so? ");
//                System.out.println(Boolean.toString(onPortalSquare));
                return;
            }
        }
//        System.out.println("Setting onPortalSquare to false.");

        onPortalSquare = false;
    }

    private Point getCorrespondingPosition(Point fromPortal) {
        Point correspondingPos = new Point();

        int fromx, fromy, portalx, portaly;
        fromx = fromPortal.x;
        fromy = fromPortal.y;
        
        for (int i = 0; i < map.getPortalSquares().size(); i++) {
            portalx = map.getPortalSquares().get(i).getPortalFromPoint().x;
            portaly = map.getPortalSquares().get(i).getPortalFromPoint().y;
            if (fromx == portalx && fromy == portaly) {
                correspondingPos = map.getPortalSquares().get(i).getPortalToSquare();
            }
        }

//        System.out.println("The corresponding position is: ");
//        System.out.println(Integer.toString(correspondingPos.x) + ";" + Integer.toString(correspondingPos.y));
        return correspondingPos;
    }

    private void encounter(StateBasedGame sbg) {
        double roll = Math.random() ;
//        System.out.println(Double.toString(roll));
        if (roll < encounterRate) {
            sbg.enterState(Game.FIGHTSTATE);

        }
        didMove = false;

    }

    private void scrollCamera(int delta, StateBasedGame sbg) {
        if (scrolling) {
            timePassed += delta;
            if (timePassed >= 2) {
                timePassed = 0;
                frameNumber++;
                if (frameNumber <= 19) {

                    if (characterDirection == WEST) {
                        xInset = xInset + 2;
                    }
                    if (characterDirection == EAST) {
                        xInset = xInset - 2;
                    }
                    if (characterDirection == NORTH) {
                        yInset = yInset + 2;
                    }
                    if (characterDirection == SOUTH) {
                        yInset = yInset - 2;
                    }
                }
                else {
                    frameNumber = 0;
                    xInset = 0;
                    yInset = 0;
                    scrolling = false;
                    scrollingFinished = true;
                    camera.setTopLeft(target);
                    isOnPortalSquare(new Point(target.x + 9, target.y + 7));
                    if (onPortalSquare) {
                        Point portalTarget = new Point(getCorrespondingPosition(new Point(target.x + 9, target.y + 7)));
                        portalTarget.x -= 9;
                        portalTarget.y -= 7;
                        camera.setTopLeft(portalTarget);
                        return;
                    }
                    else {
                        encounter(sbg);
                    }

                }
            }
        }

    }






}
