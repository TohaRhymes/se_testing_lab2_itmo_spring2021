import kotlin.math.*

class KotlinWolframImplementation : Wolfram {
    override fun sin(x: Double, eps: Double): Double = kotlin.math.sin(x)

    override fun cos(x: Double, eps: Double): Double = kotlin.math.cos(x)

    override fun csc(x: Double, eps: Double): Double = 1 / kotlin.math.sin(x)

    override fun sec(x: Double, eps: Double): Double = 1 / kotlin.math.cos(x)

    override fun tan(x: Double, eps: Double): Double = kotlin.math.tan(x)

    override fun cot(x: Double, eps: Double): Double = 1 / kotlin.math.tan(x)

    override fun ln(x: Double, eps: Double): Double = 1 / kotlin.math.ln(x)

    override fun log(x: Double, base: Double, eps: Double) = 1 / kotlin.math.log(x, base)

}