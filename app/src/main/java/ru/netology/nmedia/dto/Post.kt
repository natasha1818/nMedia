package ru.netology.nmedia.dto

data class Post (
    val idPost: Long,
    val author: String,
    val dataPost: String,
    val contentPost: String,
    var likesByMe: Boolean = false,
    var likedCount: Long = 0,
    var shareCount: Long = 0,
    var viewingCount:Long = 0
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






