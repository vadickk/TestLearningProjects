package com.test.testrxjava

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.test.testrxjava.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // TODO: We create observable, flowable and single
//        val observable = Observable.just(1, 2, 3)
//        val single = Single.just(1)
//        val flowable = Flowable.just(1, 2, 3, 4)
        // TODO: We sign on observable, flowable and single, and we get result and log our results
//        observable.subscribe {
//            Log.d("MainActivity", "Observable: $it")
//        }
//        flowable.subscribe {
//            Log.d("MainActivity", "Flowable: $it")
//        }
//        single.subscribe({
//            Log.d("MainActivity", "Single: $it")
//        }, {
//            it.printStackTrace()
//        })
        // TODO: Get data from function and we realized the another Thread for help function subscribeOn
//        dataSourceObservable()
//            // TODO: We use subscribeOn to change our thread
//            .subscribeOn(Schedulers.io())
//            // TODO: We use observeOn to change data on ui thread
//            .observeOn(AndroidSchedulers.mainThread())
//            // TODO: We use subscribe to subscribe on Observable
//            .subscribe(
//                //onNext
//                { number: Int ->
//                    Log.d("MainActivity", "Dispose: $number")
//                    binding.button.text = "Number: $number"
//                },
//                //onError
//                { mistake: Throwable -> mistake.printStackTrace() },
//                //onComplete
//                { Log.d("MyLog", "Completed") }
//            )
        // TODO: we can write result.despose to cancel the task
        val list = listOf(1, 2, 3, 4, 5, 6)
        Observable.just(list)
//            .flatMap { listOfNumbers ->
//                val myList = mutableListOf<Int>()
//                listOfNumbers.forEach { number -> myList.add(number * 2) }
//                Observable.just(myList)
//            }
//            .map { listOfNumbers ->
//                val myList = mutableListOf<Int>()
//                listOfNumbers.forEach {
//                    myList.add(it * 2)
//                }
//                return@map myList
//            }
//            .switchMap { listOfNumbers ->
//                //SwitchedMap give only last result(Observable)
//                val myList = mutableListOf<Int>()
//                listOfNumbers.forEach { number -> myList.add(number * 2) }
//                Observable.just(myList)
//            }
//            .concatMap { listOfNumbers ->
//                //ConcatMap дает результат в правильной последовальности не в зависимости от задержки(delay)
//                val myList = mutableListOf<Int>()
//                listOfNumbers.forEach { number -> myList.add(number * 2) }
//                Observable.just(myList)
//            }
//                        .buffer(2) //Buffer по факту бере елементи і чекає щоб їх було як число яке ми передали йому,
//                           якщо їх вже є як то число він нам викидує елементи
//            .groupBy { it ->
//                //GroupBy використовувається, щоб впорядкувати елементи по якомусь логічному виразі, по факту можна фільтрувати їх)
//                val myList = mutableListOf<Int>()
//                it.forEach { if (it % 2 == 0) myList.add(it) }
//                myList
//            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("MainActivity", "$it")
                },
                { it.printStackTrace() }
            )
        binding.button.setOnClickListener {
            Log.d("MainActivity", "Click")
        }
    }

    private fun dataSourceObservable(): Observable<Int> {
        return Observable.create { subscriber ->
            for (i in 0..10) {
                Thread.sleep(1000L)
                subscriber.onNext(i)
            }
            subscriber.onComplete()
        }
    }

    private fun dataSourceFlowable(): Flowable<Int> {
        return Flowable.create({ subscriber ->
            for (i in 0..10) {
                Thread.sleep(1000L)
                subscriber.onNext(i)
            }
        }, BackpressureStrategy.BUFFER)
    }

    private fun dataSourceSingle(): Single<Int> {
        return Single.create { subscriber ->
            val number = Random.nextInt(0..10)
            subscriber.onSuccess(number)
        }
    }

    private fun dataSourceCompletable(): Completable {
        return Completable.create { subscriber ->
            subscriber.onComplete()
        }
    }

    private fun dataSourceMaybe(): Maybe<Int> {
        return Maybe.create { subscriber ->
            val number = Random.nextInt(0..10)
            //We can invoke only one method
            subscriber.onSuccess(number)
            //subscriber.onComplete()
        }
    }
}