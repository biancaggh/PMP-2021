package curs6

object Ex1 {
  def main(args: Array[String]) {
    
    if (args.length == 0) {
        println("No arguments!")
        return
    } 
    
    // the first argument
    val arg0 = args(0)
    val len0 = arg0.length
    for {
      i <- 0 until len0
    } {
      println(i + ": " + arg0(i))
    }
    
    //all the other arguments
    for { k <- 1 until args.length } {
        val argk = args(k)
        val lenk = argk.length
        println(argk)
        for {
            i <- 0 until lenk
        } {
            println(i + ": " + argk(i))
        }
    }
    
  }
}