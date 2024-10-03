package tosbik.ao.tmass.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import tosbik.ao.tmass.ui.components.EmptyScreen
import tosbik.ao.tmass.ui.components.LoadingBar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import tosbik.ao.tmass.R
import tosbik.ao.tmass.ui.components.AppButton
import tosbik.ao.tmass.ui.components.AppTextField
import tosbik.ao.tmass.ui.navigation.NavRoot
import tosbik.ao.tmass.ui.theme.BackgroundColor
import tosbik.ao.tmass.ui.theme.PrimaryColor
import tosbik.ao.tmass.ui.theme.SecondaryColor
import tosbik.ao.tmass.ui.theme.TextColorPrimary
import tosbik.ao.tmass.ui.theme.TextColorSecondary

@Composable
fun AuthScreen(navHostController: NavHostController = rememberNavController()) {
    val selectableTabIndex = remember { mutableIntStateOf(0) }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .systemBarsPadding()
                .padding(top = 60.dp, bottom = 16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.tmass),
                contentDescription = "Logo",
            )
            Text(
                stringResource(R.string.management_app),
                fontSize = 16.sp,
                color = TextColorSecondary,
                modifier = Modifier.padding(top = 16.dp, bottom = 40.dp),
            )
            TabRow(
                selectedTabIndex = selectableTabIndex.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackgroundColor)
                    .padding(horizontal = 16.dp),
                containerColor = BackgroundColor,
                contentColor = TextColorPrimary,
                divider = { },
            ) {
                Tab(
                    selected = selectableTabIndex.intValue == 0,
                    content = { Text(stringResource(R.string.login)) },
                    onClick = { selectableTabIndex.intValue = 0 }
                )
                Tab(
                    selected = selectableTabIndex.intValue == 1,
                    text = { Text(stringResource(R.string.register)) },
                    onClick = { selectableTabIndex.intValue = 1 }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            when (selectableTabIndex.intValue) {
                0 -> LoginScreen(navHostController, selectableTabIndex)
                1 -> RegisterScreen(navHostController, selectableTabIndex)
            }
        }
    }
}

@Composable
fun LoginScreen(navHostController: NavHostController, selectedIndex : MutableIntState) {
    val emailController = remember { mutableStateOf("") }
    val passwordController = remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        AppTextField(
            value = emailController,
            onValueChange = { emailController.value = it },
            hintText = stringResource(R.string.enter_email),
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done,
            modifier = Modifier.padding(horizontal = 16.dp),
            leading = {
                Icon(
                    Icons.Rounded.Email,
                    contentDescription = "Person icon",
                    tint = BackgroundColor,
                )
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        AppTextField(
            value = passwordController,
            onValueChange = { passwordController.value = it },
            hintText = stringResource(R.string.enter_password),
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            isPassword = true,
            modifier = Modifier.padding(horizontal = 16.dp),
            leading = {
                Icon(
                    Icons.Rounded.Lock,
                    contentDescription = "Person icon",
                    tint = BackgroundColor,
                )
            }
        )
        TextButton(
            onClick = {}, modifier = Modifier
                .align(Alignment.End)
                .padding(end = 8.dp)
        ) {
            Text(stringResource(R.string.forgot_password), fontWeight = FontWeight.Normal)
        }
        Spacer(modifier = Modifier.height(20.dp))
        AppButton(
            text = stringResource(R.string.login),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {navHostController.navigate(NavRoot.Base.root)}
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 36.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                thickness = 0.4.dp,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 24.dp, end = 4.dp)
            )
            Text(
                stringResource(R.string.or_login_with),
                fontSize = 14.sp,
                color = TextColorSecondary,
                fontWeight = FontWeight.Light
            )
            HorizontalDivider(
                thickness = 0.4.dp,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 24.dp, start = 4.dp)
            )
        }
        Image(
            painter = painterResource(R.drawable.google),
            contentDescription = "Google icon",
            modifier = Modifier
                .background(BackgroundColor)
                .clip(MaterialTheme.shapes.large)
                .clickable { }
                .border(
                    0.4.dp,
                    SecondaryColor.copy(alpha = 0.4f),
                    shape = MaterialTheme.shapes.large
                )
                .padding(20.dp)
                .size(28.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(R.string.dont_have_account),
                fontSize = 14.sp,
                color = TextColorSecondary,
                fontWeight = FontWeight.Normal
            )
            TextButton(
                onClick = { selectedIndex.intValue = 1 },
                modifier = Modifier.padding(start = 4.dp)
            ) {
                Text(stringResource(R.string.register), fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
fun RegisterScreen(navHostController: NavHostController, selectedIndex : MutableIntState) {
    val usernameController = remember { mutableStateOf("") }
    val emailController = remember { mutableStateOf("") }
    val passwordController = remember { mutableStateOf("") }
    val passwordAgainController = remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        AppTextField(
            value = usernameController,
            onValueChange = { usernameController.value = it },
            hintText = stringResource(R.string.enter_username),
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done,
            modifier = Modifier.padding(horizontal = 16.dp),
            leading = {
                Icon(
                    Icons.Rounded.Person,
                    contentDescription = "Person icon",
                    tint = BackgroundColor,
                )
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        AppTextField(
            value = emailController,
            onValueChange = { emailController.value = it },
            hintText = stringResource(R.string.enter_email),
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done,
            modifier = Modifier.padding(horizontal = 16.dp),
            leading = {
                Icon(
                    Icons.Rounded.Email,
                    contentDescription = "Person icon",
                    tint = BackgroundColor,
                )
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        AppTextField(
            value = passwordController,
            onValueChange = { passwordController.value = it },
            hintText = stringResource(R.string.enter_password),
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            isPassword = true,
            modifier = Modifier.padding(horizontal = 16.dp),
            leading = {
                Icon(
                    Icons.Rounded.Lock,
                    contentDescription = "Person icon",
                    tint = BackgroundColor,
                )
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        AppTextField(
            value = passwordAgainController,
            onValueChange = { passwordAgainController.value = it },
            hintText = stringResource(R.string.confirm_password),
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            isPassword = true,
            modifier = Modifier.padding(horizontal = 16.dp),
            leading = {
                Icon(
                    Icons.Rounded.Lock,
                    contentDescription = "Person icon",
                    tint = BackgroundColor,
                )
            }
        )

        Spacer(modifier = Modifier.height(36.dp))
        AppButton(
            text = stringResource(R.string.register),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {navHostController.navigate(NavRoot.Base.root)}
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 36.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                thickness = 0.4.dp,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 24.dp, end = 4.dp)
            )
            Text(
                stringResource(R.string.or_login_with),
                fontSize = 14.sp,
                color = TextColorSecondary,
                fontWeight = FontWeight.Light
            )
            HorizontalDivider(
                thickness = 0.4.dp,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 24.dp, start = 4.dp)
            )
        }
        Image(
            painter = painterResource(R.drawable.google),
            contentDescription = "Google icon",
            modifier = Modifier
                .background(BackgroundColor)
                .clip(MaterialTheme.shapes.large)
                .clickable { }
                .border(
                    0.4.dp,
                    SecondaryColor.copy(alpha = 0.4f),
                    shape = MaterialTheme.shapes.large
                )
                .padding(20.dp)
                .size(28.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(R.string.already_account),
                fontSize = 14.sp,
                color = TextColorSecondary,
                fontWeight = FontWeight.Normal
            )
            TextButton(
                onClick = { selectedIndex.intValue = 0 },
                modifier = Modifier.padding(start = 4.dp)
            ) {
                Text(stringResource(R.string.login), fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    AuthScreen()
}