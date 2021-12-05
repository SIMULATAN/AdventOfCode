package com.github.simulatan.aoc.tasks.twentytwentyone;

import com.github.simulatan.aoc.Task;

import java.util.Arrays;
import java.util.List;

public class Day5 extends Task {

	public Day5() {
		super(5);
	}

	@Override
	protected Object solvePartOne(List<String> input) {
		return solve(input, true);
	}

	@Override
	protected Object solvePartTwo(List<String> input) {
		return solve(input, false);
	}

	private Object solve(List<String> input, boolean isTaskOne) {
		int boundsX = 0, boundsY = 0;
		for (String line : input) {
			String[] parts = line.split(" -> ");
			for (String part : parts) {
				boundsX = Math.max(boundsX, Integer.parseInt(part.split(",")[0]));
				boundsY = Math.max(boundsY, Integer.parseInt(part.split(",")[1]));
			}
		}
		int[][] grid = new int[boundsX+1][boundsY+1];
		for (String line : input) {
			String[] coords = line.replace(" -> ", ",").split(",");
			int startX = Integer.parseInt(coords[0]);
			int endX = Integer.parseInt(coords[2]);

			int startY = Integer.parseInt(coords[1]);
			int endY = Integer.parseInt(coords[3]);
			int changeX = (int) Math.signum(endX - startX);
			int changeY = (int) Math.signum(endY - startY);

			if (isTaskOne && changeX * changeY != 0) continue;

			int x = startX;
			int y = startY;
			while (x != endX || y != endY) {
				grid[x][y]++;
				x += changeX;
				y += changeY;
			}
			grid[x][y]++;
		}
		return Arrays.stream(grid).flatMapToInt(Arrays::stream).filter(i -> i > 1).count();
	}
}