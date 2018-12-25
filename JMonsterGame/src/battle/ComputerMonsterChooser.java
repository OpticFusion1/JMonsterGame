package battle;

import java.util.Set;
import monster.Monster;

/**
 * This class implements MonsterChooser interface to choose appropriate monsters for the computer.
 *
 */
public class ComputerMonsterChooser implements MonsterChooser{
	
	public Monster chooseDefenseMonster(Set<Monster> monsters) {
		// defense monster chosen based on highest hit points
		// monsters are sorted in descending order of hit points
		return monsters.stream()
				.sorted()
				.findFirst().orElse(null);
		
	}
	
	/**
	 * Attack monster chosen based on highest attack points
	 */
	public Monster chooseAttackMonster(Set<Monster> monsters) {
		Monster result = null;
		for (Monster m : monsters) {
			if (result == null || m.getAttackPoints() > result.getAttackPoints()) {
				if (m.getHitPoints() > 0) {
					result = m;
				}
			}
		}
		return result;
		
		
	}
	
}
