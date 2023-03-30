package ru.netology.nmedia
import kotlin.math.floor

object ShotNumber  {
    fun shortNumber(likedCount: Long): String {
        if (likedCount in 0 until 1000) {
            return likedCount.toString()
        } else if (likedCount in 1000 until 10_000 ){
            val count = (likedCount.toDouble())/1000
            return (floor(count*10.0/10.0)).toString() + "K"
        }else if(likedCount in 10_000 until 1_000_000){
            val count2 = likedCount/1000
            return count2.toString()+"K"
        }else if (likedCount in 1_000_000 until 10_000_000){
            val count3: Double = (likedCount.toDouble())/1000_000
            return (floor(count3 * 10) / 10.0).toString()+"M"
        }else{
            val count4 = likedCount/1000_000
            return count4.toString()+"M"
        }
    }
}