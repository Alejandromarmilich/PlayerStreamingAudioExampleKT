package com.example.playerstreamingexample

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.playerstreamingexample.databinding.ActivityMainBinding
import java.io.IOException

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var player: MediaPlayer
    lateinit var audioManager: AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Radio Online"
        binding.pause.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        this.audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        binding.seekBar.max  = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        binding.seekBar.progress = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)-2
        listener()
        binding.play.performClick()
    }
    fun listener(){
        binding.play.setOnClickListener {
            player = MediaPlayer()
            player.setAudioAttributes(AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build())
            try {
                player.setDataSource(getString(R.string.urlRadio))
                player.prepareAsync()
                binding.play.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
                player.setOnPreparedListener {
                    player.start()
                    binding.pause.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        binding.pause.setOnClickListener {
            player.pause()
            binding.play.visibility = View.VISIBLE
            binding.pause.visibility = View.GONE
        }
        binding.seekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress, 0)
            }
            override fun onStartTrackingTouch(seek: SeekBar) {}
            override fun onStopTrackingTouch(seek: SeekBar) {}
        })
    }
}
