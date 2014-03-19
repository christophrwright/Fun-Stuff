/** 
 * Christopher Wright
 * Mar 18, 2014
 *
 * Monster Generalizations and attackClass modifications
 * taken from the Business Card DM Guide: http://blogofholding.com/?p=512
 *
 *
 * To Implement:
 * 	likes = array
 * 	languages = array // import list of names
 * 	current affiliations, religion
 * 	distToPlayers: given an [array of player locations], 
 * 	generate values for each= 0,1,2,3,4 
 * 	for (i=0; i <= numPlayers; i++){} 
 * 	items.java
 * 
 * Functions To Add :
 * 	give(item, amount): player.items(item) -= amount, monster.bribe(item, amount)
 * 	bribe(item, amount): if (player.give(gold, amount) && (monster.canBribe == (1,2)){ 
			if (monster.canBribe == 1){ bribed = 1, gold += player.give(gold, amount), disposition_To_Player += 1 }
			if (monster.canBribe == 2){ bribed = 1, gold += player.give(gold, amount), disposition_To_Player -= 1 , monster.attack(player)}
			}
		else{disposition_To_Player -= 1 , monster.attack;}

 * // certain monsters will surrender if they are almost dead 
 * surrender():  if will_run && ( 0 <= hp <= math.ceil(hp * .05))

 * // gives a 15 % chance of demanding surrender if the player is weaker than a monster, or if a player is almost dead
 * demandSurrender(): if (((player.hpMax < monster.hp) || (player.hp < math.ceil(player.hp * .1))) && (random.randInt(100) > 85)), System.out.println(monster.name + " demands your surrender!"); 

 *-======---Program Starts---======-*/

package Monster;

import java.io.BufferedReader;
import java.util.*;

abstract class Monster{// implements Comparable<Monster> {
   // private static BufferedReader read;
    private static Random random;

    public static String name = "Name";
   	public static int level = 1;
    public static int hp = 23; // hit points
	public static int ac = 14; // Armor class
	public static int attackClass = random.nextInt(7); // [artillery, brute, Controller, lurker, skirmisher, soldier, solo]
    
	public static int initiative = 0;
	public static int[] stats = {11, 11, 11, 11, 11, 11}; //  STR, DEX, CON, INT, WIS, CHA 
	public static int[] saves = {12, 12, 12}; //  fortitude, reflex, will = 12;
	public static int baseAttack = 5;
	public static int damage = 8;

	public static int size = random.nextInt(4); // 0 = small, 1 = mid, 2 = large, 3 = huge
	public static int speed = random.nextInt(3); // .5, 1, 2 // slow, normal, fast
	public static int rank = random.nextInt(3); // (minion = .5, normal = 1, elite = 2)
	
	public static Boolean will_run = false;
	public static int dist_to_player = random.nextInt(5); // 0 = Near, 1 = mid, 2 = far, 3 = out of range 
    
			// On a scale of 0 = hates, 3 = neutral, 5 = loves the player
	// Will influence ability to hurt player later
	public static int dispostion_player = 0; // random.randInt(5);
	// The monster's alignment is currently irrelevant 
	// public static String alignment = "Neutral";

	// Currently shows what the monster was doing when the player showed up
	// Will be changed to denote how monster is currently
	// interacting with the player (trading with or training the player)
	// 0 - talking, attack penalty round one; 
	// 1 - combat ready, no penalty
	public static int status = random.nextInt(2) ;


	//FUNCTIONS
	
	// initializes Monsters
	public Monster(String n){	
		name = n; 
		switch (attackClass){
			case 0:	// Artillery
					hp = 21 + (6 * (level-1));
					ac = 12 + (level-1);

			case 1: // Brute
					hp = 26 + (6 * (level-1));
					ac = 12 + (level-1);
					damage = damage + (5 * (8 + (level-1)))/4;

			case 2: // Controller
					hp = 24 + (8 * (level-1));

			case 3: // Lurker
					hp = 21 + (6 * (level-1));

			case 4: // Skirmisher
					hp = 24 + (8 * (level-1));

			case 5: // Soldier
					hp = 24 + (8 * (level-1));
					ac = 12 + (level-1);

			case 6: // Solo
					hp = 4*(24 + (8 * (level-1)));
			break;
			}

		switch (rank){
			case 0: // Minion
					hp = hp / 2;

			case 1: // Normal
					hp = hp;
			case 2: // Elite
					hp = hp * 2;
					ac = ac + 3;
					damage = (damage *3)/2; 
			break;
			}
		}	

	// Things Monsters Can Do
/*
	public int attack(monster Player){ // } if dist_to_player = 0,1, use weapon1, else use weapon2
		int attack = baseAtk + random.nextInt(21); 
		if (attack > Player.ac){
			int damage = item.weapon.damage;
		return damage;
		}
*/
	public void runAway(){ // : if will_run && (15% <= hp <= 30%), every round add <Speed> to dist_to_player
		dist_to_player = dist_to_player + 1;
		}	

	public Boolean makeSave(int save, int requiredSave){ // : if player attacks with something that reqs a save, roll to make save; if save < playerAtk, don't make save
		int roll = random.nextInt(21);
		if ((roll + saves[save]) >= requiredSave){
			return true;
			}
		return false;
		}



	// Included in case the user wants to manually 
	// set the monster's stats for whatever reason

	public void levels(int L){ 
		level = L; 
	}
	
	public void setSaves(int fort, int ref, int will){ 
		saves[0] = ref;
		saves[1] = fort;
		saves[2] = will; 
	}
	
	public void setInitiative(int init){ 
		initiative = init; 
	}
	
	/*
	public void setData(String s, String k, String al){ 
		size = s;
		monsterKind = k;
		alignment = al;
	}
	*/
	
	public void setStats(int str, int dex, int con, int smarts, int wis, int cha){
		stats[0] = str; 
		stats[1] = dex;
		stats[2] = con;
		stats[3] = smarts;
		stats[4] = wis;
		stats[5] = cha;
	}

	// These are refer to functionality that will be added later
	/*

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
	*/

}
	
	//end Functions
	
	





