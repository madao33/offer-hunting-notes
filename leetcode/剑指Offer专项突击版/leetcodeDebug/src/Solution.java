class Solution {

    int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    public void solve(char[][] board) {
        int n = board.length, m = board[0].length;

        // 第一行和最后一行
        for (int j = 0; j < m; j++) {
            dfs(board, 0, j);
            dfs(board, n-1, j);
        }

        // 第一列和最后一列
        for (int i = 1; i < n-1; i++) {
            dfs(board, i, 0);
            dfs(board, i, m-1);
        }

        // 不与边界连通的区域修改为'X'
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j]=='A')
                    board[i][j] = 'O';
                else if (board[i][j]=='O')
                    board[i][j] = 'X';
            }
        }
    }

    public void dfs(char[][] board, int curi, int curj) {
        int n = board.length, m = board[0].length;
        // 越界或者不是'O'
        if (curi < 0 || curi >= n || curj < 0 || curj >= m || board[curi][curj]!='O')
            return ;
        board[curi][curj] = 'A';
        for (int[] dir : dirs) {
            dfs(board, curi + dir[0], curj + dir[1]);
        }
    }

}