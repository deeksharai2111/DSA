import java.util.*;
import java.util.concurrent.*;

class Solution {
    // Main entry point
    public List<List<String>> solveNQueens(int n) {
        // Thread-safe list to store final solutions
        List<List<String>> results = Collections.synchronizedList(new ArrayList<>());

        // Thread pool (parallelism = number of available cores)
        int threads = Math.min(n, Runtime.getRuntime().availableProcessors());
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Future<?>> tasks = new ArrayList<>();

        // Initialize empty board
        char[][] baseBoard = new char[n][n];
        for (char[] row : baseBoard) Arrays.fill(row, '.');

        // Submit one parallel task per possible queen in column 0
        for (int row = 0; row < n; row++) {
            final int startRow = row;
            char[][] boardCopy = deepCopy(baseBoard);

            boardCopy[startRow][0] = 'Q';
            int pos = 1 << startRow;

            // Each branch runs in parallel for first column choice
            tasks.add(executor.submit(() ->
                solve(1, n, pos, (pos) << 1, (pos) >> 1, boardCopy, results)
            ));
        }

        // Wait for all threads to finish
        for (Future<?> t : tasks) {
            try { t.get(); } catch (Exception ignored) {}
        }

        executor.shutdown();
        return results;
    }

    // Recursive backtracking using bitmask representation
    private void solve(int col, int n, int rows, int ld, int rd,
                       char[][] board, List<List<String>> ans) {
        if (col == n) {
            List<String> solution = new ArrayList<>(n);
            for (char[] r : board) solution.add(String.valueOf(r));
            ans.add(solution);
            return;
        }

        int safe = ((1 << n) - 1) & (~(rows | ld | rd));

        while (safe != 0) {
            int pos = safe & -safe; // rightmost available bit
            safe -= pos;

            int row = Integer.numberOfTrailingZeros(pos);
            board[row][col] = 'Q';

            solve(col + 1, n,
                  rows | pos,
                  (ld | pos) << 1,
                  (rd | pos) >> 1,
                  board, ans);

            board[row][col] = '.';
        }
    }

    // ✅ Missing helper method
    private static char[][] deepCopy(char[][] original) {
        char[][] copy = new char[original.length][];
        for (int i = 0; i < original.length; i++)
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        return copy;
    }
} // ✅ You forgot this closing brace
