package christmas.Event;

public enum SpecialDiscountDay {
    DAY_ONE(1),
    DAY_THREE(3),
    DAY_TEN(10),
    DAY_SEVENTEEN(17),
    DAY_TWENTY_FOUR(24),
    DAY_TWENTY_FIVE(25),
    DAY_THIRTY_ONE(31);
    private final int specialEventDay;

    SpecialDiscountDay(int specialEventDay) {
        this.specialEventDay = specialEventDay;
    }

    public int getSpecialEventDay() {
        return specialEventDay;
    }

    public static boolean isSpecialDay(int visitDate) {
        for (SpecialDiscountDay day : SpecialDiscountDay.values()) {
            if (day.getSpecialEventDay() == visitDate){
                return true;
            }
        }
        return false;
    }
}
