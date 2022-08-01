package leetcode;

import java.util.*;

class Solution30 {
    public static void main(String[] args) {
        String s = "wordgoodgoodgoodbestword";
        String[] words = {"word", "good", "best", "good"};
        List<Integer> substring = findSubstring(s, words);
        System.out.println(substring);

    }

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        if (s == null || words == null || words.length == 0) {
            return list;
        }
        int len = words[0].length();
        // 辅助数据
        Set<Character> charSet = new HashSet<>();
        int[] tables = new int[26];
        int sum = 0;
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                tables[c - 'a']++;
                sum++;
                charSet.add(c);
            }
        }

        // 左右指针寻找开始子串位置
        int[] useArr = new int[26];
        int useSum = sum;
        System.arraycopy(tables, 0, useArr, 0, 26);
        char[] str = s.toCharArray();
        int n = s.length();
        int l = 0;
        int r = 0;
        boolean dirty = false;
        while (r < n) {
            if (!charSet.contains(str[r])) {
                r++;
                l = r;
                if (dirty) {
                    System.arraycopy(tables, 0, useArr, 0, 26);
                    useSum = sum;
                    dirty = false;
                }
                continue;
            }

            if (useArr[str[r] - 'a'] > 0) {
                useArr[str[r++] - 'a']--;
                dirty = true;
                if (useSum == 1) {
                    if (validate(s, l, r, words, len)) {
                        list.add(l);
                    }
                    useArr[str[l++] - 'a']++;
                } else {
                    useSum--;
                }
                continue;
            }

            while (l < r) {
                useArr[str[l++] - 'a']++;
                useSum++;
                if (str[l - 1] == str[r]) {
                    break;
                }
            }
        }
        return list;
    }

    private static boolean validate(String str, int f, int t, String[] words, int n) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            Integer num = map.getOrDefault(word, 0);
            map.put(word, num + 1);
        }

        for (int i = f; i < t; i += n) {
            String key = str.substring(i, i + n);
            Integer num = map.remove(key);
            if (num == null) {
                return false;
            } else if (num > 1) {
                map.put(key, num - 1);
            }

        }

        return map.isEmpty();
    }

}