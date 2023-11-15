package christmas.Util;

public class CommaFormatter {
    public static String formatWithComma(int number) {
        String numberStr = Integer.toString(number);
        int length = numberStr.length();

        if (length <= 3) {
            return numberStr;
        }
        String result = insertComma(numberStr, length);

        return result;
    }

    private static String insertComma(String number, int length) {
        int count = 0;
        StringBuilder result = new StringBuilder();
        for (int i = length - 1; i >= 0; i--) {
            result.insert(0, number.charAt(i));
            count++;

            if (count == 3 && i != 0) {
                result.insert(0, ',');
                count = 0;
            }
        }
        return result.toString();
    }
}
