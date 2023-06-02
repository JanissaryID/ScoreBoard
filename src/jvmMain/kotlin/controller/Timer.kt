package controller

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
    var isActive by mutableStateOf(false)
    var isEndTime by mutableStateOf(false)
    var isHalfGame by mutableStateOf(false)
    var isDisable by mutableStateOf(false)

    private var coroutineScope = CoroutineScope(Dispatchers.IO)

    private var timeMilis = 0L
    private var lastTimestamp = 0L
    private var timeMilisTemp = 0L

    private val oneMinute = 60000L

    private var maxHalfTime = 0L
    private var maxExtraTime = 0L

    var statTimeNow by mutableStateOf(0)

    var extraStat by mutableStateOf(0)
    var halfStat by mutableStateOf(0)

    fun Start(){
        if(this@Timer.isActive) return

        coroutineScope.launch {
            lastTimestamp = System.currentTimeMillis()
            this@Timer.isActive = true
            while (this@Timer.isActive){
                if(!this@Timer.isHalfGame){
                    if(timeMilis <= maxHalfTime){
                        delay(10L)
                        timeMilis += System.currentTimeMillis() - lastTimestamp
                        lastTimestamp = System.currentTimeMillis()
                        formattedTime = FormatTime(timeMilis = timeMilis)
                    }
                    else{
                        Pause()
                        this@Timer.isEndTime = true
                        halfStat = 1
                        timeMilisTemp = timeMilis
                        timeMilis = timeMilisTemp
//                        println("${isHalfGame} -- ${isEndTime} -- ${extraStat}")
                        if(!isHalfGame && isEndTime && (extraStat == 1)){
                            extraStat = 1
                        }
                    }
                }
                else{
                    if(timeMilis <= (maxHalfTime)){
                        delay(10L)
                        timeMilis += System.currentTimeMillis() - lastTimestamp
                        lastTimestamp = System.currentTimeMillis()
                        formattedTime = FormatTime(timeMilis = timeMilis)
                    }
                    else{
                        Pause()
                        this@Timer.isEndTime = true
                        halfStat = 0
                        timeMilisTemp = timeMilis
                        timeMilis = timeMilisTemp
                        this@Timer.statTimeNow = 1
                    }
                }
            }
        }
    }

    fun Pause(){
        this@Timer.isActive = false
    }

    fun Reset(){
        coroutineScope.cancel()
        coroutineScope = CoroutineScope(Dispatchers.IO)
        if(!this@Timer.isHalfGame){
            FirstHalfGame()
        }
        else{
            SecondHalfGame()
        }
        lastTimestamp = 0L
        formattedTime = FormatTime(timeMilis = timeMilis)
        this@Timer.isActive = false
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

    fun FirstHalfGame(){
        timeMilis = 0L
        maxHalfTime = 10000L
        timeMilisTemp = 0L
        this@Timer.statTimeNow = -1
        extraStat = 0
        halfStat = 0
        formattedTime = FormatTime(timeMilis = timeMilis)
    }

    fun SecondHalfGame(){
        timeMilis = timeMilisTemp
        maxHalfTime = maxHalfTime * 2
        timeMilisTemp = 0L
        formattedTime = FormatTime(timeMilis = timeMilis)
    }

    fun FirstExtraGame(){
        if (extraStat == 0){
            maxHalfTime = timeMilisTemp
            maxHalfTime += maxExtraTime
            formattedTime = FormatTime(timeMilis = timeMilis)
            extraStat = 1
            timeMilisTemp = 0L
        }
//        println("Extra Game : $maxHalfTime")
    }

    fun SecondExtraGame(){
        if (extraStat == 1){
            maxHalfTime = timeMilisTemp
            maxHalfTime += maxExtraTime
            formattedTime = FormatTime(timeMilis = timeMilis)
            extraStat = 2
            timeMilisTemp = 0L
        }
//        println("Extra Game : $maxHalfTime")
    }

    fun SetTimer(
        halfTime: Int,
        extraTime: Int
    ){
        this@Timer.maxHalfTime = halfTime * oneMinute
        this@Timer.maxExtraTime = extraTime * oneMinute
        this@Timer.isDisable = true
    }
}