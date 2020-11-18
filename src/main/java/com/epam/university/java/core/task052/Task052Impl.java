package com.epam.university.java.core.task052;

public class Task052Impl implements Task052 {
    @Override
    public boolean validateCard(long number) {
        if (number <= 0) {
            throw new IllegalArgumentException();
        }
        String str = String.valueOf(number);
        String checkDigit = String.valueOf(str.charAt(str.length() - 1));

        return luhn(str.substring(0, str.length() - 1)).equals(checkDigit);
    }

    private String luhn(String str) {
        int sum = 0;
        int length = str.length();
        int p = length % 2;
        for (int i = length; i > 0; i--) {
            int digit = Character.getNumericValue(str.charAt(i - 1));
            if (i % 2 == p) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
                sum += digit;
            }
        }
        for (int i = length; i > 0; i--) {
            int digit = Character.getNumericValue(str.charAt(i - 1));
            if (i % 2 != p) {
                sum += digit;
            }
        }
        if ((((sum % 10) - 10) * -1) == 10) {
            return ("0");
        } else {
            return ("" + (((sum % 10) - 10) * -1));
        }
    }
}
