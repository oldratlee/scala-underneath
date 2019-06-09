package com.oldratlee.scala_underneath.feature.local_method__how_implemented_bytecode

/**
 * There is no `local methods` for `JMV`, so how scala compile `local method` to `JVM bytecode`?
 *
 * Scala compiler
 * - convert `local methods` to `private static final` methods.
 * - the converted method name is `local method` name with `$N`.
 *
 * Check the bytecode for details.
 */
object Main {
  def main(args: Array[String]): Unit = {
    val foo = new ClassHasLocalMethod

    println(
      s"${foo.method1()}\n${foo.method2("hello")}"
    )
  }
}

class ClassHasLocalMethod {
  def method1(): String = {
    /**
     * compile to
     * {{{
     * private static final java.lang.String helper$1()
     * }}}
     */
    def helper() = "hello"

    /**
     * compile to
     * {{{
     * private static final java.lang.String anotherHelper$1()
     * }}}
     */
    def anotherHelper() = "world"

    helper() + " " + anotherHelper()
  }

  def method2(s: String): String = {
    /**
     * compile to
     * {{{
     * private static final java.lang.String helper$2(java.lang.String)
     * }}}
     *
     * note: convert captured parameters to plain method parameters.
     */
    def helper() = s + " world"

    helper()
  }
}
