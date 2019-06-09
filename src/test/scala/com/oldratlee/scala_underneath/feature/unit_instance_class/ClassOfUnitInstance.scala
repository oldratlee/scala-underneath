package com.oldratlee.scala_underneath.feature.unit_instance_class

import scala.runtime.BoxedUnit

/**
 * class of unit literal instance is not same as class of `BoxedUnit` instance.
 *
 * - class of unit literal `().getClass` is `void`.
 *    - check bytecode for details
 * - `BoxedUnit.UNIT.getClass` is `scala.runtime.BoxedUnit`.
 */
object ClassOfUnitInstance {
  def main(args: Array[String]): Unit = {
    val unitLiteralInstance: Unit = ()
    val boxedUnitInstance = BoxedUnit.UNIT

    println(
      f"""            unit literal instance: ${()}
         |                            class: ${unitLiteralInstance.getClass}
         |   in-place unit literal instance: ${().getClass}
         |   in-place unit literal instance: ${List(unitLiteralInstance).head.getClass}
         |
         |               BoxedUnit instance: $boxedUnitInstance
         |                            class: ${boxedUnitInstance.getClass}
         |in-place BoxedUnit instance class: ${BoxedUnit.UNIT.getClass}
         |
         |                        void type: ${Void.TYPE}"""
        .stripMargin
    )

    // output:
    //
    //            unit literal instance: ()
    //                            class: void
    //
    //               BoxedUnit instance: ()
    //                            class: class scala.runtime.BoxedUnit
    //in-place BoxedUnit instance class: class scala.runtime.BoxedUnit
    //
    //                        void type: void
  }

  def checkBytecodeOfGetClassForUnit_ValueType(): Unit = {
    val unitLiteralClass = ().getClass
    // For `Unit` type (value type), scala compiler compile `getClass` invocation to
    //    Class unitLiteralClass = ScalaRunTime$.MODULE$.anyValClass(BoxedUnit.UNIT, ClassTag$.MODULE$.Unit());
    //                                                   ^^^^^^^^^^^           ^                       ^
    // Rewritten! return primitive `void` class

    val boxedUnitInstanceClass = BoxedUnit.UNIT.getClass
    // For `BoxedUnit` (ref type), scala compiler compile `getClass` invocation to
    //    Class boxedUnitInstanceClass = BoxedUnit.UNIT.getClass();
    // Didn't rewrite.

    val intLiteralClass = 1.getClass
    // For `Int` type (value type), scala compiler compile `getClass` invocation to
    //    Class intLiteralClass = ScalaRunTime$.MODULE$.anyValClass(BoxesRunTime.boxToInteger(1), ClassTag$.MODULE$.Int());
    //                                                  ^^^^^^^^^^^              ^                                  ^
    // Rewritten! return primitive `int` class

    println(s"$unitLiteralClass $boxedUnitInstanceClass $intLiteralClass")

    // AutoBoxing / Unboxing
    val autoUnboxing: Unit = BoxedUnit.UNIT
    // Compile to
    //     BoxedUnit autoUnboxing = BoxedUnit.UNIT;
    println(s"$autoUnboxing")

    // NOTE: Boxing Unit is not Auto!
    //       In scala, there is no excuse to use BoxedUnit explicitly,
    //       compiler do all the work for us smartly.
    val boxing: BoxedUnit = ().asInstanceOf[BoxedUnit]
    // Compile to
    //    BoxedUnit boxing = BoxedUnit.UNIT;
    println(s"$boxing")
  }
}
