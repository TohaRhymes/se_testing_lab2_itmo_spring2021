class KotlinWolframImplementation {
    companion object Functions : Wolfram {

        override fun sin(x: Double, eps: Double): Double = kotlin.math.sin(x)

        override fun cos(x: Double, eps: Double): Double = kotlin.math.cos(x)

        override fun csc(x: Double, eps: Double): Double = 1 / kotlin.math.sin(x)

        override fun sec(x: Double, eps: Double): Double = 1 / kotlin.math.cos(x)

        override fun tan(x: Double, eps: Double): Double = kotlin.math.tan(x)

        override fun cot(x: Double, eps: Double): Double = 1 / kotlin.math.tan(x)

        override fun ln(x: Double, eps: Double): Double = kotlin.math.ln(x)

        override fun log_2(x: Double, eps: Double) = kotlin.math.log(x, 2.0)
        override fun log_3(x: Double, eps: Double) = kotlin.math.log(x, 3.0)
        override fun log_5(x: Double, eps: Double) = kotlin.math.log(x, 5.0)
        override fun log_10(x: Double, eps: Double) = kotlin.math.log(x, 10.0)
    }

}