package com.github.simulatan.aoc.tasks.twentytwentyone;

import com.github.simulatan.aoc.Task;

import java.util.List;

public class Day2 extends Task {

	public Day2() {
		super(2);
	}

	@Override
	protected Object solvePartOne(List<String> input) {
		int horizontal = 0;
		int depth = 0;
		for (String line : input) {
			String[] split = line.split(" ");
			if (split.length != 2) {
				throw new IllegalArgumentException("Invalid input");
			}
			switch (split[0]) {
				case "forward" -> horizontal += Integer.parseInt(split[1]);
				case "up" -> depth -= Integer.parseInt(split[1]);
				case "down" -> depth += Integer.parseInt(split[1]);
				default -> throw new IllegalArgumentException("Invalid input");
			}
		}
		return horizontal * depth;
	}

	@Override
	protected Object solvePartTwo(List<String> input) {
		int aim = 0;
		int horizontal = 0;
		int depth = 0;
		for (String line : input) {
			String[] split = line.split(" ");
			if (split.length != 2) {
				throw new IllegalArgumentException("Invalid input");
			}
			switch (split[0]) {
				case "forward" -> {
					int i = Integer.parseInt(split[1]);
					horizontal += i;
					depth += aim * i;
				}
				case "up" -> aim -= Integer.parseInt(split[1]);
				case "down" -> aim += Integer.parseInt(split[1]);
			}
		}
		return horizontal * depth;
	}
}