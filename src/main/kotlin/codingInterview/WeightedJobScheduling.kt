package codingInterview

import java.util.*
import kotlin.collections.ArrayList


class WeightJob(val start: Int = 0, val end: Int = 0, price: Int = 0) {
    // val weightedPrice: Int = (end - start) * price
    val weightedPrice: Int = 1 * price

}

// https://www.geeksforgeeks.org/weighted-job-scheduling-log-n-time/

fun maximum(jobs: ArrayList<WeightJob>): Int {
    jobs.sortBy {
        it.end
    }

    // 1. Set up a list that will hold optimum values for weights from 1 to limit.
    val tArr = IntArray(jobs.size)

    tArr[0] = jobs[0].weightedPrice

    for (i in 1 until jobs.size) {
        tArr[i] = jobs[i].weightedPrice.coerceAtLeast(tArr[i - 1])

        println("${jobs[i].weightedPrice}; ${tArr[i - 1]}")
        println(tArr[i])
        println("---")

        for (j in i - 1 downTo 0) {
            /*
            * if end time of the current job is earlier than the start time
            * (they don't overlap), add the job j[i] value to job i[i] value
            * and store the new value in the weighted array
            * */

            if (jobs[j].end <= jobs[i].start) {
                println("end time j ${jobs[j].end} is <= than start time i ${jobs[i].start}")

                tArr[i] = tArr[i].coerceAtLeast(jobs[i].weightedPrice + tArr[j])

                println("the new maximum sum of weights is ${jobs[i].weightedPrice} + ${tArr[j]} = ${tArr[i]}")

                println("-----------")

                break
            }
        }
    }
    var maxVal = Int.MIN_VALUE
    for (v in tArr) {
        if (maxVal < v) {
            maxVal = v
        }
    }
    return maxVal
}



fun main(args: Array<String>) {

//        val events = ArrayList<WeightJob>()
//        events.add(WeightJob(3, 9 ,2))
//        events.add(WeightJob(5, 15 ,3))
//        events.add(WeightJob(12, 7 ,2))
//        events.add(WeightJob(18, 20 ,1))

    // val evW = maximum(events)
    // println(evW)

    val eventsArr = ArrayList<WeightJob>()
    eventsArr.add(WeightJob(1, 3, 5))
    eventsArr.add(WeightJob(2, 5, 6))
    eventsArr.add(WeightJob(4, 6, 5))
    eventsArr.add(WeightJob(6, 7, 4))
    eventsArr.add(WeightJob(5, 8, 11))
    eventsArr.add(WeightJob(7, 9, 2))
    val mW = maximum(eventsArr)
    println(mW)
}

