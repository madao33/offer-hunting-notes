import java.util.List;

public class mainTest {
    public static void main(String[] args){
        char[][] board = new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        String word = "ABCB";
        boolean res = new Solution().exist(board, word);
        System.out.println(res);

    }
}
