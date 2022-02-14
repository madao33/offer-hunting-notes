package chap14.unsynch;

public class UnsynchBankTest {
    public static final int MACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;
    public static final double MAX_AMOUNT = 1000;
    public static final int DELAY = 20;

    public static void main(String[] args)
    {
        Bank bank = new Bank(MACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < MACCOUNTS; i++) {
            int fromAccount = i;
            Runnable r = () -> {
                try
                {
                    while (true)
                    {
                        int toAccount = (int) (bank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                }
                catch (InterruptedException e)
                {

                }
            };
            Thread t = new Thread(r);
            t.start();
        }
    }
}
