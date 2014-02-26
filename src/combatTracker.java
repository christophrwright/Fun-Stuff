/* 
Christopher Wright
Nov 22, 2013

------------------------------------
Ω›››  D&D 4e Combat Generator  ‹‹‹‹Ω
------------------------------------

--------------------
\\\\   TO DO   //// 
--------------------
** NEXT **
 * Learn how to create a GUI with the Java Swing Library
 
** THEN **
 * Create a GUI
 * Allow DM to track character levels, etc
 * Learn regEx
 * Add a flavor text generator to combat ("The orc swings their axe mightily!")
 * Adjust monsters / encounter to party size

-======---Program Starts---======-*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class combatTracker {
	private static monster[] monst;			// implemented monster class
    private static BufferedReader read;
    private static Random randomGenerator;
    private static int partySize;
    private static int numberOfMonsters;	
    private static int level;				// Player level, used to compute Hit Points and Armor Class
    private static int difficulty;			// difficulty of encounter
    public static int numberDead;			// Number of monsters who are currently dead
    public static boolean allDead;	
    public static String currentPlayer;
    
    public static List<monster> allMonsters;
    
    // Treasure and experience rewards by level, taken from: http://paizo.com/PRD/gamemastering.html
    public static int[] exp = {200,400,600,800,1200,1600,2400,3200,4800,6400,9600,12800,19200,25600,38400,51200,76800,102400,153600,204800,307200,409600,614400,819200,1228800,1638400};
    public static int[][] treasure = {{80,120,200},{170,260,400},{350,550,400},{550,800,1200},{750,1150,1700},{1000,1550,2300},{1350,2000,3000},{1750,2600,3900},{2200,3350,5000},{2850,4250,6400},{3650,5450,8200},{4650,7000,10500},{6000,9000,13500},{7750,11600,17500},{10000,15000,22000},{13000,19500,29000},{16500,25000,38000},{22000,32000,48000},{28000,32000,48000},{28000,41000,62000},{35000,53000,79000},{44000,67000,100000}};
    

	public int n = randomGenerator.nextInt();


	
    public static void main(String[] args) {
    	
        randomGenerator = new Random();
        read = new BufferedReader(new InputStreamReader(System.in));
    	    
        try {

        	
	    	System.out.print("Size of Party: ");
	        partySize = Integer.parseInt(read.readLine());
	    
	        System.out.print("Level of Party: ");
	        level = Integer.parseInt(read.readLine());
	        
	    	System.out.print("Monsters in Encounter: ");
	    	numberOfMonsters = Integer.parseInt(read.readLine());
	    	
	    	System.out.print("Difficulty of Encounter \n(Easy = 0, Medium = 1, Hard = 2): ");
	        difficulty = Integer.parseInt(read.readLine());
	        
	        
	        monst = new monster[numberOfMonsters];
	        allMonsters = new ArrayList<monster>();
	
	               
	        System.out.println();
	        System.out.println(":: Encounter Monsters ::");
	        
	
	        // Actual Monster Generator
	        // Only generates Hit Points, Armor Class, and the damage they do every round
	        // Future Builds will also generate stats, names, how many dead it will take for them to run, etc
	        for (int i=0; i<numberOfMonsters; i++){ 
	        	monster M= new monster((i), level, difficulty, (randomGenerator.nextInt(8) - 4), (randomGenerator.nextInt(8) - 4), (randomGenerator.nextInt(8) - 4));
	        	M.display();
	        	allMonsters.add(M);
	        	monst[i] = M;
	        	}
	        
	        // The turn by turn combat tracker
	        // Future Builds will attempt to make it easier to write up session write ups, 
	        // by making it more text based, adding flavor text, and saving it to a file.
	    	
	        int currentRound = 1; 
	
	    	System.out.println("\n::: ! FIGHT ! :::");
	        while (allDead == false){
	        	
	        	System.out.println();
	        	System.out.println(":: ROUND " + currentRound + " :: \n");
	        	
	        	System.out.println(": Player Attack Phase :");
	        	
	        	// what is mon and hurt? hurt = damage, mon = number?
	        	int mon = 0;
	        	int hurt = 0;
	        	
	        	// Tracks damage players inflict on monsters
	        	for (int p = 0; p < partySize; p++){
	        		// if (allDead = true) break;
	        		System.out.print("Player Name: ");
		        	currentPlayer = read.readLine();
	        		
		        	System.out.print("Monster taking damage: ");
		        	mon = Integer.parseInt(read.readLine());
		
		        	System.out.print("How much damage are they taking? ");
		      		hurt = Integer.parseInt(read.readLine());
		        		
		      		allMonsters.get(mon).damage(hurt);
		      		
		      		System.out.println(currentPlayer + " attacks, dealing Monster " + mon + " "+ hurt + " points of damage!");
		      		System.out.println();
		      	       	
		        	if ((allMonsters.get(mon).hp) <= 0){ 
		        		System.out.println("Monster " + mon + " dies, felled by " + currentPlayer + "'s hand!");
		        		numberDead += 1;
	
		            	}
		        	}
		        if (numberDead == numberOfMonsters){ 
		    		System.out.println("\n::: ! The Battle Is Over ! :::");
		    		System.out.println("\tEXP: " + exp[level] + ", "+ (exp[level]/partySize) + " per player");
		    		System.out.println("\tTREASURE: " + treasure[level][difficulty] + " GP, "+ ((treasure[level][difficulty])/partySize) + " GP per player");
		    		allDead = true;
		    		break;
		    	}
		    	
		        else{
		        	System.out.println("\n: Enemy Attack Phase :");
		        		for (int k = 0; k < numberOfMonsters; k++){ 
		        				int atk = allMonsters.get(k).attack(randomGenerator.nextInt(8)-4);
		        				System.out.println("Monster "+ k + " Attacks for "+ atk +" Damage!");
		        			}
		        	} 
		        	
		        for (int q=0; q<numberOfMonsters; q++) allMonsters.get(q).display();
		        currentRound++;
	        }
        
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
}   