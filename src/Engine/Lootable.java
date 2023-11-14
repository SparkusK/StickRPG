/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Engine;

import java.awt.Point;

/**
 * @filename Lootable.java
 * @author Shane
 * @date 10 Jan 2013
 */

// -----------------------------------------------------------------------------
// Imports & Definition
// -----------------------------------------------------------------------------

public class Lootable extends Tile {
// -----------------------------------------------------------------------------
// Fields
// -----------------------------------------------------------------------------

    private String name;

    private String message;

    private Point position;

    private boolean interactable;

    private boolean empty = true;

    public boolean isEmpty() {
        return empty;
    }

    private void setEmpty(boolean empty) {
        this.empty = empty;
    }







// -----------------------------------------------------------------------------
// Getters $ Setters
// -----------------------------------------------------------------------------


    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public Point getPosition() {
        return position;
    }

    public void setInteractable(boolean interactable) {
        this.interactable = interactable;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(Point position) {
        this.position = position;
    }



// -----------------------------------------------------------------------------
// Constructors
// -----------------------------------------------------------------------------

//    public Lootable(String name, String message, boolean interactable, Image image, boolean walkable, Point position) {
//        super(image, walkable);
//        this.name = name;
//        this.message = message;
//        this.interactable = interactable;
//        this.position = position;
//    }

    public Lootable(String name, String message, boolean interactable, String image, boolean walkable, Point position) {
        super(image, walkable);
        this.name = name;
        this.message = message;
        this.interactable = interactable;
        this.position = position;
        this.setEmpty(false);
    }

    public Lootable() {
        super();
    }


// -----------------------------------------------------------------------------
// Methods
// -----------------------------------------------------------------------------

    @Override
    public String toString() {
        // define all the necessary parts
        String mother = super.toString();
        String pointStr = Integer.toString(position.x) + "," + Integer.toString(position.y);
        String interactablestr = Boolean.toString(interactable);
        // put them together
        String output = mother + "," + name + "," + message + "," + pointStr + "," + interactablestr;
        // return the output
        return output;

    }

// -----------------------------------------------------------------------------
// Main
// -----------------------------------------------------------------------------


}
