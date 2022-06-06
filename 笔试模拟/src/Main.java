import java.util.*;

public class Main {
    public static Map<Character, Integer> map = new HashMap<>(){
        {
            put('0', 0);
            put('1', 1);
            put('2', 2);
            put('3', 3);
            put('4', 4);
            put('5', 5);
            put('6', 6);
            put('7', 7);
            put('8', 8);
            put('9', 9);
            put('A', 10);
            put('B', 11);
            put('C', 12);
            put('D', 13);
            put('E', 14);
            put('F', 15);
        }
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();
        char[] hexs = temp.toCharArray();
        int res = 0;
        for (int i = 2; i < hexs.length; i++) {
            res = res * 16 + map.get(hexs[i]);
        }
        System.out.println(res);
    }
}