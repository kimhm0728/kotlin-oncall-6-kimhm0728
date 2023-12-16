package oncall.constants

enum class LegalHoliday(val month: Month, val day: Int) {
    NEW_REVISION(Month.JAN, 1),
    INDEPENDENCE_MOVEMENT_DAY(Month.MAR, 1),
    CHILDREN_DAY(Month.MAY, 5),
    MEMORIAL_DAY(Month.JUN, 6),
    LIBERATION_DAY(Month.AUG, 15),
    NATIONAL_FOUNDATION_DAY(Month.OCT, 3),
    HANGUL_DAY(Month.OCT, 9),
    CHRISTMAS(Month.DEC, 25)
}