package Partial

import com.cra.figaro.language._
import com.cra.figaro.library.atomic.discrete._
import com.cra.figaro.algorithm.factored.VariableElimination

object Test1 {
	def main(args: Array[String]) {
        //1
		val zar1 = FromRange(1, 7)
		val zar2 = FromRange(1, 7)
        val aruncare1 = Chain(zar1, ((z: Int) => FromRange(1, z + 1)))
		val aruncare2 = Chain(zar2, ((z: Int) => FromRange(1, z + 1)))

        //println(VariableElimination.probability(aruncare2, 4))
		val total = Apply(zar1, zar2, (i1: Int, i2: Int) => i1 + i2)
        val castig = Chain(total, (t: Int) =>
		if (t == 11 || t == 7) {
			Constant("Primul jucator a castigat!")
		}
		else if(t == 2 || t == 3 || t == 12){
			Constant("Al doilea jucator a castigat!")
        }
        else
            Constant("Este remiza.")
	)
		//2
        val p1=VariableElimination.probability(castig, "Primul jucator a castigat!")
        println("Probabilitatea ca primul jucator sa castige este de: "+p1)
        //3
        val p2=VariableElimination.probability(castig, "Al doilea jucator a castigat!")
        println("Probabilitatea ca al doilea jucator sa castige este de: "+p2)
	}
}