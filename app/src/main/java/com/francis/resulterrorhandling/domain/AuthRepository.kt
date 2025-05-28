package com.francis.resulterrorhandling.domain

interface AuthRepository {

    suspend fun register(password:String):ResponseResult<User,DataError.Network>

}

data class User(
    val fullName: String,
    val token: String,
    val email: String,
)