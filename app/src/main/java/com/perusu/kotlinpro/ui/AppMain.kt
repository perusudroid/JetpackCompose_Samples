package com.perusu.kotlinpro.ui

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.remember
import androidx.compose.runtime.savedinstancestate.rememberSavedInstanceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.jetpoll.navigation.BackDispatcherAmbient
import com.example.jetpoll.navigation.Navigator
import com.perusu.kotlinpro.R
import com.perusu.kotlinpro.navigation.Actions
import com.perusu.kotlinpro.navigation.Destination
import com.perusu.kotlinpro.ui.views.LearnList
import com.perusu.kotlinpro.ui.views.ListView
import com.perusu.kotlinpro.ui.views.StaticView
import com.perusu.kotlinpro.utils.ProvideDisplayInsets

@Composable
fun AppMain(backDispatcher: OnBackPressedDispatcher) {

    val navigator: Navigator<Destination> = rememberSavedInstanceState(
        saver = Navigator.saver(backDispatcher)
    ) {
        Navigator(Destination.Home, backDispatcher)
    }
    val actions = remember(navigator) { Actions(navigator) }


    Providers(BackDispatcherAmbient provides backDispatcher) {
        ProvideDisplayInsets {
            Crossfade(navigator.current) {
                when (it) {
                    is Destination.Home -> LearnList(actions.selectOnLearnList)
                    is Destination.StaticView -> StaticView()
                    is Destination.ListView -> ListView()
                }
            }
        }
    }
}


@Composable
private fun AppBar() {
    TopAppBar(
        navigationIcon = {
            Icon(Icons.Rounded.Palette, Modifier.padding(horizontal = 12.dp))
        },
        title = {
            Text(text = stringResource(R.string.app_name))
        },
        backgroundColor = MaterialTheme.colors.primary
    )
}