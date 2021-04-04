import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    calculator()

    var loop = 0
    while (loop == 0){
        println("\nChcesz policzyć jeszcze raz? (tak/nie)")
        var x = readLine()
        if(x == "tak"){
            calculator()
        }
    }
}


fun calculator(){
    println("\n~~~~~~~~~~~~~~~~~~~~")
    println("Zapisz działanie:")
    var equation: String = readLine()!!
    println("\nRozwiązanie:")

    fun getNumberBefore(sign:Char):Double{
        var index:Int = equation.indexOf(sign)
        var lenght: Int = 0
        loopbefore@ for (i in index.downTo(1)) {
            var char: String = equation.substring(i - 1, i)
            when (char) {
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "." -> lenght++
                else -> break@loopbefore
            }
        }
        var number = equation.substring(index - lenght, index)

        var old_number = number + sign.toString()
        var new_number = number.toDouble().toString() + sign.toString()
        equation = equation.replace(old_number, new_number)

        return number.toDouble()
    }

    fun getNumberAfter(sign:Char):Double{
        var index:Int = equation.indexOf(sign)
        var lenght: Int = 0
        loopafter@ for (i in index+1..equation.lastIndex) {
            var char: String = equation.substring(i, i+1)
            when (char) {
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "." -> lenght++
                else -> break@loopafter
            }
        }
        var number = equation.substring(index+1, index + lenght+1)

        var old_number = sign.toString() + number
        var new_number = sign.toString() + number.toDouble().toString()
        equation = equation.replace(old_number, new_number)

        return number.toDouble()
    }

    fun getCount(sign:Char, position:String = "both"){
        while(equation.contains(sign.toString()) == true) {
            var number_before = 0.0
            var number_after = 0.0

            var old_string:String
            when(position){
                "both" ->{
                    number_before = getNumberBefore(sign)
                    number_after = getNumberAfter(sign)
                    old_string = number_before.toString() + sign.toString() + number_after.toString()
                }
                "after" ->{
                    number_after = getNumberAfter(sign)
                    old_string = sign.toString() + number_after.toString()
                }
                "before" ->{
                    number_before = getNumberBefore(sign)
                    old_string = number_before.toString() + sign.toString()
                }
                else ->{
                    number_before = getNumberBefore(sign)
                    number_after = getNumberAfter(sign)
                    old_string = number_before.toString() + sign.toString() + number_after.toString()
                }
            }

            var new_string: String
            when (sign) {
                '%' -> new_string = (number_before % number_after).toString()
                '^' -> new_string = (number_before.pow(number_after)).toString()
                's' -> new_string = (sqrt(number_after)).toString()
                '*' -> new_string = (number_before * number_after).toString()
                '/' -> new_string = (number_before / number_after).toString()
                '+' -> new_string = (number_before + number_after).toString()
                '-' -> new_string = (number_before - number_after).toString()
                else -> new_string = ""
            }
            equation = equation.replace(old_string, new_string)
            println(equation)
        }
    }

    getCount('^')
    getCount('s', "after")
    getCount('%')
    getCount('*')
    getCount('/')

    var index1:Int = equation.indexOf('+')
    var index2:Int = equation.indexOf('-')
    if (index1 < index2){
        getCount('+')
        getCount('-')
    }
    else{
        getCount('-')
        getCount('+')
    }



    println("~~~~~~~~~~~~~~~~~~~~")
}