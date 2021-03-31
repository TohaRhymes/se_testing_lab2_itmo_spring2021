import kotlin.math.abs
import kotlin.math.pow

class GeniusWolframImplemetation : Wolfram {
    override fun sin(x: Double, eps: Double): Double {
        if (abs(x) > 1)
            return Double.NaN
        var cur = 0.0
        var prev = 0.0
        var n = 1
        while (abs(cur - prev) > eps) {
            prev = cur
            cur += (-1.0).pow(n - 1) * (x).pow(2 * n - 1) / factorial(2 * n - 1)
            n++
        }
        return cur
    }

    override fun cos(x: Double, eps: Double): Double = 1 - 2 * sin(x / 2, eps).pow(2)

    override fun csc(x: Double, eps: Double): Double = 1 / sin(x, eps)

    override fun sec(x: Double, eps: Double): Double = 1 / (1 - 2 * sin(x / 2, eps).pow(2))

    override fun tan(x: Double, eps: Double): Double = sin(x, eps) / (1 - 2 * sin(x / 2, eps).pow(2))

    override fun cot(x: Double, eps: Double): Double = (1 - 2 * sin(x / 2, eps).pow(2)) / sin(x, eps)

    override fun ln(x: Double, eps: Double): Double {
        if (x <= 0)
            return Double.NaN;

        val arg = (x - 1) / (x + 1)
        var res = arg
        var prev = Double.MAX_VALUE
        var n = 3
        while (abs(res - prev) > eps) {
            prev = res
            res += arg.pow(n) / n
            n += 2
        }
        return 2 * res
    }

    override fun log(x: Double, base: Double, eps: Double): Double = ln(x, eps) / ln(base, eps)

    private fun factorial(f: Int): Int = if (f <= 1) 1 else f * factorial(f - 1)

}