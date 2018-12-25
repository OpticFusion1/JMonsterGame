package monster;

/**
 * Represents a Monster for a battling game.
 */
public class Monster implements Comparable<Monster> {

	// Fields
	protected Type type;
	protected String name;
	protected int hitPoints;
	protected int attackPoints;
	protected boolean fireDodge = false;

	/** Creates a new Monster with the given properties */
	public Monster(String name, Type type, int hitPoints, int attackPoints) {
		this.name = name;
		this.type = type;
		this.hitPoints = hitPoints;
		this.attackPoints = attackPoints;

	}

	// Getters and setters
	public int getHitPoints() {
		return hitPoints;
	}

	public int getAttackPoints() {
		return attackPoints;
	}

	public Type getType() {
		return this.type;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * Attacks another Monster
	 * 
	 * @param otherMonster The other Monster to attack
	 * @throws MonsterException if either Monster is knocked out, or if otherMonster
	 *                          == this
	 */
	public void attack(Monster otherMonster) throws MonsterException {
		// A monster cannot attack itself
		if (otherMonster == this) {
			throw new MonsterException("A monster cannot attack itself");
		}

		// A monster cannot attack or be attacked if it is knocked out
		if (this.hitPoints <= 0 || otherMonster.getHitPoints() <= 0) {
			throw new MonsterException("Knocked out monsters cannot attack or be attacked");
		}

		if (otherMonster.dodge()) {
			this.removeHitPoints(10);
		} else {
			// Check if the other monster is weak against our type
			boolean otherIsWeak = otherMonster.getType().isWeakAgainst(type);
			int pointsToRemove = (otherIsWeak) ? this.attackPoints + 20 : this.attackPoints;
			otherMonster.removeHitPoints(pointsToRemove);
		}
	}

	/**
	 * Allows a Monster to dodge in battle, based on certain, arbitrarily defined conditions.
	 * 
	 * @return True if the Monster will dodge when next attacked, and false if not
	 */
	public boolean dodge() {

		if (this.type == Type.WATER) {
			return (hitPoints >= 50);
		} else if (this.type == Type.FIRE) {
			fireDodge = !fireDodge;
			return fireDodge;
		} else {
			return false;
		}

	}

	/**
	 * Removes the indicated number of hit points from this Monster. 
	 * HP cannot go below 0.
	 * 
	 * @param points The points to remove
	 */
	private void removeHitPoints(int points) {
		this.hitPoints -= points;
		if (hitPoints <= 0) {
			// Monster is knocked out
			hitPoints = 0;
		}
	}

	@Override
	public String toString() {
		return "(name: " + this.name + " type:" + type + ", hp:" + hitPoints + ", ap:" + attackPoints + ", wk:"
				+ this.type.getWeaknesses() + ")";
	}

	@Override
	public int compareTo(Monster otherMonster) {
		int result = Integer.compare(otherMonster.hitPoints, this.hitPoints);
		if (result == 0) {
			result = Integer.compare(otherMonster.attackPoints, this.attackPoints);
		}
		if (result == 0) {
			result = this.type.compareTo(otherMonster.type);
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Monster other = (Monster) obj;
		if (attackPoints != other.attackPoints)
			return false;
		if (hitPoints != other.hitPoints)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + attackPoints;
		result = prime * result + hitPoints;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

}
