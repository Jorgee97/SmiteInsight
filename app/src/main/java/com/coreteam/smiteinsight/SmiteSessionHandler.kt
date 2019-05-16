package com.coreteam.smiteinsight

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.SimpleDateFormat
import java.util.*

class SmiteSessionHandler {
    private val devId = "3170"
    private val authKey = "F0F52EA4CB7F4ACF8E6BFB1926E85CDB"
    private val format = SimpleDateFormat("yyyyMMddHHmmss", Locale.US)

    fun getMD5Hash(input: String) : String {
        lateinit var md5: MessageDigest
        try {
            md5 = MessageDigest.getInstance("MD5")
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        md5.update(input.toByteArray(), 0, input.length)
        return BigInteger(1, md5.digest()).toString(16)
    }

    fun getDevKey(): String {
        return  devId
    }

    fun getAuthKey(): String {
        return authKey
    }

    fun getTimeStamp(): String {
        format.timeZone = TimeZone.getTimeZone("UTC")
        return format.format(Date())
    }

    fun signature(): String {
        return getMD5Hash(devId + "createsession" + authKey + getTimeStamp())
    }
}