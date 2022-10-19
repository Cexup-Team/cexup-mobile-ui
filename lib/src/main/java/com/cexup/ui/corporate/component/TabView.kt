package com.cexup.ui.corporate.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.SecondaryCorporate
import com.cexup.ui.corporate.theme.inactive
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

data class TabContent(
    var header: String,
    var content: @Composable () -> Unit,
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabView (
    tabContents: List<TabContent>,
    pagerState: PagerState,
) {
    val scope = rememberCoroutineScope()
    Column {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color.Transparent,
            contentColor = Color.White,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .pagerTabIndicatorOffset(pagerState, tabPositions)
                        .clip(RoundedCornerShape(10.dp))
                        .width(80.dp),
                    color = SecondaryCorporate,
                    height = 3.dp,
                )
            },
            edgePadding = 0.dp,
        ) {
            tabContents.forEachIndexed { index, tab ->
                LeadingIconTab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    modifier = Modifier.padding(0.dp),
                    text = {
                        Text(
                            text = tab.header,
                            color = when (pagerState.currentPage) {
                                index -> Heading
                                else -> inactive
                            },
                            style = MaterialTheme.typography.body1.copy(
                                fontSize = 16.sp,
                                fontWeight = FontWeight(500),
                                textAlign = TextAlign.Start
                            ),
                        )
                    },
                    icon = {},
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top,
            count = tabContents.size
        ) { page ->
            tabContents[page].content.invoke()
        }
    }
}
