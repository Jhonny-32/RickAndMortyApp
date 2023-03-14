package com.example.rickandmortyapp.domain.use_case

import com.example.rickandmortyapp.data.Result
import com.example.rickandmortyapp.domain.model.Characters
import com.example.rickandmortyapp.domain.repositories.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    operator fun invoke(page: Int): Flow<Result<List<Characters>>> {
        return repository.getCharacters(page)
    }

}