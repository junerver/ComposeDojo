package xyz.junerver.composedojo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import xyz.junerver.composedojo.AppViewModel

/**
 * Description:
 * @author Junerver
 * date: 2022/6/28-16:24
 * Email: junerver@gmail.com
 * Version: v1.0
 */
@Composable
fun MainScreen(navController: NavController) {
    val vm = viewModel<AppViewModel>()
    var name by vm.testState

    Surface {
        Column {
            Text(text = "MainScreen+$name")
            Button(onClick = { navController.navigate("other") }) {
                Text(text = "点我跳转")
            }
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name-main") }
            )
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