package battle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.rules.TemporaryFolder;

import monster.Monster;
import monster.Type;

public class BattleMain {
	public static void main(String[] args) throws Exception {
		
		/*
		Trainer t1 = Trainer.loadFromFile("trainer1.txt");
		t1.setMonsterChooser(new HumanMonsterChooser(new Scanner(System.in)));
		Trainer t2 = Trainer.loadFromFile("trainer2.txt");
		t2.setMonsterChooser(new ComputerMonsterChooser());
		*/
		
		// New code -- creating the trainers directly
//		Trainer t1 = new Trainer("Ash");
//		t1.addMonster(new Monster("Pikachu", Type.ELECTRIC, 35, 55));
//		t1.addMonster(new Monster("Rowlet", Type.GRASS, 68, 55));
//		t1.addMonster(new Monster("Litten", Type.FIRE, 45, 65));
//		t1.setMonsterChooser(new HumanMonsterChooser(new Scanner(System.in)));
//
//		Trainer t2 = new Trainer("Kiawe");
//		t2.addMonster(new Monster("Turtonator", Type.FIRE, 60, 78));
//		t2.addMonster(new Monster("Alolan Marowak", Type.FIRE, 60, 80));
//		t2.setMonsterChooser(new ComputerMonsterChooser());
//
//		Battle b = new Battle (t1, t2);
//		Trainer winner = b.doBattle();
//		System.out.println("Winner is: " + winner.getName());
		TemporaryFolder tempFolder = new TemporaryFolder();

		Monster pikachu, rowlet, litten, turtonator, alolanMarowak, gyarados, megaGyarados;
		Trainer ash, kiawe;
		MonsterChooser cmc;
		
	
		ash = new Trainer("Ash");
		pikachu = new Monster("Pikachu", Type.ELECTRIC, 35, 55);
		rowlet = new Monster("Rowlet", Type.GRASS, 68, 55);
		litten = new Monster("Litten", Type.FIRE, 45, 65);
		ash.addMonster(pikachu);
		ash.addMonster(rowlet);
		ash.addMonster(litten);
		
		turtonator = new Monster("Turtonator", Type.FIRE, 60, 78);
		alolanMarowak = new Monster("Alolan Marowak", Type.FIRE, 60, 80);
		kiawe = new Trainer("Kiawe");
		kiawe.addMonster(turtonator);
		kiawe.addMonster(alolanMarowak);
		
		gyarados = new Monster("Gyarados", Type.WATER, 95, 155);
		megaGyarados = new Monster("Mega Gyarados", Type.WATER, 115, 175);
		
		cmc = new ComputerMonsterChooser();
		
		Path path = getOutputFile(tempFolder);
		System.out.println(ash.getName());
		System.out.println(ash.getMonsters());
		ash.saveToFile(path.toString());
		Trainer trainer2 = Trainer.loadFromFile(path.toString());
		System.out.println(trainer2.getName());
		System.out.println(trainer2.getMonsters());
		
		
	}
	
	
	public static Path getOutputFile(TemporaryFolder tempFolder) throws IOException {
		tempFolder.create();
		Path path = tempFolder.newFile().toPath();
		Files.deleteIfExists(path);
		return path;
	}
}
