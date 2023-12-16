package oncall

import oncall.constants.DayOfWeek
import oncall.constants.Month
import oncall.service.DayClassifier
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class OnCallTest {

    @ParameterizedTest
    @MethodSource("평일 판별 테스트 데이터")
    fun `해당 날짜가 평일인지 판별한다`(dayOfWeek: DayOfWeek, expected: Boolean) {
        // given

        // when
        val actual = DayClassifier.isWeekday(dayOfWeek)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("법정 공휴일 판별 테스트 데이터")
    fun `해당 날짜가 법정 공휴일인지 판별한다`(month: Month, day: Int, expected: Boolean) {
        // given

        // when
        val actual = DayClassifier.isLegalHoliday(month, day)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("휴일 판별 테스트 데이터")
    fun `해당 날짜가 휴일인지 판별한다`(month: Month, day: Int, dayOfWeek: DayOfWeek, expected: Boolean) {
        // given

        // when
        val actual = DayClassifier.isHoliday(month, day, dayOfWeek)

        // then
        assertThat(actual).isEqualTo(expected)
    }


    companion object {
        @JvmStatic
        fun `평일 판별 테스트 데이터`() = listOf(
            Arguments.of(DayOfWeek.MON, true),
            Arguments.of(DayOfWeek.SAT, false),
            Arguments.of(DayOfWeek.WED, true)
        )

        @JvmStatic
        fun `법정 공휴일 판별 테스트 데이터`() = listOf(
            Arguments.of(Month.DEC, 25, true),
            Arguments.of(Month.JUL, 28, false),
            Arguments.of(Month.MAR, 1, true)
        )

        @JvmStatic
        fun `휴일 판별 테스트 데이터`() = listOf(
            Arguments.of(Month.DEC, 25, DayOfWeek.TUE, true),
            Arguments.of(Month.JUL, 28, DayOfWeek.SAT, true),
            Arguments.of(Month.MAR, 16, DayOfWeek.MON, false)
        )
    }
}