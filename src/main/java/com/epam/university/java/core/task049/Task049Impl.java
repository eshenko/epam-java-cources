package com.epam.university.java.core.task049;

public class Task049Impl implements Task049 {
    @Override
    public String getResultList(String first, String second) {
        if (first == null || second == null || first.isEmpty() || second.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (first.equals(second)) {
            return first;
        }
        if (first.length() > second.length()) {
            return getCommon(first, second.toCharArray());
        } else {
            return getCommon(second, first.toCharArray());
        }
    }

    /** Get string with longest common sequence.
     * @param str first string.
     * @param chars array of chars to search in the first string.
     * @return string with longest common sequence.
     */
    private String getCommon(String str, char[] chars) {
        StringBuilder result = new StringBuilder();
        for (char c : chars) {
            String tmp = String.valueOf(c);
            if (c == chars[0] && str.contains(tmp)) {
                result.append(tmp);
            } else if (str.contains(result + tmp)) {
                result.append(tmp);
            }
        }
        return result.toString();
    }
}
