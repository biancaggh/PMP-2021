package Lab9
import com.cra.figaro.algorithm.sampling.Importance
import com.cra.figaro.language.{Apply, Chain, Constant, Element, Select}
import com.cra.figaro.library.compound.^^

object Ex5 {
  def main(args: Array[String]): Unit ={
    val nr = 11
    val fraction = 0.3

    val investment: Array[Element[Double]] = Array.fill(nr)(Constant(0.0))
    val profit: Array[Element[Double]] = Array.fill(nr)(Constant(0.0))
    var capital: Array[Element[Double]] = Array.fill(nr)(Constant(0.0))

    capital(0) = Constant(1500)

    def transition(value: Int): (Element[(Double, Double, Double)]) = {
        val cap = capital(value - 1)
        val investitie = Apply(cap, (cap: Double) => cap * fraction)
    
        val profitF = Chain(investitie, cap, (inv: Double, cap: Double) =>
           if (inv >= 50.0 * cap / 100.0) Select(0.1 -> ((15.0 / 100.0) * cap), 0.3 -> ((25.0 / 100.0) * cap), 0.6 -> ((60.0 / 100.0) * cap));
           else if (inv >= 30.0 * cap / 100.0) Select(0.2 -> ((40.0 / 100.0) * cap), 0.6 -> ((60.0 / 100.0) * cap), 0.2 -> ((30.0 / 100.0) * cap));
           else Select(0.6 -> ((25.0 / 100.0) * cap), 0.3 -> ((35.0 / 100.0) * cap), 0.1 -> ((40.0 / 100.0) * cap)))
        val capitalF = Apply(profitF, cap, investitie,
           (prof: Double, cap: Double, invest: Double) => cap + prof - invest)
        ^^(investitie, profitF, capitalF)
       }
        for {value <- 1 until nr} {
           val newState = transition(value)
           investment(value) = newState._1
           profit(value) = newState._2
           capital(value) = newState._3
       }
       println(Importance.probability(capital(10), (c: Double) => c > 1500))

  }
}