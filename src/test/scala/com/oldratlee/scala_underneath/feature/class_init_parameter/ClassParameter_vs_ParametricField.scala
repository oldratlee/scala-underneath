package com.oldratlee.scala_underneath.feature.class_init_parameter

/**
 * The difference of `ClassParameter` and `ParametricField`:
 *
 * TODO add difference list description
 *
 * - ...
 * - ...
 *
 * Check the bytecode for details.
 */
object Main {
  def main(args: Array[String]): Unit = {
    // TODO Demo code
  }
}


///////////////////////////////////////////////////////////////
// ClassParameter
///////////////////////////////////////////////////////////////

/**
 * - Scala compiler generate private class fields for class parameters(`name`, `age`).
 * - Despite with backend class fields, scala does not allow to access these class parameters(aka. class fields) except `this instance`.
 *   - Use `private parametric field` instead. See [[PrivateParametricField.name]]
 */
class ClassParameter(name: String, age: Int) {
  override def toString: String = s"$name $age"

  // Despite with backend class fields,
  // scala does not allow to access these class parameters(aka. class fields) except `this instance`.
  //
  // def compare(other: ClassParameter): Boolean = name.equals(other.name)
  //
  // compilation error:
  //    value name is not a member of com.oldratlee.scala_underneath.feature.class_init_parameter.ClassParameter
  //    [error]   def compare(other: ClassParameter): Boolean = name.equals(other.name)
  //    [error]                                                                   ^
}

/**
 * Scala compiler will NOT generate field for unused class parameter.
 *
 * Check bytecode for details.
 */
class ClassParameterEmptyClass(name: String, age: Int)


///////////////////////////////////////////////////////////////
// ParametricField
///////////////////////////////////////////////////////////////

class ParametricField(val name: String, val age: Int) {
  override def toString: String = s"$name $age"
}

class PrivateParametricField(private val name: String, private val age: Int) {
  override def toString: String = s"$name $age"

  def compare(other: PrivateParametricField): Boolean = name.equals(other.name)
}

class ParametricFieldEmptyClass(val name: String, val age: Int)
