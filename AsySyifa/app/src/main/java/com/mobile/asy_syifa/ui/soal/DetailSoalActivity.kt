package com.mobile.asy_syifa.ui.soal

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.RelativeSizeSpan
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.mobile.asy_syifa.R
import com.mobile.asy_syifa.data.ResultData
import com.mobile.asy_syifa.databinding.ActivityDetailSoalBinding

class DetailSoalActivity : AppCompatActivity() {


    private lateinit var binding: ActivityDetailSoalBinding

    //Daftar soal
    private var questionText = arrayOf(
        "apa bahasa inggris dari bekam?",//1
        "Apa organisasi resmi yang menaungi pembekam di Indonesia?",//2
        "Apa kepanjangan dari PBI?",//3
        "Apa fungsi Alkohol 70% dalam praktek bekam?",//4
        "Pada zaman Rasulullah, apa yang beliau gunakan untuk melakukan pengobatan bekam?",//5
        "Dibawah ini yang bukan merupakan peralatan bekam SOP PBI adalah...",//6
        "Jelaskan fungsi dari minyak herbal dalam pengobatan bekam!",//7
        "Sebutkan salah satu larangan bekamm?",//8
        "Sebutkan salah sastu perlengkapan bekam klasik!",//9
        "Sebutkan salah satu perlengkapan bekam SOP PBI",//10
        "Yang termasuk titik bekam Nabawi adalah...",//11
        "Bagian tubuh yang dilarang untuk berbekam adalah...",//12
        "Apa kondisi khusus yang dimana pasien dilarang berbekam?"//13
    )

    //Daftar Jawaban
    private var option = arrayOf(
        arrayOf(
            "Blood Cupping",
            "Blood Strike",
            "Reverse Blood"
        ),//1
        arrayOf(
            "PBI",
            "PBSI",
            "PSSI"
        ),//2
        arrayOf(
            "Praktisi Bekam Indonesia",
            "Perserikatan Bekam Indonesia",
            "Perkumpulan Bekam Indonesia"
        ),//3
        arrayOf(
            "Untuk dioleskan pada pasien",
            "Untuk mengurangi rasa sakit",
            "Untuk membersihkan peralatan bekam"
        ),//4
        arrayOf(
            "Kop Plastik",
            "Cawan Kaca",
            "Kuali"
        ),//5
        arrayOf(
            "Kop",
            "Lancet",
            "Bambu"
        ),//6
        arrayOf(
            "Sebagai media pelembut kulit",
            "Untuk membersihkan kop bekam",
            "untuk melindungi tubuh"
        ),//7
        arrayOf(
            "Hamil",
            "Lapar",
            "Gangguan Jiwa"
        ),//8
        arrayOf(
            "Tanduk Sapi",
            "Rumput",
            "Jubah"
        ),//9
        arrayOf(
            "Piring",
            "Mangkok Stainless",
            "Detergen"
        ),//10
        arrayOf(
            "Al-'Ain",
            "Al-Hammah",
            "Al-Hammam",
        ),//11
        arrayOf(
            "Leher bagian depan",
            "Leher bagian samping",
            "leher bagian belakang",
        ),//12
        arrayOf(
            "Penderita Stroke Akut",
            "Penderita dehindrasi walaupun ringan",
            "Penderita gagal ginjal"
        ),//13
    )

    //Kunci jawaban
    private var correctAnswer = arrayOf(0, 0, 2, 2, 1, 2, 0, 0, 0, 1, 1, 2, 1)

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSoalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playAnimation()
        shuffleSoal()
        displayQuestion()

        binding.btnOption1.setOnClickListener {
            checkAnswerr(0)
        }

        binding.btnOption2.setOnClickListener {
            checkAnswerr(1)
        }

        binding.btnOption3.setOnClickListener {
            checkAnswerr(2)
        }

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    //Menampilkan soal secara random
    private fun shuffleSoal() {
        val shuffledIndices = questionText.indices.shuffled()
        val shuffledQuestions = shuffledIndices.take(5).toTypedArray()

        val shuffledOption = mutableListOf<Array<String>>()
        val shuffledCorrectAnswer = mutableListOf<Int>()
        val shuffledSoal = mutableListOf<String>()

        for (index in shuffledQuestions) {
            shuffledOption.add(option[index])
            shuffledCorrectAnswer.add(correctAnswer[index])
            shuffledSoal.add(questionText[index])
        }

        option = shuffledOption.toTypedArray()
        correctAnswer = shuffledCorrectAnswer.toTypedArray()
        questionText = shuffledSoal.toTypedArray()
    }

    //Menampilkan soal
    private fun displayQuestion() {
        binding.tvSoal.text = questionText[currentQuestionIndex]
        binding.btnOption1.text = option[currentQuestionIndex][0]
        binding.btnOption2.text = option[currentQuestionIndex][1]
        binding.btnOption3.text = option[currentQuestionIndex][2]
        resetButtonColors()
    }

    //Memeriksa jawaban
    private fun checkAnswerr(selectedAnswerIndex: Int) {
        val correctAnswerIndex = correctAnswer[currentQuestionIndex]

        if (selectedAnswerIndex == correctAnswerIndex) {
            score++
            correctButtonColors(selectedAnswerIndex)
        } else {
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }
        if (currentQuestionIndex < questionText.size - 1) {
            currentQuestionIndex++
            binding.tvSoalJudul.postDelayed({displayQuestion()}, 1000)
        } else {
            showResult()
        }
    }

    //Menentukan warna pada jawaban yang benar
    private fun correctButtonColors(buttonIndex: Int) {
        when(buttonIndex) {
            0 -> binding.btnOption1.setBackgroundColor(Color.GREEN)
            1 -> binding.btnOption2.setBackgroundColor(Color.GREEN)
            2 -> binding.btnOption3.setBackgroundColor(Color.GREEN)
        }
    }

    //Menentukan warna pada jawaban yang salah
    private fun wrongButtonColors(buttonIndex: Int) {
        when(buttonIndex) {
            0 -> binding.btnOption1.setBackgroundColor(Color.RED)
            1 -> binding.btnOption2.setBackgroundColor(Color.RED)
            2 -> binding.btnOption3.setBackgroundColor(Color.RED)
        }
    }

    //Mereset warna jawaban
    private fun resetButtonColors() {
        binding.btnOption1.setBackgroundColor(Color.rgb(0, 78, 113))
        binding.btnOption2.setBackgroundColor(Color.rgb(0, 78, 113))
        binding.btnOption3.setBackgroundColor(Color.rgb(0, 78, 113))
//        binding.btnOption3.setBackgroundColor(Color.rgb(50, 59, 96))
    }

    //Menampilkan nilai hasil
    private fun showResult() {
        val scaledScore = (score.toDouble() / questionText.size.toDouble() * 100).toInt()
        val resultMessage = "Kamu Mendapat Nilai\n$scaledScore"

        val dialogView = layoutInflater.inflate(R.layout.layout_pop_up, null)
        val textResult = dialogView.findViewById<TextView>(R.id.tv_result)
        val ivEmote = dialogView.findViewById<ImageView>(R.id.ivEmote)
        val tvDesc = dialogView.findViewById<TextView>(R.id.tv_desc)
        val buttonAgain = dialogView.findViewById<Button>(R.id.btn_again)
        val buttonBack = dialogView.findViewById<Button>(R.id.btn_back)

        val spannableStringBuilder = SpannableStringBuilder(resultMessage)
        val spanStart = resultMessage.indexOf("$scaledScore")
        val spanEnd = spanStart + scaledScore.toString().length

        spannableStringBuilder.setSpan(
            RelativeSizeSpan(1.5f),
            spanStart,
            spanEnd,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        textResult.text = spannableStringBuilder

        val resultData = getResultData(scaledScore)
        ivEmote.setImageResource(resultData.emoteResourceId)
        tvDesc.text = resultData.descriptionMessage

        val alertDialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()

        buttonAgain.setOnClickListener {
            restartQuiz()
            alertDialog.dismiss()
        }

        buttonBack.setOnClickListener {
            finish()
        }

    }

    //Mengulang soal dari awal
    private fun restartQuiz() {
        currentQuestionIndex = 0
        score = 0
        shuffleSoal()
        displayQuestion()
    }

    //Mengelompokkan emote berdasarkan nilai
    private fun getResultData(score: Int): ResultData {
        return when {
            score >= 100 -> ResultData(R.drawable.emote1, getString(R.string.score1))
            score in 80..99 -> ResultData(R.drawable.emote2, getString(R.string.score2))
            score in 60..79 -> ResultData(R.drawable.emote3, getString(R.string.score3))
            score in 40..59 -> ResultData(R.drawable.emote4, getString(R.string.score4))
            score in 20..39 -> ResultData(R.drawable.emote5, getString(R.string.score5))
            score in 0..19 -> ResultData(R.drawable.emote4, getString(R.string.score5))
            else -> ResultData(R.drawable.emote3, getString(R.string.score_else))
        }
    }

    private fun playAnimation() {
        val soal = ObjectAnimator.ofFloat(binding.tvSoal, View.ALPHA, 1f).setDuration(1000)
        val jwb1 = ObjectAnimator.ofFloat(binding.btnOption1, View.ALPHA, 1f).setDuration(1000)
        val jwb2 = ObjectAnimator.ofFloat(binding.btnOption2, View.ALPHA, 1f).setDuration(1000)
        val jwb3 = ObjectAnimator.ofFloat(binding.btnOption3, View.ALPHA, 1f).setDuration(1000)

        AnimatorSet().apply {
            playTogether(soal, jwb1, jwb2, jwb3)
            start()
        }
    }
}