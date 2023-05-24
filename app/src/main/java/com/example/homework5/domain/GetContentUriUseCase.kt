package com.example.homework5.domain

import android.content.ContentResolver
import android.net.Uri
import javax.inject.Inject

class GetContentUriUseCase @Inject constructor(
    private val contentResolver: ContentResolver
) {
    operator fun invoke(uri:Uri): ByteArray?{                   //примерно то же самое, что экзекьют
        return  contentResolver.openInputStream(uri).use {      //юз - если элекмент типа closable, то в конце юза он закроется и не будет утечки памяти
            it?.readBytes()
        }
    }
}