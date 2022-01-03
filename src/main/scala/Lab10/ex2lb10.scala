package Lab10
import com.cra.figaro.algorithm.sampling.Importance
import com.cra.figaro.language.{Select, Apply, Constant, Element, Chain, Universe}
import com.cra.figaro.algorithm.filtering.ParticleFilter
import com.cra.figaro.library.compound.{*, ^^}

object Var3 {
  def main(args: Array[String]): Unit ={
    val value = 0.3

    val initial = Universe.createNew()
    Constant(1500.0)("capital", initial)
    
    def transition(cap: Double): (Element[(Double, Double, Double)]) = {
        val capital = Constant(cap)
        val investitie = Apply(capital, (cap: Double) =>   cap * value)
    
        val profitNou = Chain(investitie, capital, (inv: Double, cap: Double) =>
           if (inv >= 0.5 * cap) Select(0.1 -> (0.4 * cap), 0.3 -> (0.5 * cap), 0.6 -> (0.7 * cap));
           else if (inv >= 0.3 * cap) Select(0.2 -> (0.25 * cap), 0.6 -> (0.5 * cap), 0.2 -> (0.35 * cap));
           else Select(0.6 -> (0.3 * cap), 0.3 -> (0.2 * cap), 0.1 -> (0.1 * cap)))
        val capitalNou = Apply(profitNou, capital, investitie,
           (prof: Double, cap: Double, invest: Double) => cap + prof - invest)
        ^^(investitie, profitNou, capitalNou)
       }
    
    def UniversNou(uni: Universe): Universe = {
        val next = Universe.createNew()
        val capitalVechi = uni.get[Double]("capital")
    
        val newState = Chain(capitalVechi, transition _)
        Apply(newState, (s: (Double, Double, Double)) => s._1)("investment", next)
        Apply(newState, (s: (Double, Double, Double)) => s._2)("profit", next)
        Apply(newState, (s: (Double, Double, Double)) => s._3)("capital", next)
        next
       }
    
    val alg = ParticleFilter(initial, UniversNou, 10000)
    alg.start()
    
    for {luna <- 1 to 12} {
        alg.advanceTime()
        println("Luna " + luna + ": ")
        println("profit asteptat : " + alg.currentExpectation("profit", (p: Double) => p))
        println("investitie asteptata : " + alg.currentExpectation("investment", (p: Double) => p))
        println("capital asteptat : " + alg.currentExpectation("capital", (p: Double) => p))
       }
  }
}