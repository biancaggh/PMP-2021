package Lab10
import com.cra.figaro.algorithm.sampling.Importance
import com.cra.figaro.language.{Apply, Chain, Constant, Element, Select}

object Var2 {
  def main(args: Array[String]): Unit ={
    val nr = 12
    val value = 0.3

    val investment: Array[Element[Double]] = Array.fill(nr)(Constant(0.0))
    val profit: Array[Element[Double]] = Array.fill(nr)(Constant(0.0))
    val capital: Array[Element[Double]] = Array.fill(nr)(Constant(0.0))

    capital(0) = Constant(1500)

    for {i <- 1 until nr} {
      investment(i) = Apply(capital(i - 1), (cap: Double) => cap * value) 

      profit(i) = Chain(investment(i), capital(i - 1), (inv: Double, cap: Double) =>
        if (inv >= 0.5 * cap) Select(0.1 -> (0.4 * cap), 0.3 -> (0.5 * cap), 0.6 -> (0.7 * cap)); 
        else if (inv >= 0.3 * cap) Select(0.2 -> (0.25 * cap), 0.6 -> (0.5 * cap), 0.2 -> (0.35 * cap)); 
        else Select(0.6 -> (0.3 * cap), 0.3 -> (0.2 * cap), 0.1 -> (0.1 * cap))) 
      capital(i) = Apply(profit(i), capital(i - 1), investment(i),
        (prof: Double, cap: Double, invest: Double) => cap + prof - invest)
    }

    println(Importance.probability(capital(10), (c: Double) => c > 1500))


  }
}