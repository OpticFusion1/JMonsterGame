package monster;

import java.util.Arrays;

/**
 * This enumerated type represents the different types that a monster can have.
 *
 */
public enum Type {


	FIRE(new String[]{"WATER"}),
	WATER(new String[]{"ELECTRIC", "GRASS"}), 
	ELECTRIC(new String[]{"GRASS"}), 
	GRASS(new String[]{"FIRE"});
	
	
	
	Type(String[] weaknesses) {
		this.weaknesses = weaknesses;
		
	}
	

	private String[] weaknesses;
			
	
	public boolean isWeakAgainst(Type otherType) {
		
		for (String wk : this.weaknesses) {
			if (otherType == Type.valueOf(wk)) {
				return true;
			}
		}
		
		return false;
	}
	
	public String getWeaknesses() {
		return Arrays.toString(this.weaknesses);
	}
	
		
	
	
	
}
