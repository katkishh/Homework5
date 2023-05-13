package com.example.homework5.MainActivity.presentation

import android.os.Bundle
import android.view.View
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.homework5.databinding.FragmentAuthBinding

class AuthFragment: Fragment() {
    private val binding by viewBinding(FragmentAuthBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root){view, insets ->
            val imeInset = insets.getInsets(WindowInsetsCompat.Type.ime())

            binding.btnContinue.updatePadding(
                bottom = imeInset.bottom
            )

            WindowInsetsCompat.Builder().setInsets(
                WindowInsetsCompat.Type.ime(),
                Insets.of(
                    imeInset.left,
                    0,
                    imeInset.right,
                    0)
            ).build()
        }

        binding.root.requestApplyInsets()
    }
}