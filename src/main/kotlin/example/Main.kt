package example

import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType.methodType

class Foo {
    fun foo(): Any {
        println("Hello world")
        return "result"
    }
}

fun main() {
    val fooMethod = Foo::class.java.declaredMethods.single { it.name == "foo" }
    val mh = MethodHandles.lookup()
        .unreflect(fooMethod)
        .asType(methodType(Any::class.java, Any::class.java))
    println("result = ${mh.invokeExact(Foo() as Any) as Any}")
}
