class Solution {
    private final int[] row = new int[9];
    private final int[] col = new int[9];
    private final int[] box = new int[9];

    private final int[][] empties = new int[81][2]; // (r, c)
    private int nEmpty = 0;
    private boolean solved = false;

    public void solveSudoku(char[][] board) {
        // init masks + collect empties
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.') {
                    empties[nEmpty][0] = r;
                    empties[nEmpty][1] = c;
                    nEmpty++;
                } else {
                    int d = ch - '1';
                    int b = (r / 3) * 3 + (c / 3);
                    int bit = 1 << d;
                    row[r] |= bit;
                    col[c] |= bit;
                    box[b] |= bit;
                }
            }
        }
        dfs(board, 0);
    }

    private void dfs(char[][] board, int idx) {
        if (idx == nEmpty) { solved = true; return; }

        // Choose next cell with MRV
        int best = idx, minChoices = 10;
        for (int i = idx; i < nEmpty; i++) {
            int r = empties[i][0], c = empties[i][1];
            int used = row[r] | col[c] | box[(r / 3) * 3 + (c / 3)];
            int choices = 9 - Integer.bitCount(used & 0x1FF);
            if (choices < minChoices) {
                minChoices = choices;
                best = i;
                if (choices == 1) break;
            }
        }
        swap(empties, idx, best);

        int r = empties[idx][0], c = empties[idx][1];
        int b = (r / 3) * 3 + (c / 3);

        int used = row[r] | col[c] | box[b];
        int avail = (~used) & 0x1FF; // bits 0..8 for digits 1..9

        for (int d = 0; d < 9 && !solved; d++) {
            int bit = 1 << d;
            if ((avail & bit) != 0) {
                place(r, c, b, d, board);
                dfs(board, idx + 1);
                if (!solved) remove(r, c, b, d, board);
            }
        }
    }

    private void place(int r, int c, int b, int d, char[][] board) {
        int bit = 1 << d;
        row[r] |= bit; col[c] |= bit; box[b] |= bit;
        board[r][c] = (char) ('1' + d);
    }

    private void remove(int r, int c, int b, int d, char[][] board) {
        int bit = 1 << d;
        row[r] &= ~bit; col[c] &= ~bit; box[b] &= ~bit;
        board[r][c] = '.';
    }

    private void swap(int[][] a, int i, int j) {
        if (i == j) return;
        int tr = a[i][0], tc = a[i][1];
        a[i][0] = a[j][0]; a[i][1] = a[j][1];
        a[j][0] = tr;      a[j][1] = tc;
    }
}
