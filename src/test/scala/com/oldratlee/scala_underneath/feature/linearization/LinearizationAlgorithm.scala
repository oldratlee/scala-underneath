package com.oldratlee.scala_underneath.feature.linearization

import scala.collection.immutable.ListSet

/**
 * linearization algorithm of scala inheritance hierarchy.
 */
object LinearizationAlgorithm {

  /**
   * print the Linearization for each classes.
   *
   * inheritance hierarchy of demo classes:
   *
   * <img src="https://cloud.githubusercontent.com/assets/1063891/24009230/49c48722-0aaf-11e7-8493-5b5deca096c5.jpg" />
   */
  def main(args: Array[String]): Unit = {
    for (clz <- List(animal, furry, hasLegs, fourLegged, cat)) {
      println(f"${clz.className.name}%-10s : ${
        linearization(clz).toIndexedSeq.reverse.map(_.className.name).mkString(", ")
      }")
    }

    /*
      Output:

      Animal     : Animal, AnyRef, Any
      Furry      : Furry, Animal, AnyRef, Any
      HasLegs    : HasLegs, Animal, AnyRef, Any
      FourLegged : FourLegged, HasLegs, Animal, AnyRef, Any
      Cat        : Cat, FourLegged, HasLegs, Furry, Animal, AnyRef, Any
    */
  }

  /**
   * == linearization algorithm ==
   *
   * - treat inheritance hierarchy as a tree, child class as tree root.
   *    - not a normal tree, since child can belong to multiply parent.
   *    - in the strict sense, inheritance hierarchy is a [[https://www.wikiwand.com/en/Directed_acyclic_graph DAG]].
   *    - <img src="https://user-images.githubusercontent.com/1063891/59826823-a3c5a900-9369-11e9-89e3-26cb6de7116c.png" width="328" />
   * - travel the tree post-oder(left-right-root), removing redundant(first win).
   *    - <img src="https://user-images.githubusercontent.com/1063891/59827012-2cdce000-936a-11e9-816b-214f503e523b.png" width="358" />
   *
   * == NOTE about [[ListSet]] collection ==
   *
   * - elements is unduplicated.
   * - ordered, aka insert order is kept.
   * - removing redundant(first win). subsequent insert elements is ignored, aka first insert element win.
   */
  def linearization(clazz: Clazz): ListSet[Clazz] = clazz match {
    case Clazz(_, supers) => supers.flatMap(linearization) + clazz
  }

  case class Clazz(className: Symbol, supers: ListSet[Clazz])

  val any = Clazz('Any, ListSet())
  val anyRef = Clazz('AnyRef, ListSet(any))
  val animal = Clazz('Animal, ListSet(anyRef))
  val furry = Clazz('Furry, ListSet(animal))
  val hasLegs = Clazz('HasLegs, ListSet(animal))
  val fourLegged = Clazz('FourLegged, ListSet(hasLegs))
  val cat = Clazz('Cat, ListSet(animal, furry, fourLegged))
}
