import java.util.*;

class Solution {
    public void solve(int col, List<String> board, List<List<String>> ans, 
                      int n, boolean[] leftRow, boolean[] upperDiagonal, boolean[] lowerDiagonal) {
        if (col == n) {
            ans.add(new ArrayList<>(board));
            return;
        }

        for (int row = 0; row < n; row++) {
            // Check if position is safe using hashing
            if (!leftRow[row] && !lowerDiagonal[row + col] && !upperDiagonal[n - 1 + col - row]) {
                // Place queen
                char[] arr = board.get(row).toCharArray();
                arr[col] = 'Q';
                board.set(row, new String(arr));

                // Mark as occupied
                leftRow[row] = true;
                lowerDiagonal[row + col] = true;
                upperDiagonal[n - 1 + col - row] = true;

                // Recursive call
                solve(col + 1, board, ans, n, leftRow, upperDiagonal, lowerDiagonal);

                // Backtrack: remove queen
                arr[col] = '.';
                board.set(row, new String(arr));
                leftRow[row] = false;
                lowerDiagonal[row + col] = false;
                upperDiagonal[n - 1 + col - row] = false;
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        List<String> board = new ArrayList<>();
        char[] row = new char[n];
        Arrays.fill(row, '.');

        for (int i = 0; i < n; i++) board.add(new String(row));

        // Create hash arrays
        boolean[] leftRow = new boolean[n];
        boolean[] lowerDiagonal = new boolean[2 * n - 1];
        boolean[] upperDiagonal = new boolean[2 * n - 1];

        solve(0, board, ans, n, leftRow, upperDiagonal, lowerDiagonal);
        return ans;
    }
}
