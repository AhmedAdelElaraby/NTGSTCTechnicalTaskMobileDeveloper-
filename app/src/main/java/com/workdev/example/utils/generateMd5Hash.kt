package com.workdev.example.utils

import java.math.BigInteger
import java.security.MessageDigest




fun generateMd5Hash(input: String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
}