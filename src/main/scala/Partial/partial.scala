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
        //4
        //Observam ca rezultatul primei probabilitati este mai mare decat al doilea. Facand un calcul simplu in ceea ce priveste sumele celor 2 zaruri, 
        //vom observa ca (dupa cum ne spune si cerinta) avem valori intre  2 si 12. Important este cate valori avem de fiecare
        //vom avea o valoare de 2, doua valori de 3, trei valori de 4, patru valori de 5, cinci valori de 6, sase valori de 7, cinci valori de 8, patru valori de 9, trei valori de 10,
        //2 valori de 11 si o valoare de 12
        // (1,1) => 2, (1,2), (2,1) => 3, (1,3),(3,1),(2,2) => 4, (1,4),(4,1),(2,3),(3,2) => 5 .....
        //daca adunam numarul de valori necesare pentru ca primul jucator sa castige vom vedea ca acesta are 6+2 sanse sa castige. Asta inseamna ca avem o probabilitate de 8/36
        //daca adunam pentru cel de al doilea, vom observa ca avem 1+2+1 sanse de castig. Asta inseamna 4/36
        //deci in urma observatiilor facute ajungem la rezultatul oferit (ca j1 are sanse mai mari de castig)
	}
}