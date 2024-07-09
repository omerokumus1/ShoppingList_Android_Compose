package com.example.shoppinglistcompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

data class ShoppingItem(val id: Int, val name: String, val quantity: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingList() {
    val itemList = remember {
        mutableStateOf<List<ShoppingItem>>(emptyList())
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { }) {
            Text(text = "Add Item")
        }
        LazyColumn {
            items(itemList.value.size) { index ->
                val item = itemList.value[index]
                // Display the item
            }
        }
    }

}