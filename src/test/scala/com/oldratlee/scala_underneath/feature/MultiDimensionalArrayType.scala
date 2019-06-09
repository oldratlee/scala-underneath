package com.oldratlee.scala_underneath.feature

/**
 * Type/class of `Array(Array(1, 2), Array(3, 4)): Array[Array[Int]]`
 * is same to `Array.ofDim[Int](2, 2): Array[Array[Int]]`.
 *
 * In java, the type is `int[][]`.
 */
object MultiDimensionalArrayType {
  private val aa: Array[Array[Int]] = Array(Array(10, 11), Array(20, 21))

  private val a2: Array[Array[Int]] = Array.ofDim[Int](11, 22)
  private val a3: Array[Array[Array[Int]]] = Array.ofDim[Int](11, 22, 33)

  def main(args: Array[String]): Unit = {
    println(
      f"""aa: ${aa.getClass.getName}%-5s ${aa.getClass.getCanonicalName}
         |a2: ${a2.getClass.getName}%-5s ${a2.getClass.getCanonicalName}
         |a3: ${a3.getClass.getName}%-5s ${a3.getClass.getCanonicalName}"""
        .stripMargin

      // output:
      // aa: [[I   int[][]
      // a2: [[I   int[][]
      // a3: [[[I  int[][][]
    )
  }
}
