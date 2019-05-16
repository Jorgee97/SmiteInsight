package com.coreteam.smiteinsight.api

import com.coreteam.smiteinsight.entity.SessionInfo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import java.lang.reflect.Array

interface SmiteApi {

    @GET("createsessionjson/{developerId}/{signature}/{timestamp}")
    fun getSession(@Path("developerId") developerId: String, @Path("signature") signature: String,
                   @Path("timestamp") timestamp: String): Observable<SessionInfo>

    @GET("testsessionjson/{developerId}/{signature}/{session}/{timestamp}")
    fun testSession(@Path("developerId") developerId: String, @Path("signature") signature: String,
                    @Path("session") session: String, @Path("timestamp") timestamp: String) : Observable<SessionInfo>

    @GET("getesportsproleaguedetailsjson/{developerId}/{signature}/{session}/{timestamp}")
    fun getEsportsDetails(@Path("developerId") developerId: String, @Path("signature") signature: String,
                          @Path("session") session: String, @Path("timestamp") timestamp: String) : Observable<Array>
}