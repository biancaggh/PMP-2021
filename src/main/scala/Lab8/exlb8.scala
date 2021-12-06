package Lab8

import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.language._
import com.cra.figaro.library.compound.{CPD, OneOf, RichCPD}


object Ex3 {
    def main(args: Array[String]) {
        val capitole= 10
        val studiu: Array[Element[Symbol]] = Array.fill(capitole)(Constant('putin))
        val scor: Array[Element[Int]] = Array.fill(capitole)(Constant(1))
        val aTrecut: Array[Element[Symbol]] = Array.fill(capitole)(Constant('picat))

        studiu(0) = Select (0.3 -> 'putin, 0.4 -> 'ok, 0.3-> 'tot)

        for {capitol <- 1 until capitole}{
            studiu(capitol) = CPD(studiu(capitol-1),
            'tot -> Select(0.1 -> 'putin, 0.2 -> 'ok, 0.7 -> 'tot),
            'ok -> Select(0.2 -> 'putin, 0.4 -> 'ok, 0.4 -> 'tot),
            'putin -> Select(0.6 -> 'putin, 0.3 -> 'ok, 0.1 -> 'tot))
        }

        for { capitol <- 0 until capitole }
        {
            scor(capitol) = CPD(studiu(capitol),
            'tot -> Select(0.1 -> 1, 0.1 -> 2, 0.3 -> 3, 0.3 -> 4, 0.5 -> 5, 0.6 -> 6, 0.5 -> 7, 0.3 -> 8, 0.1 -> 9, 0.25 -> 10),
            'ok -> Select(0.1 -> 1, 0.3 -> 2, 0.1 -> 3, 0.5 -> 4, 0.3 -> 5, 0.4 -> 6, 0.15 -> 7, 0.1 -> 8, 0.1 -> 9, 0.15 -> 10),
            'putin -> Select(0.4 -> 1, 0.6 -> 2, 0.4 -> 3, 0.1 -> 4, 0.1 -> 5,0.1 -> 6, 0.1 -> 7, 0.1 -> 8, 0.1 -> 9, 0.05 -> 10))
        }

        for { capitol <- 0 until capitole }
        {
            aTrecut(capitol) = RichCPD(scor(capitol),
            OneOf(1, 2, 3, 4) -> Constant('picat),
            OneOf(5, 6, 7, 8) -> Constant('trecut),
            OneOf(9, 10) -> Constant('succes))
        }

        val var1 = VariableElimination.probability(aTrecut(9), 'succes)
        println("Probabilitatea de a trece ultimul test: " + var1)

        aTrecut(0).observe('trecut)
        val var2 = VariableElimination.probability(aTrecut(9), 'succes)
        println("Probabilitatea de a trece ultimul test daca l-a trecut pe primul: "+ var2)

        aTrecut(1).observe('trecut)
        val var3 = VariableElimination.probability(aTrecut(9), 'succes)
        println("Probabilitatea de a trece ultimul test daca le-a trecut pe primele doua: " + var3)

        aTrecut(2).observe('trecut)
        val var4 = VariableElimination.probability(aTrecut(9), 'succes)
        println("Probabilitatea de a trece ultimul test daca le-a trecut pe primele trei: " + var4)
    }
}