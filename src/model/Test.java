/*
 * FE-Randomizer App 
 *
 * Author: Tim Kierzkowski and Matt Bernie Bernstein
 * 
 * Version 0.0
 * 
 * 4-11-2016
 * 
 * A testing part to run some simple tests. it does some things.
 */
package model;


// TODO: Auto-generated Javadoc
/**
 * The Class Test.
 */
public class Test {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Run testRun = new Run(Run.GAME_AWAKENING);
		testRun.startChapter(0);
		testRun.endChapter();
		for(int i=0;i<10;i++){
			System.out.println("Now starting Chapter "+testRun.curChapter + "\n Team:");
			testRun.startChapter(testRun.getAvailableChapters().getFirst().getIndex(),3);
			testRun.endChapter();
			System.out.println("\n");
		}

	}
}
