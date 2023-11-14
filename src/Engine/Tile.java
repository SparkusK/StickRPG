/**
 * @filename Texture.java
 * @author Shane
 * @date 09 Jan 2013
 */

package Engine;

// -----------------------------------------------------------------------------
// Imports & Definition
// -----------------------------------------------------------------------------

public class Tile {

// -----------------------------------------------------------------------------
// Fields
// -----------------------------------------------------------------------------

    private boolean walkable = false;
    private String imageName;

// -----------------------------------------------------------------------------
// Getters $ Setters
// -----------------------------------------------------------------------------



    public boolean isWalkable() {
        return walkable;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    public void setImageName(String image) {
        this.imageName = image;
    }

    public String getImageName() {
        return imageName;
    }




// -----------------------------------------------------------------------------
// Constructors
// -----------------------------------------------------------------------------



    public Tile(String image, boolean walkable) {
        this.imageName = image;
        this.walkable = walkable;
    }

    public Tile() {
    }


// -----------------------------------------------------------------------------
// Methods
// -----------------------------------------------------------------------------

    @Override
    public String toString() {
        String file = this.imageName;
        String walkableString = Boolean.toString(this.walkable);
        String output = file + "," + walkableString;
        return output;
    }

}
