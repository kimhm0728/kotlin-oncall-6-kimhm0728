package oncall

import oncall.io.input.InputValidator
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ExceptionTest {

    private val inputValidator = InputValidator()

    @ParameterizedTest
    @ValueSource(strings = ["12,월", "2,토", "5,금"])
    fun `월과 시작 요일을 요구사항에 맞게 입력하면 예외가 발생하지 않는다`(input: String) {
        Assertions.assertThatCode { inputValidator.validateMonthAndStartDay(input) }
            .doesNotThrowAnyException()
    }

    @ParameterizedTest
    @ValueSource(strings = ["15,월", "2,", "1,1"])
    fun `월과 시작 요일을 요구사항에 맞지 않게 입력하면 예외가 발생한다`(input: String) {
        Assertions.assertThatThrownBy { inputValidator.validateMonthAndStartDay(input) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @ParameterizedTest
    @ValueSource(strings = ["준팍,도밥,고니,수아,루루", "수아,루루,글로,솔로스타,우코,슬링키,참새,도리,준팍,도밥,고니"])
    fun `근무 순서를 요구사항에 맞게 입력하면 예외가 발생하지 않는다`(input: String) {
        Assertions.assertThatCode { inputValidator.validateWorkSort(input) }
            .doesNotThrowAnyException()
    }

    @ParameterizedTest
    @ValueSource(strings = ["준팍,도밥", "a,b,c,e,d", "솔로스타,우코,슬링키,"])
    fun `근무 순서를 요구사항에 맞지 않게 입력하면 예외가 발생한다`(input: String) {
        Assertions.assertThatThrownBy { inputValidator.validateWorkSort(input) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}