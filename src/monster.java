public class monster implements Comparable<monster> {
	public int monNum;
	public String name;
	public double hp; 
	public double damage;
	public double[] diff = {.5,1,1.5};
	public int AC;
	public boolean dead = (hp >= 0);
		
	public monster(int n, int level, int difficulty, int Arand, int Brand, int Crand){
		monNum = n;
		name = "Mon: " + Integer.toString(n);
		hp = ((13 + (level * 8) + Arand)*diff[difficulty]);
		damage = (6 + level + Brand)*diff[difficulty];
		AC = 11 + level + Crand;

	}

	public void damage(int hit){
		hp = hp - hit;
		// made redundant: unknown if I'll heep it.
		// if (0 > hp) dead = true;
	}
	
	public double attack(int plus){
		return (damage + plus);
	}
	
		
		public void display(){
			System.out.print(name); 
			System.out.print("  HP = " + hp);
			System.out.print("  AC = " + AC);

			System.out.println();
		}
		
		public int compareTo(monster other){
			if (monNum > other.monNum) return 1;
			else if (name == other.name) return 0;
			else return -1;
		}
	}
