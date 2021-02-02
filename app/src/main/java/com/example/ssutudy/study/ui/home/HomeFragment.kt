package com.example.ssutudy.study.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.ssutudy.R
import com.example.ssutudy.study.dto.StudyLog
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.fragment_main_home.view.*

class HomeFragment : Fragment() {
    private lateinit var pieChart : PieChart

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_home, container, false)

        //pie
        val entries : ArrayList<PieEntry> = arrayListOf()

        entries.add(PieEntry(900f, 0))
        entries.add(PieEntry(1000f, 1))
        entries.add(PieEntry(1100f, 2))
        entries.add(PieEntry(800f, 3))
        entries.add(PieEntry(700f, 4))

        val dataset = PieDataSet(entries, "entries")

        val years : ArrayList<String> = arrayListOf()
        years.add("900")
        years.add("1000f")
        years.add("1100f")
        years.add("800f")
        years.add("700f")

        val data = PieData(dataset)
        pieChart = view.chart_each_study_time
        pieChart.data = data
        dataset.colors = ColorTemplate.COLORFUL_COLORS.asList()
        pieChart.animateXY(1000, 1000)

        //line
        val lineChart = view.chart_week_study_time
        val lineEntries = arrayListOf<Entry>()
        lineEntries.add(Entry(1f, 0.5f, 0))
        lineEntries.add(Entry(2f, 3.0f, 1))
        lineEntries.add(Entry(3f, 1.0f, 2))

        val set1 = LineDataSet(lineEntries, "linechart")
        val data1 = LineData(set1)
        set1.color = Color.BLUE
        set1.setCircleColor(Color.RED)
        lineChart.data = data1
        set1.setDrawFilled(true)
        set1.fillDrawable = ResourcesCompat.getDrawable(resources, R.drawable.chart_gradient_color, null)
        lineChart.animateY(1000)
        data1.setValueTextSize(15f)

        lineChart.setDrawGridBackground(false)
        lineChart.setDrawBorders(false)
        //axis
        val xLabel = arrayListOf<String>()
        xLabel.add("월")
        xLabel.add("화")
        xLabel.add("수")
        xLabel.add("목")
        xLabel.add("금")
        xLabel.add("토")
        xLabel.add("일")
        val xAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setAvoidFirstLastClipping(true)
        xAxis.setDrawGridLines(false)
        set1.mode = LineDataSet.Mode.CUBIC_BEZIER

        xAxis.valueFormatter = object: ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return xLabel.get(value.toInt())
            }
        }


        return view
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun displayChart(studyLog: StudyLog) {

    }
}