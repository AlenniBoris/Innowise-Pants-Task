package com.example.pants.presentation.colorpicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.pants.presentation.colorpicker.ui.screen.ColorPickerScreen
import com.example.pants.databinding.FragmentPickerBinding
import com.example.pants.presentation.game.GameFragment.Companion.COLOR_NAME_ARG
import com.example.pants.presentation.SharedGameViewModel
import com.example.pants.presentation.colorpicker.model.ColorPickerStateHolder
import com.example.pants.uikit.compose.theme.PantsAppTheme
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class ColorPickerFragment : Fragment() {
    private var _viewBinding: FragmentPickerBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val viewModel by activityViewModel<SharedGameViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        _viewBinding = FragmentPickerBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val colorName = arguments?.getString(COLOR_NAME_ARG)
        colorName?.let { name ->
            viewModel.setColorModelByName(name)
        }

        bindCompose()

    }

    private fun bindCompose() {
        viewBinding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                PantsAppTheme {
                    Box(modifier = Modifier.fillMaxSize()) {
                        val stateHolder = remember(viewModel) { ColorPickerStateHolder(viewModel) }

                        ColorPickerScreen(
                            stateHolder = stateHolder,
                            onSave = {
                                viewModel.saveColor()
                                parentFragmentManager.popBackStack()
                            },
                            onUpdateColorSettings = { hue ->
                                viewModel.updateColorSettings(hue)
                            },
                        )
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}
