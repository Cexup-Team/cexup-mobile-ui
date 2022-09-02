package com.cexup.ui.consumer.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.consumer.theme.ColorGray
import com.cexup.ui.consumer.theme.ConsumerTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

/**
 * OnBoard Page
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 28/08/2021
 */
data class OnBoardingModelUIState(
    var title:Int,
    val text:Int,
    val image:Int
)

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalPagerApi
@Composable
fun ScreenOnBoarding(
    modifier: Modifier = Modifier,
    data:List<OnBoardingModelUIState> = listOf(),
    privacyPolicyContent: @Composable ColumnScope.()->Unit={},
    onBackPressed: () -> Unit={},
) {
    val state = rememberPagerState(
        initialPage = 0,
    )
    val scope = rememberCoroutineScope()
    var bottomState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    fun openOrHideBottomSheet(show: Boolean) {
        if (show) {
            scope.launch {
                bottomState.show()
            }
        } else {
            scope.launch { bottomState.hide() }
        }
    }
    ModalBottomSheetLayout(
        sheetContent = privacyPolicyContent,
        sheetState = bottomState
    ) {
        Column(modifier = modifier.fillMaxSize()) {
            TopSection(
                onBackPressed = {
                    onBackPressed()
                },
                onSkipPressed = {
                    openOrHideBottomSheet(true)
                }
            )
            HorizontalPager(
                count=data.size,
                state = state,
                modifier = modifier
                    .fillMaxSize()
                    .weight(0.8f)
            ) { page ->
                OnBoardingItem(item = data[page])
            }
            BottomSection(size = data.size, index = state.currentPage) {
                val stateCurrent = (state.currentPage + 1)
                if (stateCurrent < data.size) {
                    scope.launch {
                        state.scrollToPage(page = state.currentPage + 1)
                    }
                } else {

                    openOrHideBottomSheet(true)

                    scope.launch {
                        bottomState.show()
                    }

                }

            }
        }
    }
}


@Composable
fun OnBoardingItem(modifier: Modifier = Modifier, item: OnBoardingModelUIState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Image(
            painter = painterResource(id = item.image),
            contentDescription = "",
            modifier = modifier.width(180.dp)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(24.dp)
        ) {
            Text(
                text = stringResource(id = item.title),
                fontSize = 24.sp,
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = modifier.height(5.dp))
            Text(
                text = stringResource(id = item.text),
                fontSize = 18.sp,
                color = ColorGray.copy(
                    alpha = 0.8f
                ), textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun TopSection(m: Modifier = Modifier, onBackPressed: () -> Unit, onSkipPressed: () -> Unit) {
    Box(
        modifier = m
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(16.dp)
    ) {
        IconButton(
            onClick = onBackPressed,
            modifier = m.align(Alignment.CenterStart)
        ) {
            Icon(
                Icons.Outlined.KeyboardArrowLeft,
                tint = MaterialTheme.colors.onBackground,
                contentDescription = ""
            )
        }

        TextButton(
            onClick = onSkipPressed,
            modifier = m.align(Alignment.CenterEnd)
        ) {
            Text(
                text = "Skip",
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}

@Composable
fun BottomSection(modifier: Modifier = Modifier, size: Int, index: Int, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(16.dp)
    ) {
        Indicators(size = size, index = index)
        FloatingActionButton(
            onClick = onClick,
            modifier = modifier.align(Alignment.CenterEnd),
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
        ) {
            Icon(Icons.Outlined.KeyboardArrowRight, contentDescription = "")
        }
    }
}

@Composable
fun BoxScope.Indicators(m: Modifier = Modifier, size: Int, index: Int) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = m.align(Alignment.CenterStart),
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }
    }

}

@Composable
fun Indicator(modifier: Modifier = Modifier, isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    Box(
        modifier = modifier
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                color =
                if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground.copy(
                    alpha = 0.5f
                )
            )
    ) {

    }
}

@ExperimentalPagerApi
@Preview
@Composable
fun PreviewPageOnboard() {
    ConsumerTheme {
        ScreenOnBoarding()
    }
}


