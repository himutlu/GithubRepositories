package com.him.githubrepositories.feature

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.him.githubrepositories.R
import com.him.githubrepositories.core.util.hide
import com.him.githubrepositories.core.util.show
import com.him.githubrepositories.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun showFetchingErrorPopup(
        message: String?,
        positiveButtonHandler: () -> Unit,
        negativeButtonHandler: (() -> Unit)? = null
    ) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.error))
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(
                getString(R.string.retry)
            ) { _, _ ->
                positiveButtonHandler.invoke()
            }.setNegativeButton(getString(R.string.go_back)) { _, _ ->
                negativeButtonHandler?.invoke()
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

    fun showProgressbarAndHideContainer() {
        if (!binding.progressBar.isVisible)
            binding.progressBar.show()
        hideFragmentContainer()
    }

    fun hideProgressbar() {
        if (binding.progressBar.isVisible)
            binding.progressBar.hide()
    }

    fun hideFragmentContainer() {
        if (binding.fragmentContainerView.isVisible)
            binding.fragmentContainerView.hide()
    }

    fun showFragmentContainer() {
        if (!binding.fragmentContainerView.isVisible)
            binding.fragmentContainerView.show()
    }
}