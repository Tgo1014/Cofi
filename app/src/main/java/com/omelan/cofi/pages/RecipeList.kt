package com.omelan.cofi.pages

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.ExperimentalAnimatedInsets
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.omelan.cofi.components.PiPAwareAppBar
import com.omelan.cofi.components.RecipeItem
import com.omelan.cofi.model.RecipeViewModel

@ExperimentalAnimatedInsets
@Composable
fun RecipeList(
    navigateToRecipe: (recipeId: Int) -> Unit,
    addNewRecipe: () -> Unit,
    goToSettings: () -> Unit,
    recipeViewModel: RecipeViewModel = viewModel(),
) {
    val recipes = recipeViewModel.getAllRecipes().observeAsState(initial = listOf())
    Scaffold(
        topBar = {
            PiPAwareAppBar(
                actions = {
                    IconButton(onClick = goToSettings) {
                        Icon(Icons.Rounded.Settings, contentDescription = null)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = addNewRecipe,
                modifier = Modifier.navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    tint = MaterialTheme.colors.onBackground,
                    contentDescription = null,
                )
            }
        },
    ) {
        LazyColumn(
            contentPadding = rememberInsetsPaddingValues(
                insets = LocalWindowInsets.current.navigationBars,
                additionalTop = 5.dp
            ),
            modifier = Modifier.fillMaxSize()
        ) {
            items(recipes.value) { recipe ->
                RecipeItem(
                    recipe = recipe,
                    onPress = navigateToRecipe,
                )
            }
        }
    }
}

@ExperimentalAnimatedInsets
@Preview
@Composable
fun RecipeListPreview() {
    RecipeList(navigateToRecipe = {}, addNewRecipe = {}, goToSettings = {})
}