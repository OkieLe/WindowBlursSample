package io.github.aaos.blurs

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.aaos.blurs.ui.theme.BlursSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BlursSampleTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.Transparent
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        Button(
                            modifier = Modifier
                                .padding(horizontal = 24.dp, vertical = 16.dp)
                                .align(Alignment.CenterStart)
                                .padding(vertical = 16.dp),
                            onClick = { finish() }
                        ) {
                            Text(text = "FINISH", style = MaterialTheme.typography.displayLarge)
                        }
                        Column(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .wrapContentSize()
                        ) {
                            BlurButton(
                                text = "BACKGROUND BLUR",
                                targetActivity = BackgroundBlurDemo::class.java,
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            BlurButton(
                                clicker = ::showWindowBackgroundBlur,
                                text = "PARTIAL BACKGROUND BLUR"
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            BlurButton(
                                text = "BLUR BEHIND",
                                targetActivity = BlurBehindDemo::class.java
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            BlurButton(
                                clicker = ::showWindowBlurBehind,
                                text = "PARTIAL BLUR BEHIND"
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            BlurButton(
                                text = "DIM BEHIND",
                                targetActivity = DimBehindDemo::class.java
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            BlurButton(
                                clicker = ::showWindowDimBehind,
                                text = "PARTIAL DIM BEHIND"
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun ColumnScope.BlurButton(
        text: String, clicker: (() -> Unit)? = null,
        targetActivity: Class<out Activity>? = null
    ) {
        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp),
            onClick = {
                clicker?.invoke() ?: targetActivity?.let {
                    startActivity(
                        Intent(
                            this@MainActivity,
                            it
                        )
                    )
                }
            }
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.displayLarge
            )
        }
    }

    private val viewBackgroundBlur by lazy {
        layoutInflater.inflate(
            R.layout.view_backgrond_blur,
            findViewById(android.R.id.content), false
        )
    }
    private val dialogBackgroundBlur by lazy {
        Dialog(this, R.style.Theme_BackgroundBlur_Dialog_Demo).also { dialog ->
            dialog.setContentView(viewBackgroundBlur)
            dialog.window?.setLayout(1080, 720)
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)
            dialog.setOwnerActivity(this)
            dialog.setTitle("Partial Background Blur")
            viewBackgroundBlur.findViewById<View>(R.id.dismiss).setOnClickListener {
                dialog.dismiss()
            }
        }
    }

    private fun showWindowBackgroundBlur() {
        dialogBackgroundBlur.show()
    }

    private val viewBlurBehind by lazy {
        layoutInflater.inflate(
            R.layout.view_blur_behind,
            findViewById(android.R.id.content), false
        )
    }
    private val dialogBlurBehind by lazy {
        Dialog(this, R.style.Theme_BlurBehind_Dialog_Demo).also { dialog ->
            dialog.setContentView(viewBlurBehind)
            dialog.window?.setLayout(1080, 720)
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)
            dialog.setOwnerActivity(this)
            dialog.setTitle("Partial Blur Behind")
            viewBlurBehind.findViewById<View>(R.id.dismiss).setOnClickListener {
                dialog.dismiss()
            }
        }
    }

    private fun showWindowBlurBehind() {
        dialogBlurBehind.show()
    }

    private val viewDimBehind by lazy {
        layoutInflater.inflate(
            R.layout.view_dim_behind,
            findViewById(android.R.id.content), false
        )
    }
    private val dialogDimBehind by lazy {
        Dialog(this, R.style.Theme_DimBehind_Dialog_Demo).also { dialog ->
            dialog.setContentView(viewDimBehind)
            dialog.window?.setLayout(1080, 720)
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)
            dialog.setOwnerActivity(this)
            dialog.setTitle("Partial Dim Behind")
            viewDimBehind.findViewById<View>(R.id.dismiss).setOnClickListener {
                dialog.dismiss()
            }
        }
    }

    private fun showWindowDimBehind() {
        dialogDimBehind.show()
    }
}
