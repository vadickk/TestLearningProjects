package com.test.testsqlite.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.test.testsqlite.core.app.Application
import com.test.testsqlite.databinding.ActivityMainBinding
import com.test.testsqlite.data.entities.Note
import com.test.testsqlite.presentation.adapter.NoteAdapter
import com.test.testsqlite.presentation.viewmodel.MainViewModel
import com.test.testsqlite.presentation.viewmodel.MainViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    private val mainViewModel: MainViewModel by viewModels { mainViewModelFactory }
    private val noteAdapter = NoteAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        (applicationContext as Application).appComponent.inject(this)

        binding.recyclerView.adapter = noteAdapter

        mainViewModel.observeTitleError(this) { inputTextState ->
            inputTextState.apply(binding.etTitle, binding.layoutTitle)
        }

        mainViewModel.observeSubtitleError(this) { inputTextState ->
            inputTextState.apply(binding.etSubtitle, binding.layoutSubtitle)
        }

        mainViewModel.observeNotesList(this) { notes ->
            noteAdapter.setNewContent(notes)
        }

        mainViewModel.init()

        binding.btnAddNote.setOnClickListener {
            val note = Note(
                title = binding.etTitle.text.toString(),
                subtitle = binding.etSubtitle.text.toString()
            )
            mainViewModel.insertNoteToDatabase(note)
        }

        binding.btnDeleteAllData.setOnClickListener {
            mainViewModel.deleteAllNotes()
        }
    }
}