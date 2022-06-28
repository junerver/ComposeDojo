package xyz.junerver.composedojo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

/**
 * Description:
 * @author Junerver
 * date: 2022/6/28-16:24
 * Email: junerver@gmail.com
 * Version: v1.0
 */
@Composable
fun MainScreen(navController: NavController) {
    Surface {
        Column {
            Text(text = "MainScreen")
            Button(onClick = { navController.navigate("other") }) {
                Text(text = "点我跳转")
            }
        }
    }
}

@Composable
fun OtherScreen(navController: NavController) {
    Surface {
        Column {
            Text(text = "OtherScreen")
            Button(onClick = { navController.navigate("main") }) {
                Text(text = "点我跳回")
            }
        }
    }
}