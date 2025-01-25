package com.esoapps.cleanarchweather.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.esoapps.domain.model.MainModel
import com.esoapps.domain.ext.Resource
import com.esoapps.cleanarchweather.utils.Constants.DEFAULT_LOCATION
import com.esoapps.cleanarchweather.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(
                modifier = Modifier.fillMaxSize(),

                content = { padding ->
                    MainScreen()
                },
            )
        }
    }
}

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {

    val currentState by viewModel.currentState.collectAsState()

    val (text, onValueChange) = remember { mutableStateOf(DEFAULT_LOCATION) }
    LaunchedEffect(true) {
        viewModel.getDataFromApi(text)
    }
    when (currentState) {
        is Resource.Loading -> {
            LoadingUi()
        }

        is Resource.Error -> {

            ErrorUi(currentState.message.toString())
        }

        is Resource.Success -> {

            SuccessUi(
                currentState.data as MainModel,
                text = text,
                onValueChange = onValueChange
            ) {

                viewModel.getDataFromApi(it)
            }
        }


    }


}


@Composable
fun SuccessUi(
    current: MainModel,
    text: String,
    onValueChange: (String) -> Unit,
    btnClicked: (String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            label = { Text("Enter City Name") },
            singleLine = true
        )

        Button(
            onClick = { btnClicked(text) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            Text(text = "Get Weather")
        }
        Spacer(modifier = Modifier.height(25.dp))

        AsyncImage(
            modifier = Modifier
                .size(80.dp),
            contentDescription = "",
            model = "https:${current.condition.icon}",
        )
        Text(
            text = current.condition.text,
            modifier = Modifier,
            style = TextStyle(fontSize = 16.sp, color = MaterialTheme.colorScheme.onBackground)
        )

        Text(
            text = text,
            modifier = Modifier.padding(45.dp),
            style = TextStyle(fontSize = 21.sp, color = MaterialTheme.colorScheme.onBackground)
        )


        Text(
            text = "${current.tempC} C",
            modifier = Modifier,
            style = TextStyle(fontSize = 21.sp, color = MaterialTheme.colorScheme.onBackground)
        )

        MiniCards(
            title = "Humidity",
            value = "${current.humidity}%",
        )


        MiniCards(
            title = "Cloud",
            value = "${current.cloud} ",
        )

    }
}


@Composable
fun MiniCards(
    title: String,
    value: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = title,
                style = TextStyle(fontSize = 18.sp, color = MaterialTheme.colorScheme.onBackground)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = value,
                style = TextStyle(fontSize = 16.sp, color = MaterialTheme.colorScheme.onBackground)
            )

        }

    }

}

@Composable
fun LoadingUi() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        CircularProgressIndicator(
            color = Color.Red,
        )
    }
}

@Composable
fun ErrorUi(msg: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Text(
            text = msg,
            modifier = Modifier,
            style = TextStyle(fontSize = 16.sp, color = Color.Red)
        )
    }
}