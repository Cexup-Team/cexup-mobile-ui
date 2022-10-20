package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R

@Composable
fun CardEmptySpace(
    modifier: Modifier = Modifier,
    nameNurse : String = ""
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_empty_state),
            contentDescription = "Empty Space",
            modifier = modifier
                .width(199.dp)
                .height(193.dp))
        Text(
            text = "Hi, $nameNurse!",
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.primaryVariant,
                fontWeight = FontWeight(600),
                fontSize = 22.sp
            ),
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.corporate_checkup_empty),
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.secondaryVariant,
                fontWeight = FontWeight(600),
                fontSize = 12.sp
            ),
            maxLines = 2,
            textAlign = TextAlign.Center
        )

    }

}