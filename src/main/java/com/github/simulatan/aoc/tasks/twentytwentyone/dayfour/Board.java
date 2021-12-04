package com.github.simulatan.aoc.tasks.twentytwentyone.dayfour;

public class Board {

	private final int[][] board;
	private final boolean[][] used;

	public Board(int[][] board) {
		this.board = board;
		used = new boolean[board.length][board[0].length];
	}

	public void place(int x, int y, int value) {
		board[x][y] = value;
	}

	public void numberUsed(int number) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == number) {
					markUsed(i, j);
				}
			}
		}
	}

	public void markUsed(int x, int y) {
		used[x][y] = true;
		lastPlaced = board[x][y];
	}

	public boolean isFinished() {
		outer: for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (!used[i][j]) {
					continue outer;
				}
			}
			return true;
		}
		outer: for (int i = 0; i < board[0].length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (!used[j][i]) {
					continue outer;
				}
			}
			return true;
		}
		for (int i = 0; i < board.length; i++) {
			if (!used[i][i]) {
				return false;
			}
		}
		return true;
	}

	private int lastPlaced = -1;

	public int getUnusedNumbers() {
		int result = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (!used[i][j]) {
					result += board[i][j];
				}
			}
		}
		return result * lastPlaced;
	}

	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (used[i][j]) {
					result += "[" + board[i][j] + "] ";
				} else {
					result += board[i][j] + " ";
				}
			}
			result += "\n";
		}
		return result;
	}
}