package integrated
import GeniusWolframImplementation
import KotlinWolframImplementation
import org.junit.jupiter.api.Assertions.assertEquals
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import tested_fun
import java.nio.file.Files
import java.nio.file.Path


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IntegrationTest {
    @BeforeAll
    fun prepareCSV() {
        Files.deleteIfExists(Path.of("out.csv"))
        Files.createFile(Path.of("out.csv"))
        csvWriter().writeAll(listOf(listOf("x", "f(x)")), "out.csv", append = true)

    }


    private fun ArrayList<ArrayList<Double>>.appendRow(row: ArrayList<Double>) : ArrayList<ArrayList<Double>> {
        this.add(row)
        return this
    }


    private fun applyToCSV(rows: ArrayList<ArrayList<Double>>) {
        csvWriter().writeAll(rows, "out.csv", append = true)
    }

    private fun xPhaseTesting(x: Double, eps: Double, geniusList: ArrayList<String>): Double {
        val functions: HashMap<String, (Double, Double) -> Double> = hashMapOf(
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

        for (replacing_fun in geniusList) {
            functions.replace("sin", KotlinWolframImplementation::sin, GeniusWolframImplementation::sin)
            when (replacing_fun) {
                "sin" -> functions.replace("sin", KotlinWolframImplementation::sin, GeniusWolframImplementation::sin)
                "cos" -> functions.replace("cos", KotlinWolframImplementation::cos, GeniusWolframImplementation::cos)
                "csc" -> functions.replace("csc", KotlinWolframImplementation::csc, GeniusWolframImplementation::csc)
                "sec" -> functions.replace("sec", KotlinWolframImplementation::sec, GeniusWolframImplementation::sec)
                "tan" -> functions.replace("tan", KotlinWolframImplementation::tan, GeniusWolframImplementation::tan)
                "cot" -> functions.replace("cot", KotlinWolframImplementation::cot, GeniusWolframImplementation::cot)
                "ln" -> functions.replace("ln", KotlinWolframImplementation::ln, GeniusWolframImplementation::ln)
                "log_2" -> functions.replace(
                    "log_2",
                    KotlinWolframImplementation::log_2,
                    GeniusWolframImplementation::log_2
                )
                "log_3" -> functions.replace(
                    "log_3",
                    KotlinWolframImplementation::log_3,
                    GeniusWolframImplementation::log_3
                )
                "log_5" -> functions.replace(
                    "log_5",
                    KotlinWolframImplementation::log_5,
                    GeniusWolframImplementation::log_5
                )
                "log_10" -> functions.replace(
                    "log_10",
                    KotlinWolframImplementation::log_10,
                    GeniusWolframImplementation::log_10
                )
            }
        }
        return tested_fun(x, eps, functions)
    }


    // Период синуса [0; 2pi] => Возьмем точки из периода + то жемое (на всякий) для отрицательного
    @ParameterizedTest
    @CsvSource(
        "-7, 0.000001",
        "-5, 0.000001",
        "-2.03, 0.000001",
        "-1.1, 0.000001",
        "0, 0.000001",
        "0.2, 0.000001",
        "1.55, 0.000001",
        "3.7, 0.000001",
        "5.7, 0.000001",
        "6.78, 0.000001"
    )
    fun custom_sin_check(x: Double, eps: Double) {
        assertEquals(KotlinWolframImplementation.sin(x, eps), GeniusWolframImplementation.sin(x, eps), eps)
    }


    // ln(x) не определен для x<=0, для остальных - монотонно возрастает
    // Значит возьмем одно отрицательное значение, ноль, положителное меньше, равное, больше 1.
    @ParameterizedTest
    @CsvSource(
        "-0.39, 0.000001",
        "0.28, 0.000001",
        "1, 0.000001",
        "7.22, 0.000001"
    )
    fun custom_ln_check(x: Double, eps: Double) {
        assertEquals(KotlinWolframImplementation.ln(x, eps), GeniusWolframImplementation.ln(x, eps), eps)
    }


    //Для x<=0, ОДЗ: x!=-k*pi/4, k = {0,1,2,...}
    //Для x>0, любое
    @ParameterizedTest
    @CsvSource(
        "-7, 0.000001",
        "-5, 0.000001",
        "-2.03, 0.000001",
        "-1.1, 0.000001",
        "0, 0.000001",
        "0.2, 0.000001",
        "6.78, 0.000001"
    )
    fun first_phase_test(x: Double, eps: Double) {
        val ref_list = arrayListOf<String>()
        val cur_list = arrayListOf("sin", "ln")
        assertEquals(xPhaseTesting(x, eps, ref_list), xPhaseTesting(x, eps, cur_list), eps)
        var rows: ArrayList<ArrayList<Double>> = ArrayList()
        rows = rows.appendRow(arrayListOf(x, xPhaseTesting(x, eps, cur_list)))
        applyToCSV(rows)
    }

    @ParameterizedTest
    @CsvSource(
        "-7, 0.000001",
        "-5, 0.000001",
        "-2.03, 0.000001",
        "-1.1, 0.000001",
        "0, 0.000001",
        "0.2, 0.000001",
        "6.78, 0.000001"
    )
    fun second_phase_test(x: Double, eps: Double) {
        val ref_list = arrayListOf<String>()
        val cur_list = arrayListOf("sin", "ln", "cos")
        assertEquals(xPhaseTesting(x, eps, ref_list), xPhaseTesting(x, eps, cur_list), eps)
        var rows: ArrayList<ArrayList<Double>> = ArrayList()
        rows = rows.appendRow(arrayListOf(x, xPhaseTesting(x, eps, cur_list)))
        applyToCSV(rows)
    }


    @ParameterizedTest
    @CsvSource(
        "-7, 0.000001",
        "-5, 0.000001",
        "-2.03, 0.000001",
        "-1.1, 0.000001",
        "0, 0.000001",
        "0.2, 0.000001",
        "6.78, 0.000001"
    )
    fun third_phase_test(x: Double, eps: Double) {
        val ref_list = arrayListOf<String>()
        val cur_list = arrayListOf("sin", "ln", "cos")
        assertEquals(xPhaseTesting(x, eps, ref_list), xPhaseTesting(x, eps, cur_list), eps)
        var rows: ArrayList<ArrayList<Double>> = ArrayList()
        rows = rows.appendRow(arrayListOf(x, xPhaseTesting(x, eps, cur_list)))
        applyToCSV(rows)
    }


    @ParameterizedTest
    @CsvSource(
        "-7, 0.000001",
        "-5, 0.000001",
        "-2.03, 0.000001",
        "-1.1, 0.000001",
        "0, 0.000001",
        "0.2, 0.000001",
        "6.78, 0.000001"
    )
    fun fourth_phase_test(x: Double, eps: Double) {
        val ref_list = arrayListOf<String>()
        val cur_list = arrayListOf("sin", "ln", "cos", "csc")
        assertEquals(xPhaseTesting(x, eps, ref_list), xPhaseTesting(x, eps, cur_list), eps)
        var rows: ArrayList<ArrayList<Double>> = ArrayList()
        rows = rows.appendRow(arrayListOf(x, xPhaseTesting(x, eps, cur_list)))
        applyToCSV(rows)
    }

    @ParameterizedTest
    @CsvSource(
        "-7, 0.000001",
        "-5, 0.000001",
        "-2.03, 0.000001",
        "-1.1, 0.000001",
        "0, 0.000001",
        "0.2, 0.000001",
        "6.78, 0.000001"
    )
    fun fifth_phase_test(x: Double, eps: Double) {
        val ref_list = arrayListOf<String>()
        val cur_list = arrayListOf("sin", "ln", "cos", "csc", "sec",)
        assertEquals(xPhaseTesting(x, eps, ref_list), xPhaseTesting(x, eps, cur_list), eps)
        var rows: ArrayList<ArrayList<Double>> = ArrayList()
        rows = rows.appendRow(arrayListOf(x, xPhaseTesting(x, eps, cur_list)))
        applyToCSV(rows)
    }

    @ParameterizedTest
    @CsvSource(
        "-7, 0.000001",
        "-5, 0.000001",
        "-2.03, 0.000001",
        "-1.1, 0.000001",
        "0, 0.000001",
        "0.2, 0.000001",
        "6.78, 0.000001"
    )
    fun sixth_phase_test(x: Double, eps: Double) {
        val ref_list = arrayListOf<String>()
        val cur_list = arrayListOf("sin", "ln", "cos", "csc", "sec", "tan")
        assertEquals(xPhaseTesting(x, eps, ref_list), xPhaseTesting(x, eps, cur_list), eps)
        var rows: ArrayList<ArrayList<Double>> = ArrayList()
        rows = rows.appendRow(arrayListOf(x, xPhaseTesting(x, eps, cur_list)))
        applyToCSV(rows)
    }

    @ParameterizedTest
    @CsvSource(
        "-7, 0.000001",
        "-5, 0.000001",
        "-2.03, 0.000001",
        "-1.1, 0.000001",
        "0, 0.000001",
        "0.2, 0.000001",
        "6.78, 0.000001"
    )
    fun seventh_phase_test(x: Double, eps: Double) {
        val ref_list = arrayListOf<String>()
        val cur_list = arrayListOf("sin", "ln", "cos", "csc", "sec", "tan", "cot")
        assertEquals(xPhaseTesting(x, eps, ref_list), xPhaseTesting(x, eps, cur_list), eps)
        var rows: ArrayList<ArrayList<Double>> = ArrayList()
        rows = rows.appendRow(arrayListOf(x, xPhaseTesting(x, eps, cur_list)))
        applyToCSV(rows)
    }

    @ParameterizedTest
    @CsvSource(
        "-7, 0.000001",
        "-5, 0.000001",
        "-2.03, 0.000001",
        "-1.1, 0.000001",
        "0, 0.000001",
        "0.2, 0.000001",
        "6.78, 0.000001"
    )
    fun eighth_phase_test(x: Double, eps: Double) {
        val ref_list = arrayListOf<String>()
        val cur_list = arrayListOf("sin", "ln", "cos", "csc", "sec", "tan", "cot", "log_2")
        assertEquals(xPhaseTesting(x, eps, ref_list), xPhaseTesting(x, eps, cur_list), eps)
        var rows: ArrayList<ArrayList<Double>> = ArrayList()
        rows = rows.appendRow(arrayListOf(x, xPhaseTesting(x, eps, cur_list)))
        applyToCSV(rows)
    }


    @ParameterizedTest
    @CsvSource(
        "-7, 0.000001",
        "-5, 0.000001",
        "-2.03, 0.000001",
        "-1.1, 0.000001",
        "0, 0.000001",
        "0.2, 0.000001",
        "6.78, 0.000001"
    )
    fun ninth_phase_test(x: Double, eps: Double) {
        val ref_list = arrayListOf<String>()
        val cur_list = arrayListOf("sin", "ln", "cos", "csc", "sec", "tan", "cot", "log_2", "log_3")
        assertEquals(xPhaseTesting(x, eps, ref_list), xPhaseTesting(x, eps, cur_list), eps)
        var rows: ArrayList<ArrayList<Double>> = ArrayList()
        rows = rows.appendRow(arrayListOf(x, xPhaseTesting(x, eps, cur_list)))
        applyToCSV(rows)
    }


    @ParameterizedTest
    @CsvSource(
        "-7, 0.000001",
        "-5, 0.000001",
        "-2.03, 0.000001",
        "-1.1, 0.000001",
        "0, 0.000001",
        "0.2, 0.000001",
        "6.78, 0.000001"
    )
    fun tenth_phase_test(x: Double, eps: Double) {
        val ref_list = arrayListOf<String>()
        val cur_list = arrayListOf("sin", "ln", "cos", "csc", "sec", "tan", "cot", "log_2", "log_3", "log_5")
        assertEquals(xPhaseTesting(x, eps, ref_list), xPhaseTesting(x, eps, cur_list), eps)
        var rows: ArrayList<ArrayList<Double>> = ArrayList()
        rows = rows.appendRow(arrayListOf(x, xPhaseTesting(x, eps, cur_list)))
        applyToCSV(rows)
    }



    @ParameterizedTest
    @CsvSource(
        "-7, 0.000001",
        "-5, 0.000001",
        "-2.03, 0.000001",
        "-1.1, 0.000001",
        "0, 0.000001",
        "0.2, 0.000001",
        "6.78, 0.000001"
    )
    fun eleventh_phase_test(x: Double, eps: Double) {
        val ref_list = arrayListOf<String>()
        val cur_list = arrayListOf("sin", "ln", "cos", "csc", "sec", "tan", "cot", "log_2", "log_3", "log_5", "log_10")
        assertEquals(xPhaseTesting(x, eps, ref_list), xPhaseTesting(x, eps, cur_list), eps)
        var rows: ArrayList<ArrayList<Double>> = ArrayList()
        rows = rows.appendRow(arrayListOf(x, xPhaseTesting(x, eps, cur_list)))
        applyToCSV(rows)
    }



}