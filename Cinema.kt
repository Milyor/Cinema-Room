package cinema

import kotlin.math.ceil

fun main() {
    val lists = Lists()
    lists.rowsAndSeats()
    println()
    do {
        println(
            "1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit"
        )
        var menu = readln().toInt()
        when (menu) {
            1 -> lists.printCinema()
            2 -> lists.buyTicket()
            3 -> lists.statistics()
            0 -> return
            else -> println("Wrong input!")
        }
    } while (menu != 0)
}

class Lists {
    private var rows: Int = 0
    private var numSeats: Int = 0
    private var rowNum = 0
    private var seatNum = 0
    private var ticketSold = 0
    private var maxTicket = 0
    private var minTicket = 0

    private val inputList: MutableList<MutableList<Any>> = mutableListOf(mutableListOf(" "))

    fun rowsAndSeats() {
        println("Enter the number of rows:")
        rows = readln().toInt()
        println("Enter the number of seats in each row:")
        numSeats = readln().toInt()

        for (k in 1..numSeats){
            inputList[0].add(k)
        }
        for (i in 1..rows) {
            val rowList = mutableListOf<Any>()
            rowList.add(i)
            for (j in 1..numSeats){
                rowList.add('S')
            }
            inputList.add(rowList)
        }
    }


    private fun price(){
        if (rows * numSeats < 60) {
            maxTicket+= 10
            println("Ticket price: $10")
        } else if (rowNum  < ceil(rows / 2.0).toInt() ) {
            maxTicket+= 10
            println("Ticket price: $10")
        } else {
            minTicket+= 8
            println("Ticket price: $8")
        }
    }
    fun printCinema() {
        println()
        print("Cinema:\n")
        println(inputList[0].joinToString(" "))
        for (i in 1 until inputList.size){
            println(inputList[i].joinToString(" "))
        }
        println()
    }
    fun buyTicket (){
        var validInput = false
        do {
            println()
            println("Enter a row number:")
            rowNum = readln().toInt()
            println("Enter a seat number in that row:")
            seatNum = readln().toInt()
            try{
                inputList[rowNum][seatNum]
            } catch (e: IndexOutOfBoundsException){
                rowNum = 0
                seatNum = 0
                println("Wrong input!")
                continue
            }
            if (inputList[rowNum][seatNum] == 'B'){
                println("That ticket has already been purchased!")
            } else {
            inputList[rowNum][seatNum] = 'B'
            ticketSold++
            price()
                validInput = true
            }

        } while (!validInput)


    }
    fun statistics(){
        val totalIncome = if(rows * numSeats > 60) {
            ((rows / 2 * numSeats) * 10 ) + (((rows + 1) / 2 * numSeats) * 8 )

        } else {
            (numSeats * rows) * 10
        }
        val percentage = (ticketSold / (rows * numSeats).toDouble()) * 100
        val formatPercentage = "%.2f".format(percentage)
        val currentIncome = maxTicket + minTicket
        println("Number of purchased tickets: $ticketSold\n" +
                "Percentage: $formatPercentage%\n" +
                "Current income: $$currentIncome\n" +
                "Total income: $$totalIncome\n")
    }

}

