package curs4

import com.cra.figaro.language.{Flip, Select, Apply}	
import com.cra.figaro.library.compound.{CPD, RichCPD}
import com.cra.figaro.algorithm.factored.VariableElimination

object Ex1 {
    val rembrandt = Flip(0.1)
    val subject = Select(0.8 -> 'people, 0.2 -> 'landscape)
    val size = CPD(subject,
                    'people    -> Select(0.25 -> 'small, 0.25 -> 'medium, 0.5 -> 'large),
                    'landscape -> Select(0.25 -> 'small, 0.5 -> 'medium, 0.25 -> 'large)
                  )
    val brightness = CPD(subject,
                         'people    -> Select(0.8 -> 'dark, 0.2 -> 'bright),
                         'landscape -> Select(0.3 -> 'dark, 0.7 -> 'bright)
                        )
                        
    val price = CPD(rembrandt, subject,
                    (false, 'people) -> Flip(0.2),
                    (false, 'landscape) -> Flip(0.1),
                    (true, 'people) -> Flip(0.9),
                    (true, 'landscape) -> Flip(0.4)
                   )
    /*
    val realprice = Apply(price, (b) => if b then 10000 else 100)

    val buy = RichCPD(rembrand, size, realprice,
                        (OneOf(true), *, *) -> Flip(0.9)
                        (*, *, *) -> Flip(0.3)
                    )
    */              
    def main(args: Array[String]) {
        println("Probab. for brightness dark: " + 
            VariableElimination.probability(brightness, 'dark))
        
        val algorithm = VariableElimination(brightness, size)
        algorithm.start()
        println("Probab. for brightness dark and size medium: " + 
            algorithm.probability(brightness, 'dark) + "; " +
            algorithm.probability(size, 'medium))
        
        algorithm.stop();
        brightness.observe('bright)
        algorithm.resume();
        //brightness.unobserve()   // comment / uncomment
        
        //subject.observe('landscape)   // comment / uncomment

        val algorithm2 = VariableElimination(brightness, size)
        algorithm2.start()
        println("Probab. for brightness dark and size medium: " + 
            algorithm2.probability(brightness, 'dark) + "; " +
            algorithm2.probability(size, 'medium))
        algorithm.kill()
        algorithm2.kill()
        
    }
}
