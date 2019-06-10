package com.oldratlee.scala_underneath.feature.bean_property

import scala.beans.{BeanProperty, BooleanBeanProperty}

object BeanPropertyUsage {
  def main(args: Array[String]): Unit = {
    val jerry = Employee("jerry", 42, true, "programmer")

    println(
      // access fields by getter method
      s"""name: ${jerry.getName}
         |age: ${jerry.getAge}
         |sex: ${jerry.isSex}
         |title: ${jerry.getTitle}"""
        .stripMargin
    )
  }
}

/**
 * - [[BeanProperty]] annotation can cooperate with `trait`.
 * - [[BooleanBeanProperty]]: the generated Bean getter will be named `isFieldName` instead of `getFieldName`
 */
trait PersonState {
  @BeanProperty val name: String
  var age: Int
  @BooleanBeanProperty val sex: Boolean
}

// PersonState compile to
//
// public interface PersonState {
//   public abstract String name();
//
//   public abstract int age();
//
//   public abstract void age_$eq(int i);
//
//   public abstract boolean sex();
//
//   public abstract String getName(); // *generated* getter method via @BeanProperty
//
//   public abstract boolean isSex(); // *generated* getter method via @BooleanBeanProperty
// }

/**
 * - [[BeanProperty]] annotation can cooperate with
 *    - `case class`
 *    - var field: [[age]]
 */
case class Person(@BeanProperty name: String,
                  @BeanProperty var age: Int,
                  @BooleanBeanProperty sex: Boolean)
  extends PersonState

trait EmployeeState {
  @BeanProperty val title: String
}

// EmployeeState compile to
//
// public interface EmployeeState {
//   public abstract String title();
//
//   public abstract String getTitle(); // *generated* getter method via @BeanProperty
// }

case class Employee(@BeanProperty name: String,
                    @BeanProperty var age: Int,
                    @BooleanBeanProperty sex: Boolean,
                    @BeanProperty title: String)
  extends PersonState with EmployeeState
