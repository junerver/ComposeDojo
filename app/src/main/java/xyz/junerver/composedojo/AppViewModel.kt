package xyz.junerver.composedojo

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

/**
 * Description:
 * @author Junerver
 * date: 2022/2/14-9:23
 * Email: junerver@gmail.com
 * Version: v1.0
 */
class AppViewModel : ViewModel() {
    val testState = mutableStateOf("")
    var navController: NavController? = null
}