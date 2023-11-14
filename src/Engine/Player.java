/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Engine;

/**
 * @filename Player.java
 * @author Shane
 * @date 30 Jan 2013
 */

// -----------------------------------------------------------------------------
// Imports & Definition
// -----------------------------------------------------------------------------

public class Player extends CombatCharacter {
// -----------------------------------------------------------------------------
// Fields
// -----------------------------------------------------------------------------

    private int Strength;
    private int level;
    private int exp;
    private int expToNext;


    private int MP;
    private int MaxMP;



// -----------------------------------------------------------------------------
// Getters $ Setters
// -----------------------------------------------------------------------------

    public int getExp() {
        return this.exp;
    }

    public int getExpToNext() {
        return this.expToNext;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getMP() {
        return MP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public int getMaxMP() {
        return MaxMP;
    }

    public void setMaxMP(int MaxMP) {
        this.MaxMP = MaxMP;
    }

// -----------------------------------------------------------------------------
// Constructors
// -----------------------------------------------------------------------------
    Player(int maxHP, int _curHP, int _str, int _level, int _exp){
        super(maxHP, _curHP);
        this.Strength = _str;
        this.level = _level;
        this.exp = _exp;
    }

    public Player(int _maxHP, int _curHP, int _str, int _level, int _exp, int _MaxMP, int _MP) {
        super(_maxHP, _curHP);
        this.Strength = _str;
        this.level = _level;
        this.exp = _exp;
        this.MP = _MP;
        this.MaxMP = _MaxMP;
    }



// -----------------------------------------------------------------------------
// Inner Classes
// -----------------------------------------------------------------------------

// TODO: Inner Classes

// -----------------------------------------------------------------------------
// Methods
// -----------------------------------------------------------------------------
    @Override
    public int getDamage() {
        int dam = 0;
        double strModifier = (double)this.getStrength() * 1.3;
        double range = (double)this.getStrength() * 0.09 * 2;
        double random = (Math.random() * range);
        dam = (int)(strModifier + (random - range));
        return dam;
    }

    private int getStrength() {
        return this.Strength;
    }

    public int determineExpToNext() {
        this.expToNext = 50 * (int)Math.pow((double)2, (double)this.level);

//        System.out.println("The level is: "        + Integer.toString(this.level));
//        System.out.println("The multiplier is: "   + Integer.toString((int)Math.pow((double)2, (double)this.level)));
//        System.out.println("The final amount is: " + Integer.toString(50 * (int)Math.pow((double)2, (double)this.level)));
//
        return this.expToNext;
    }

    public boolean isLevelUp() {
        if (this.exp >= this.determineExpToNext()) return true;
        return false;
    }

    public void levelUp() {
        if (this.isLevelUp()) {
            this.setLevel(this.getLevel() + 1);
            this.setMaximumHP(this.getMaxHP() + (int)(20 * (2^this.level) * 0.06));
            this.setHP(this.getMaxHP());
            this.Strength += 3;
        }


    }

    private int getLevel() {
        return this.level;
    }

    private void setLevel(int i) {
        this.level = i;
    }



// -----------------------------------------------------------------------------
// Main
// -----------------------------------------------------------------------------


}
