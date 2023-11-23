package com.ashana.compose.navigation.dummy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterDetailNameScreen(
    onNextButtonClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    var fullName by remember { mutableStateOf("") }

    Column(
        modifier = modifier.padding(18.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            label = { Text(text = "Enter full name") },
            placeholder = { Text(text = "John doe") },
            value = fullName,
            onValueChange = {
                fullName = it
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            content = { Text(text = "Next") },
            onClick = { onNextButtonClick(fullName) }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewEditerDetailName() {
    EnterDetailNameScreen(
        onNextButtonClick = {}
    )
}