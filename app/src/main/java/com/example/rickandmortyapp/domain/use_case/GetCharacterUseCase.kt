package com.example.rickandmortyapp.domain.use_case

import com.example.rickandmortyapp.data.Result
import com.example.rickandmortyapp.domain.model.Character
import com.example.rickandmortyapp.domain.repositories.CharacterRepository
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    suspend operator fun invoke(id: Int): Result<Character> {
        return repository.getCharacter(id)
    }

}