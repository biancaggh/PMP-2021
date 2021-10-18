package Lab3
import com.cra.figaro.language._
import com.cra.figaro.algorithm.factored.VariableElimination

object Check1 {
  val x = Flip(0.4)
  val y = Flip(0.4)
  val z = x
  val w = x === z
  println(VariableElimination.probability(w, true))
}
 
object Check2 {
  val x = Flip(0.4)
  val y = Flip(0.4)
  val z = y
  val w = x === z
  println(VariableElimination.probability(w, true))
}
