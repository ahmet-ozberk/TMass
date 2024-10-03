package tosbik.ao.tmass.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import tosbik.ao.tmass.R
import tosbik.ao.tmass.ui.auth.AuthScreen
import tosbik.ao.tmass.ui.theme.CardWhiteColor
import tosbik.ao.tmass.ui.theme.PrimaryColor
import tosbik.ao.tmass.ui.theme.TextColorSecondary


@Composable
fun AppTextField(
    value: MutableState<String>? = null,
    textValue: String? = null,
    onValueChange: (String) -> Unit,
    leading: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
    onSubmitted: ((String) -> Unit)? = null,
    hintText: String = "",
    isFocused: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    isPassword: Boolean = false,
    enabled: Boolean = true,
    stringLength: Int? = null,
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    onError: @Composable ((String) -> Unit)? = null
) {
    var isFocusedState by remember { mutableStateOf(isFocused) }
    var isPass by remember { mutableStateOf(true) }
    val localFocusManager = LocalFocusManager.current

    Column {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(48.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(CardWhiteColor)
                .border(
                    width = 1.dp,
                    color = if (isFocusedState) PrimaryColor else PrimaryColor.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxSize()
            ) {
                if (leading != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                            .background(PrimaryColor)
                            .padding(16.dp)
                    ) { leading() }
                }
                BasicTextField(value = textValue ?: value?.value ?: "",
                    onValueChange = {
                        if (stringLength == null || it.length <= stringLength) {
                            onValueChange(it)
                        }
                    },
                    singleLine = true,
                    enabled = enabled,
                    readOnly = enabled.not(),
                    textStyle = TextStyle(
                        fontSize = 14.sp, color = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = keyboardType, imeAction = imeAction
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        onSubmitted?.invoke(value?.value ?: "")
                        localFocusManager.clearFocus()
                    }),
                    cursorBrush = SolidColor(PrimaryColor),
                    maxLines = maxLines,
                    decorationBox = { innerTextField ->
                        Box(
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (value?.value?.isEmpty() == true) {
                                Text(
                                    text = hintText,
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        color = TextColorSecondary,
                                    ),
                                )
                            }
                            innerTextField()
                        }
                    },
                    visualTransformation = if (isPassword) if (!isPass) VisualTransformation.None else PasswordVisualTransformation() else VisualTransformation.None,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f)
                        .onFocusChanged { focusState ->
                            isFocusedState = focusState.isFocused
                        })
                if (trailing != null) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Box(modifier = Modifier.height(17.dp)) { trailing() }
                }
                if (isPassword) {
                    if (trailing != null) {
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                    IconButton(onClick = { isPass = !isPass }) {
                        if (!isPass) {
                            Icon(
                                painter = painterResource(R.drawable.eye_slash_solid),
                                contentDescription = null,
                                modifier = Modifier.height(14.dp)
                            )
                        } else {
                            Icon(
                                painter = painterResource(R.drawable.eye_solid),
                                contentDescription = null,
                                modifier = Modifier.height(14.dp)
                            )
                        }
                    }
                }

            }
        }
        if (onError != null && value?.value?.isNotEmpty() == true) {
            onError(value.value)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFf5f5f5)
@Composable
fun AppTextFieldPreview() {
    val navHostController = rememberNavController()
    AuthScreen(navHostController)
}