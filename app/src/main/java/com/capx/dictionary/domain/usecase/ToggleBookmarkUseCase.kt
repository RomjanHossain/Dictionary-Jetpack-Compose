package com.capx.dictionary.domain.usecase

import com.capx.dictionary.data.entity.DictionaryBookmark
import com.capx.dictionary.domain.repository.DictionaryRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class ToggleBookmarkUseCase @Inject constructor(
    private val repository: DictionaryRepository
) {
    suspend operator fun invoke(wordId: Int, title: String) {
        val isCurrentlyBookmarked = repository.isBookmarked(wordId).first()
        if (isCurrentlyBookmarked) {
            repository.deleteBookmarkFromWordID(wordId)
        } else {
            repository.insertBookmark(DictionaryBookmark(wordID = wordId, title = title))
        }
    }
}
