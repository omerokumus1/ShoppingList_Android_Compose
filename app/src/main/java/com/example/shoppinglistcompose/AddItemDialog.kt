package com.example.shoppinglistcompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddItemDialog(showAlertDialog: MutableState<Boolean>, onConfirm: (String, String) -> Unit) {
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }

    fun resetState() {
        itemName = ""
        itemQuantity = ""
    }

    fun dismissDialog() {
        resetState()
        showAlertDialog.value = false
    }

    fun addClickHandler() {
        onConfirm(itemName, itemQuantity)
        dismissDialog()
    }

    AlertDialog(
        onDismissRequest = ::dismissDialog,
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = ::addClickHandler) {
                    Text(text = "Add")
                }
                Button(onClick = ::dismissDialog) {
                    Text(text = "Cancel")
                }
            }

        },
        title = { Text(text = "Add Shopping Item") },
        text = {
            Column {
                OutlinedTextField(
                    value = itemName, onValueChange = { itemName = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                OutlinedTextField(
                    value = itemQuantity, onValueChange = { itemQuantity = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
    )
}