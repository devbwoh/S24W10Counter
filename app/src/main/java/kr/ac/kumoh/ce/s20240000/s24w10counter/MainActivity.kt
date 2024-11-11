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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kr.ac.kumoh.ce.s20240000.s24w10counter.ui.theme.S24W10CounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //val vm = ViewModelProvider(this)[CounterViewModel::class.java]

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            S24W10CounterTheme {
                //MainScreen(vm)
                MainScreen()
            }
        }
    }
}

@Composable
//fun MainScreen(viewModel: CounterViewModel) {
//fun MainScreen() {
fun MainScreen(viewModel: CounterViewModel = viewModel()) {
    //val (count, setCount) = rememberSaveable { mutableIntStateOf(0) }

    //val viewModel: CounterViewModel = viewModel()

    val count by viewModel.count.observeAsState(0)
    val (expanded, setExpanded) = rememberSaveable { mutableStateOf(false) }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            Counter(
                Modifier.padding(innerPadding),
                count,
                { viewModel.onAdd() },
                { viewModel.onSub() },
                { viewModel.onReset() },
                expanded, setExpanded,
            )
        }
    }
}

@Composable
fun Counter(
    modifier: Modifier = Modifier,
    count: Int,
    onAdd: () -> Unit,
    onSub: () -> Unit,
    onReset: () -> Unit,
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
                onAdd()
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
                            onSub()

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
                        onReset()
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
