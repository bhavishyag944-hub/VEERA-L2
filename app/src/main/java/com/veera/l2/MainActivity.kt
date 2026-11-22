package com.veera.l2

import android.os.Bundle
import android.net.Uri
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : ComponentActivity() {

    private lateinit var statusText: TextView

    private val videoPicker =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            uri: Uri? ->
            if (uri != null) {
                statusText.text = "Video Selected Successfully"
                Toast.makeText(
                    this,
                    "Video added to VEERA L2",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = Color.rgb(12, 10, 22)
        window.navigationBarColor = Color.BLACK

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 35, 24, 24)
            setBackgroundColor(Color.rgb(12, 10, 22))
        }

        val scroll = ScrollView(this)
        val content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        val title = TextView(this).apply {
            text = "VEERA L2"
            textSize = 34f
            gravity = Gravity.CENTER
            setTextColor(Color.rgb(170, 100, 255))
        }

        val subtitle = TextView(this).apply {
            text = "PROFESSIONAL AI VIDEO EDITOR"
            textSize = 13f
            gravity = Gravity.CENTER
            setTextColor(Color.WHITE)
        }

        statusText = TextView(this).apply {
            text = "Welcome to VEERA L2"
            textSize = 16f
            gravity = Gravity.CENTER
            setTextColor(Color.WHITE)
            setPadding(10, 25, 10, 25)
        }

        content.addView(title)
        content.addView(subtitle)
        content.addView(statusText)

        addSection(content, "VIDEO STUDIO")

        addButton(content, "＋ Import Video") {
            videoPicker.launch("video/*")
        }

        addButton(content, "🤖 AI Auto Editing") {
            showMessage("AI Editing Engine will be connected next.")
        }

        addSection(content, "PROFESSIONAL TOOLS")

        addButton(content, "🎬 Video Timeline") {
            showMessage("Multi-track Timeline")
        }

        addButton(content, "🎨 Color Grading") {
            showMessage("Color Grading Studio")
        }

        addButton(content, "✨ Cinematic Effects") {
            showMessage("Cinematic Effects Studio")
        }

        addButton(content, "🎵 Audio Studio") {
            showMessage("Audio Studio")
        }

        addButton(content, "🎥 Export Video") {
            showMessage("Export Engine will be connected next.")
        }

        val footer = TextView(this).apply {
            text = "VEERA L2  •  CREATE BEYOND LIMITS"
            gravity = Gravity.CENTER
            textSize = 11f
            setTextColor(Color.LTGRAY)
            setPadding(5, 35, 5, 15)
        }

        content.addView(footer)
        scroll.addView(content)
        root.addView(
            scroll,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )

        setContentView(root)
    }

    private fun addSection(
        parent: LinearLayout,
        name: String
    ) {
        val text = TextView(this).apply {
            this.text = name
            textSize = 17f
            setTextColor(Color.rgb(100, 160, 255))
            setPadding(5, 25, 5, 12)
        }
        parent.addView(text)
    }

    private fun addButton(
        parent: LinearLayout,
        name: String,
        action: () -> Unit
    ) {
        val button = Button(this).apply {
            text = name
            textSize = 15f
            setTextColor(Color.WHITE)
            setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(
                    Color.rgb(65, 35, 110)
                )
            )
            setOnClickListener { action() }
        }

        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            60
        )
        params.setMargins(0, 7, 0, 7)
        parent.addView(button, params)
    }

    private fun showMessage(message: String) {
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}
