package xyz.junerver.composedojo.ui.TestStateChange

import androidx.compose.material.Text
import androidx.compose.runtime.*
import kotlinx.coroutines.delay

/**
 * Description:
 * @author Junerver
 * date: 2022/2/14-15:27
 * Email: junerver@gmail.com
 * Version: v1.0
 */
@Composable
fun MultiState() {
    var state:UIState by remember {
        mutableStateOf(UIState.Loading)
    }
    LaunchedEffect(key1 = Unit ){
        if (state is UIState.Loading) {
            //请求数据
            delay(2000)
            state = UIState.ShowData
            delay(2000)
            state = UIState.Error
            delay(2000)
            state = UIState.Default
        }
    }

    when (state) {
        UIState.Loading->{ Text(text = "当前处于 loading")}
        UIState.ShowData->{ Text(text = "当前处于 showdata")}
        UIState.Error->{ Text(text = "当前处于 error")}
        UIState.Default->{ Text(text = "当前处于 default")}
    }

}

sealed class UIState {
    object Loading :UIState()
    object ShowData :UIState()
    object Error :UIState()
    object Default:UIState()
}