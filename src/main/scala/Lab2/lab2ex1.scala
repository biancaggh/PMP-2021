import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.library.compound._
import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._


object Clothes{
    def main(args: Array[String]){
        val produs = Constant("Produs")
        val p1 = Flip(1)
        val p2 = Flip(0.5)
        val p3 = Flip(0.8)
        val p4 = Flip(0.2)
        val result = CPD (p1,p2, p3, p4, 
            (true, false, true, false) -> Constant("True"),
            (false, true, false, true) -> Constant("False"),
            )

        val algorithm = Importance(1000, result)
        algorithm.start()
        //println("--------------")
        println(algorithm.probability( p1,true))
        println(algorithm.probability( p3,true))

    }
}