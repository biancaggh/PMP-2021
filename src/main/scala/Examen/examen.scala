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

    class Nominalizare(val at: Autor, val ab: Album){
        def getProb(){
            val nominalizat=Chain(at.pupular,ab.calitate,(at: Boolean,ab:Boolean) => {
                if(!at && ab=="mica") Flip(0.013)
                else if(at && ab=="mica") Flip(0.014)
                else if(!at && ab=="medie") Flip(0.016)
                else if(at && ab=="medie") Flip(0.043)
                else if(!at && ab=="mare") Flip(0.047)
                else Flip(0.18)
            }
            )
        }
    }
	
	def main(args: Array[String]) {
		val autori=List(1,2,3,4,5)
        val albume=10

		
	}
}