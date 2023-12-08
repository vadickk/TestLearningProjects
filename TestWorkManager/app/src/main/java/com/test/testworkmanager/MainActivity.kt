package com.test.testworkmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.*
import com.test.testworkmanager.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //Кидаем данные в work manager
        val inputWorkData = workDataOf(
            "title" to "Hello",
            "message" to "Test"
        )
        //Ограничения
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        //Одноразовая хуйня
        val uploadWorkRequest = OneTimeWorkRequestBuilder<MyWorkManager>()
            .setConstraints(constraints)
            .setInputData(inputWorkData)
            .build()
        //Переодическая хуйня
        val periodicWorkRequest =
            PeriodicWorkRequestBuilder<MyWorkManager>(15, TimeUnit.MINUTES).build()
        //По клику на кнопочку вызывает действие WorkManager
        val workManager = WorkManager.getInstance(this)
        binding.btnStartWorker.setOnClickListener {
            workManager.enqueue(uploadWorkRequest)
            //Получаем Output Data выводим в логи результат
            workManager.getWorkInfoByIdLiveData(uploadWorkRequest.id).observe(this) { info ->
                if (info != null && info.state.isFinished) {
                    val result1 = info.outputData.getInt("1", 1000)
                    val result2 = info.outputData.getInt("2", 1000)
                    Log.d("MyLog", "$result1 and $result2")
                }
            }
        }
        binding.btnStopWorker.setOnClickListener {
            //Чтобы отменить работу
            workManager.cancelWorkById(uploadWorkRequest.id)
        }
    }
}