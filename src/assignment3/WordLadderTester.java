/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * <Joseph Bae>
 * <jb65632>
 * <16235>
 * <Raiyan Chowdhury>
 * <rac4444>
 * <16235>
 * Slip days used: <0>
 * Git URL: https://github.com/josephbae96/ee422c-jb65632-.git
 * Spring 2017
 */

package assignment3;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
/**
 * This is the sample test cases for students
 * @author lisahua
 *
 */
public class WordLadderTester {
	private static Set<String> dict;
	private static ByteArrayOutputStream outContent;

	@BeforeClass
	public static void setUp() {
		Main.initialize();
		dict = Main.makeDictionary();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	}

	private boolean verifyLadder(ArrayList<String> ladder) {
		String prev = null;
		if (ladder == null)
			return true;
		for (String word : ladder) {
			if (!dict.contains(word.toUpperCase()) && !dict.contains(word.toLowerCase())) {
				return false;
			}
			if (prev != null && !differByOne(prev, word))
				return false;
			prev = word;
		}
		return true;
	}

	private static boolean differByOne(String s1, String s2) {
		if (s1.length() != s2.length())
			return false;

		int diff = 0;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i) && diff++ > 1) {
				return false;
			}
		}

		return true;
	}

	/** Has Word Ladder **/
	@Test(timeout = 30000)
	public void testBFS1() {
		ArrayList<String> res = Main.getWordLadderBFS("hello", "cells");

		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(verifyLadder(res));
		assertFalse(res == null || res.size() == 0 || res.size() == 2);
		assertTrue(res.size() < 6);
	}

	@Test(timeout = 30000)
	public void testDFS1() {
		ArrayList<String> res = Main.getWordLadderDFS("hello", "cells");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(verifyLadder(res));
		assertFalse(res == null || res.size() == 0 || res.size() == 2);

	}

	/** No Word Ladder **/
	@Test(timeout = 30000)
	public void testBFS2() {
		ArrayList<String> res = Main.getWordLadderBFS("aldol", "drawl");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(res == null || res.size() == 0 || res.size() == 2);

	}

	@Test(timeout = 30000)
	public void testDFS2() {
		ArrayList<String> res = Main.getWordLadderDFS("aldol", "drawl");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(res == null || res.size() == 0 || res.size() == 2);
	}
	
	@Test(timeout = 30000)
	public void BFS_no_ladder() {
		ArrayList<String> res = Main.getWordLadderBFS("duvet", "aloof");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(res.size() == 2 && res.get(0).equals("duvet") && res.get(1).equals("aloof"));
	}
	
	@Test
	public void BFS_reverse_ladder() {
		ArrayList<String> res1 = Main.getWordLadderBFS("smart", "money");
		ArrayList<String> res2 = Main.getWordLadderBFS("money", "smart");
		assertTrue(res1.size() == res2.size());
	}
	
	@Test(timeout = 30000)
	public void BFS_valid_ladder() {
		ArrayList<String> res = Main.getWordLadderBFS("green", "money");
		for (int i = 0; i < res.size()-1; i++) {
			assertTrue(Main.differ_by_One(res.get(i), res.get(i+1)));
		}
	}
	
	@Test(timeout = 10000)
	public void BFS_time_test() {
		ArrayList<String> res1 = Main.getWordLadderBFS("lycra", "godly");
		ArrayList<String> res2 = Main.getWordLadderBFS("lycra", "godly");
		ArrayList<String> res3 = Main.getWordLadderBFS("lycra", "godly");
		ArrayList<String> res4 = Main.getWordLadderBFS("lycra", "godly");
		ArrayList<String> res5 = Main.getWordLadderBFS("lycra", "godly");
	}
	
	public void BFS_same_word() {
		ArrayList<String> res = Main.getWordLadderBFS("money", "money");
		assertTrue(res.size() == 2 && res.get(0) == res.get(1));
	}
	
	@Test(timeout = 30000)
	public void DFS_no_ladder() {
		ArrayList<String> res = Main.getWordLadderDFS("duvet", "aloof");
		assertTrue(res.size() == 2 && res.get(0) == "duvet" && res.get(1) == "aloof");
	}
	
	@Test
	public void DFS_valid_ladder() {
		ArrayList<String> res = Main.getWordLadderDFS("green", "money");
		for (int i = 0; i < res.size()-1; i++) {
			assertTrue(Main.differ_by_One(res.get(i), res.get(i+1)));
		}
	}
	
	@Test
	public void DFS_same_word() {
		ArrayList<String> res = Main.getWordLadderDFS("money", "money");
		assertTrue(res.size() == 2 && res.get(0) == res.get(1));
	}
	
	@Test (timeout = 10000)
	public void DFS_time_test() {
		ArrayList<String> res1 = Main.getWordLadderDFS("lycra", "godly");
		ArrayList<String> res2 = Main.getWordLadderDFS("lycra", "godly");
		ArrayList<String> res3 = Main.getWordLadderDFS("lycra", "godly");
		ArrayList<String> res4 = Main.getWordLadderDFS("lycra", "godly");
		ArrayList<String> res5 = Main.getWordLadderDFS("lycra", "godly");
	}

	@Test(timeout = 30000)
	public void testPrintLadder() {
		ArrayList<String> res = Main.getWordLadderBFS("twixt", "hakus");
		outContent.reset();
		Main.printLadder(res);
		String str = outContent.toString().replace("\n", "").replace(".", "").trim();
		assertEquals("no word ladder can be found between twixt and hakus", str);
	}
}
