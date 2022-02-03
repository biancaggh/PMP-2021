package Sesiune

import com.cra.figaro.language.{Element, Select, Flip, Apply, Chain}
import com.cra.figaro.library.compound.{^^}
import com.cra.figaro.algorithm.factored.VariableElimination

object T1 {

	class Autor{
		val pupular=Flip(1/6)
	}

	class Album( val at: Autor) {
		val calitate=Select (0.27 -> 'mica, 0.6 -> 'medie, 0.13-> 'mare)
	}

    class Nominalizare( val ab: Album){
        def getProb(){
            val nominalizat=Chain(at,ab.calitate,(at: Boolean,ab:Boolean) => {
                if(!at && ab=="mica") Flip(0.013)
                else if(at && ab=="mica") Flip(0.014)
                else if(!at && ab=="medie") Flip(0.016)
                else if(at && ab=="medie") Flip(0.043)
                else if(!at && ab=="mare") Flip(0.047)
                else Flip(0.18)
            }
            return nominalizat
            )
        }
    }
	
	def main(args: Array[String]) {
        val albumm= new Album()
		val autori=List(1,2,3,4,5)
        val albume=List.tabulate(10)((aut: Int) => ^^(aut,albumm))
        val nom: Array[Element[Int]] = Array.fill(10)(Constant(1))
        for {alm <- 1 until albume}{
            nom(alm)=Nominalizare(alm)
        }
        println(VariableElimination.probability(nom(1), true))

		
	}
}