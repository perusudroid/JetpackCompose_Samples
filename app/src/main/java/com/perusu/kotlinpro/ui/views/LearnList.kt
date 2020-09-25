package com.perusu.kotlinpro.ui.views

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.perusu.kotlinpro.data.local.PostRepo

@Composable
fun LearnList(onItemClicked: (String) -> Unit) {

    val learnList = remember { PostRepo.getLearnList() }
    MaterialTheme {
        Scaffold(
            bodyContent = {
                ScrollableColumn {
                    headerView("LearnList")
                    learnList.forEach {
                        populateList(it, onItemClicked)
                        Divider(startIndent = 10.dp)
                    }
                }
            }
        )
    }
}


@Composable
fun populateList(itemName: String, onItemClicked: (String) -> Unit) {
    ListItem(
        modifier = Modifier
            .clickable {
                onItemClicked(itemName)
            }
            .padding(8.dp),
        text = { Text(text = itemName) })
}