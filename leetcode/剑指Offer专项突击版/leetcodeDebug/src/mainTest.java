import java.util.List;

public class mainTest {
    public static void main(String[] args){
        char[][] board = new char[][]{{'X','O','X','O','X','O'},{'O','X','O','X','O','X'},{'X','O','X','O','X','O'},{'O','X','O','X','O','X'}};

        new Solution().solve(board);
        System.out.println(board);

    }
}
