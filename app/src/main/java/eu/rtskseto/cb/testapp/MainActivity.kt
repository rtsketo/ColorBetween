package eu.rtskseto.cb.testapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import eu.rtskseto.cb.core.ColorBetween

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ContentView() }
    }

    @Composable
    fun ContentView() {
        var color by remember { mutableStateOf(White) }
        var slider by remember { mutableStateOf(0f) }
        var first by remember { mutableStateOf("#201F3F") }
        var second by remember { mutableStateOf("#10AFAA") }

        fun updateColor() = ColorBetween(
            oldParse(first),
            oldParse(second)
        ).get(slider).toNew()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color)
        )
        Column {
            Slider(
                value = slider,
                onValueChange = {
                    slider = it
                    color = updateColor()
                },
                modifier = Modifier
                    .background(White)
            )

            Row {
                TextField(
                    value = first,
                    onValueChange = { first = it },
                    label = { Text("First color") },
                    modifier = Modifier
                        .weight(1f)
                        .background(White)
                )

                TextField(
                    value = second,
                    onValueChange = { second = it },
                    label = { Text("Second color") },
                    modifier = Modifier
                        .weight(1f)
                        .background(White)
                )
            }
        }
    }

    @Preview
    @Composable
    fun Preview() { ContentView() }

    private fun Color.toOld() =
        android.graphics.Color.argb(
            toArgb().alpha,
            toArgb().red,
            toArgb().green,
            toArgb().blue
        )

    private fun Int.toNew() =
        Color(this)

    private fun oldParse(color: String) =
        try {
            android.graphics.Color.parseColor(
                if (color.startsWith("#")) color
                else "#$color"
            )
        } catch (e: Throwable) {
            android.graphics.Color.WHITE
        }
}
