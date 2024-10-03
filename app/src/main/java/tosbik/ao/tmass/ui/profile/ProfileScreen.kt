package tosbik.ao.tmass.ui.profile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tosbik.ao.tmass.R
import tosbik.ao.tmass.ui.theme.AccentColor
import tosbik.ao.tmass.ui.theme.BackgroundColor
import tosbik.ao.tmass.ui.theme.CardWhiteColor
import tosbik.ao.tmass.ui.theme.ErrorColor
import tosbik.ao.tmass.ui.theme.PrimaryColor
import tosbik.ao.tmass.ui.theme.SecondaryColor
import tosbik.ao.tmass.ui.theme.SoftWhite
import tosbik.ao.tmass.ui.theme.TextColorPrimary
import tosbik.ao.tmass.ui.theme.TextColorSecondary

@Composable
fun ProfileScreen() {
    val statusBarInsets = WindowInsets.statusBars.asPaddingValues()
    val screenWidth = LocalConfiguration.current.screenWidthDp
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PrimaryColor)
                    .padding(
                        top = statusBarInsets.calculateTopPadding() + 16.dp,
                        bottom = 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                    )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(R.drawable.fake_user),
                        contentDescription = "User Image",
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(screenWidth.dp / 4f)
                            .background(PrimaryColor)
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Text(
                        "Jolie Doe",
                        fontSize = 24.sp,
                        color = SoftWhite,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        "@joliedoe0808",
                        fontSize = 14.sp,
                        color = SoftWhite,
                        fontWeight = FontWeight.Light
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        "Mobile Developer",
                        fontSize = 16.sp,
                        color = SoftWhite,
                        fontWeight = FontWeight.Light
                    )


                }
            }
            Card(
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(24.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = CardWhiteColor),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    ProfileCardButton(text = "Personal Information", R.drawable.user_pen_solid) {
                        Log.e("ProfileScreen", "Kişisel Bilgiler")
                    }
                    ProfileCardButton(text = "Account Settings", R.drawable.sliders_solid) {
                        Log.e("ProfileScreen", "Kişisel Bilgiler")
                    }
                    ProfileCardButton(text = "Notifications", R.drawable.bell_solid) {
                        Log.e("ProfileScreen", "Kişisel Bilgiler")
                    }
                    ProfileCardButton(text = "Privacy", R.drawable.user_lock_solid) {
                        Log.e("ProfileScreen", "Kişisel Bilgiler")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .clickable { }
                            .background(PrimaryColor.copy(alpha = 0.1f))
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 16.dp)
                    ) {
                        Text(
                            text = "Log Out",
                            color = PrimaryColor,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Icon(
                            painter = painterResource(R.drawable.right_from_bracket_solid),
                            contentDescription = null,
                            tint = PrimaryColor,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ProfileCardButton(
    text: String,
    icon: Int,
    textColor: Color = TextColorPrimary,
    backgroundColor: Color = SoftWhite,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onClick() }
            .background(backgroundColor)
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = text,
            color = textColor,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(R.drawable.angle_right_solid),
            contentDescription = null,
            tint = textColor.copy(alpha = 0.6f),
            modifier = Modifier.size(20.dp)
        )
    }
}


@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}