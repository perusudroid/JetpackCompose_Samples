package com.perusu.kotlinpro.navigation

import android.os.Parcelable
import com.example.jetpoll.navigation.Navigator
import kotlinx.android.parcel.Parcelize

sealed class Destination : Parcelable {
    @Parcelize
    object Home : Destination()

    @Parcelize
    object StaticView : Destination()

    @Parcelize
    object ListView : Destination()
}

class Actions(navigator: Navigator<Destination>) {

    val selectOnLearnList: (String) -> Unit = { selectedItem: String ->
        when (selectedItem) {
            "Static UI" -> navigator.navigate(Destination.StaticView)
            "ListView" -> navigator.navigate(Destination.ListView)
        }
    }

    val pressOnBack: () -> Unit = {
        navigator.back()
    }
}