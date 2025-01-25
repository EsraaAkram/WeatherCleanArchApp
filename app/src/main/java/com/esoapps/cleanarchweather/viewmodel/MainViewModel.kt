package com.esoapps.cleanarchweather.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esoapps.domain.model.MainModel
import com.esoapps.domain.usecase.MainUseCase
import com.esoapps.domain.ext.DispatcherProvider
import com.esoapps.domain.ext.Resource
import com.esoapps.domain.ext.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainUseCase: MainUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    init {

    }

    private val _currentState = MutableStateFlow<Resource<MainModel>>(Resource.Loading())
    val currentState: StateFlow<Resource<MainModel>> get() = _currentState.asStateFlow()

    fun getDataFromApi(location: String) = viewModelScope.launch(dispatcherProvider.main) {
        mainUseCase.invoke(location = location.trim())
            .flowOn(dispatcherProvider.io)
            .catch { _currentState.value = Resource.Error(it.message.toString()) }
            .collect { response ->

                when (response) {
                    is Result.Loading -> _currentState.value = Resource.Loading()
                    is Result.Error -> _currentState.value = Resource.Error(response.message)
                    is Result.Success -> _currentState.value = Resource.Success(response.data)
                }
                Log.d("response", _currentState.value.message.toString())
                Log.d("response", _currentState.value.data.toString())
            }
    }

}