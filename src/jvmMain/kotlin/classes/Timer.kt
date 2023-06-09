package classes

import EXTRA_TIME
import HALF_TIME
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.*
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class Timer {

    var formattedTime by mutableStateOf("00:00")
    var formattedTimeAdditional by mutableStateOf("00:00")
    var isActive by mutableStateOf(false)
    var isActiveAdditional by mutableStateOf(false)
    var isEndTime by mutableStateOf(false)
    var isEndTimeAdditional by mutableStateOf(false)
    var isDisable by mutableStateOf(false)
    var showAdditional by mutableStateOf(false)

    var HalfGame by mutableStateOf(-1)

    private var coroutineScope = CoroutineScope(Dispatchers.IO)
    private var coroutineScopeAdditional = CoroutineScope(Dispatchers.IO)

    private var timeMilis = 0L
    private var timeMilisAdditional = 0L
    private var lastTimestamp = 0L
    private var lastTimestampAdditional = 0L

    private val oneMinute = 60000L

    private var maxHalfTime = 0L

    private var halfTime = 0L
    private var extraTime = 0L
    private var additionalTime = 0L

    private var indexSelected = 0

//    init {
////        this@Timer.isActive = false
////        this@Timer.isActiveAdditional = false
////        this@Timer.isEndTime = false
////        this@Timer.isDisable = false
////        this@Timer.showAdditional = false
////        this@Timer.HalfGame = -1
//        SetTimer()
//        ChoseTime()
//    }

    fun GetMilis(): Long{
        return this@Timer.timeMilis
    }

    fun GetHalfTime(): Long{
        return this@Timer.halfTime
    }

//    fun CheckMaxMilis(){
//        if(this@Timer.maxHalfTime < 60000L){
//            SetTimer()
//        }
//    }

    fun GetMaxMilis(): Long{
        return this@Timer.maxHalfTime
    }
    fun Start(){
        if(this@Timer.isActive) return

        coroutineScope.launch {
            lastTimestamp = System.currentTimeMillis()
            this@Timer.isActive = true
            while (this@Timer.isActive){
                if(timeMilis <= maxHalfTime){
                    delay(10L)
                    timeMilis += System.currentTimeMillis() - lastTimestamp
                    lastTimestamp = System.currentTimeMillis()
                    formattedTime = FormatTime(timeMilis = timeMilis)
                }
                else{
//                    Pause()
                    this@Timer.isEndTime = true
                    StartAdditional()
                    this@Timer.isActive = false
                }
            }
        }
    }

    fun Pause(){
        this@Timer.isActive = false
    }

    fun PauseAdditional(){
        this@Timer.isActiveAdditional = false
    }

    fun Reset(){
        coroutineScope.cancel()
        coroutineScope = CoroutineScope(Dispatchers.IO)
        coroutineScopeAdditional.cancel()
        coroutineScopeAdditional = CoroutineScope(Dispatchers.IO)
        ChoseTime(index = indexSelected)
        lastTimestamp = 0L
        lastTimestampAdditional = 0L
        this@Timer.timeMilisAdditional = 0L
        formattedTime = FormatTime(timeMilis = timeMilis)
        formattedTimeAdditional = FormatTimeAdditional(timeMilis = timeMilisAdditional)
        this@Timer.isActive = false
        this@Timer.isActiveAdditional = false
        this@Timer.isEndTime = false
    }

    fun StartAdditional(){
        if(this@Timer.isActiveAdditional) return

        coroutineScopeAdditional.launch {
            lastTimestampAdditional = System.currentTimeMillis()
            this@Timer.isActiveAdditional = true
            while (this@Timer.isActiveAdditional){

                delay(10L)
                timeMilisAdditional += System.currentTimeMillis() - lastTimestampAdditional
                lastTimestampAdditional = System.currentTimeMillis()
                formattedTimeAdditional = FormatTimeAdditional(timeMilis = timeMilisAdditional)
            }
        }
    }

    private fun FormatTime(timeMilis: Long): String{
        val localDateTime = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(timeMilis),
            ZoneId.systemDefault()
        )
        val formatter= DateTimeFormatter.ofPattern(
            "mm:ss",
            Locale.getDefault()
        )
        return localDateTime.format(formatter)
    }

    private fun FormatTimeAdditional(timeMilis: Long): String{
        val localDateTime = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(timeMilis),
            ZoneId.systemDefault()
        )
        val formatter= DateTimeFormatter.ofPattern(
            "mm:ss",
            Locale.getDefault()
        )
        return localDateTime.format(formatter)
    }

    fun ChoseTime(index: Int = HalfGame){
        indexSelected = index
        when(index){
            0 -> {
                this@Timer.timeMilis = 0L
                this@Timer.maxHalfTime = this@Timer.halfTime
//                println("Here ${this@Timer.maxHalfTime}")
            }
            1 -> {
                this@Timer.maxHalfTime = 0L
                this@Timer.timeMilis = this@Timer.halfTime
                this@Timer.maxHalfTime = this@Timer.halfTime * 2
            }
            2 -> {
                this@Timer.maxHalfTime = 0L
                this@Timer.timeMilis = this@Timer.halfTime * 2
                this@Timer.maxHalfTime = (this@Timer.halfTime * 2) + this@Timer.extraTime
            }
            3 -> {
                this@Timer.maxHalfTime = 0L
                this@Timer.timeMilis = (this@Timer.halfTime * 2) + this@Timer.extraTime
                this@Timer.maxHalfTime = (this@Timer.halfTime * 2) + (this@Timer.extraTime * 2)
            }
        }
        formattedTime = FormatTime(timeMilis = timeMilis)
    }

    fun SetTimer(
//        halfTime: Int,
//        extraTime: Int
    ){
        this@Timer.halfTime = HALF_TIME * oneMinute
        this@Timer.extraTime = EXTRA_TIME * oneMinute
        this@Timer.isDisable = true
    }

    fun SetInjury(
        injuryTime: Int
    ){
        this@Timer.additionalTime = injuryTime * oneMinute
    }

    fun ShowInjury(): Int{
        return (this@Timer.additionalTime / oneMinute).toInt()
    }
}