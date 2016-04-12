/*
 * FE-Randomizer App 
 *
 * Author: Tim Kierzkowski and Matt Bernie Bernstein
 * 
 * Version 0.0
 * 
 * 4-11-2016
 * 
 *  Data class for a character. called unit cause character is already a thing in java.
 */
package model;

/**
 * The Class Unit.
 */
public class Unit{
	
	/** The name and class. */
	String name, clas;
	
	/** The level, effective level, and index. */
	int lvl, efLvl,index;
	
	/**
	 * Instantiates a new unit.
	 *
	 * @param name the name
	 * @param clas the clas
	 * @param lvl the lvl
	 * @param efLvl the ef lvl
	 * @param index the index
	 */
	public Unit(String name, String clas, int lvl, int efLvl,int index){
		this.name = name;
		this.clas =clas;
		this.lvl = lvl;
		this.efLvl = efLvl;
		this.index = index;
	}
	
	/**
	 * Levels up the character. increments effective level
	 *
	 * @return the new level.
	 */
	protected int lvlUp(){
		this.setLvl(this.getLvl()+1);
		this.setEfLvl(this.getEfLvl()+1);
		return this.lvl;
	}
	
	/**
	 * Promotes a character and resets level to 1. increments effective level
	 *
	 * @param nClass the n class
	 * @return the int
	 */
	protected int promote(String nClass){
		this.setClas(nClass);
		this.setLvl(1);
		this.setEfLvl(this.getEfLvl()+1);
		return this.lvl;
	}
	
	/**
	 * Copy. Because dont touch the constant characters in the awakening file. just dont.
	 *
	 * @return a copy of the unit
	 */
	protected Unit copy(){
		return new Unit(this.name,this.clas,this.lvl,this.efLvl,this.index);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return this.name+ " a level "+this.lvl+" "+ this.clas; 
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
	 * Gets the clas.
	 *
	 * @return the clas
	 */
	public String getClas() {
		return clas;
	}
	
	/**
	 * Sets the clas.
	 *
	 * @param clas the new clas
	 */
	public void setClas(String clas) {
		this.clas = clas;
	}
	
	/**
	 * Gets the lvl.
	 *
	 * @return the lvl
	 */
	public int getLvl() {
		return lvl;
	}
	
	/**
	 * Sets the lvl.
	 *
	 * @param lvl the new lvl
	 */
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	
	/**
	 * Gets the ef lvl.
	 *
	 * @return the ef lvl
	 */
	public int getEfLvl() {
		return efLvl;
	}
	
	/**
	 * Sets the ef lvl.
	 *
	 * @param efLvl the new ef lvl
	 */
	public void setEfLvl(int efLvl) {
		this.efLvl = efLvl;
	}
	
	/**
	 * Gets the index.
	 *
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 * Sets the index.
	 *
	 * @param index the new index
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
	
}
