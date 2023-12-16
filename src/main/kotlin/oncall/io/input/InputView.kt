package oncall.io.input

import camp.nextstep.edu.missionutils.Console
import oncall.model.OnCallInfo

class InputView(
    private val validator: InputValidator = InputValidator(),
    private val converter: InputConverter = InputConverter(),
) {

    fun readMonthAndStartDay(): OnCallInfo {
        print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ")
        val input = Console.readLine()

        validator.validateMonthAndStartDay(input)
        return converter.convertMonthAndStartDay(input)
    }
}