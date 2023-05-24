package com.example.homework5.service

import android.app.Notification
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.IBinder
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.homework5.domain.posts.CreatePostUseCase
import com.example.homework5.domain.GetContentUriUseCase
import com.example.homework5.R
import com.example.homework5.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CreatePostService: Service(), CoroutineScope by MainScope() {
    companion object{


        fun createIntent(
            context: Context,
            text: String?,
            images:List<Uri>,
        ) = Intent(context, CreatePostService::class.java).apply {
            action = Constants.ACTION_CREATE_POST
            putExtra(Constants.ARG_TEXT_KEY, text)
            putParcelableArrayListExtra(Constants.ARG_IMAGES_KEY, ArrayList(images))
        }
    }

    @Inject lateinit var getContentUriUseCase: GetContentUriUseCase
    @Inject lateinit var createPostUseCase: CreatePostUseCase

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        intent?.let {
            if (it.action == Constants.ACTION_CREATE_POST){
                startForeground(Constants.NOTIFICATION_ID, createNotification())
                val text = it.extras?.getString(Constants.ARG_TEXT_KEY)
                val images = it.extras?.getParcelableArrayList<Uri>(Constants.ARG_IMAGES_KEY)?.map { uri ->
                    getContentUriUseCase(uri)
                }

                launch {
                    createPostUseCase.execute(text, images?.filterNotNull())
                    stopSelf()
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun createNotification(): Notification{
        createNotificationChanel()
        return NotificationCompat.Builder(this, Constants.CHANEL_ID)
        .setContentTitle(Constants.TITLE_CREATE_POST)
        .setSmallIcon(R.drawable.ic_cancel_48)
            .setProgress(0, 0, true)
            .build()
    }


    private fun createNotificationChanel(){
        val chanel = NotificationChannelCompat.Builder(
            Constants.CHANEL_ID,
            NotificationManagerCompat.IMPORTANCE_LOW
        ).setName(Constants.CHANEL_NAME)
            .build()

        NotificationManagerCompat.from(this).createNotificationChannel(chanel)
    }

    override fun onDestroy() {
        cancel()
        super.onDestroy()
    }


}