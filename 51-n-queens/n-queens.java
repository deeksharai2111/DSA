import java.util.*;

class Solution {
    List<List<String>> ans = new ArrayList<>();

    private void solve(int n, int col, int rows, int ld, int rd, char[][] board) {
        if (col == n) {
            List<String> temp = new ArrayList<>();
            for (char[] r : board) temp.add(new String(r));
            ans.add(temp);
            return;
        }

        // bitmask of all safe positions
        int safe = ((1 << n) - 1) & (~(rows | ld | rd));

        while (safe != 0) {
            // pick the rightmost available position
            int pos = safe & -safe;
            safe -= pos;

            int row = Integer.numberOfTrailingZeros(pos);
            board[row][col] = 'Q';

            solve(n, col + 1,
                  rows | pos,
                  (ld | pos) << 1,
                  (rd | pos) >> 1,
                  board);

            board[row][col] = '.';
        }
    }

    public List<List<String>> solveNQueens(int n) {
        ans.clear();
        char[][] board = new char[n][n];
        for (char[] r : board) Arrays.fill(r, '.');
        solve(n, 0, 0, 0, 0, board);
        return ans;
    }
}
