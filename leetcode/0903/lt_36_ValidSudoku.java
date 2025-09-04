class Solution {
    public boolean isValidSudoku(char[][] board) {
        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] boxes = new int[9];

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.') continue;

                int bit = 1 << (ch - '1');
                int b = (r / 3) * 3 + (c / 3);

                if ((rows[r] & bit) != 0) return false;
                if ((cols[c] & bit) != 0) return false;
                if ((boxes[b] & bit) != 0) return false;

                rows[r] |= bit;
                cols[c] |= bit;
                boxes[b] |= bit;
            }
        }
        return true;
    }
}
