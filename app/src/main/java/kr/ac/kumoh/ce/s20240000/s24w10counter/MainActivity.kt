package kr.ac.kumoh.ce.s20240000.s24w10counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Counter()
            Counter()
        }
    }
}

@Composable
fun Counter(modifier: Modifier = Modifier) {
    val (count, setCount) = remember { mutableIntStateOf(0) }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$count",
            fontSize = 100.sp,
        )
        Button(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            onClick = {
                setCount(count + 1)
                expanded = false
            }
        ) {
            Text(
                "증가",
                fontSize = 30.sp,
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            onClick = {
                expanded = !expanded
            }
        ) {
            Text(
                "더 보기",
                fontSize = 30.sp,
            )
        }
        AnimatedVisibility(expanded) {
            Row {
                Button(
                    modifier = Modifier.padding(16.dp).weight(1F),
                    onClick = {
                        if (count > 0)
                            setCount(count - 1)

                        expanded = false
                    }
                ) {
                    Text(
                        "감소",
                        fontSize = 30.sp,
                    )
                }
                Button(
                    modifier = Modifier.padding(16.dp).weight(1F),
                    onClick = {
                        setCount(0)
                        expanded = false
                    }
                ) {
                    Text(
                        "초기화",
                        fontSize = 30.sp,
                    )
                }
            }
        }
    }
}

