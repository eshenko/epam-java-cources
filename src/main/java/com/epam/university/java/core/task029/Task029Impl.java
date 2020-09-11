package com.epam.university.java.core.task029;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Task029Impl implements Task029 {
    @Override
    public Collection<String> fillCrossword(Collection<String> rows, Collection<String> words) {
        if (rows == null || words == null) {
            throw new IllegalArgumentException();
        }
        List<String> rowsList = new ArrayList<>(rows);
        List<String> wordsList = new ArrayList<>(words);
        for (String word : wordsList) {
            wordsList.remove(word);
            return fillCrossword(addWord(rowsList, word), wordsList);
        }
        return rowsList;
    }

    /**
     * Checking can the word be added horizontally.
     * @param row row for checking
     * @param word word for checking
     * @param charIndex word start index
     * @return can the word be added horizontally
     */
    private boolean checkHorizontal(String row, String word, int charIndex) {
        if (row.length() < charIndex + word.length()
                || (row.length() > charIndex + word.length()
                && row.charAt(charIndex + word.length()) != '+')
                || (charIndex > 0 && row.charAt(charIndex - 1) != '+')) {
            return false;
        }
        String rowSubstring = row.substring(charIndex, charIndex + word.length());
        for (int i = 0; i < rowSubstring.length(); i++) {
            if (rowSubstring.charAt(i) != '-' && rowSubstring.charAt(i) != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checking can the word be added vertically.
     * @param rows list of rows for checking
     * @param word word for checking
     * @param charIndex line start index
     * @param rowIndex char start index
     * @return can the word be added vertically
     */
    private boolean checkVertical(List<String> rows, String word, int charIndex, int rowIndex) {
        if ((rows.size() > rowIndex + word.length()
                && rows.get(rowIndex + word.length()).charAt(charIndex) != '+')
                || (rowIndex > 0 && rows.get(rowIndex - 1).charAt(charIndex) != '+')) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if (rows.get(rowIndex).charAt(charIndex) != '-'
                    && rows.get(rowIndex).charAt(charIndex) != word.charAt(i)) {
                return false;
            }
            rowIndex++;
        }
        return rowIndex == rows.size() || rows.get(rowIndex).charAt(charIndex) != '-';
    }

    /**
     * Add word to list.
     * @param rows list for adding
     * @param word word for adding
     * @return updated list
     */
    private List<String> addWord(List<String> rows, String word) {
        for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
            for (int charIndex = 0; charIndex < rows.get(rowIndex).length(); charIndex++) {
                if (rows.get(rowIndex).charAt(charIndex) != '+') {
                    if (checkHorizontal(rows.get(rowIndex), word, charIndex)) {
                        byte[] bytes = rows.get(rowIndex).getBytes();
                        for (int j = charIndex; j < charIndex + word.length(); j++) {
                            bytes[j] = (byte) word.charAt(j - charIndex);
                        }
                        rows.set(rowIndex, new String(bytes));
                        return rows;
                    }
                    if (checkVertical(rows, word, charIndex, rowIndex)) {
                        for (int j = rowIndex; j < rowIndex + word.length(); j++) {
                            String curRow = rows.get(j);
                            byte[] rowBytes = curRow.getBytes();
                            rowBytes[charIndex] = (byte) word.charAt(j - rowIndex);
                            rows.set(j, new String(rowBytes));
                        }
                        return rows;
                    }
                }
            }
        }
        return rows;
    }
}
