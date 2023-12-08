package com.test.testvideo

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.test.testvideo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val videoPlayer = binding.videoView
        val myVideoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.sample)
        //videoPlayer.setVideoURI(myVideoUri)
        videoPlayer.setVideoPath("http://techslides.com/demos/sample-videos/small.mp4")
        val mediaController = MediaController(this)
        videoPlayer.setMediaController(mediaController)
        mediaController.setMediaPlayer(videoPlayer)

        binding.btnStart.setOnClickListener {
            videoPlayer.start()
        }
        binding.btnPause.setOnClickListener {
            videoPlayer.pause()
        }
        binding.btnStop.setOnClickListener {
            videoPlayer.stopPlayback()
            videoPlayer.resume()
        }

    }
}