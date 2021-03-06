package com.example.newvivamacho.ui.graph

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.newvivamacho.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.fragment_graph.*

class GraphFragment : Fragment() {

    // スタイルとフォントファミリーの設定
    private var mTypeface = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)

    // ↓この定義が必要（追加）
    private var lineC: LineChart? = null
    private var weightText: EditText? = null
    private var weight: ArrayList<Float?> = arrayListOf()

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_graph, container, false)
        // ↓この定義が必要（追加）
        lineC = root.findViewById(R.id.lineChart)
        weightText = root.findViewById(R.id.weightText)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendbutton.setOnClickListener {
            weight.add(weightText!!.text.toString().toFloatOrNull())
            //lineC = root.findViewById(R.id.lineChart)
            //weightText = root.findViewById(R.id.weightText)

            // グラフの設定
            if (weight.size >= 0) {
                setupLineChart()
                lineC!!.data = lineData(weight.size, 100f)

            }
        }
    }

    // LineChart用のデータ作成
    private fun lineData(count : Int, range: Float): LineData {

        val values = mutableListOf<Entry>()


        for (i in weight.indices) {

            values.add(Entry(i.toFloat(), weight[i]!!.toFloat()))

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
                setDrawInside(false)
            }

            //y軸右側の設定
            axisRight.isEnabled = false

            //X軸表示
            xAxis.apply {
                typeface = mTypeface
                setDrawLabels(false)
                // 格子線を表示する
                setDrawGridLines(false)
            }

            //y軸左側の表示
            axisLeft.apply {
                typeface = mTypeface
                textColor = Color.BLACK
                // 格子線を表示する
                setDrawGridLines(true)
            }
        }
    }
}

