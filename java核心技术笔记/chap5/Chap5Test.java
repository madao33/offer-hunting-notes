package chap5;

import java.util.Random;

public class Chap5Test {
    public static void main(String[] args)
    {
        // String s = "Ok";
        // StringBuilder sb = new StringBuilder(s);
        // System.out.println(s.hashCode() + " " + sb.hashCode());
        // String t = new String("Ok");
        // StringBuilder tb = new StringBuilder(t);
        // System.out.println(t.hashCode() + " " + tb.hashCode());

        Integer a = 100;
        Integer b = 100;
        System.out.println(a == b);
        System.out.println(a.equals(b));

        Integer n = 1;
        Double x = 2.0;
        System.out.println(true ? n : x); // Prints 1.0

        System.out.println(max(3.1, 40.4, -5));

        Random generator = new Random();
        Class cl = generator.getClass();
        System.out.println(cl.getName());

        // String name = "java.util.Random";
        // Class cl2 = Class.forName(name);
        // System.out.println(cl2);

        try{
            String name = "java.util.Random";
            Class cl2 = Class.forName(name);
            System.out.println(cl2);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static double max(double... values)
    {
        double largest = Double.NEGATIVE_INFINITY;
        for(double v : values)
            if(v > largest)
                largest = v;
        return largest;
    }
}
