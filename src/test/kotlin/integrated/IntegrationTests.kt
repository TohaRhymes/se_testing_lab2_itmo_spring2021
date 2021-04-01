package integrated
import GeniusWolframImplementation
import KotlinWolframImplementation
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import tested_fun

class IntegrationTest {

    private fun referenceTesting(x: Double, eps: Double): Double {
        val functions: Map<String, (Double, Double) -> Double> = mapOf(
            "sin" to KotlinWolframImplementation::sin,
            "cos" to KotlinWolframImplementation::cos,
            "csc" to KotlinWolframImplementation::csc,
            "sec" to KotlinWolframImplementation::sec,
            "tan" to KotlinWolframImplementation::tan,
            "cot" to KotlinWolframImplementation::cot,
            "ln" to KotlinWolframImplementation::ln,
            "log_2" to KotlinWolframImplementation::log_2,
            "log_3" to KotlinWolframImplementation::log_3,
            "log_5" to KotlinWolframImplementation::log_5,
            "log_10" to KotlinWolframImplementation::log_10
        )
        return tested_fun(x, eps, functions)
    }

    private fun firstPhaseTesting(x: Double, eps: Double): Double {
        val functions: Map<String, (Double, Double) -> Double> = mapOf(
            "sin" to GeniusWolframImplementation::sin,
            "cos" to KotlinWolframImplementation::cos,
            "csc" to KotlinWolframImplementation::csc,
            "sec" to KotlinWolframImplementation::sec,
            "tan" to KotlinWolframImplementation::tan,
            "cot" to KotlinWolframImplementation::cot,
            "ln" to GeniusWolframImplementation::ln,
            "log_2" to KotlinWolframImplementation::log_2,
            "log_3" to KotlinWolframImplementation::log_3,
            "log_5" to KotlinWolframImplementation::log_5,
            "log_10" to KotlinWolframImplementation::log_10
        )
        return tested_fun(x, eps, functions)
    }



    @ParameterizedTest
    @CsvSource(
        "-0.5, 0.000001",
        "0.5, 0.000001",
        "0.2, 0.000001"
    )
    fun `custom sin with actual function`(x: Double, eps: Double) {
        assertEquals(GeniusWolframImplementation.sin(x, eps), KotlinWolframImplementation.sin(x, eps), eps)
    }



    @ParameterizedTest
    @CsvSource(
        "-0.5, 0.000001",
        "-0.1, 0.000001",
        "0.5, 0.000001",
        "3, 0.000001",
        "0.2, 0.000001"
    )
    fun `custom ln with actual function`(x: Double, eps: Double) {
        assertEquals(KotlinWolframImplementation.ln(x, eps), GeniusWolframImplementation.ln(x, eps), eps)
    }




    @ParameterizedTest
    @CsvSource(
        "-0.5, 0.000001",
        "0.5, 0.000001",
        "0.2, 0.000001"
    )
    fun TEST_CURRENT(x: Double, eps: Double) {
        assertEquals(referenceTesting(x, eps), firstPhaseTesting(x, eps), eps)
    }



//
//
//    @ParameterizedTest
//    @CsvSource(
//        "0.5, 0.000001",
//        "0.2, 0.000001"
//    )
//    fun `Integration test for function with gradually increasing number of custom functions implementations`(
//        x: Double,
//        eps: Double
//    ) {
//        var actualFunctions: HashMap<String, () -> Double> = hashMapOf(
//            "sin" to { ActualFunctionImplementation.sin(x) },
//            "csc" to { ActualFunctionImplementation.csc(x) },
//            "sec" to { ActualFunctionImplementation.sec(x) },
//            "ln" to { ActualFunctionImplementation.ln(x) },
//            "log_2" to { ActualFunctionImplementation.log_2(x) },
//            "log_3" to { ActualFunctionImplementation.log_3(x) },
//            "log_5" to { ActualFunctionImplementation.log_5(x) },
//            "log_10" to { ActualFunctionImplementation.log_10(x) }
//        )
//
//        for (i in 0..actualFunctions.size) {
//            when (i) {
//                0 -> assertEquals(actualFunctionValue(x), Utility.f(x, actualFunctions), eps, "Failed on $i stage")
//                1 -> {
//                    actualFunctions = actualFunctions.replaceWith("sin") { CustomFunctionImplementation.sin(x, eps) }
//                    assertEquals(actualFunctionValue(x), Utility.f(x, actualFunctions), eps, "Failed on $i stage")
//                }
//                2 -> {
//                    actualFunctions = actualFunctions.replaceWith("csc") { CustomFunctionImplementation.csc(x, eps) }
//                    assertEquals(actualFunctionValue(x), Utility.f(x, actualFunctions), eps, "Failed on $i stage")
//                }
//                3 -> {
//                    actualFunctions = actualFunctions.replaceWith("sec") { CustomFunctionImplementation.sec(x, eps) }
//                    assertEquals(actualFunctionValue(x), Utility.f(x, actualFunctions), eps, "Failed on $i stage")
//                }
//                4 -> {
//                    actualFunctions = actualFunctions.replaceWith("ln") { CustomFunctionImplementation.ln(x, eps) }
//                    assertEquals(actualFunctionValue(x), Utility.f(x, actualFunctions), 0.00001, "Failed on $i stage")
//                }
//                5 -> {
//                    actualFunctions = actualFunctions.replaceWith("log_2") { CustomFunctionImplementation.log_2(x, eps) }
//                    assertEquals(actualFunctionValue(x), Utility.f(x, actualFunctions), 0.00001, "Failed on $i stage")
//                }
//                6 -> {
//                    actualFunctions = actualFunctions.replaceWith("log_3") { CustomFunctionImplementation.log_3(x, eps) }
//                    assertEquals(actualFunctionValue(x), Utility.f(x, actualFunctions), 0.00001, "Failed on $i stage")
//                }
//                7 -> {
//                    actualFunctions = actualFunctions.replaceWith("log_5") { CustomFunctionImplementation.log_5(x, eps) }
//                    assertEquals(actualFunctionValue(x), Utility.f(x, actualFunctions), 0.00001, "Failed on $i stage")
//                }
//                8 -> {
//                    actualFunctions = actualFunctions.replaceWith("log_10") { CustomFunctionImplementation.log_10(x, eps) }
//                    assertEquals(actualFunctionValue(x), Utility.f(x, actualFunctions), 0.00001, "Failed on $i stage")
//                }

//            }
//        }
//    }
}