package com.example.homework5.data.model

import android.net.Uri
import java.util.UUID

data class ImageCompact(
    val id: String = UUID.randomUUID().toString(),
    val uri: Uri,
)
