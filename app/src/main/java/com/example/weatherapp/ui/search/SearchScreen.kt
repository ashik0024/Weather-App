package com.example.weatherapp.ui.search


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R


@Composable
fun SearchScreen(
    zilaList: List<ZilaInfo>,
    onItemClicked: (ZilaInfo) -> Unit
) {
    val sf_pro = FontFamily(
        Font(R.font.sf_pro_regular, FontWeight.Normal),
        Font(R.font.sf_pro_regular, FontWeight.Bold)
    )

    var isFocused by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }

    /**
     * matching user input to zilla list
     */
    val filteredItems = remember(query) {
        if (query.isNotEmpty()) {
            zilaList.filter {
                it.name.toString().contains(query,ignoreCase = true) }
        } else {
            emptyList()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

            /**
             * setting search TextField behavior on focusState
             */

            TextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    },
                placeholder = {
                    if (!isFocused && query.isEmpty()) {
                        Text(
                            text = LocalContext.current.getString(R.string.search),
                            color = Color.White,
                            fontFamily = sf_pro
                        )
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    placeholderColor = Color.White
                ),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn {
                if (query.isNotEmpty()) {
                    items(filteredItems) { item ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                                .clickable {
                                    onItemClicked(item)
                                }
                        ) {
                            Text(
                                text = item.name.toString(),
                                modifier = Modifier
                                    .padding(16.dp),
                                color = Color.White,
                                fontFamily = sf_pro
                            )
                        }
                    }
                }
            }
        }
    }

}

