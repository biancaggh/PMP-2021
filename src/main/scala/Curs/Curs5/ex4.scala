package curs5

import com.cra.figaro.language.{Constant, Flip, Select}	
import com.cra.figaro.library.atomic.discrete.{Poisson}
import com.cra.figaro.algorithm.sampling.Importance


object Ex4 {

    def main(args: Array[String]) {	
        
        val pdistr = Poisson(1.0)
        
        val alg = Importance(100000, pdistr)
        alg.start()
        for (i <- 0 to 9){
            println("Probability for number of nodes " + i + " is " +
                alg.probability(pdistr, i))
        } 
        
        println("*********")
        var p = new Array[Int](11)
        for (i <- 0 to 1000000) {
            pdistr.generate()
            val v:Int = pdistr.value
            if (v < 10 ) { p(v) += 1 } else { p(10) += 1 }
        }
        for (i <- 0 to 10) {
            println(i + ": " + p(i))
        } 
        
    }
}




