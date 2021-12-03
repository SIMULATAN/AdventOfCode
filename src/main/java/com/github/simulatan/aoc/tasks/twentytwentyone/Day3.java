package com.github.simulatan.aoc.tasks.twentytwentyone;

import com.github.simulatan.aoc.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day3 extends Task {
	public Day3() {
		super(3);
	}

	@Override
	protected Object solvePartOne(List<String> input) {
		boolean[][] board = new boolean[input.get(0).length()][input.size()];
		for (int i = 0; i < input.size(); i++) {
			for (int j = 0; j < input.get(0).length(); j++) {
				board[j][i] = input.get(i).charAt(j) == '1';
			}
		}
		String gamma = "";
		String epsilon = "";
		for (boolean[] booleans : board) {
			int zeros = 0;
			int ones = 0;
			for (boolean aBoolean : booleans) {
				if (aBoolean) ones++;
				else zeros++;
			}
			gamma += zeros > ones ? '0' : '1';
			epsilon += zeros > ones ? '1' : '0';
		}
		return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2);
	}

	@Override
	protected Object solvePartTwo(List<String> input) {
		List<List<Boolean>> bits1 = new ArrayList<>(input.stream().map(s -> s.chars().mapToObj(c -> (char) c).map(d -> d.equals('1')).collect(Collectors.toList())).toList());
		// the list will get modified which is why i clone it
		List<List<Boolean>> bits2 = new ArrayList<>(bits1);

		// results of the search (in format [ true, false, true, false ] for the number 1010)
		List<Boolean> search1 = doSearch(bits1, true).get(0);
		List<Boolean> search2 = doSearch(bits2, false).get(0);

		// mapping function for boolean -> bit
		Function<Boolean, String> cons = (b -> b ? "1" : "0");
		return Integer.parseInt(search1.stream().map(cons).collect(Collectors.joining()), 2)
			 * Integer.parseInt(search2.stream().map(cons).collect(Collectors.joining()), 2);
	}

	private static List<List<Boolean>> doSearch(List<List<Boolean>> bits, boolean isOxygen) {
		for (int i = 0; i < bits.get(0).size(); i++) {
			int zeros = 0;
			int ones = 0;
			for (List<Boolean> booleans : bits) {
				if (booleans.get(i)) ones++;
				else zeros++;
			}
			boolean toDelete = isOxygen == (zeros > ones);
			List<List<Boolean>> newBits = new ArrayList<>(bits);
			for (int j = bits.size()-1; j >= 0; j--) {
				boolean bit = bits.get(j).get(i);
				if (bit == toDelete) {
					newBits.remove(j);
				}
				if (bits.size() == 1) return bits;
			}
			bits = newBits;
		}
		return bits.size() > 1 ? doSearch(bits, isOxygen) : bits;
	}
}