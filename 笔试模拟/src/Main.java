import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int cnt = 0;
            for (int i = n; i <= m; i++) {
                if (isWaterNum(i)) {
                    if (cnt != 0)
                        System.out.print(" ");
                    System.out.print(i);
                    cnt++;
                }
            }
            if (cnt == 0)
                System.out.println("no");
            else
                System.out.println();
        }
    }

    public static boolean isWaterNum(int num) {
        String str = Integer.toString(num);
        char[] bits = str.toCharArray();
        int sum = 0;
        for (char bit : bits) {
            int bit_num = bit - '0';
            sum += bit_num * bit_num * bit_num;
        }
        if (sum == num)
            return true;
        return false;
    }
}