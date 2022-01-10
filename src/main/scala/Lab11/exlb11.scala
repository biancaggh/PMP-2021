import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.algorithm.factored.beliefpropagation.BeliefPropagation
import com.cra.figaro.algorithm.sampling.Importance
import com.cra.figaro.language.Flip
import com.cra.figaro.library.compound.If

object Ex3 {
  def main(args: Array[String]): Unit ={
    val presedinte = Flip(1.0/40000000.0) //presedinte - prob

    val presedinte_stangaci=Flip(0.5) //50% dintre presedintii --> stangaci
    val populatie_stangaci=Flip(0.1) //10% din populatie --> stangace

    val stangaci = If(presedinte, presedinte_stangaci, populatie_stangaci)
//a
    stangaci.observe(true)
    println(VariableElimination.probability(presedinte, true))
    println(BeliefPropagation.probability(presedinte, true))
    println(Importance.probability(presedinte, true))
    println()
    stangaci.unobserve()
//b
    val presedinte_Harvard=Flip(0.15) //15% dintre presedinti --> Harvard
    val student_Harvard=Flip(1.0/2000.0) //1/2000 americani --> Harvard

    val Harvard=If(presedinte, presedinte_Harvard, student_Harvard)

    Harvard.observe(true)
    println(VariableElimination.probability(presedinte, true))
    println(BeliefPropagation.probability(presedinte, true))
    println(Importance.probability(presedinte, true))
    println()
//c
    stangaci.observe(true)
    println(VariableElimination.probability(presedinte, true))
    println(BeliefPropagation.probability(presedinte, true))
    println(Importance.probability(presedinte, true))
  }
}