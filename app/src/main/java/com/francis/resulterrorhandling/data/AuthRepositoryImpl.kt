package com.francis.resulterrorhandling.data

import com.francis.resulterrorhandling.domain.AuthRepository
import com.francis.resulterrorhandling.domain.DataError
import com.francis.resulterrorhandling.domain.ResponseResult
import com.francis.resulterrorhandling.domain.User
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.nio.channels.UnresolvedAddressException

class AuthRepositoryImpl:AuthRepository {
    override suspend fun register(password: String): ResponseResult<User, DataError.Network> {
        //API call logic
        return try{
            val user = User("dummy","dummy","dummy")
            ResponseResult.Success(user)
        }catch (e: HttpException){
            when(e.code()){
                408 -> ResponseResult.Error(DataError.Network.REQUEST_TIMEOUT)
                413 -> ResponseResult.Error(DataError.Network.PAYLOAD_TOO_LARGE)
                429 -> ResponseResult.Error(DataError.Network.TOO_MANY_REQUESTS)
                500 -> ResponseResult.Error(DataError.Network.SERVER_ERROR)
                else-> ResponseResult.Error(DataError.Network.UNKNOWN)
            }
        }catch (e:SerializationException){
            ResponseResult.Error(DataError.Network.SERIALIZATION)
        }catch (e:UnresolvedAddressException){
            ResponseResult.Error(DataError.Network.NO_INTERNET)
        }catch (e:Exception){
            ResponseResult.Error(DataError.Network.UNKNOWN)
        }
    }
}