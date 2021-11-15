package curs5

import com.cra.figaro.language.{Constant, Flip, Select}	
import com.cra.figaro.library.atomic.discrete.{Binomial}
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.algorithm.sampling.Importance


object Ex5 {

    def main(args: Array[String]) {	
        val affordability = 0.56
        val numberFriendsLike = Select(1.0 -> 20)
        val numberBuy = Binomial(numberFriendsLike, Constant(affordability))
        val alg = Importance(100000, numberBuy)
        alg.start()
        for {i <- 1 until 20}
            println(i + ": " + alg.probability(numberBuy, i))
        
    }
}




