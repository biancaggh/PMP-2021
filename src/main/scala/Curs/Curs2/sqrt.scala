// import scala.math._

// def square(x: Double) = x * x

// def sqrt(x: Double) = {

//   def sqrtIter(guess: Double, x: Double): Double =
//     if (isGoodEnough(guess, x)) guess
//     else sqrtIter(improve(guess, x), x)

//   def improve(guess: Double, x: Double) =
//     (guess + x / guess) / 2
    
//   def isGoodEnough(guess: Double, x: Double) =
//     abs(square(guess) - x) < 0.001
    
//   sqrtIter(2.0, x)
// }

