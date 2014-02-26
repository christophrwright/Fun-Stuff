/*
 * Contains Generalized Monster Stats by Role
 * from the Business Card DM Guide
 * 
 * Lists to read: 
 * randomEffect 
 * nameList
 * monsterKind 
 * alignment
 * 
 * */ 
package Monster;

import java.io.BufferedReader;
import java.util.*;

abstract class Monster{// implements Comparable<Monster> {
   // private static BufferedReader read;
    private static Random randomGenerator;

    public static String name = "Name";
    public static String size = "Medium";
    public static String monsterKind;
    public static String alignment = "Neutral";
	
	public static List<String> Languages = new ArrayList<String>();
	public static List<String> Skills;
	public static List<attack> attackList;
	
	public static int STR, DEX, CON, INT, WIS, CHA = 11;
	public static int level = 1;
	public static int initiative = 0;
	public static int health = 25;
	public static int armorClass = 14;
	public static int fortitude, reflex, will = 12;
	public static int baseAttack = 5;
	public static int averageDamage = 8;
	public static int speed = 5;
	
	public static boolean Leader, Minion = false;

	//FUNCTIONS
	
	public Monster(String n){	name = n; }
	
	public void canSpeak(List<String> langs){
		Languages.add("Common");
		for (int i = 0; i<langs.size(); i++) Languages.add(langs.get(i));
	}
	
	public void trainedIn(List<String> skill){
		for (int i = 0; i<skill.size(); i++) Skills.add(skill.get(i));
	}
	
	public void nameMonster(String n){ 
		name = n; 
	}
	
	public void levels(int L){ 
		level = L; 
	}
	
	public void saves(int fort, int ref, int w){ 
		reflex = ref;
		fortitude = fort;
		will = w; 
	}
	
	public void motivate(int init){ 
		initiative = init; 
	}
	
	public void setData(String s, String k, String al){ 
		size = s;
		monsterKind = k;
		alignment = al;
	}
	
	public void setStats(int str, int dex, int con, int smarts, int wis, int cha){
	STR = str;
	DEX = dex;
	CON = con;
	INT = smarts;
	WIS = wis;
	CHA = cha;
}
	
	//end Functions
	
	public static class attack{
		
		public List<String> randomEffect;
		
		public static List<attack> attackList;

		public int range = 1;
		public int damage = 8;

		public boolean charged = true;
		public boolean encounterPower = false;
		public boolean dailyPower = false;

		public String name;
		public String action;
		public String save = "Fortitude";
		public String typeofDamage = "Physical";
		public String effect = "None";
		public String flavorText = "The attack connects!";
		
    	int x = randomGenerator.nextInt(randomEffect.size());

    	public attack(String n){ name = n; }
		
		public attack(String n, int dist, String type, String s){
			name = n;
			range = dist;
			damage = 5 + (randomGenerator.nextInt(5));
			typeofDamage = type;
			save = s;
		}
		
		public static List<attack> allMoves(List<String> names){	
			List<attack> attackList = new ArrayList<attack>();
			String[] types = {"Acid", "Bludgeoning", "Butts", "Cold", "Divine", "Electric", "Ex-Sangunated", "Falling", "Fire", "Force", "Gas Attack", "Hadoken", "Infernal", "Laughing", "Lemming", "Pasta", "Physical", "Piercing", "Poison", "Psychic", "Ninja", "Non-Lethal", "Really Weird", "Runny Nose", "Sanity", "Slashing", "Sonic", "Stinky", "Tentacle", "Wandering Emu"};
			String[] saves = {"Fortitude", "Will", "Reflex", "Strength", "Dexterity", "Constitution", "Inteligence", "Wisdom", "Charisma", "Luck", ""};
			for (int i=0; i < names.size();i++){
				attackList.add(new attack(names.get(i), randomGenerator.nextInt(10), types[randomGenerator.nextInt(types.length)], saves[randomGenerator.nextInt(saves.length)]));
			}
			return attackList;
		}
	}


	
	abstract class Controller extends Monster{
		public int health = 24 + (8 * (level-1));
		
	}
	
	public class Lurker extends Monster{
		public int health = 21 + (6 * (level-1));
	}
	
	public class Skirmisher extends Monster{
		public int health = 24 + (8 * (level-1));
	}
	
	public class Elite extends Monster{
		public int health = 2*(24 + (8 * (level-1)));
	}
	
	public class Artillery extends Monster{		
		public int health = 21 + (6 * (level-1));
		public int armorclass = 12 + (level-1);
	}
	
	public class Brute extends Monster{		
		public int health = 26 + (6 * (level-1));
		public int armorclass = 12 + (level-1);
		public double moreDamage = Math.ceil(1.25 * (8 + (level-1)));
		public int averageDamage = (int) moreDamage;
		}
	
	public class Soldier extends Monster{
		public int health = 24 + (8 * (level-1));
		public int armorclass = 12 + (level-1);
	}

	public class Solo extends Monster{
		public int health = 4*(24 + (8 * (level-1)));
	}
}
