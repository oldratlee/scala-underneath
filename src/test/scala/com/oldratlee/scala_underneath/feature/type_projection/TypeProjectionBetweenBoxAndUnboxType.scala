package com.oldratlee.scala_underneath.feature.type_projection

/**
 * Type projection between box and unbox type.
 *
 * TODO
 *
 * How many box and unbox types do scala type system have?
 *
 * When and how is conversion between box and unbox type?
 */
object TypeProjectionBetweenBoxAndUnboxType {
  def main(args: Array[String]): Unit = {
    val nullInteger: Integer = null

    ///////////////////////////////////////////////////////////////
    // project Integer to Int(Scala)
    ///////////////////////////////////////////////////////////////

    println(nullInteger.asInstanceOf[Int] == null)
    // compile to: (omitted the println invocation)
    //    BoxesRunTime.boxToBoolean( nullInteger == null )
    //
    // output: true
    //
    // NOTE:
    //    Since the only parameter type of `println` is `Any`(compiled to `Object` type in JVM bytecode)
    //    `BoxesRunTime.boxToBoolean` is needed so as to box value type(`boolean`) to ref type(`Object`).

    println(nullInteger.asInstanceOf[Int] == 0)
    // compile to: (omitted the println invocation)
    //    BoxesRunTime.boxToBoolean( BoxesRunTime.unboxToInt(nullInteger) == 0 )
    //                                            ^^^^^^^^^^
    // output: true

    // !!Attention!!
    //
    // Both
    //    nullInteger.asInstanceOf[Int] == null
    // and
    //    nullInteger.asInstanceOf[Int] == 0
    // are `true`!

    ///////////////////////////////////////////////////////////////
    // project Integer to Int(Scala), and assign to a Int val
    ///////////////////////////////////////////////////////////////

    val scalaInt = nullInteger.asInstanceOf[Int]
    // compile to:
    //    int scalaInt = BoxesRunTime.unboxToInt(nullInteger);
    //                                ^^^^^^^^^^
    // result: 0

    println(scalaInt == null)
    // compile to: (omitted the println invocation)
    //    BoxesRunTime.boxToBoolean( BoxesRunTime.boxToInteger(scalaInt) == null )
    //
    // output: false

    println(scalaInt == 0)
    // compile to: (omitted the println invocation)
    //    BoxesRunTime.boxToBoolean( scalaInt == 0 )
    //
    // output: true
  }
}

