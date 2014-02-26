public class npc implements Comparable<npc> {
	public int npcNum;
	public String name;
	public int hp; 
	public int damage;
	public int AC;
	public boolean dead = (hp >= 0);
		
	public npc(int n, String npcName, int level, int rand){
		// 		System.out.print("What is this person's name? ");
		
		npcNum = n;
		name = npcName;// "NPC " + Integer.toString(n);
		hp = 13 + (level * 8) + rand;
		damage = 6 + level + rand;
		AC = 11 + level + rand;

	}

	public void damage(int hit){
		hp = hp - hit;
	}
	
	public int attack(int plus, int minus){
		return (damage + plus - minus);
	}
	
		
		public void display(){
			System.out.print(name); 
			System.out.print("  HP = " + hp);
			System.out.print("  AC = " + AC);

			System.out.println();
		}
		
		public int compareTo(npc other){
			if (npcNum > other.npcNum) return 1;
			else if (name == other.name) return 0;
			else return -1;
		}
	}
