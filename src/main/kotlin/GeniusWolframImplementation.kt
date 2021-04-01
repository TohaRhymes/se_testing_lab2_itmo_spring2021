import kotlin.math.abs
import kotlin.math.pow

class GeniusWolframImplementation {
    companion object Functions : Wolfram {


        override fun sin(x: Double, eps: Double): Double {
            var cur = 0.0
            var prev = Double.MAX_VALUE
            var n = 0
            while (abs(cur - prev) > eps / 1000) {
                prev = cur
                var additional = (-1.0).pow(n) * (x).pow(2 * n + 1)
                for (i in 1..2 * n + 1)
                    additional /= i
                cur +=  additional
                n++
            }
            return cur
        }

        override fun cos(x: Double, eps: Double): Double = 1 - 2 * sin(x / 2, eps).pow(2)

        override fun csc(x: Double, eps: Double): Double = 1 / sin(x, eps)

        override fun sec(x: Double, eps: Double): Double = 1 / cos(x, eps)

        override fun tan(x: Double, eps: Double): Double = sin(x, eps) / cos(x, eps)

        override fun cot(x: Double, eps: Double): Double = cos(x, eps) / sin(x, eps)

        override fun ln(x: Double, eps: Double): Double {
            return if (x <= 0)
                Double.NaN
            else {
                val arg = (x - 1) / (x + 1)
                var res = arg
                var prev = Double.MAX_VALUE
                var n = 3
                while (abs(res - prev) > eps/1000) {
                    prev = res
                    res += arg.pow(n) / n
                    n += 2
                }
                2 * res
            }
        }

        override fun log_2(x: Double, eps: Double): Double = ln(x, eps) / ln(2.0, eps)
        override fun log_3(x: Double, eps: Double): Double = ln(x, eps) / ln(3.0, eps)
        override fun log_5(x: Double, eps: Double): Double = ln(x, eps) / ln(5.0, eps)
        override fun log_10(x: Double, eps: Double): Double = ln(x, eps) / ln(10.0, eps)


    }


}