package com.example.numberguessinggame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numberguessinggame.ui.theme.NumberGuessingGameTheme
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberGuessingGameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NumberGuessingGameScreen()
                }
            }
        }
    }
}
var randomRange: Int = (1..1000).random()
@Composable
fun NumberGuessingGameScreen(){
    var guessingValue by remember { mutableStateOf("")}
    val Guess = guessingValue.toIntOrNull() ?: 0
    val output = randomGuess(Guess, randomRange)
    Column (
        modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = stringResource(R.string.app_name),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left,
//            color = colorResource(R.color.white)

//            backgroundColor = colorResource(R.color.purple_700),
        )
       Text(text = stringResource(R.string.sub_title),
           fontSize = 20.sp,
           fontWeight = FontWeight.Bold,
           textAlign = TextAlign.Center,
       )
        Spacer(Modifier.height(184.dp))
        EnterNumber(value = guessingValue, onValueChange = { guessingValue = it})
        Spacer(Modifier.height(160.dp))
        Button(onClick = { randomRange = (1..1000).random() }) {
            Text(text = stringResource(R.string.retry), fontSize = 10.sp)
        }
        Spacer(Modifier.height(10.dp))
        Text(text = stringResource(R.string.hint, output),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = colorResource(R.color.grey)
        )


    }
}




@Composable
fun EnterNumber(
    value: String,
    onValueChange: (String) -> Unit,
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.placeholder))},
        modifier = Modifier.fillMaxWidth( ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = colorResource(R.color.white)
        )
    )
}

private fun randomGuess(
    Guess: Int,
    randomRange: Int
):String {
    var hint: String = ""
    if (Guess > randomRange){
        hint = " higher!"
    } else if (Guess < randomRange){
        hint = " lower!"
    } else{
        hint = " correct!"
    }
    return hint
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NumberGuessingGameTheme {
        NumberGuessingGameScreen()
    }
}