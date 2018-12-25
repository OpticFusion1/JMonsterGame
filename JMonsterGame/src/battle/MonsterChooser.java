package battle;

import java.util.Set;

import monster.Monster;

public interface MonsterChooser {
	
	
	public Monster chooseAttackMonster(Set<Monster> monsters);
		

	public Monster chooseDefenseMonster(Set<Monster> monsters);
	

}
