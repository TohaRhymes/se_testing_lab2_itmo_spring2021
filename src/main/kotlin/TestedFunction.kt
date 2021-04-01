import kotlin.math.pow

fun tested_fun(x: Double, eps: Double, fun_map: Map<String, (Double, Double) -> Double>): Double {
    val sin = fun_map["sin"]!!
    val cos = fun_map["cos"]!!
    val csc = fun_map["csc"]!!
    val sec = fun_map["sec"]!!
    val tan = fun_map["tan"]!!
    val cot = fun_map["cot"]!!
    val ln = fun_map["ln"]!!
    val log_2 = fun_map["log_2"]!!
    val log_3 = fun_map["log_3"]!!
    val log_5 = fun_map["log_5"]!!
    val log_10 = fun_map["log_10"]!!

    return if (x <= 0)
        (((((((((sin(x, eps) + cos(x, eps)) * sin(x, eps)) / sin(x, eps)) * csc(x, eps)) / (cot(x, eps) / (cot(
            x,
            eps
        ) - (cos(x, eps) + cos(x, eps))))) * ((sec(x, eps) / sec(x, eps)) + sec(x, eps))).pow(3)) - (((tan(
            x,
            eps
        ) / (tan(x, eps) + tan(x, eps))) - (((cot(x, eps) / (sin(x, eps) + cot(x, eps))) / cos(x, eps)) + tan(
            x,
            eps
        ))) - sec(x, eps))) / cos(x, eps))
    else
        (((((log_3(x, eps) - log_5(x, eps)) + log_10(x, eps)) * ln(x, eps)) + (log_5(x, eps) - log_2(
            x,
            eps
        ))) - log_2(x, eps))

}

