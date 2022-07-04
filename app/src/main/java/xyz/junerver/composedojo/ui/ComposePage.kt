package xyz.junerver.composedojo.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import xyz.junerver.composedojo.AppViewModel
import xyz.junerver.composedojo.rememberGlobalState

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
    var global by rememberGlobalState()
    // 数组、list不能直接使用 mutableStateOf()，需要使用 mutableStateListOf()
    val list = remember {
        mutableStateListOf("1", "2", "3")
    }

    Surface {
        Column {
            Text(text = "MainScreen+$name")
            Text(text = "Global: $global")
            Button(onClick = { navController.navigate("other") }) {
                Text(text = "点我跳转")
            }
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name-main") }
            )
            list.map {
                TText(text = it)
            }
            Button(onClick = {
//                val l = ArrayList(list)
//                l.add((list.size+1).toString())
//                list = l
                list.add((list.size+1).toString())
//                name += list.size
                Log.d("TAG", "MainScreen: list.size = ${list.size}")
            }) {
                Text(text = "点我添加 ${list.size}")
            }
        }

    }
}

@Composable
fun TText(text:String){
    Text(text = "$text - ${Math.random()}")
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