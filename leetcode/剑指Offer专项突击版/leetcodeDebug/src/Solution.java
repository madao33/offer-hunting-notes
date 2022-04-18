class Solution {
    int[][] directions = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    public boolean exist(char[][] board, String word) {
        int n = board.length, m = board[0].length;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (check(board, word, visited, i, j, 0))
                    return true;
            }
        }
        return false;
    }

    public boolean check(char[][] board, String word, boolean[][] visited, int i, int j, int k) {
        if (i < 0 || i > board.length || j < 0 || j > board[0].length)
            return false;
        if (board[i][j] != word.charAt(k))
            return false;
        if (word.length()-1 == k)
            return true;
        visited[i][j] = true;
        for (int[] dir : directions) {
            int newi = i + dir[0], newj = j + dir[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length && !visited[newi][newj]) {
                if(check(board, word, visited, newi, newj, k+1)) {
                    visited[i][j] = false;
                    return true;
                }
            }
        }
        visited[i][j] = false;
        return false;
    }
}