package dk.clausr.anchoreddraggableexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dk.clausr.anchoreddraggableexample.ui.theme.AnchoredDraggableExampleTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val coroutineScope = rememberCoroutineScope()
            val swiperState = rememberSwiperState {
                Log.d("MainAct", "Swipe done")
            }
            AnchoredDraggableExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Swiper(
                            text = "Test",
                            enabled = true,
                            modifier = Modifier.padding(horizontal = 16.dp),
                            state = swiperState,
                        )

                        Button(
                            modifier = Modifier.padding(top = 16.dp),
                            onClick = {
                                coroutineScope.launch { swiperState.reset() }
                            },
                        ) {
                            Text(text = "Reset")
                        }
                    }
                }
            }
        }
    }
}
