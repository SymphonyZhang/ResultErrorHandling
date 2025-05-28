package com.francis.resulterrorhandling.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.francis.resulterrorhandling.domain.AuthRepository
import com.francis.resulterrorhandling.domain.ResponseResult
import com.francis.resulterrorhandling.domain.UserDataValidator
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val userDataValidator: UserDataValidator,
    private val repository: AuthRepository
):ViewModel() {

    private val _sharedError = MutableSharedFlow<UserEvent>(replay = 0)
    val sharedError:SharedFlow<UserEvent> = _sharedError

    fun onRegisterClick(password:String){
        when(val result =userDataValidator.validatePassword(password)){
            is ResponseResult.Error -> {
                when (result.error) {
                    UserDataValidator.PasswordError.TOO_SHORT -> TODO()
                    UserDataValidator.PasswordError.NO_UPPERCASE -> TODO()
                    UserDataValidator.PasswordError.NO_DIGIT -> TODO()
                }
            }
            is ResponseResult.Success -> {

            }
        }
        viewModelScope.launch {
            when(val result = repository.register(password)){
                is ResponseResult.Error -> {
                    val errorMessage = result.asErrorUiText()
                    _sharedError.emit(UserEvent.Error(errorMessage))
                }
                is ResponseResult.Success -> {
                    result.data
                }
            }
        }
    }
}

sealed interface UserEvent{
    data class Error(val error: UiText):UserEvent
}