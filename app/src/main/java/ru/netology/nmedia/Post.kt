package ru.netology.nmedia

import android.icu.text.CompactDecimalFormat
import android.icu.text.NumberFormat
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.abs
import kotlin.math.ln
import kotlin.math.pow

data class Post (
    val idPost: Long,
    val author: String,
    val dataPost: String,
    val contentPost: String,
    var likesByMe: Boolean = false,
    var likedCount: Long = 0,
    var shareCount: Long = 0
     ) {}

//    fun compactNumber(number: Long): String {
//        val array = arrayOf(' ', 'k', 'M')
//        val value = Math.floor(Math.log10(number.toDouble())).toInt()
//        val  base = value / 3
//        if (value >= 3 && base < array.size) {
//            return DecimalFormat("#0.0").format(number/ Math.pow(10.0, (base * 3).toDouble())) + array[base]
//        } else {
//            return DecimalFormat("#,##0").format(number)
//        }
//    }

    fun shortNumber(likedCount: Long): String {
        if (likedCount in 0 until 1000) {
            return likedCount.toString()
        } else if (likedCount in 1000 until 10_000 ){
            var count = (likedCount.toDouble())/1000
           return String.format("%.${1}f" , count)+ "K"
        }else if(likedCount in 10_000 until 1_000_000){
            var count2 = likedCount/1000
            return count2.toString()+"K"
        }else if (likedCount in 1_000_000 until 10_000_000){
            var count3: Double = (likedCount.toDouble())/1000_000
            return String.format("%.1f",count3)+"M"
        }else{
            var count4 = likedCount/1000_000
            return count4.toString()+"M"
        }
        }




