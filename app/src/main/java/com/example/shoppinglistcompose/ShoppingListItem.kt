package com.example.shoppinglistcompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    onEditClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    onSaveClicked: (ShoppingItem) -> Unit,
) {
    var item by remember { mutableStateOf(item) }
    val isEditing = remember {
        mutableStateOf(false)
    }

    fun editClickHandler() {
        isEditing.value = true
        onEditClicked()
    }

    fun saveClickHandler() {
        isEditing.value = false
        onSaveClicked(item)
    }

    val rowModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp)

    @Composable
    fun NameView() {
        if (!isEditing.value) Text(text = item.name ?: "") else TextField(
            modifier = Modifier
                .width(100.dp)
                .padding(end = 8.dp),
            value = item.name ?: "",
            onValueChange = { item = item.copy(name = it) }
        )
    }

    @Composable
    fun QuantityView() {
        if (!isEditing.value) Text(text = "Qty: ${item.quantity}") else TextField(
            modifier = Modifier.width(100.dp),
            value = item.quantity.toString(),
            onValueChange = { item = item.copy(quantity = it.toIntOrNull()?.toString() ?: "") }
        )
    }

    OutlinedCard(
        modifier = Modifier.padding(8.dp),
    ) {
        Row(
            modifier = rowModifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NameView()
            QuantityView()
            Row {
                if (isEditing.value) {
                    SaveButton(::saveClickHandler)
                } else {
                    EditButton(::editClickHandler)
                }
                DeleteButton(onDeleteClicked)

            }
        }
    }
}

@Composable
fun DeleteButton(onDeleteClicked: () -> Unit) {
    IconButton(onClick = onDeleteClicked) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "Delete Item"
        )
    }
}

@Composable
fun EditButton(onEditClicked: () -> Unit) {
    IconButton(onClick = onEditClicked) {
        Icon(
            imageVector = Icons.Filled.Edit,
            contentDescription = "Edit Item"
        )
    }
}

@Composable
fun SaveButton(onSaveClicked: () -> Unit) {
    IconButton(onClick = onSaveClicked) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = "Save Item"
        )
    }
}