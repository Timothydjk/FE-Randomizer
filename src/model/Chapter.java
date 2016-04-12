/*
 * FE-Randomizer App 
 *
 * Author: Tim Kierzkowski and Matt Bernie Bernstein
 * 
 * Version 0.0
 * 
 * 4-11-2016
 * 
 * Data storage of a chapter
 */
package model;

/**
 * The Class Chapter. Represents a single chapter
 * 
 */
public class Chapter {
	
	/** The 'post requirements'. i.e. the things that require this chapter before you can start*/
	int[] postReq;
	
	/** The characters gained in this chapter. */
	int[] charGained;
	
	/** The characters required to be used in this chapter. */
	int[] reqChar = new int[]{};
	
	/** The number of units you can use in this chapter. */
	int numUnits;
	
	/** The index for this in the game. */
	int index;
	
	/** The name of the chapter. */
	String name;
	
	/**
	 * Instantiates a new chapter. Required Units is optional.
	 *
	 * @param postReq the post req
	 * @param charGained the char gained
	 * @param numUnits the num units
	 * @param name the name
	 * @param index the index
	 */
	public Chapter(int[] postReq,int[] charGained,int numUnits, String name, int index){
		this.postReq = postReq;
		this.charGained = charGained;
		this.numUnits = numUnits;
		this.name = name;
		this.index = index;
	}
	
	/**
	 * Instantiates a new chapter.
	 *
	 * @param postReq the post req
	 * @param charGained the char gained
	 * @param numUnits the num units
	 * @param name the name
	 * @param index the index
	 * @param reqChar the req char
	 */
	public Chapter(int[] postReq,int[] charGained,int numUnits, String name, int index, int[] reqChar){
		this.postReq = postReq;
		this.charGained = charGained;
		this.numUnits = numUnits;
		this.reqChar = reqChar;
		this.name = name;
		this.index = index;
	}
	
	/**
	 * Gets the post req.
	 *
	 * @return the post req
	 */
	public int[] getPostReq() {
		return postReq;
	}
	
	/**
	 * Sets the post req.
	 *
	 * @param postReq the new post req
	 */
	public void setPostReq(int[] postReq) {
		this.postReq = postReq;
	}
	
	/**
	 * Gets the char gained.
	 *
	 * @return the char gained
	 */
	public int[] getCharGained() {
		return charGained;
	}
	
	/**
	 * Sets the char gained.
	 *
	 * @param charGained the new char gained
	 */
	public void setCharGained(int[] charGained) {
		this.charGained = charGained;
	}
	
	/**
	 * Gets the num units.
	 *
	 * @return the num units
	 */
	public int getNumUnits() {
		return numUnits;
	}
	
	/**
	 * Sets the num units.
	 *
	 * @param numUnits the new num units
	 */
	public void setNumUnits(int numUnits) {
		this.numUnits = numUnits;
	}
	
	/**
	 * Gets the req char.
	 *
	 * @return the req char
	 */
	public int[] getReqChar() {
		return reqChar;
	}
	
	/**
	 * Sets the req char.
	 *
	 * @param reqChar the new req char
	 */
	public void setReqChar(int[] reqChar) {
		this.reqChar = reqChar;
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
