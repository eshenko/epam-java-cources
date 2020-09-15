package com.epam.university.java.core.task026;

public class Task026Impl implements Task026 {
    @Override
    public String encrypt(String sourceString, int shift) {
        if (sourceString == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder result = new StringBuilder();
        shift = shift % 26;
        for (char c : sourceString.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                if (c + shift > 'z') {
                    result.append((char)('a' + (c + shift - 'z' - 1)));
                } else {
                    result.append((char)(c + shift));
                }
            } else if (c >= 'A' && c <= 'Z') {
                if (c + shift > 'Z') {
                    result.append((char)('A' + (c + shift - 'Z' - 1)));
                } else {
                    result.append((char)(c + shift));
                }
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    @Override
    public String decrypt(String encryptedString, int shift) {
        if (encryptedString == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder result = new StringBuilder();
        shift = shift % 26;
        for (char c : encryptedString.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                if (c - shift < 'a') {
                    result.append((char)('z' - (shift - (c - 'a')) + 1));
                } else {
                    result.append((char)(c - shift));
                }
            } else if (c >= 'A' && c <= 'Z') {
                if (c - shift < 'A') {
                    result.append((char)('Z' - (shift - (c - 'A')) + 1));
                } else {
                    result.append((char)(c - shift));
                }
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
