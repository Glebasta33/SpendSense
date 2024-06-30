package com.example.spendsense.categories.creation.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.spendsense.MR
import com.example.spendsense.categories.creation.CreateCategoryData
import com.example.spendsense.common.ui.atoms.AppButton
import com.example.spendsense.common.ui.atoms.AppTextField
import com.example.spendsense.common.ui.atoms.BottomModalContainer
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun CreateCategoryView(
    isExpand: Boolean,
    onSave: (CreateCategoryData) -> Unit
) {

    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    val focusManager = LocalFocusManager.current

    var title by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }
    var rColor by remember { mutableFloatStateOf(0.3f) }
    var gColor by remember { mutableFloatStateOf(0.6f) }
    var bColor by remember { mutableFloatStateOf(0.9f) }

    LaunchedEffect(isExpand) {
        if (isExpand) {
            focusRequester.requestFocus()
        } else {
            focusManager.clearFocus()
            title = ""
            subtitle = ""
            rColor = 0.3f
            gColor = 0.6f
            bColor = 0.9f
        }
    }

    BottomModalContainer {
        AppTextField(
            value = title,
            onValueChange = { title = it },
            modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
            placeholder = stringResource(MR.strings.title_category_placeholder)
        )
        Spacer(Modifier.height(16.dp))

        AppTextField(
            value = subtitle,
            onValueChange = { subtitle = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = stringResource(MR.strings.subtitle_category_placeholder)
        )
        Spacer(Modifier.height(16.dp))

        ColorBox(rColor, gColor, bColor) {
            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                ColorSlider(Color.Red, rColor) { rColor = it }
                ColorSlider(Color.Green, gColor) { gColor = it }
                ColorSlider(Color.Blue, bColor) { bColor = it }
            }
        }

        Spacer(Modifier.height(16.dp))

        AppButton(stringResource(MR.strings.save_button)) {
            onSave(
                CreateCategoryData(
                    title = title,
                    subtitle = subtitle,
                    colorHex = Color(rColor, gColor, bColor).toArgb().toString(16)
                )
            )
        }
    }
}