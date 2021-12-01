package com.github.simulatan.aoc;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

/**
 * The return type of {@link Task#solvePartOne(List)} and {@link Task#solvePartTwo(List)} is Object to allow numbers to be returned as well saving you a total of 16 characters!
 */
public abstract class Task {

	private final int day;

	protected Task(int day) {
		this.day = day;
	}

	public final void solve() {
		try {
			List<String> input = Files.readAllLines(new File("input/day" + day + ".txt").toPath());
			System.out.println("Day " + this.day + " Task 1: " + solvePartOne(input));
			System.out.println("Day " + this.day + " Task 2: " + solvePartTwo(input));
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

	protected abstract Object solvePartOne(List<String> input);

	/**
	 * Not abstract because not needed for the first task
	 */
	protected Object solvePartTwo(List<String> input) {return null;}
}