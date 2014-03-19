Abstract class attack{
		
		public static List<String> randomEffect;
		
		public static List<attack> attackList;

		public static int range = 1;
		public static int damage = 8;

		public static boolean charged = true;
		public static boolean encounterPower = false;
		public static boolean dailyPower = false;

		public static String name;
		public static String action;
		public static String save = "Fortitude";
		public static String typeofDamage = "Physical";
		public static String effect = "None";
		public static String flavorText = "The attack connects!";
		
		public static int x = randomGenerator.nextInt(randomEffect.size());

		public static void attack(String n){ name = n; }
		
		public static void attack(String n, int dist, String type, String s){
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
