package com.example.calculator.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.calculator.presentation.SubjectViewModel
import com.example.core_ui.theme.DefaultPadding
import com.example.core_ui.theme.DefaultPadding2x
import com.example.gpacalculator.db.CourseEntity
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.mp.KoinPlatform

@Composable
fun SubjectScreen(
    semester: Int,
    onBackClick: () -> Unit
) {
    KoinContext {
        KoinPlatform.getKoin()
    }

    val viewModel = koinViewModel<SubjectViewModel>()
    val subjects by viewModel.subjects.collectAsState()

    LaunchedEffect(semester) {
        viewModel.initializeSubjects(semester.toLong())
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(DefaultPadding2x)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Subjects for Semester $semester",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(DefaultPadding2x))

        // Список предметов
        LazyColumn {
            items(subjects.size) { index ->
                SubjectItem(
                    subject = subjects[index],
                    onSubjectChange = { updatedSubject ->
                        Log.d("Updating List", updatedSubject.toString())
                        viewModel.updateSubject(updatedSubject)
                    }
                )
            }
        }
    }
}

@Composable
fun SubjectItem(
    subject: CourseEntity,
    onSubjectChange: (CourseEntity) -> Unit
) {

    var name by remember { mutableStateOf(subject.name) }
    var expanded by remember { mutableStateOf(false) } // Для DropdownMenu

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(DefaultPadding),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(modifier = Modifier.padding(DefaultPadding2x)) {
            // TextField для ввода названия предмета
            TextField(
                value = name,
                onValueChange = { newName ->
                    Log.d("NAME", newName)
                    name = newName
                    onSubjectChange(subject.copy(name = newName))
                },
                label = { Text("Subject") },
                modifier = Modifier.weight(2f),
            )

            Spacer(modifier = Modifier.width(DefaultPadding))

            // TextField для ввода credits
            TextField(
                value = subject.credits.toString(),
                onValueChange = { newCredits ->
                    val parsedCredits = newCredits.toLongOrNull() ?: subject.credits
                    onSubjectChange(subject.copy(credits = parsedCredits))
                },
                label = { Text("Credits") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1.5f)
            )

            Spacer(modifier = Modifier.width(DefaultPadding))

            // DropdownMenu для оценки
            Box(modifier = Modifier.weight(2f)) {
                TextField(
                    value = subject.grade.toString(),
                    onValueChange = {},
                    modifier = Modifier.clickable { expanded = true },
                    enabled = false, // Выключаем возможность прямого ввода
                    label = {
                        Text("Grade")
                    },
                    trailingIcon = {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                    }
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    (1..5).forEach { newGrade ->
                        DropdownMenuItem(
                            onClick = {
                                onSubjectChange(subject.copy(grade = newGrade.toLong()))
                                expanded = false
                            },
                            text = {
                                Text(text = newGrade.toString())
                            }
                        )
                    }
                }
            }
        }
    }
}