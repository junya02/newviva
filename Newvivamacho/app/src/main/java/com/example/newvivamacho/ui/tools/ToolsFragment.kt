package com.example.newvivamacho.ui.tools

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.newvivamacho.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.fragment_tools.*
import java.lang.Math.log
import kotlin.math.log

class ToolsFragment : Fragment() {

    // スタイルとフォントファミリーの設定
    private var mTypeface = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)
    // データの個数
    private val chartDataCount = 20

    // ↓この定義が必要（追加）
    private var lineC: LineChart? = null
    private var weightText: EditText? = null
    private var weight: ArrayList<Float?> = arrayListOf()

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_tools, container, false)
        // ↓この定義が必要（追加）
        lineC = root.findViewById(R.id.lineChart)
        weightText = root.findViewById(R.id.weightText)
        // グラフの設定
        //setupLineChart()
        // データの設定
        //lineC!!.data = lineData(chartDataCount, 100f)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendbutton.setOnClickListener {
            weight.add(weightText!!.text.toString().toFloatOrNull())
            //lineC = root.findViewById(R.id.lineChart)
            //weightText = root.findViewById(R.id.weightText)
            // グラフの設定
            //if (weight.size >= 1) {
                setupLineChart()
                lineC!!.data = lineData(chartDataCount, 100f)
            //}
            

        }
    }

    // LineChart用のデータ作成
    private fun lineData(count: Int, range: Float): LineData {

        val values = mutableListOf<Entry>()

        for (i in weight.indices) {
            // 値はランダムで表示させる
            //(Math.random() * (range)).toFloat()
              //        val value = (Math.random() * (range)).toFloat()
            //values.add(Entry(i.toFloat(), value))
            values.add(Entry(i.toFloat(), weight[i]!!.toFloat()))
            //weight=weight+1
        }

        // グラフのレイアウトの設定
        val yVals = LineDataSet(values, "テストデータ").apply {
            axisDependency = YAxis.AxisDependency.LEFT
            color = Color.BLACK
            // タップ時のハイライトカラー
            highLightColor = Color.YELLOW
            setDrawCircles(true)
            setDrawCircleHole(true)
            // 点の値非表示
            setDrawValues(true)
            // 線の太さ
            lineWidth = 2f
        }
        val data = LineData(yVals)
        return data
    }

    private fun setupLineChart() {
        lineC!!.apply {
            description.isEnabled = false
            setTouchEnabled(true)
            isDragEnabled = true
            // 拡大縮小可能
            isScaleXEnabled = true
            setPinchZoom(true)
            setDrawGridBackground(true)

            //データラベルの表示
            legend.apply {
                form = Legend.LegendForm.LINE
                typeface = mTypeface
                textSize = 11f
                textColor = Color.BLACK
                verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
                horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
                orientation = Legend.LegendOrientation.HORIZONTAL
                setDrawInside(true)
            }

            //y軸右側の設定
            axisRight.isEnabled = false

            //X軸表示
            xAxis.apply {
                typeface = mTypeface
                setDrawLabels(false)
                // 格子線を表示する
                setDrawGridLines(true)
            }

            //y軸左側の表示
            axisLeft.apply {
                typeface = mTypeface
                textColor = Color.BLACK
                // 格子線を表示する
                setDrawGridLines(false)
            }
        }
    }
}

