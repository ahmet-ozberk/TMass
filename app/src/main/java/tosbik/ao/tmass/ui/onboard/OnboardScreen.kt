package tosbik.ao.tmass.ui.onboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import tosbik.ao.tmass.R
import tosbik.ao.tmass.common.Constants
import tosbik.ao.tmass.common.OnboardDataModel
import tosbik.ao.tmass.ui.components.AppButton
import tosbik.ao.tmass.ui.navigation.NavRoot
import tosbik.ao.tmass.ui.theme.PrimaryColor
import tosbik.ao.tmass.ui.theme.TMassTheme
import tosbik.ao.tmass.ui.theme.TextColorSecondary
import tosbik.ao.tmass.ui.theme.interFontFamily

@Composable
fun OnboardScreen(navHostController: NavHostController) {
    val coroutineScope = rememberCoroutineScope()
    val onboardList = listOf(
        Constants.onboard1,
        Constants.onboard2,
        Constants.onboard3,
    )
    val pagerState = rememberPagerState { onboardList.size }


    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(top = 8.dp, bottom = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                onboardList.forEachIndexed { index, _ ->
                    Box(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(12.dp)
                            .clip(CircleShape)
                            .background(
                                if (index == pagerState.currentPage) PrimaryColor else PrimaryColor.copy(
                                    alpha = 0.1f
                                )
                            )
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                TextButton(onClick = {
                    coroutineScope.launch {
                        if (pagerState.currentPage < onboardList.size - 1) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        } else {
                            pagerState.animateScrollToPage(0)
                        }
                    }
                }) {
                    Text(
                        text = stringResource(
                            when {
                                pagerState.currentPage < onboardList.size - 1 -> R.string.onboard_skip
                                else -> R.string.rtrn
                            }
                        ),
                        fontWeight = FontWeight.SemiBold,
                    )
                }

            }
            HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) {
                OnboardTab(onboardList[it])
            }
            AppButton(
                text = stringResource(R.string.onboard_started),
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                navHostController.navigate(NavRoot.Auth.root){
                    popUpTo(NavRoot.Onboard.root){
                        inclusive = true
                    }
                }
            }
        }
    }
}

@Composable
private fun OnboardTab(model: OnboardDataModel) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically)
    ) {
        Image(
            painter = painterResource(model.image),
            contentDescription = null,
            modifier = Modifier
                .size(screenWidth * 0.8f),
            contentScale = ContentScale.Fit,
        )
        Text(
            text = model.title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
        )
        Text(
            text = model.description,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = TextColorSecondary,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(screenWidth * 0.8f),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardScreenPreview() {
    val navHostController = rememberNavController()
        OnboardScreen(navHostController)
}