package com.capx.dictionary.domain.usecase

import com.capx.dictionary.domain.repository.DictionaryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveBookmarkStatusUseCase @Inject constructor(
    private val repository: DictionaryRepository
) {
    operator fun invoke(wordId: Int): Flow<Boolean> {
        return repository.isBookmarked(wordId)
    }
}
