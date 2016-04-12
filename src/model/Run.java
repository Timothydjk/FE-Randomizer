/*
 * FE-Randomizer App 
 *
 * Author: Tim Kierzkowski and Matt Bernie Bernstein
 * 
 * Version 0.0
 * 
 * 4-11-2016
 * 
 * A run of the game. think like, this is my current run, for context. game is more like the cartridge?
 */
package model;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

/**
 * The Class Run.
 */
public class Run {
	
	/** The current chapter. */
	int curChapter=0;
	
	/** The game you are playing (currently just awakening. */
	Game game;
	
	/** The Constant GAME_AWAKENING. */
	public final static int GAME_AWAKENING = 12;
	
	/** strats for random team generation */
	/** The Constant STRAT_UNIFORMRANDOM. */
	public final static int STRAT_UNIFORMRANDOM = 1;
	
	/** The Constant STRAT_STRONGER. */
	public final static int STRAT_STRONGER = 3;
	
	/** The Constant STRAT_WEAKER. */
	public final static int STRAT_WEAKER = 2;
	
	/**
	 * Instantiates a new run.
	 *
	 * @param type the type of game you are playing
	 */
	public Run(int type){
		switch(type){
		case GAME_AWAKENING:
			this.game = Awakening.initialize();
			break;
		}
	}
	
	/**
	 * Save run.
	 * still does nothing
	 *
	 * @return true, if successful
	 */
	//TODO: FILE I/O
	public boolean saveRun(){
		return true;
	}
	
	/**
	 * Load run.
	 * still does nothing
	 *
	 * @return true, if successful
	 */
	public boolean loadRun(){
		return true;
	}
	
	/**
	 * Start chapter. Generates a uniform random team.
	 *
	 * @param chap the chapter to start.
	 * @return true, if successful
	 */
	//TODO: Start/end Chapter
	public boolean startChapter(int chap){
		return startChapter(chap,STRAT_UNIFORMRANDOM);
	}
	
	/**
	 * Start chapter. Generates and prints a random team based on strat. Also adds any new characters to the game
	 *
	 * @param chap the chapter to start
	 * @param strat the strat for random team generation
	 * @return true, if successful
	 */
	public boolean startChapter(int chap, int strat){
		this.curChapter = chap;
		Unit[] team = getRandomTeam(strat);
		for (int i=0;i<team.length;i++){
			System.out.println(team[i].toString());
		}
		return this.game.addAvailChars(this.game.getChaps()[chap].charGained);
	}
	
	/**
	 * End chapter, saves game, updates available chapters.
	 *
	 * @return true, if successful
	 */
	public boolean endChapter(){
		int[] aChapChanges = new int[this.game.getChaps().length];
		aChapChanges[curChapter] = -1;
		for (int i=0;i<this.game.getChaps()[curChapter].getPostReq().length;i++){
			aChapChanges[this.game.getChaps()[curChapter].getPostReq()[i]] = 1;
		}
		this.game.updateAvailChaps(aChapChanges);
		return saveRun();
	}
	
	/**
	 * Level character.
	 *
	 * @param unit the character
	 * @return new level
	 */
	public int lvlChar(int unit){
		return this.game.chars[unit].lvlUp();
	}
	
	/**
	 * Promote. yeah.
	 *
	 * @param unit the character to promote
	 * @param nClass the new class
	 * @return the int
	 */
	public int promote(int unit, String nClass){
		return this.game.chars[unit].promote(nClass);
	}
	
	/**
	 * Kill char.
	 *
	 * @param charIndex the char index
	 * @return true, if successful
	 */
	public boolean killChar(int charIndex){
		return this.game.killChar(charIndex);
	}
	
	/**
	 * Run status. Essentially the toString(), but not formatted well.
	 *
	 * @return the string
	 */
	public String runStatus(){
		String stat = "Current Chapter: " + this.game.getChaps()[curChapter].getName() + "\n Character status: " +this.game.unitStatus();
		return stat;
	}
	
	/**
	 * Gets the available chapters. in a LINKED LIST!!
	 *
	 * @return the available chapters
	 */
	public LinkedList<Chapter> getAvailableChapters(){
		return this.game.getAvailableChapters();
	}
	
	/**
	 * Gets the random team. Aight, so this is where things get fun.
	 *
	 * @param Strat the strat
	 * @return the random team
	 */
	public Unit[] getRandomTeam(int Strat){
		//first we figure out how many units we can have and make a 'team' of that many slots
		Unit[] team = new Unit[this.game.getChaps()[curChapter].getNumUnits()];
		// we then clone the available units list because arrays.
		int[] availUnits = this.game.availableUnits.clone();
		//we add any required characters and remove them from the available list.
		for (int i=0;i<this.game.getChaps()[curChapter].getReqChar().length;i++){
			team[i]= this.game.chars[this.game.getChaps()[curChapter].getReqChar()[i]];
			availUnits[this.game.getChaps()[curChapter].getReqChar()[i]] =0;
		}
		//fill the rest of the list
		for (int i=this.game.getChaps()[curChapter].getReqChar().length;i<team.length;i++){
			//randomly grab characters from probability list and update probability list
			Unit adding = probGrab(availUnits,Strat);
			//set the availability to 0
			team[i] = adding;
			availUnits[adding.getIndex()] = 0;
		}
		return team;
	}
	
	/**
	 * Probabilistically grabs a unit. you'll see.
	 * 
	 * Strat 1 is uniform, strat 2 is weaker? strat 3 is stronger?
	 *
	 * @param availUnits the avail units
	 * @param Strat the strat
	 * @return the unit
	 */
	private Unit probGrab(int[] availUnits, int Strat){
		//weighted list represents the probability weight of the unit. imagine you are pulling names from a hat, its ho
		// many times you put that name in a hat. more times, more likely to pull.
		int[] weightedList = new int[0];
		int counter = 0;
		switch(Strat){
		case STRAT_UNIFORMRANDOM:
			counter = 0;
			//first figure out how many units are still available to chose from
			for (int i=0;i<availUnits.length;i++){
				if (availUnits[i]==1) counter++;
			}
			weightedList = new int[counter];
			int j=0;
			//add each ont to a list once. equal probability.
			for (int i=0;i<availUnits.length;i++){
				if (availUnits[i]==1) {
					weightedList[j]= i;
					j++;
				}
			}
			break;
		case STRAT_WEAKER:
			counter = 0;
			for (int i =0;i<availUnits.length;i++){
				if (availUnits[i]==1) counter++;
			}
			//again, figure out how many available characters, and we are going to add one name once, one name twice, etc. (hence trianglesum)
			weightedList = new int[(counter*(counter+1))/2];
			LinkedList<Unit> sortedUnits = this.game.getAvailableUnits(availUnits);
			//yay inline comparators. sortinging linked list by strength.
			sortedUnits.sort(new Comparator<Unit>(){

				@Override
				public int compare(Unit unitA, Unit unitB) {
					
					return unitB.getEfLvl()-unitA.getEfLvl();
				}});
			//j indexes into weightedlist. we then add it i times, increasing i each time. 
			j=0;
			for (int i=0;i<sortedUnits.size();i++){
				for(int k=0;k<i+1;k++){
					weightedList[j] = sortedUnits.get(i).getIndex();
					j++;
				}
			}
			break;
		case STRAT_STRONGER:
			//essentially same as above, but the comparator is the negative of before
			counter = 0;
			for (int i =0;i<availUnits.length;i++){
				if (availUnits[i]==1) counter++;
			}
			weightedList = new int[(counter*(counter+1))/2];
			sortedUnits = this.game.getAvailableUnits(availUnits);
			sortedUnits.sort(new Comparator<Unit>(){

				@Override
				public int compare(Unit unitA, Unit unitB) {
					
					return unitA.getEfLvl()-unitB.getEfLvl();
				}});
			j=0;
			for (int i=0;i<sortedUnits.size();i++){
				for(int k=0;k<i+1;k++){
					weightedList[j] = sortedUnits.get(i).getIndex();
					j++;
				}
			}
			break;
		}
		Random r = new Random();
		return this.game.getChars()[weightedList[r.nextInt(weightedList.length)]];
		
	}
	
}
