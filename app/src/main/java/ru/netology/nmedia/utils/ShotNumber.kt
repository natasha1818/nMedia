package ru.netology.nmedia

import kotlin.math.floor

object ShotNumber {
    fun shortNumber(likedCount: Long): String {
        when (likedCount) {
            in 0..999 -> return likedCount.toString()
            in 1000..9_999 -> return (floor(((likedCount.toDouble()) / 1000) * 10.0 / 10.0)).toString() + "K"
            in 10_000..999_999 -> return (likedCount / 1000).toString() + "K"
            in 1_000_000..9_999_999 -> return (floor(((likedCount.toDouble()) / 1000_000) * 10) / 10.0).toString() + "M"
            else -> return (likedCount / 1000_000).toString() + "M"

        }

    }
}