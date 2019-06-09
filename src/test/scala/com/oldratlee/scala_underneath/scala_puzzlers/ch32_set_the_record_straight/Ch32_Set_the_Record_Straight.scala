package com.oldratlee.scala_underneath.scala_puzzlers.ch32_set_the_record_straight

/**
 * Scala
 */
object Set_the_Record_Straight {
  def main(args: Array[String]): Unit = {
    val numbers = List("1", "2").toSet() + "3"
    println(numbers)
  }

  // which is right answer?
  //
  // 1. Set(1, 2, 3)
  // 2. Compilation Failure
  // 3. false3
  // 4. 123
}

object Explaination {
  def main(args: Array[String]): Unit = {
    // 0. toSet method signature:
    //    def toSet[B >: A]: Set[B]

    // 1. the method toSet itself takes no parameters.

    val set1: Set[String] = List("1", "2").toSet
    println(set1)
    // output: Set(1, 2)

    // 2.1 the element type of the resulting set can be different than that of the original list.
    //    More specially, the type of the set elements can be a supertype of the list element type.
    //
    // You could provide the desired element type explicitly.

    List("1", "2").toSet[AnyRef]
    // res1: scala.collection.immutable.Set[AnyRef] = Set(1, 2)

    // 2.2 In this case, however, the additional set of parentheses forces the compiler to reason differently.
    //     Because toSet cannot be invoked with parentheses,
    //     the compiler can only interpret the parentheses
    //     as an attempt to invoke apply on the result of calling toSet.

    List("1", "2").toSet()
    // warning: there was one deprecation warning (since 2.11.0); for details, enable `:setting -deprecation' or `:replay -deprecation'
    // res0: Boolean = false

    // is same as to

    List("1", "2").toSet.apply() // apply() == Set#contains()
    // warning: there was one deprecation warning (since 2.11.0); for details, enable `:setting -deprecation' or `:replay -deprecation'
    // res1: Boolean = false

    // 2.3 But what is the element being tested for in this case?
    //     Method apply definitely expects an argument (its parameter elem does not have a default value)
    //     and it looks as if none is being provided.
    //
    //     Here a compiler feature called **argument adaptation** comes into play —
    //     the compiler adapts the argument list, inserting the Unit value, (),
    //     to match the single parameter specified by the method declaration.
    //
    //     You can verify this by compiling the same line of code with the -Ywarn-adapted-args option:
    //
    //     > scala> List("1", "2").toSet.apply()
    //     <console>:8: warning: Adapting argument list by inserting ():
    //     this is unlikely to be what you want.
    //            signature: GenSetLike.apply(elem: A): Boolean
    //      given arguments: <none>
    //     after adaptation: GenSetLike((): Unit) List("1", "2").toSet.apply()

    // so `List("1", "2").toSet.apply()` is same to `List("1", "2").toSet.apply(())`
    println(
      List("1", "2").toSet.apply(())
    )
    // output: false

    // At this point, the compiler must determine whether the set returned by toSet contains the Unit value.
    //    How does that even compile, since the Unit value is clearly not a String?
    //    Recall that the compiler can infer the element type of the resulting set to be any supertype of String.
    //    As illustrated in the Scala type hierarchy,
    //    the common supertype of Unit and String is Any.
    //
    //    The resulting expression is:
    List("1", "2").toSet[Any].apply(())
    // output: false


    // 3. Thus the actual expression being evaluated is:
    false + "3"
    // output: false3
  }
}

/**
 * = suggestion in book =
 *
 * - Include empty parentheses in method invocations only for side-effecting methods.
 * - Beware of unintended *type widening*
 * caused by methods on collections
 * that allow the element type of a returned collection
 * to be wider than the original element type.
 *
 * = my personal suggestion =
 *
 * - Use `-Ywarn-adapted-args` compiler option?
 */
object SuggestionAndBasePractice {
}

