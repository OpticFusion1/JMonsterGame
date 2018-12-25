package battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import monster.Monster;

/**
 * This class allows human players to choose a monster.
 *
 */
public class HumanMonsterChooser implements MonsterChooser{
	private Scanner reader; 
	
	public HumanMonsterChooser(Scanner reader) {
		this.reader = reader;
	}
	
	@Override
	public Monster chooseAttackMonster(Set<Monster> monsters) {
		System.out.println("please choose an atk monster:");
		return chooseMonster(monsters);
		
		
	}

	@Override
	public Monster chooseDefenseMonster(Set<Monster> monsters) {
		System.out.println("please choose a def monster:");
		return chooseMonster(monsters);
	}	
	
	
	
	/**
	 * This is a helper method to allow the player to choose a monster.
	 * 
	 * @param monsters the set of monsters the player has
	 * 
	 */
	private Monster chooseMonster(Set<Monster> monsters) {
		
		List<Monster> monsterList = new ArrayList<Monster>(monsters);
		List<Integer> validNumbers = new ArrayList<Integer>();
		
		for (Monster m: monsterList) {
			if(m.getHitPoints()>0) {
				System.out.print(monsterList.indexOf(m));
				System.out.println(" " + m);
				
				validNumbers.add(monsterList.indexOf(m));
		
			}
		}
		//monsters have a unique number, user inputs the number corresponding to the monster he wishes to choose
		while (true){
			//check if input is valid
			try {
				int userChoice = reader.nextInt();
				if (validNumbers.contains(userChoice)) {
					return monsterList.get(userChoice);
				}
				System.out.println("invalid choice");
				}
			catch (Exception e) {
				System.out.println(e);
				reader.next();
				
			}
		
		}
	}
	
}
