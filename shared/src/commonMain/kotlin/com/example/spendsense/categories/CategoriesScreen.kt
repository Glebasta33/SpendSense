package com.example.spendsense.categories

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex
import com.example.spendsense.categories.creation.compose.CreateCategoryView
import com.example.spendsense.categories.list.compose.CategoriesListView
import com.example.spendsense.categories.list.compose.CategoriesViewModel
import com.example.spendsense.common.ui.atoms.FAB
import com.example.spendsense.common.ui.atoms.RootBox
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BoxScope.CategoriesScreen(
    viewModel: CategoriesViewModel
) {

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetContent = {
            CreateCategoryView(
                isExpand = sheetState.currentValue == ModalBottomSheetValue.Expanded
            ) { category ->
                scope.launch { sheetState.hide() }
                viewModel.createCategory(category)
            }
        },
        sheetState = sheetState,
        sheetBackgroundColor = Color.Transparent,
        modifier = Modifier.zIndex(1f)
    ) {
        RootBox {
            CategoriesListView(
                viewModel = viewModel,
                onClick = { category ->

                }
            )

            FAB { scope.launch { sheetState.show() } }
        }
    }


}