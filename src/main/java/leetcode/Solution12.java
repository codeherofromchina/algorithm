package leetcode;

import java.util.TreeMap;

class Solution12 {
    public static void main(String[] args) {
        String s = intToRoman(1994);
        System.out.println(s);
    }


    public static String intToRoman(int num) {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(1, "I");
        map.put(4, "IV");
        map.put(5, "V");
        map.put(9, "IX");
        map.put(10, "X");
        map.put(40, "XL");
        map.put(50, "L");
        map.put(90, "XC");
        map.put(100, "C");
        map.put(400, "CD");
        map.put(500, "D");
        map.put(900, "CM");
        map.put(1000, "M");

        StringBuffer retBuffer = new StringBuffer();
        while (num > 0) {
            Integer key = map.floorKey(num);
            retBuffer.append(map.get(key));
            num -= key;
        }


        return retBuffer.toString();
    }
}