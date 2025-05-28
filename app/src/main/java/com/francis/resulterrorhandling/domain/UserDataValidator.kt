package com.francis.resulterrorhandling.domain



class UserDataValidator {

    fun validatePassword(password:String): ResponseResult<Unit,PasswordError>{
        if(password.length < 9){
            return ResponseResult.Error(PasswordError.TOO_SHORT)
        }
        val hasUppercaseChar = password.any { it.isUpperCase() }
        if(!hasUppercaseChar){
            return ResponseResult.Error(PasswordError.NO_UPPERCASE)
        }
        val hasDigit = password.any{
            it.isDigit()
        }
        if(!hasDigit){
            return ResponseResult.Error(PasswordError.NO_DIGIT)
        }
        return ResponseResult.Success(Unit)
    }

    enum class PasswordError:Error{
        TOO_SHORT,
        NO_UPPERCASE,
        NO_DIGIT
    }
}