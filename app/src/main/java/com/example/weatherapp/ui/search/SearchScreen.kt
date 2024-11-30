package com.example.weatherapp.ui.search


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.ui.home.TimePeriodStyle


@Composable
fun SearchScreen(timePeriodStyle: TimePeriodStyle, zilaList: List<ZilaInfo>) {

    var query by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
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
            painter = painterResource(timePeriodStyle.drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            TextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(timePeriodStyle.backgroundColor)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    },

                placeholder = {
                    if (!isFocused && query.isEmpty()) {
                        Text(
                            text = "Search...",
                            color = timePeriodStyle.textColor
                        )
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = timePeriodStyle.textColor,
                    cursorColor = timePeriodStyle.textColor,
                    focusedIndicatorColor = timePeriodStyle.textColor,
                    unfocusedIndicatorColor = timePeriodStyle.backgroundColor,
                    placeholderColor = timePeriodStyle.textColor
                ),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(modifier = Modifier
                .background(timePeriodStyle.backgroundColor)) {
                if (query.isNotEmpty()) {
                    items(filteredItems) { item ->
                        Text(
                            text = item.name.toString(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .background(timePeriodStyle.backgroundColor, RoundedCornerShape(4.dp))
                                .padding(16.dp),
                            color = timePeriodStyle.textColor,
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }
        }
    }

}

