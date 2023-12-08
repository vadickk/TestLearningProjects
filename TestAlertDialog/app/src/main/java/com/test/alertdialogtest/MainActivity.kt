package com.test.alertdialogtest

import android.content.DialogInterface
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.test.alertdialogtest.databinding.ActivityMainBinding
import com.test.alertdialogtest.databinding.PartVolumeInputBinding
import com.test.alertdialogtest.databinding.ViewSeekBarBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var volume = 50

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val arrayForSingleDialog = arrayOf(
            "Кошки",
            "Папугаи",
            "Лошади",
            "Собаки"
        )
        val arrayOfBoolean = arrayOf(
            false,
            false,
            false,
            false
        )
        val firstListener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> showToast("Мне тоже")
                DialogInterface.BUTTON_NEGATIVE -> showToast("Сочуствую.")
                DialogInterface.BUTTON_NEUTRAL -> showToast("Да уж - лень, лень, лень...")
            }
        }
        //first
        fun simpleDialog() {
            val simpleDialog = AlertDialog.Builder(this)
                .setCancelable(true)
                .setIcon(R.mipmap.ic_launcher_round)
                .setTitle("Первый Диалог")
                .setMessage("Вам нравится андроид?")
                .setPositiveButton("Да", firstListener)
                .setNegativeButton("Нет", firstListener)
                .setNeutralButton("Не интересно", firstListener)
                .create()
                .show()
        }
        //second
        fun singleDialogWithoutConfirmButton() {
            val singleDialogWithoutConfirmButton = AlertDialog.Builder(this)
                .setCancelable(true)
                .setTitle("Кто вам больше нравится?")
                .setSingleChoiceItems(arrayForSingleDialog, -1) { dialog, position ->
                    when (position) {
                        0 -> dialog.dismiss()
                        1 -> dialog.dismiss()
                        2 -> dialog.dismiss()
                        3 -> dialog.dismiss()
                    }
                    binding.textView.text = arrayForSingleDialog[position]
                }.create().show()
        }
        //third
        fun singleDialogWithConfirmButton() {
            var position = -1
            val singleDialogWithConfirmButton = AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("Кто вам больше нравится?")
                .setSingleChoiceItems(arrayForSingleDialog, 0) { dialog, pos ->
                    when (pos) {
                        0 -> position = 0
                        1 -> position = 1
                        2 -> position = 2
                        3 -> position = 3
                    }
                }
                .setPositiveButton("Confirm") { _, _ ->
                    binding.textView.text = arrayForSingleDialog[position]
                }.create().show()
        }
        //next
        fun multiDialogWithoutConfirmButton() {
            val arrayOfText = ArrayList<String>()
            val multiDialogWithoutConfirmButton = AlertDialog.Builder(this)
                .setCancelable(true)
                .setTitle("Выберете ваших любимых животних")
                .setMultiChoiceItems(arrayForSingleDialog, null) { dialog, pos, isChecked ->
                    if (isChecked) {
                        arrayOfText.add(arrayForSingleDialog[pos] + "\n")
                        binding.textView.text = ""
                        for (i in arrayOfText.indices) {
                            binding.textView.append(arrayOfText[i])
                        }
                    } else {
                        arrayOfText.remove(arrayForSingleDialog[pos] + "\n")
                        binding.textView.text = ""
                        for (i in arrayOfText.indices) {
                            binding.textView.append(arrayOfText[i])
                        }
                    }
                }.create().show()
        }
        //next
        fun multiDialogWithConfirmButton() {
            val arrayOfTextWithConfirm = ArrayList<String>()
            val multiDialogWithConfirmButton = AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("Выберете ваших любимых животних")
                .setMultiChoiceItems(arrayForSingleDialog, null) { dialog, pos, isChecked ->
                    if (isChecked) {
                        arrayOfTextWithConfirm.add(arrayForSingleDialog[pos] + "\n")
                        binding.textView.text = ""
                        for (i in arrayOfTextWithConfirm.indices) {
                            binding.textView.append(arrayOfTextWithConfirm[i])
                        }
                    } else {
                        arrayOfTextWithConfirm.remove(arrayForSingleDialog[pos] + "\n")
                        binding.textView.text = ""
                        for (i in arrayOfTextWithConfirm.indices) {
                            binding.textView.append(arrayOfTextWithConfirm[i])
                        }
                    }
                }
                .setPositiveButton("Confirm") { dialog, pos ->
                    dialog.dismiss()
                }.create().show()
        }
        //next
        fun customDialogWithSeekBar() {
            val dialogBinding = ViewSeekBarBinding.inflate(layoutInflater)
            dialogBinding.seekBar.progress = volume
            dialogBinding.root.removeView(binding.root)
            val some = AlertDialog.Builder(this)
                .setTitle("Диалог с кастомной вью))")
                .setMessage("Оцените мою внешку")
                .setView(dialogBinding.root)
                .setPositiveButton("Confirm") { _, _ ->
                    volume = dialogBinding.seekBar.progress
                    binding.textView.text = volume.toString()
                }.create().show()
        }
        //next
        fun showCustomInputAlertDialog() {
            val dialogBinding = PartVolumeInputBinding.inflate(layoutInflater)
            dialogBinding.volumeInputEditText.setText(volume.toString())

            val dialog = AlertDialog.Builder(this)
                .setTitle("Some")
                .setView(dialogBinding.root)
                .setPositiveButton("Confirm", null)
                .create()
            dialog.setOnShowListener {
                dialogBinding.volumeInputEditText.requestFocus()
                dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                    val enteredText = dialogBinding.volumeInputEditText.text.toString()
                    if (enteredText.isBlank()) {
                        dialogBinding.volumeInputEditText.error = "empty value"
                        return@setOnClickListener
                    }
                    val volume = enteredText.toIntOrNull()
                    if (volume == null || volume > 100) {
                        dialogBinding.volumeInputEditText.error = "invalid value"
                        return@setOnClickListener
                    }
                    this.volume = volume
                    binding.textView.text = volume.toString()
                    dialog.dismiss()
                }
            }
            dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
            dialog.show()
        }
        //button
        binding.button.setOnClickListener {
            showCustomInputAlertDialog()
        }

    }

    private fun showToast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show()
    }
}