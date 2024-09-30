package com.flexath.ecommercemobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.flexath.ecommercemobile.presentation.navigation.Screen
import com.flexath.ecommercemobile.presentation.navigation.SetUpNavGraph
import com.flexath.ecommercemobile.presentation.viewmodels.MainViewModel
import com.flexath.ecommercemobile.ui.theme.EcommerceMobileTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcommerceMobileTheme {
                val navController = rememberNavController()
                val mainViewModel = hiltViewModel<MainViewModel>()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SetUpNavGraph(
                        modifier = Modifier.fillMaxSize(),
                        mainViewModel = mainViewModel,
                        startDestination = Screen.Splash,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EcommerceMobileTheme {
        Greeting("Android")
    }
}