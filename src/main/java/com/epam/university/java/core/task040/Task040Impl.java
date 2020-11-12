package com.epam.university.java.core.task040;

public class Task040Impl implements Task040 {
    @Override
    public int countScore(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException();
        }
        str = str.replace(" ", "");
        char[] chars = str.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (count == 300 || (i == chars.length - 2 && chars[chars.length - 3] == 'X')) {
                return count;
            }
            if (chars[i] == 'X') {
                count += 10;
                if (Character.isDigit(chars[i + 1])) {
                    count += Integer.parseInt(String.valueOf(chars[i + 1]));
                }
                if (Character.isDigit(chars[i + 2])) {
                    count += Integer.parseInt(String.valueOf(chars[i + 2]));
                }
                if (chars[i + 1] == 'X') {
                    count += 10;
                }
                if (chars[i + 2] == 'X') {
                    count += 10;
                }
                if (chars[i + 2] == '/') {
                    count += 10 - Integer.parseInt(String.valueOf(chars[i + 1]));
                }
            } else if (chars[i] == '/') {
                count += 10 - Integer.parseInt(String.valueOf(chars[i - 1]));
                if (Character.isDigit(chars[i + 1])) {
                    count += Integer.parseInt(String.valueOf(chars[i + 1]));
                } else if (chars[i + 1] == 'X') {
                    count += 10;
                }
            } else if (Character.isDigit(chars[i])) {
                count += Integer.parseInt(String.valueOf(chars[i]));
            }
        }
        return count;
    }
}
