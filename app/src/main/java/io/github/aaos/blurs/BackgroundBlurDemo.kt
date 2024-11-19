package io.github.aaos.blurs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.aaos.blurs.ui.theme.BlursSampleTheme

class BackgroundBlurDemo : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlursSampleTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.Transparent,
                ) { padding ->
                    Column(
                        modifier = Modifier
                            .padding(padding)
                            .fillMaxSize()
                    ) {
                        Button(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(vertical = 24.dp),
                            onClick = { finish() }
                        ) {
                            Text(text = "FINISH", style = MaterialTheme.typography.displayLarge)
                        }
                        Row(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .fillMaxHeight()
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.CenterVertically),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.displayLarge.copy(color = Color.Magenta),
                                text = "BACKGROUND BLUR"
                            )
                        }
                    }
                }
            }
        }
    }
}
