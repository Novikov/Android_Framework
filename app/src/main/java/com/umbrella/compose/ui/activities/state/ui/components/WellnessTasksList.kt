package com.umbrella.compose.ui.activities.state.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.umbrella.compose.ui.activities.state.WellnessTaskItem

@Composable
fun WellnessTasksList(
    list: List<WellnessTask>,
    onCloseTask: (WellnessTask) -> Unit,
    onCheckedTask: (WellnessTask, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = list,
            key = { task -> task.id }
        ) { task ->
            WellnessTaskItem(
                taskName = task.label,
                checked = task.checked,
                onCheckedChange = { checked -> onCheckedTask(task, checked) },
                onClose = { onCloseTask(task) }
            )
        }
    }
}