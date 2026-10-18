package com.example.vangtichai

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var tvTakaDisplay: TextView
    private lateinit var tvNote500: TextView
    private lateinit var tvNote100: TextView
    private lateinit var tvNote50: TextView
    private lateinit var tvNote20: TextView
    private lateinit var tvNote10: TextView
    private lateinit var tvNote5: TextView
    private lateinit var tvNote2: TextView
    private lateinit var tvNote1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        setupListeners()
        observeViewModel()
    }

    private fun initViews() {
        tvTakaDisplay = findViewById(R.id.tv_taka_display)
        tvNote500 = findViewById(R.id.tv_note_500)
        tvNote100 = findViewById(R.id.tv_note_100)
        tvNote50 = findViewById(R.id.tv_note_50)
        tvNote20 = findViewById(R.id.tv_note_20)
        tvNote10 = findViewById(R.id.tv_note_10)
        tvNote5 = findViewById(R.id.tv_note_5)
        tvNote2 = findViewById(R.id.tv_note_2)
        tvNote1 = findViewById(R.id.tv_note_1)
    }

    private fun setupListeners() {
        val digitButtonsMap = mapOf(
            R.id.btn_0 to 0,
            R.id.btn_1 to 1,
            R.id.btn_2 to 2,
            R.id.btn_3 to 3,
            R.id.btn_4 to 4,
            R.id.btn_5 to 5,
            R.id.btn_6 to 6,
            R.id.btn_7 to 7,
            R.id.btn_8 to 8,
            R.id.btn_9 to 9,
        )

        for ((buttonId, digit) in digitButtonsMap) {
            findViewById<Button>(buttonId)?.setOnClickListener {
                viewModel.onDigitClicked(digit)
            }
        }

        findViewById<Button>(R.id.btn_clear)?.setOnClickListener {
            viewModel.onClearClicked()
        }
    }

    private fun observeViewModel() {
        viewModel.amountText.observe(this) { amount ->
            if (amount.isEmpty()) {
                tvTakaDisplay.text = getString(R.string.taka_display_empty)
            } else {
                tvTakaDisplay.text = "${getString(R.string.taka_label_prefix)}$amount"
            }
        }

        viewModel.changeMap.observe(this) { change ->
            tvNote500.text = "500: ${change[500] ?: 0L}"
            tvNote100.text = "100: ${change[100] ?: 0L}"
            tvNote50.text = "50: ${change[50] ?: 0L}"
            tvNote20.text = "20: ${change[20] ?: 0L}"
            tvNote10.text = "10: ${change[10] ?: 0L}"
            tvNote5.text = "5: ${change[5] ?: 0L}"
            tvNote2.text = "2: ${change[2] ?: 0L}"
            tvNote1.text = "1: ${change[1] ?: 0L}"
        }
    }
}
