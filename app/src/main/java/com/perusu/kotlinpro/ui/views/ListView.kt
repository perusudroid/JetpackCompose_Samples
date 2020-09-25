package com.perusu.kotlinpro.ui.views

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.annotatedString
import androidx.compose.ui.unit.dp
import com.perusu.kotlinpro.data.local.Post
import com.perusu.kotlinpro.data.local.PostRepo
import com.perusu.kotlinpro.R
import java.util.*

@Composable
fun ListView() {

    val featured = remember { PostRepo.getFeaturedPost() }
    val posts = remember { PostRepo.getPosts() }

    MaterialTheme {
        Scaffold(
            topBar = { AppBar() },
            bodyContent = {
                ScrollableColumn {
                    headerView("Featured")
                    ContentPost(featured, Modifier.padding(16.dp))
                    headerView("Popular posts")
                    posts.forEach {
                        PostItem(it)
                        Divider(startIndent = 30.dp)
                    }
                }
            }
        )
    }

}

@Composable
fun PostItem(post: Post) {
    ListItem(
        modifier = Modifier
            .clickable { }
            .padding(8.dp),
        icon = { Image(asset = imageResource(post.imageThumbId)) },
        text = { Text(text = post.title) },
        secondaryText = { PostMetadata(post) }
    )
}

@Composable
fun ContentPost(featured: Post, modifier: Modifier = Modifier) {
    Card(modifier) {
        Column(modifier = Modifier.fillMaxWidth()
            .clickable { /* onClick */ }) {
            Image(
                asset = imageResource(featured.imageId),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .preferredHeightIn(min = 180.dp)
                    .fillMaxWidth()
            )
            Spacer(Modifier.preferredHeight(10.dp))
            val padding = Modifier.padding(10.dp)
            Text(featured.title, padding)
            Text(featured.metadata.author.name, padding)
            PostMetadata(featured, padding)
            Spacer(Modifier.preferredHeight(10.dp))
        }
    }
}

@Composable
private fun PostMetadata(
    post: Post,
    modifier: Modifier = Modifier
) {
    val divider = "  •  "
    val tagDivider = "  "
    val text = annotatedString {
        append(post.metadata.date)
        append(divider)
        append(stringResource(R.string.read_time, post.metadata.readTimeMinutes))
        append(divider)
        post.tags.forEachIndexed { index, tag ->
            if (index != 0) {
                append(tagDivider)
            }
            append(" ${tag.toUpperCase(Locale.getDefault())} ")
        }
    }
    Text(
        text = text,
        modifier = modifier
    )
}

@Composable
fun headerView(text: String, modifier: Modifier = Modifier) {
    Text(
        text,
        modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
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