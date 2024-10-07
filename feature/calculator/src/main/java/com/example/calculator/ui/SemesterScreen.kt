package com.example.calculator.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.theme.DefaultPadding
import com.example.core_ui.theme.DefaultPadding2x


@Composable
fun SemesterScreen(
    modifier: Modifier = Modifier,
    onSemesterClick: (Int) -> Unit,
    onResetClick: () -> Unit
) {
    //val viewmodel = koinViewModel<SemesterViewModel>()

    val semesters = listOf(
        "Semester 1", "Semester 2", "Semester 3", "Semester 4",
        "Semester 5", "Semester 6", "Semester 7", "Semester 8"
    )

    Column(modifier = Modifier.fillMaxSize()) {
        // Список семестров
        LazyColumn {
            items(semesters.size) { index ->
                SemesterItem(
                    semesterName = semesters[index],
                    onClick = { onSemesterClick(index + 1) }
                )
            }
        }

        // Кнопка для обнуления данных
        Button(
            onClick = onResetClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(DefaultPadding2x)
        ) {
            Text(text = "Reset All Data")
        }
    }
}

@Composable
fun SemesterItem(
    semesterName: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(DefaultPadding)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Text(
            text = semesterName,
            modifier = Modifier.padding(DefaultPadding2x),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSemesterScreen() {
    SemesterScreen(onSemesterClick = {}, onResetClick = {})
}