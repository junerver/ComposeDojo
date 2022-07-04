package xyz.junerver.composedojo

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)

        val (a, b) = returnPair()
        val count = add(1, 23, 4, 5, 6)

        val p = Person()
        val p2 = PersonD("Junerver1", 128,12)

        val (na,_,cc) = p2
        println("$na  $cc")

        val p111 = PersonD("Junerver1", 128,12)
        val p222 = PersonD("Junerver1", 128,12)
        println(p111 == p222)

    }

    fun returnPair(): Pair<Int, String> {
        return Pair(1, "2")
    }

    fun add(vararg numbers: Int): Int {
        var sum = 0
        for (number in numbers) {
            sum += number
        }
        return sum
    }

    class Person() {
        var name: String=""
        var age: Int=0
        var sex:Int=0

        operator fun component1() = name
        operator fun component2() = age
        operator fun component3() = sex
    }

     data class PersonD(val name: String, val age: Int, val sex:Int)
}