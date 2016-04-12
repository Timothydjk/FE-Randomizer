/*
 * FE-Randomizer App 
 *
 * Author: Tim Kierzkowski and Matt Bernie Bernstein
 * 
 * Version 0.0
 * 
 * 4-11-2016
 * 
 * The Game data storage.
 */
package model;

import java.util.LinkedList;


/**
 * The Class Game.
 */
public class Game {
	
	/** The name. For use in File IO*/
	String name;
	
	/** The chapters in the game. */
	Chapter[] chaps = new Chapter[0];
	
	/** The characters. */
	Unit[] chars = new Unit[0];
	
	/** The currently available chapters. (essentially a bitstring)*/
	int[] availableChapters = new int[0];
	
	/** The currently available units.(essentially a bitstring) */
	int[] availableUnits = new int[0];
	
	
	/**
	 * Instantiates a new game.
	 *
	 * @param name the name
	 */
	public Game(String name){
		this.name = name;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the chapterss.
	 *
	 * @return the chaps
	 */
	public Chapter[] getChaps() {
		return chaps;
	}
	
	/**
	 * Sets the chapterss.
	 *
	 * @param chaps the new chaps
	 */
	public void setChaps(Chapter[] chaps) {
		this.chaps = chaps;
		this.availableChapters = new int[this.chaps.length];
	}
	
	/**
	 * Gets the characters.
	 *
	 * @return the chars
	 */
	public Unit[] getChars() {
		return chars;
	}
	
	/**
	 * Sets the characterss.
	 *
	 * @param chars the new chars
	 */
	public void setChars(Unit[] chars) {
		this.chars = chars;
		this.availableUnits = new int[this.chars.length];
	}
	
	/**
	 * Gets the available units.
	 *
	 * @return the available units
	 */
	public LinkedList<Unit> getAvailableUnits(){
		LinkedList<Unit> availUnits = new LinkedList<Unit>();
		for (int i=0; i<this.availableUnits.length; i++){
			if (this.availableUnits[i]==1){
				availUnits.add(this.chars[i]);
			}
		}
		return availUnits;
	}
	
	/**
	 * Gets the available units.
	 *
	 * @param avail the avail
	 * @return the available units
	 */
	public LinkedList<Unit> getAvailableUnits(int[] avail){
		LinkedList<Unit> availUnits = new LinkedList<Unit>();
		for (int i=0; i<avail.length; i++){
			if (avail[i]==1){
				availUnits.add(this.chars[i]);
			}
		}
		return availUnits;
	}
	
	/**
	 * Gets the available chapters.
	 *
	 * @return the available chapters
	 */
	public LinkedList<Chapter> getAvailableChapters(){
		LinkedList<Chapter> availUnits = new LinkedList<Chapter>();
		for (int i=0; i<this.availableChapters.length; i++){
			if (this.availableChapters[i]==1){
				availUnits.add(this.chaps[i]);
			}
		}
		return availUnits;
	}
	
	/**
	 * Update available chapters.
	 *
	 * @param change the change
	 * @return true, if successful
	 */
	public boolean updateAvailChaps(int[] change){
		if (change.length != this.availableChapters.length) return false;
		for (int i=0;i<change.length;i++){
			this.availableChapters[i] += change[i];
		}
		return true;
	}
	
	/**
	 * Adds new characters.
	 *
	 * @param newChars the new chars
	 * @return true, if successful
	 */
	public boolean addAvailChars(int[] newChars){
		for (int i=0; i<newChars.length;i++){
			this.availableUnits[newChars[i]] = 1;
		}
		return true;
	}
	
	/**
	 * Kills a character (sets it to unavailable
	 *
	 * @param charInd the char ind
	 * @return true, if successful
	 */
	public boolean killChar(int charInd){
		this.availableUnits[charInd] = 0;
		return true;
	}
	
	/**
	 * Essentially the print statement for the game. Mostly for testing purposes
	 *
	 * @return the string
	 */
	public String unitStatus(){
		String stat = "";
		for (int i=0; i<this.chars.length;i++){
			if (this.availableUnits[i]==1){
				stat += this.chars[i].toString()+"\n";
			}
		}
		return stat;
	}
	
}
