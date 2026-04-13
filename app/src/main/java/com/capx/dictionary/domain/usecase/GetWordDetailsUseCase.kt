package com.capx.dictionary.domain.usecase

import com.capx.dictionary.data.entity.DictionaryDataDetails
import com.capx.dictionary.domain.repository.DictionaryRepository
import javax.inject.Inject

class GetWordDetailsUseCase @Inject constructor(
    private val repository: DictionaryRepository
) {
    suspend operator fun invoke(word: String): List<DictionaryDataDetails> {
        return repository.getSingleWord(word)
    }
}
