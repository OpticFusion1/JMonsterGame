package battle;

import monster.Monster;
import monster.Type;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Represents a Trainer, who has a set of Monsters and can battle other
 * Trainers.
 */
public class Trainer {

	private MonsterChooser chooser;
	private String name;
	/** The set of Monsters on this Trainer's team */
	private Set<Monster> monsters;

	/**
	 * Creates a new trainer.
	 * 
	 * @param name The name of the Trainer
	 */
	public Trainer(String name) {
		this.name = name;
		this.monsters = new HashSet<>();
	}

	/**
	 * Adds a Monster to this Trainer's set.
	 * 
	 * @param m The Monster to add
	 */
	public void addMonster(Monster m) {
		this.monsters.add(m);
	}

	/**
	 * @return The set of Monsters
	 */
	public Set<Monster> getMonsters() {
		return monsters;
	}

	/**
	 * @return This Trainer's name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return All of this Trainer's Monsters, categorized by type
	 */
	public Map<Type, Set<Monster>> getMonstersByType() {
		Map<Type, Set<Monster>> result = new HashMap<>();

		for (Monster m : monsters) {
			Set<Monster> typeMonsters = result.get(m.getType());
			if (typeMonsters == null) {
				typeMonsters = new HashSet<>();
				result.put(m.getType(), typeMonsters);
			}
			typeMonsters.add(m);
		}
		return result;
	}

	public void setMonsterChooser(MonsterChooser monsterChooser) {
		this.chooser = monsterChooser;
	}

	/**
	 * Checks whether this Trainer can still continue in a battle.
	 * 
	 * @return True if there is at least one monster with positive HP, and false if
	 *         not
	 */
	public boolean canFight() {
		return monsters.stream().filter(s -> s.getHitPoints() > 0).findAny().isPresent();
	}

	/**
	 * Chooses a monster to attack in battle. This implementation chooses the
	 * non-knocked-out Monster with the maximum Attack Points.
	 * 
	 * @return The selected Monster, or null if no such Monster can be found
	 */
	public Monster chooseAttackMonster() {
		return this.chooser.chooseAttackMonster(this.monsters);
	}

	/**
	 * Chooses a monster to defend in battle. This implementation chooses the
	 * non-knocked-out Monster with the maximum Hit Points.
	 * 
	 * @return The selected Monster, or null if no such Monster can be found
	 */
	public Monster chooseDefenseMonster() {
		return this.chooser.chooseDefenseMonster(this.monsters);
	}

	/**
	 * Loads a Trainer from the given file and returns the result.
	 * 
	 * @param filename The file to load from
	 * @return A Trainer based on the information in the file
	 * @throws IOException If the file cannot be accessed
	 */
	public static Trainer loadFromFile(String filename) throws IOException {
		// Load the whole file into a List<String> in memory
		Path p = Paths.get(filename);
		List<String> lines = Files.readAllLines(p);

		// First line is name
		String name = lines.remove(0);
		Trainer trainer = new Trainer(name);

		// Process the rest of the lines into Monster objects
		for (String line : lines) {
			// Split the line
			String[] fields = line.split("\\^");
			// Use the fields to create a new Monster and add it
			Monster monster = createMonster(fields[0], fields[1], Integer.parseInt(fields[2]),
					Integer.parseInt(fields[3]));
			if (monster != null) {
				trainer.addMonster(monster);
			}
		}

		// Return the newly created Trainer
		return trainer;
	}

	/**
	 * Saves the current Trainer to the given file.
	 * 
	 * @param filename The file to save to
	 * @throws IOException If there is an error accessing the file.
	 */
	public void saveToFile(String filename) throws IOException {
		// Open the file
		PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get(filename)));

		writer.println(this.name);

		// Write the monsters one line at a time
		for (Monster monster : monsters) {
			String[] properties = new String[4];
			properties[0] = monster.getName();
			properties[1] = monster.getType().name();
			properties[2] = String.valueOf(monster.getHitPoints());
			properties[3] = String.valueOf(monster.getAttackPoints());
			writer.println(String.join("^", properties));
		}

		// We are done!
		writer.close();
	}

	/** Helper method: creates a new Monster object based on the parameters */
	private static Monster createMonster(String name, String type, int hitPoints, int attackPoints) {
		return new Monster(name, Type.valueOf(type), hitPoints, attackPoints);

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((monsters == null) ? 0 : monsters.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Trainer other = (Trainer) obj;
		if (monsters == null) {
			if (other.monsters != null)
				return false;
		} else if (!monsters.equals(other.monsters))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
