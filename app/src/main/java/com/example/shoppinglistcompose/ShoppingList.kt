package com.example.shoppinglistcompose

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

data class ShoppingItem(val id: Int, val name: String?, val quantity: String?)

@Composable
fun ShoppingList() {
    val context = LocalContext.current
    val itemList = remember {
        mutableStateOf<List<ShoppingItem>>(listOf(ShoppingItem(0, "Test", "1")))
    }
    val showAlertDialog = remember {
        mutableStateOf(false)
    }

    if (showAlertDialog.value) {
        AddItemDialog(showAlertDialog = showAlertDialog) { itemName, itemQuantity ->
            val item = ShoppingItem(
                id = itemList.value.size + 1,
                name = itemName,
                quantity = itemQuantity.toIntOrNull()?.toString() ?: ""
            )
            itemList.value += item
        }
    }

    fun onEditClicked() {
        Toast.makeText(context, "Edit clicked", Toast.LENGTH_SHORT).show()
    }

    fun onDeleteClicked() {
        Toast.makeText(context, "Delete clicked", Toast.LENGTH_SHORT).show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { showAlertDialog.value = true }) {
            Text(text = "Add Item")
        }
        LazyColumn {
            items(itemList.value.size) { index ->
                val item = itemList.value[index]
                // Display the item
                ShoppingListItem(
                    item = item,
                    onEditClicked = ::onEditClicked,
                    onDeleteClicked = ::onDeleteClicked,
                    onSaveClicked = { updatedItem ->
                        val updatedList = itemList.value.toMutableList()
                        updatedList[index] = updatedItem
                        itemList.value = updatedList
                    }
                )
            }
        }
    }

}