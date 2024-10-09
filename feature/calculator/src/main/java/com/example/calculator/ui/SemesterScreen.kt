package com.example.calculator.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.calculator.presentation.SemesterViewModel
import com.example.core_ui.theme.DefaultPadding
import com.example.core_ui.theme.DefaultPadding2x
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun SemesterScreen(
    modifier: Modifier = Modifier,
    onSemesterClick: (Int) -> Unit,
    onResetClick: () -> Unit
) {

    val viewModel = koinViewModel<SemesterViewModel>()
    val gpaState by viewModel.gpa

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            textAlign = TextAlign.Center,
            text = "GPA: $gpaState",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(DefaultPadding2x)
                .fillMaxWidth()
        )

        Spacer(
            modifier = Modifier
                .padding(DefaultPadding)
        )

        // Список семестров, занимающий оставшееся пространство
        LazyColumn(
            modifier = Modifier
                .weight(1f) // Это заставляет LazyColumn занимать оставшееся пространство
                .fillMaxWidth()
        ) {
            items(viewModel.semesters.size) { index ->
                SemesterItem(
                    semesterName = viewModel.semesters[index],
                    onClick = { onSemesterClick(index + 1) },
                )
            }
        }

        // Кнопка для обнуления данных, всегда находящаяся внизу
        Button(
            onClick = { viewModel.resetAllData() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(DefaultPadding2x)
        ) {
            Text(text = "Reset All Data")
        }

        Spacer(modifier = Modifier.padding(DefaultPadding2x * 2))
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