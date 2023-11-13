package christmas.Util;

public class CommaFormatter {
    public static String formatWithComma(int number) {
        String numberStr = Integer.toString(number);
        int length = numberStr.length();

        if (length <= 3) {
            return numberStr; // 세 자리 미만이면 그대로 반환
        }

        StringBuilder result = new StringBuilder();
        int count = 0;

        for (int i = length - 1; i >= 0; i--) {
            result.insert(0, numberStr.charAt(i));
            count++;

            if (count == 3 && i != 0) {
                result.insert(0, ',');
                count = 0;
            }
        }

        return result.toString();
    }
}
