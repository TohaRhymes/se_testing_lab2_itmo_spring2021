interface Wolfram {
    fun sin(x: Double, eps: Double = 0.0001): Double
    fun cos(x: Double, eps: Double = 0.0001): Double
    fun csc(x: Double, eps: Double = 0.0001): Double
    fun sec(x: Double, eps: Double = 0.0001): Double
    fun tan(x: Double, eps: Double = 0.0001): Double
    fun cot(x: Double, eps: Double = 0.0001): Double
    fun ln(x: Double, eps: Double = 0.0001): Double
    fun log_2(x: Double, eps: Double = 0.0001): Double
    fun log_3(x: Double, eps: Double = 0.0001): Double
    fun log_5(x: Double, eps: Double = 0.0001): Double
    fun log_10(x: Double, eps: Double = 0.0001): Double
}