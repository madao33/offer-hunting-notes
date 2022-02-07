package chap9.map;

import java.util.*;

public class MapTest {
    public static void main(String[] args) {
        Map<String, String> staff = new HashMap<>();
        staff.put("144-25-5464", new String("AmyLee"));
        staff.put("567-24-2546",new String("HarryHacker"));
        staff.put("157-62-7935",new String("CaryCooper"));
        staff.put("456-62-5527",new String("FrancescaCruz"));
        //print all entries
        System.out.println(staff);
        //remove an entry
        staff.remove("567-24-2546");
        //replace an entry
        staff.put("456-62-5527",new String("FrancescaHiller"));
        //look up avalue
        System.out.println(staff.get("157-62-7935"));
        //iterate through all entries
        staff.forEach((k,v)->System.out.println("key=" + k + ", value=" + v));
    }
}

