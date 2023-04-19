package com.example.homework5.MainActivity

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import com.example.homework5.R
import com.example.homework5.databinding.ViewProfileCardBinding
import com.google.android.material.card.MaterialCardView

class ViewProfile @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defAttributeSet: Int = 0,
) : MaterialCardView(context, attributeSet, defAttributeSet) {

    private lateinit var binding: ViewProfileCardBinding

    init {
        binding = ViewProfileCardBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )

        context.withStyledAttributes(
            attributeSet,
            R.styleable.ViewProfile,
            defAttributeSet,
            defStyleRes = 0
        ) {
            getString(R.styleable.ViewProfile_userName)?.let { userName ->
                binding.textViewUserName.text = userName
            }
            getString(R.styleable.ViewProfile_userStatus)?.let { userStatus ->
                binding.textViewUserStatus.text = userStatus
            }
            getInt(R.styleable.ViewProfile_IMGNum, 0).let { IMGNum ->
                binding.textViewIMGNum.text = IMGNum.toString()
            }
            getInt(R.styleable.ViewProfile_subsNum, 0).let { subsNum ->
                binding.textViewSubsNum.text = subsNum.toString()
            }
            getInt(R.styleable.ViewProfile_postsNum, 0).let { postsNum ->
                binding.textViewPostsNum.text = postsNum.toString()
            }
            getColor(R.styleable.ViewProfile_btnColor, 0x6750A4.toInt()).let { btnColor ->
                binding.buttonSubscribe.setBackgroundColor(btnColor)

            }
        }
    }

    fun setUserName(text: String) {
        binding.textViewUserName.text = text
    }

    fun setUserStatus(text: String) {
        binding.textViewUserStatus.text = text
    }

    fun setIMGNum(num: Int) {
        binding.textViewIMGNum.text = num.toString()
    }

    fun setSubsNum(num: Int) {
        binding.textViewSubsNum.text = num.toString()
    }

    fun setPostsNum(num: Int) {
        binding.textViewPostsNum.text = num.toString()
    }

    fun setBtnColor(@ColorRes color: Int) {
        binding.buttonSubscribe.setBackgroundColor(
            ContextCompat.getColor(context, color)
        )
    }

}