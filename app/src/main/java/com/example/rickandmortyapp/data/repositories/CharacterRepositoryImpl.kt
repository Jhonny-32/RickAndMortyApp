package com.example.rickandmortyapp.data.repositories

import com.example.rickandmortyapp.data.source.remote.RickAndMortyApi
import com.example.rickandmortyapp.domain.model.Character
import com.example.rickandmortyapp.domain.model.Characters
import com.example.rickandmortyapp.domain.repositories.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.example.rickandmortyapp.data.Result
import com.example.rickandmortyapp.data.source.remote.dto.toCharacter
import com.example.rickandmortyapp.data.source.remote.dto.toListCharacters
import okio.IOException
import retrofit2.HttpException


class CharacterRepositoryImpl @Inject constructor(
    private val api : RickAndMortyApi
) : CharacterRepository {

    override fun getCharacters(page: Int): Flow<Result<List<Characters>>> = flow{
        emit(Result.Loading())
        try {
            val response = api.getCharacters(page).toListCharacters()
            emit(Result.Success(response))
        }catch (e: HttpException){
            emit(Result.Error(
                message = "Algo ha salido mal",
                data = null
            ))
        }catch (e: IOException){
            emit(Result.Error(
                message = "No se ha podido acceder al servidor, compruebe su conexion de internet",
                data = null
            ))
        }
    }

    override suspend fun getCharacter(id: Int): Result<Character> {
        val response = try {
            api.getCharacter(id)
        }catch (e: Exception){
            return Result.Error(
                message = "Se ha producido un error desconocido"
            )
        }
        return Result.Success(response.toCharacter())
    }


}