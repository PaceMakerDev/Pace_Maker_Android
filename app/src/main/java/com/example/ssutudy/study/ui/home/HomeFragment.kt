package com.example.ssutudy.study.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ssutudy.R
import com.example.ssutudy.study.dto.StudyLog
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {
    private lateinit var pieChart : PieChart

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

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
        pieChart = view.chart
        pieChart.data = data
        dataset.colors = ColorTemplate.COLORFUL_COLORS.asList()
        //pieChart.animateXY(1000, 1000)
        pieChart.animateXY(1000, 1000)
        //pieChart.visibility = View.GONE

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        pieChart.visibility = View.GONE
    }

    fun displayChart(studyLog: StudyLog) {

    }
}