package xyz.junerver.composedojo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import xyz.junerver.composedojo.ui.MainScreen
import xyz.junerver.composedojo.ui.OtherScreen
import xyz.junerver.composedojo.ui.TestStateChange.MultiState
import xyz.junerver.composedojo.ui.theme.ComposeDojoTheme

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDojoTheme {
                lifecycleScope.launch { Log.d(TAG, "我是非要在Compose中执行的协程block") }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") { Greeting(navController) }
                        composable("main") { MainScreen(navController) }
                        composable("other") { OtherScreen(navController) }
                        /*...*/
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(navController: NavController) {
    var counter by remember { mutableStateOf(0) }
    var visible by remember { mutableStateOf(true) }
    LaunchedEffect(key1 = Unit, block = {
        Log.d(TAG, "Greeting: 我被调用执行了")
    })

    Column {
        if (visible) {
            Text(text = "Hello !${counter}")
        }
        Button(
            onClick = {
                counter += 1
                visible = !visible
                navController.navigate("main")
            },
            modifier = Modifier
                .height(50.dp)
                .width(100.dp)
        ) {
            Text(text = "点一下")
        }
        HelloContent()
        var name by rememberSaveable { mutableStateOf("") }
        //两种不同的状态提升
        HelloContent(value = name, onValueChange = { name = it })
        HelloContent(rememberSaveable { mutableStateOf("") })
        MultiState()
    }
}

//region
@Composable
fun HelloContent() {
    val scope = rememberCoroutineScope()
    Column(modifier = Modifier.padding(16.dp)) {
        val vm = viewModel<AppViewModel>()
        var name by vm.testState
        if (name.isNotEmpty()) {
            Text(
                text = "Hello, $name!",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h5
            )
        }
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
    }
}

@Composable
fun HelloContent(value: String, onValueChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        if (value.isNotEmpty()) {
            Text(
                text = "Hello, $value!",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h5
            )
        }
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text("Name") }
        )
    }
}

@Composable
fun HelloContent(state: MutableState<String>) {
    var name by state
    Column(modifier = Modifier.padding(16.dp)) {
        if (name.isNotEmpty()) {
            Text(
                text = "Hello, $name!",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h5
            )
        }
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
    }
}
//endregion


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeDojoTheme {
        Surface(
            color = MaterialTheme.colors.background
        ) {
//            Greeting()
        }
    }
}