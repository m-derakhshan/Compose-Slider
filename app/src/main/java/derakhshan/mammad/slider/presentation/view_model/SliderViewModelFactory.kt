package derakhshan.mammad.slider.presentation.view_model

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SliderViewModelFactory(
    private val items: List<@Composable () -> Unit>,
    private val screenWidth: Int
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SliderViewModel(sliderItems = items, screenWidth = screenWidth) as T
    }
}