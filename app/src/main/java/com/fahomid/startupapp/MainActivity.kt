package com.fahomid.startupapp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var textView: TextView
    private lateinit var optionsSpinner: Spinner
    private lateinit var colorSpinner: Spinner
    private lateinit var editText: EditText
    private lateinit var buttonCompleteTask: Button

    private val defaultTextSize = 24f
    private val defaultTextColor = Color.BLACK
    private val defaultBackgroundColor = Color.WHITE

    private var currentTextSize = defaultTextSize
    private var currentTextColor = defaultTextColor
    private var currentBackgroundColor = defaultBackgroundColor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        constraintLayout = findViewById(R.id.constraintLayout)
        textView = findViewById(R.id.textview)
        optionsSpinner = findViewById(R.id.optionsSpinner)
        colorSpinner = findViewById(R.id.colorSpinner)
        editText = findViewById(R.id.editText)
        buttonCompleteTask = findViewById(R.id.buttonCompleteTask)

        // Initialize views with default values
        textView.textSize = currentTextSize
        textView.setTextColor(currentTextColor)
        constraintLayout.setBackgroundColor(currentBackgroundColor)

        // Options Spinner Adapter
        val optionsAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.options_items,
            android.R.layout.simple_spinner_item
        )
        optionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        optionsSpinner.adapter = optionsAdapter

        // Set default selection to "Select action"
        optionsSpinner.setSelection(0)

        // Color Spinner Adapter
        val colorAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.color_items,
            android.R.layout.simple_spinner_item
        )
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        colorSpinner.adapter = colorAdapter

        // Set default selection to "Select a color"
        colorSpinner.setSelection(0)

        // Options Spinner Listener
        optionsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                colorSpinner.visibility = View.GONE
                editText.visibility = View.GONE
                colorSpinner.setSelection(0)
                editText.text.clear()
                when (position) {
                    1 -> { // Change Background
                        colorSpinner.visibility = View.VISIBLE
                        colorSpinner.setSelection(getColorPosition(currentBackgroundColor))
                    }
                    2 -> { // Change Text Color
                        colorSpinner.visibility = View.VISIBLE
                        colorSpinner.setSelection(getColorPosition(currentTextColor))
                    }
                    3 -> { // Change TextView Text Size
                        editText.visibility = View.VISIBLE
                        editText.inputType = android.text.InputType.TYPE_CLASS_NUMBER
                        editText.hint = "Enter text size in sp"
                        editText.setText(currentTextSize.toInt().toString())
                    }
                    4 -> { // Edit TextView
                        editText.visibility = View.VISIBLE
                        editText.inputType = android.text.InputType.TYPE_CLASS_TEXT
                        editText.hint = "Edit TextView"
                        editText.setText(textView.text)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        // Button Complete Task Listener
        buttonCompleteTask.setOnClickListener {
            val selectedOption = optionsSpinner.selectedItem.toString()
            when (selectedOption) {
                "Select An Action" -> {
                    showSnackbar("Please select an action!", true)
                }
                "Change Background" -> {
                    val selectedColor = colorSpinner.selectedItem.toString()
                    if (selectedColor == "Select A Color") {
                        showSnackbar("Please select a color!", true)
                    } else {
                        currentBackgroundColor = getColorFromString(selectedColor)
                        constraintLayout.setBackgroundColor(currentBackgroundColor)
                        showSnackbar("Background color changed to $selectedColor!", false)
                    }
                }
                "Change Text Color" -> {
                    val selectedColor = colorSpinner.selectedItem.toString()
                    if (selectedColor == "Select A Color") {
                        showSnackbar("Please select a color!", true)
                    } else {
                        currentTextColor = getColorFromString(selectedColor)
                        textView.setTextColor(currentTextColor)
                        showSnackbar("Text color changed to $selectedColor!", false)
                    }
                }
                "Change TextView Text Size" -> {
                    val newSize = editText.text.toString().toFloatOrNull()
                    if (newSize != null) {
                        if (newSize in 14f..60f) {
                            currentTextSize = newSize
                            textView.textSize = currentTextSize
                            showSnackbar("Text size changed to ${newSize}sp!", false)
                        } else {
                            showSnackbar("Text size must be between 14sp and 60sp!", true)
                        }
                    } else {
                        showSnackbar("Please enter a valid number for text size!", true)
                    }
                }
                "Edit TextView" -> {
                    val newText = editText.text.toString()
                    if (newText.isNotEmpty()) {
                        textView.text = newText
                        showSnackbar("TextView text changed!", false)
                    } else {
                        showSnackbar("Please enter text!", true)
                    }
                }
                "Reset Views" -> {
                    resetViews()
                    showSnackbar("Views have been reset!", false)
                }
            }
        }
    }

    private fun showSnackbar(message: String, isError: Boolean) {
        val snackbar = Snackbar.make(constraintLayout, message, Snackbar.LENGTH_SHORT)
        if (isError) {
            snackbar.setBackgroundTint(Color.RED)
        } else {
            snackbar.setBackgroundTint(Color.GREEN)
        }
        snackbar.show()
    }

    private fun getColorFromString(colorString: String): Int {
        return when (colorString) {
            "Red" -> Color.RED
            "Green" -> Color.GREEN
            "Blue" -> Color.BLUE
            "Yellow" -> Color.YELLOW
            "Black" -> Color.BLACK
            "White" -> Color.WHITE
            "Gray" -> Color.GRAY
            "Cyan" -> Color.CYAN
            "Magenta" -> Color.MAGENTA
            else -> Color.WHITE
        }
    }

    private fun getColorPosition(color: Int): Int {
        return when (color) {
            Color.RED -> 1
            Color.GREEN -> 2
            Color.BLUE -> 3
            Color.YELLOW -> 4
            Color.BLACK -> 5
            Color.WHITE -> 6
            Color.GRAY -> 7
            Color.CYAN -> 8
            Color.MAGENTA -> 9
            else -> 0
        }
    }

    private fun resetViews() {
        textView.text = "Welcome!"
        currentTextColor = defaultTextColor
        currentTextSize = defaultTextSize
        currentBackgroundColor = defaultBackgroundColor
        textView.setTextColor(currentTextColor)
        textView.textSize = currentTextSize
        constraintLayout.setBackgroundColor(currentBackgroundColor)
        optionsSpinner.setSelection(0)
        colorSpinner.setSelection(0)
        editText.text.clear()
    }
}
