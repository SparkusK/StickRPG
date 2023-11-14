package Engine;

/**
 * @filename CombatCharacter.java
 * @author Shane Kelly
 * @date 10 Jan 2013
 */

public class CombatCharacter {
// -----------------------------------------------------------------------------
// Fields
// -----------------------------------------------------------------------------

    private int maximumHP;

    private int currentHP;

    private int damage;

    private int expReward;

// -----------------------------------------------------------------------------
// Getters $ Setters
// -----------------------------------------------------------------------------

    public int getMaxHP() {
        return maximumHP;
    }

    public int getDamage() {
        return damage;
    }

    public int getHP() {
        return currentHP;
    }

    public void setDamage(int Damage) {
        this.damage = Damage;
    }

    public void setHP(int HP) {
        this.currentHP = HP;
    }

    public void setMaximumHP(int MaximumHP) {
        this.maximumHP = MaximumHP;
    }
    
    public int getExpReward() {
        return expReward;
    }

// -----------------------------------------------------------------------------
// Constructors
// -----------------------------------------------------------------------------

    public CombatCharacter(int HP, int Damage, int currentHP, int _exp) {
        this.maximumHP = HP;
        this.currentHP = currentHP;
        this.damage = Damage;
        this.expReward = _exp;
    }

    public CombatCharacter(int HP, int currentHP) {
        this.maximumHP = HP;
        this.currentHP = currentHP;
    }

// -----------------------------------------------------------------------------
// Methods
// -----------------------------------------------------------------------------

    public void Attack(CombatCharacter character) {
        character.setHP(character.getHP() - this.getDamage());
        
    }
}
