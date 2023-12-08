package com.test.testsqlite.domain.validation

import com.test.testsqlite.data.ExceptionMessage
import com.test.testsqlite.data.entities.Note
import com.test.testsqlite.presentation.ManageNotes

interface ValidNote {

    fun validNote(note: Note): Boolean

    class Base(
        private val manageNotes: ManageNotes
    ): ValidNote {
        override fun validNote(note: Note): Boolean {
            if (note.title?.isEmpty() == true) {
                manageNotes.showTitleError(ExceptionMessage.TITLE_IS_EMPTY)
                return false
            }
            if (note.subtitle?.isEmpty() == true) {
                manageNotes.showSubtitleError(ExceptionMessage.SUBTITLE_IS_EMPTY)
                return false
            }
            return true
        }

    }
}