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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
    val (count1, setCount1) = rememberSaveable { mutableIntStateOf(0) }
    val (expanded1, setExpanded1) = rememberSaveable { mutableStateOf(false) }

    val (count2, setCount2) = rememberSaveable { mutableIntStateOf(0) }
    val (expanded2, setExpanded2) = rememberSaveable { mutableStateOf(false) }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            Counter(
                Modifier.padding(innerPadding),
                count1, setCount1,
                expanded1, setExpanded1,
            )
            Counter(
                Modifier.padding(innerPadding),
                count2, setCount2,
                expanded2, setExpanded2,
            )
        }
    }
}

@Composable
fun Counter(
    modifier: Modifier = Modifier,
    count: Int,
    onChangeCount: (Int) -> Unit,
    expanded: Boolean,
    onChangeExpanded: (Boolean) -> Unit,
) {
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
                onChangeCount(count + 1)
                onChangeExpanded(false)
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
                onChangeExpanded(!expanded)
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
                            onChangeCount(count - 1)

                        onChangeExpanded(false)
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
                        onChangeCount(0)
                        onChangeExpanded(false)
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
