package kr.ac.kumoh.ce.s20240000.s24w10counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import kr.ac.kumoh.ce.s20240000.s24w10counter.ui.theme.S24W10CounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            S24W10CounterTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Clicker(Modifier.padding(innerPadding))
    }
}

@Composable
fun Clicker(modifier: Modifier = Modifier) {
    //var txtString = "눌러주세요";
    val (txtString, setTxtString) = remember { mutableStateOf("눌러주세요") }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = txtString,
            fontSize = 60.sp,
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                //txtString = "눌렸습니다"
                setTxtString("눌렸습니다")
            }
        ) {
            Text("눌러봐")
        }
    }
}