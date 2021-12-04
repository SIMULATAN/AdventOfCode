package com.github.simulatan.aoc.tasks.twentytwentyone.dayfour;

import com.github.simulatan.aoc.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day4 extends Task {

	public Day4() {
		super(4);
	}

	@Override
	protected Object solvePartOne(List<String> input) {
		return solve(input, 1);
	}

	@Override
	protected Object solvePartTwo(List<String> input) {
		return solve(input, 2);
	}

	private int solve(List<String> input, int task) {
		List<Board> boards = new ArrayList<>();
		List<List<String>> lines = new ArrayList<>();
		List<String> currentBoard = new ArrayList<>();
		for (int i = 2; i < input.size(); i++) {
			if (input.get(i).isEmpty()) {
				lines.add(currentBoard);
				currentBoard = new ArrayList<>();
			} else {
				currentBoard.add(input.get(i));
			}
		}
		lines.add(currentBoard);
		for (List<String> line : lines) {
			Board board = new Board(new int[line.size()][(int) Arrays.stream(line.get(0).split("\\s+")).filter(s -> !s.isEmpty()).count()]);
			for (int i = 0; i < line.size(); i++) {
				int j = 0;
				for (String num : line.get(i).split("\\s+")) {
					if (num.isEmpty()) continue;
					board.place(i, j++, Integer.parseInt(num));
				}
			}
			boards.add(board);
		}
		// parse input sequence
		List<Integer> sequence = Arrays.stream(input.get(0).split(",")).map(Integer::parseInt).toList();
		// run sequence
		for (int chosenNumber : sequence) {
			for (Board board : new ArrayList<>(boards)) {
				board.numberUsed(chosenNumber);
				if (board.isFinished()) {
					if (task == 1) return board.getUnusedNumbers();
					if (boards.size() == 1) {
						return board.getUnusedNumbers();
					} else {
						boards.remove(board);
					}
				}
			}
		}
		return -1;
	}
}