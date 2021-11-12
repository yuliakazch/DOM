package com.dom.features.home.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dom.R
import com.dom.components.error.ErrorView
import com.dom.components.progress.LoadingView
import com.dom.components.theme.VeryLightGray
import com.dom.components.topbar.TopBarView
import com.dom.features.home.presentation.HomeEffect
import com.dom.features.home.presentation.HomeEvent
import com.dom.features.home.presentation.HomeState
import com.dom.shared.folder.domain.entity.Folder
import kotlinx.coroutines.flow.Flow

@ExperimentalFoundationApi
@Composable
fun HomeScreen(
	state: HomeState,
	effectFlow: Flow<HomeEffect>?,
	onEventSent: (event: HomeEvent) -> Unit,
	onNavigationRequested: (navigationEffect: HomeEffect.Navigation) -> Unit
) {
	val scaffoldState: ScaffoldState = rememberScaffoldState()

	val textError = stringResource(R.string.error)

	Scaffold(
		topBar = {
			TopBarView(
				title = stringResource(R.string.home_title),
			)
		}
	) {
		Surface(
			color = VeryLightGray,
			modifier = Modifier.fillMaxSize()
		) {
			when {
				state.loading -> LoadingView()

				state.data == null -> ErrorView()

				else -> HomeView(state.data)
			}
		}
	}
}

@ExperimentalFoundationApi
@Composable
fun HomeView(
	folders: List<Folder>,
) {
	LazyVerticalGrid(
		cells = GridCells.Fixed(2),
		modifier = Modifier.padding(top = 16.dp, bottom = 64.dp)
	) {
		folders.forEach { folder ->
			item {
				FolderItem(name = folder.name, image = null)
			}
		}
	}
}