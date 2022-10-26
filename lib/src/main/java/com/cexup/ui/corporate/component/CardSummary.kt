package com.cexup.ui.corporate.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.datum.*
import com.cexup.ui.component.chart.BaseChartView
import com.cexup.ui.corporate.theme.Heading
import com.github.mikephil.charting.data.Entry

data class DataChartModel(
    var label:List<String> = listOf(),
    var entry:List<Entry> = listOf()
)

@Composable
fun CardSummary(
    modifier: Modifier = Modifier,
    listEntryBmi: DataChartModel? = DataChartModel(),
    listEntryWaist: DataChartModel? = DataChartModel(),
    listEntryTemp: DataChartModel? = DataChartModel(),
    listEntryPulseOximetry: DataChartModel? = DataChartModel(),
    listEntryHeartRate: DataChartModel? = DataChartModel(),
) {
    Column {
        Column(
            modifier = modifier.height(300.dp)
        ) {
            Text(
                text = "Waist",
                fontSize = 22.sp,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight(700),
                color = Heading,
                modifier = modifier.padding(bottom = 3.dp)
            )
            BaseChartView(
                data = listEntryWaist?.entry ?: listOf(),
                name = listEntryWaist?.label ?: listOf(),
                description = "Waist",
                maxAxis = 60f,
                minAxis = 0f,
            )
        }
        Column(
            modifier = modifier.height(300.dp)
        ) {
            Text(
                text = "Temperature",
                fontSize = 22.sp,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight(700),
                color = Heading,
                modifier = modifier.padding(bottom = 3.dp)
            )
            BaseChartView(
                data = listEntryTemp?.entry ?: listOf(),
                name = listEntryTemp?.label ?: listOf(),
                description = "Temperature",
                maxAxis = 150f,
                minAxis = 50f,
                limitLine = limitLineTemperature,
                legends = listLegendTemperature
            )
        }
        Column(
            modifier = modifier.height(300.dp)
        ) {
            Text(
                text = "Body Mass Index",
                fontSize = 22.sp,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight(700),
                color = Heading,
                modifier = modifier.padding(bottom = 3.dp)
            )
            BaseChartView(
                data = listEntryBmi?.entry ?: listOf(),
                name = listEntryBmi?.label ?: listOf(),
                description = "Body Mass Index",
                maxAxis = 50f,
                minAxis = 10f,
                limitLine = limitLineBodyMassIndex,
                legends = listLegendBmi
            )
        }
        Column(
            modifier = modifier.height(300.dp)
        ) {
            Text(
                text = "Pulse Oximetry",
                fontSize = 22.sp,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight(700),
                color = Heading,
                modifier = modifier.padding(bottom = 3.dp)
            )
            BaseChartView(
                data = listEntryPulseOximetry?.entry ?: listOf(),
                name = listEntryPulseOximetry?.label ?: listOf(),
                description = "Pulse Oximetry",
                maxAxis = 150f,
                minAxis = 50f,
                limitLine = limitLineBloodOxygen,
                legends = listLegendBloodOxygen
            )
        }
        Column(
            modifier = modifier.height(300.dp)
        ) {
            Text(
                text = "Heart Rate",
                fontSize = 22.sp,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight(700),
                color = Heading,
                modifier = modifier.padding(bottom = 3.dp)
            )
            BaseChartView(
                data = listEntryHeartRate?.entry ?: listOf(),
                name = listEntryHeartRate?.label ?: listOf(),
                description = "Heart Rate",
                maxAxis = 150f,
                minAxis = 50f,
                limitLine = limitLineHeartRate,
                legends = listLegendHeartRate
            )
        }
    }
}
