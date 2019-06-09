package com.oldratlee.scala_underneath.feature

/**
 * Scala compiler will not check `exhaustive match`
 * for [[scala.collection.Seq]]!
 */
object NoExhaustiveMatchCheckForSeq {
  def main(args: Array[String]): Unit = {

    ///////////////////////////////////////////////////////////////
    // not exhaustive match, no `Nil` case.
    ///////////////////////////////////////////////////////////////

    // For Seq, scala compiler didn't check `exhaustive match`,
    // didn't emit `warning: match may not be exhaustive`.
    println(
      Seq(1, 2, 3) match {
        case head +: tail => s"$head +: $tail"
      }
    )

    // For List, scala compiler checked `exhaustive match`, emit compilation warning:
    //    warning: match may not be exhaustive.
    //    It would fail on the following input: Nil
    //          List(1, 2, 3) match {
    //          ^
    //    one warning found
    println(
      List(1, 2, 3) match {
        case head :: tail => s"$head :: $tail"
      }
    )

    println("\n======================================\n")

    ///////////////////////////////////////////////////////////////
    // exhaustive match for Seq/List
    ///////////////////////////////////////////////////////////////

    println(
      Seq(1, 2, 3) match {
        case head +: tail => s"$head +: $tail"
        case Nil => "Nil"
      }
    )

    println(
      List(1, 2, 3) match {
        case head :: tail => s"$head :: $tail"
        case Nil => "Nil"
      }
    )
  }
}
