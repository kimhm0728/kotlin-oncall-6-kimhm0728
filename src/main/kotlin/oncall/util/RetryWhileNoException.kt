package oncall.util

fun <T> retryWhileNoException(action: () -> T): T {
    while (true) {
        try {
            return action()
        } catch (e: IllegalArgumentException) {
            println("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.")
        }
    }
}