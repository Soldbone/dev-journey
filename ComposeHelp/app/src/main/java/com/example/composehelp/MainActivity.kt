package com.example.composehelp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composehelp.ui.theme.ComposeHelpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeHelpTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HelpImage(
                        header = stringResource(R.string.jetpack_compose_tutorial_header),
                        p1 = stringResource(R.string.jetpack_compose_tutorial_p1),
                        p2 = stringResource(R.string.jetpack_compose_tutorial_p2),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun HelpText(header: String, p1: String, p2: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = header,
            fontSize = 24.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(16.dp)
        )
        Text(
            text = p1,
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Justify
        )
        Text(
            text = p2,
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun HelpImage(header: String, p1: String, p2: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.bg_compose_background)
    Column(modifier = modifier) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        HelpText(
            header = header,
            p1 = p1,
            p2 = p2
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeHelpTheme {
        HelpImage(
            header = stringResource(R.string.jetpack_compose_tutorial_header),
            p1 = stringResource(R.string.jetpack_compose_tutorial_p1),
            p2 = stringResource(R.string.jetpack_compose_tutorial_p2)
        )
    }
}