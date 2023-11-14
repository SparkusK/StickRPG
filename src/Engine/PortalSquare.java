/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Engine;

import java.awt.Point;

/**
 * @filename PortalSquare.java
 * @author Shane
 * @date 14 Jan 2013
 */

// -----------------------------------------------------------------------------
// Imports & Definition
// -----------------------------------------------------------------------------

public class PortalSquare {
// -----------------------------------------------------------------------------
// Fields
// -----------------------------------------------------------------------------

    private Point portalFromPoint;
    private String portalToLevel;
    private Point portalToSquare;




// -----------------------------------------------------------------------------
// Getters $ Setters
// -----------------------------------------------------------------------------

    public String getPortalToLevel() {
        return portalToLevel;
    }

    public void setPortalToLevel(String portalToLevel) {
        this.portalToLevel = portalToLevel;
    }

    public void setPortalToSquare(Point portalToSquare) {
        this.portalToSquare = portalToSquare;
    }

    public void setPortalFromPoint(Point portalFromPoint) {
        this.portalFromPoint = portalFromPoint;
    }

    public Point getPortalToSquare() {
        return portalToSquare;
    }

    public Point getPortalFromPoint() {
        return portalFromPoint;
    }



// -----------------------------------------------------------------------------
// Constructors
// -----------------------------------------------------------------------------

    public PortalSquare(Point portalFromPoint, String portalToLevel, Point portalToSquare) {
        this.portalFromPoint = portalFromPoint;
        this.portalToLevel = portalToLevel;
        this.portalToSquare = portalToSquare;
    }

    public PortalSquare(Point portalFromPoint, Point portalToSquare) {
        this.portalFromPoint = portalFromPoint;
        this.portalToSquare = portalToSquare;
    }

// -----------------------------------------------------------------------------
// Inner Classes
// -----------------------------------------------------------------------------

// TODO: Inner Classes

// -----------------------------------------------------------------------------
// Methods
// -----------------------------------------------------------------------------

// TODO: Methods

// -----------------------------------------------------------------------------
// Main
// -----------------------------------------------------------------------------


}
