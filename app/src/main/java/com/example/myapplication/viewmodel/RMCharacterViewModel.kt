package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.ApiRMCharacter
import com.example.myapplication.data.model.CreateRMCharacter
import com.example.myapplication.data.model.FavoriteRMCharacterCrossRef
import com.example.myapplication.data.model.RMCharacter
import com.example.myapplication.data.retrofit.ApiRMCharacterRepository
import com.example.myapplication.data.room.AppDbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RMCharacterViewModel(private val repository: AppDbRepository) : ViewModel() {

    private val _apiCharacters = MutableStateFlow<List<ApiRMCharacter>>(emptyList())
    val apiCharacters = _apiCharacters.asStateFlow()

    private val _createdCharacters = MutableStateFlow<List<CreateRMCharacter>>(emptyList())
    val createdCharacters = _createdCharacters.asStateFlow()

    private val _apiCharactersFromFavorite = MutableStateFlow<List<ApiRMCharacter>>(emptyList())
    val apiCharacterFromFavorite = _apiCharactersFromFavorite.asStateFlow()

    private val _createdCharactersFromFavorite = MutableStateFlow<List<CreateRMCharacter>>(emptyList())
    val createdCharactersFromFavorite = _createdCharactersFromFavorite.asStateFlow()

    private val _message = MutableStateFlow<String?>(null)
    val message = _message.asStateFlow()

    // Laster opp til grensesnitt
    init {
        getAndStoreApiCharacters()
        getAllApiCharacters()

        getAllCreatedCharacters()
    }

    // Henter
    private fun getAndStoreApiCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            val apiCharacters = ApiRMCharacterRepository.getCharactersFromApi()
            apiCharacters.forEach { apiCharacter ->
                repository.insertApiCharacters(apiCharacter)
            }
            getAllApiCharacters()
        }
    }

    private fun getAllApiCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _apiCharacters.value = repository.getApiCharacters()
            } catch (e: Exception) {
                _message.value = "Failed to add"
            }
        }
    }

    private fun getAllCreatedCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _createdCharacters.value = repository.getCreatedCharacters()
        }
    }

    fun getApiCharactersFromFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _apiCharactersFromFavorite.value = repository.getApiCharactersInFavorite()
            }  catch (e: Exception) {
                _message.value = "Failed to add"
            }

        }
    }

    fun getCreatedCharactersFromFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            _createdCharactersFromFavorite.value = repository.getCreatedCharactersInFavorites()
        }
    }


    // Legger til
    fun insertCreatedCharacters(character: CreateRMCharacter) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.insertCreatedCharacter(character)
                getAllCreatedCharacters()
            } catch (e: Exception) {
                _message.value = "Failed to add"
            }

        }
    }

    private fun addApiCharacterToFavorite(apiCharacterId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.insertApiCharacterToFavorite(
                    FavoriteRMCharacterCrossRef(
                        id = apiCharacterId,
                        createdCharacterId = null
                    )
                )
                getApiCharactersFromFavorite()
            } catch (e: Exception) {
                _message.value = "Failed to add"
            }
        }
    }

    private fun addCreatedCharacterToFavorite(createdCharacterId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.insertCreatedCharacterToFavorite(
                    FavoriteRMCharacterCrossRef(
                        createdCharacterId = createdCharacterId,
                        id = null
                    )
                )
                getCreatedCharactersFromFavorite()
            } catch (e: Exception) {
                _message.value = "Failed to add"
            }
        }
    }

    // Slette
    fun deleteCreatedCharacter(character: CreateRMCharacter) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.deleteCreatedCharacter(character.createdCharacterId)
                getAllCreatedCharacters()
            } catch (e: Exception) {
                _message.value = "Failed to delete"
            }

        }
    }

    fun deleteCharacterFromFavorite(character: RMCharacter) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (character is ApiRMCharacter) {
                    repository.deleteApiCharacterFromFavorite(character.apiCharacterId)
                } else if (character is CreateRMCharacter) {
                    repository.deleteCreatedCharacterFromFavorite(character.createdCharacterId)
                }
                getApiCharactersFromFavorite()
                getCreatedCharactersFromFavorite()
            } catch (e: Exception) {
                _message.value = "Failed to delete"
            }
        }
    }

    // Sjekker favoritt status
    fun toggleApiFavorite(character: ApiRMCharacter) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (_apiCharactersFromFavorite.value.any { apiCharacter ->
                    apiCharacter.apiCharacterId == character.apiCharacterId }
                ) {
                    deleteCharacterFromFavorite(character)
                } else {
                    addApiCharacterToFavorite(character.apiCharacterId)
                }
                getApiCharactersFromFavorite()
            } catch (_: Exception) {

            }
        }
    }

    fun toggleCreatedFavorite(character: CreateRMCharacter) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (_createdCharactersFromFavorite.value.any { createdCharacter ->
                    createdCharacter.createdCharacterId == character.createdCharacterId }
                ) {
                    deleteCharacterFromFavorite(character)
                } else {
                    addCreatedCharacterToFavorite(character.createdCharacterId)
                }
            } catch (_: Exception) {

            }
        }
    }

    fun clearMessage() {
        _message.value = ""
    }


}
