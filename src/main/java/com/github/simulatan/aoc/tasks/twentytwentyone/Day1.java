package com.github.simulatan.aoc.tasks.twentytwentyone;

import com.github.simulatan.aoc.Task;

import java.util.List;
import java.util.stream.Collectors;

public class Day1 extends Task {

	public Day1() {
		super(1);
	}

	@Override
	protected Object solvePartOne(List<String> input) {
		// count the number of times a depth measurement increases from the previous measurement. (There is no measurement before the first measurement.)
		int count = 0;
		List<Integer> measurements = input.stream().map(Integer::parseInt).collect(Collectors.toList());
		// check if the current measurement is higher than the previous one using streams
		for (int i = 1; i < measurements.size(); i++) {
			if (measurements.get(i) > measurements.get(i - 1)) {
				count++;
			}
		}
		return count;
	}

	@Override
	protected Object solvePartTwo(List<String> input) {
		// count the number of times a depth measurement increases from the previous 3 measurements. (There is no measurement before the first measurement.)
		List<Integer> measurements = input.stream().map(Integer::parseInt).collect(Collectors.toList());
		int count = 0;
		int previous = Integer.MAX_VALUE;
		for (int i = 2; i < measurements.size(); i++) {
			int current = measurements.get(i);
			if (current + measurements.get(i - 1) + measurements.get(i - 2) > previous) {
				count++;
			}
			previous = measurements.get(i - 2) + measurements.get(i - 1) + current;
		}
		return count;
	}
}