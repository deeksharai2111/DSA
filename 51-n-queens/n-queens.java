import java.util.*;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) Arrays.fill(row, '.');

        solve(0, n, 0, 0, 0, board, ans);
        return ans;
    }

    private void solve(int col, int n, int rows, int ld, int rd,
                       char[][] board, List<List<String>> ans) {
        if (col == n) {
            // Convert board to List<String> efficiently
            List<String> temp = new ArrayList<>(n);
            for (char[] r : board) temp.add(new String(r));
            ans.add(temp);
            return;
        }

        // Compute all safe positions in current column
        int safe = ((1 << n) - 1) & (~(rows | ld | rd));

        while (safe != 0) {
            // Pick the rightmost available position
            int pos = safe & -safe;
            safe -= pos;

            // Calculate row from bit index
            int row = Integer.numberOfTrailingZeros(pos);
            board[row][col] = 'Q';

            // Recursive call (shift diagonals)
            solve(col + 1, n, rows | pos, (ld | pos) << 1, (rd | pos) >> 1, board, ans);

            // Backtrack
            board[row][col] = '.';
        }
    }
}
