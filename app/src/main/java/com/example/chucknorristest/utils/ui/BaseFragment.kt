package com.example.chucknorristest.utils.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.chucknorrisjokes.databinding.ChuckNorrisItemBinding
import com.example.chucknorristest.di.ChuckNorrisApp
import com.example.chucknorristest.utils.ChuckNorrisViewModelFactory
import com.example.chucknorristest.utils.UIState
import com.example.chucknorristest.viewmodel.ChuckNorrisViewModel
import javax.inject.Inject

open class BaseFragment: Fragment() {

    @Inject
    lateinit var chuckNorrisViewModelFactory: ChuckNorrisViewModelFactory

    private val chuckNorrisViewModel: ChuckNorrisViewModel by lazy {
        ViewModelProvider(requireActivity(), chuckNorrisViewModelFactory)[ChuckNorrisViewModel::class.java]
    }


    private val binding by lazy {
        ChuckNorrisItemBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ChuckNorrisApp.chuckNorrisComponent.inject(this)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        chuckNorrisViewModel.randomJoke.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.LOADING -> {}
                is UIState.ERROR -> {}
                is UIState.SUCCESS -> {
                    binding.chuckJoke.text = state.response.jokesValues
                }
            }
        }
        binding.nextJokeBtn.setOnClickListener{
            chuckNorrisViewModel.getData()
        }
        return binding.root
    }

    protected fun showError(message: String, action: () -> Unit) {
        AlertDialog.Builder(requireActivity())
            .setTitle("Error Occurred")
            .setMessage(message)
            .setPositiveButton("Retry") { dialog, _ ->
                action()
                dialog.dismiss()
            }
            .setNegativeButton("Dismiss") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}
