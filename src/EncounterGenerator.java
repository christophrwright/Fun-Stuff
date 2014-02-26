/*Christopher Wright
Class: 22C:031
Nov 15, 2013
Student Id: 00837155

Simple D&D 4e Encounter Generator
-======---Program Starts---======-*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import Monster.*;
import Monster.Monster.Soldier;


public class EncounterGenerator{
    private static Monster[] dude;
    private static BufferedReader read;
    private static Random randomGenerator;
    private static int size;
    
    public List<String> moves;
    public boolean stillEntering;
    public Soldier puppy;

	public static void main(String[] args){
		read = new BufferedReader(new InputStreamReader(System.in));
	       
        randomGenerator = new Random();

		List<String> moves = new ArrayList<String>();
		
		System.out.print("How Many Attacks Do You Want? ");
        size = Integer.parseInt(read.readLine());
        dude = new Monster[size];
      
        int a = 0;
        while (a < size){
        	System.out.print("Enter The Kickass Names of Your Attacks : ");
        	moves.add();// import regex here);
        	a++;
		}
        
        Soldier puppy = new Soldier("braithwaite");
        puppy.allAttacks = Monster.attack.allMoves(moves);
	}
}
