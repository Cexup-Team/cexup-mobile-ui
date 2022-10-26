package com.cexup.ui.corporate.component

import android.view.ContextThemeWrapper
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.cexup.ui.R
import com.cexup.ui.component.chart.XAxisTimeFormatter
import com.cexup.ui.utils.CustomChartMarker

data class LabelAndColorChart(
    val label : String,
    val colorLine : Int,
    val coloGradient : Int
)

@Composable
fun ChartPatientHistory(
    data: List<Entry>,
    data2 : List<Entry>,
    xValueFormatter: List<String> = listOf(),
    description: String,
    maxAxis: Float = 100f,
    minAxis: Float =0f,
    label1 : LabelAndColorChart,
    label2 : LabelAndColorChart
){
    val isDark = isSystemInDarkTheme()
    val context = LocalContext.current
//    val limitWidth = 0.7f

    AndroidView(
        modifier= Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Transparent),
        factory = {
            LineChart(ContextThemeWrapper(it, R.style.Chart)).apply {
                setBackgroundResource(R.drawable.bg_chart)
                axisRight.apply {
                    isEnabled = false
                    setDrawAxisLine(false)
                    setDrawGridLines(false)
                    textColor = if(isDark) android.graphics.Color.WHITE else android.graphics.Color.BLACK
                }
                legend.apply {
                    isEnabled = false
                    form = Legend.LegendForm.CIRCLE

                }


                axisLeft.apply {
                    axisMaximum = maxAxis
                    axisMinimum = minAxis
                    removeAllLimitLines()

                    //formatter
                    isEnabled = true
                    setDrawAxisLine(false)
                    setDrawGridLines(true)
                    textColor =if(isDark) android.graphics.Color.WHITE else android.graphics.Color.BLACK
                }
                xAxis.apply {

                    //disable axis
                    setDrawGridLines(false)
                    position = XAxis.XAxisPosition.BOTTOM

                    //
                    labelRotationAngle = 0f

                    granularity = 1f

                    axisMaximum = 20+0.1f
                    //
                    textColor = if(isDark) android.graphics.Color.WHITE else android.graphics.Color.BLACK

                }

                this.description.text = description
                this.description.textSize = 16f
                this.description.textColor = if(isDark) android.graphics.Color.WHITE else android.graphics.Color.BLACK
                this.setPadding(
                    0,20,0,30
                )


                //Part8
                setTouchEnabled(true)
                setPinchZoom(true)

                //Part9

                setNoDataText("No Data to be shown!")

                //Part10
                animateX(1800, Easing.EaseInExpo)

                //add custom marker
                val markerView = CustomChartMarker(context, R.layout.layout_marker_chart)
                marker = markerView

            }

        },
        update = {
                view ->

            view.xAxis.valueFormatter  = XAxisTimeFormatter(xValueFormatter)
            val lineDataSet = LineDataSet(data, label1.label)
            val lineDataSet2 = LineDataSet(data2, label2.label)
            lineDataSet.apply {
                //make chart smooth
                mode = LineDataSet.Mode.CUBIC_BEZIER
                cubicIntensity = 0.05f

                //set transparencyFF18A0FB
                setColor(label1.colorLine,1000)
                //set value in each circle
                setDrawValues(false)
                //Part4 set color fill (area)
                setDrawFilled(true)
                setDrawCircles(false)

                lineWidth = 3f

                fillDrawable = AppCompatResources.getDrawable(context, label1.coloGradient)

            }
            lineDataSet2.apply {
                //make chart smooth
                mode = LineDataSet.Mode.CUBIC_BEZIER
                cubicIntensity = 0.05f

                //set transparencyFF18A0FB
                setColor(label2.colorLine,1000)
                //set value in each circle
                setDrawValues(false)
                //Part4 set color fill (area)
                setDrawFilled(true)
                setDrawCircles(false)

                lineWidth = 3f

                fillDrawable = AppCompatResources.getDrawable(context, label2.coloGradient)

            }

            val iline = listOf(lineDataSet, lineDataSet2)


            //set data
            view.data = LineData(iline)


            view.invalidate()
        }
    )
}