package tosbik.ao.tmass.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tosbik.ao.tmass.ui.components.AppButton
import tosbik.ao.tmass.ui.theme.SecondaryColor
import tosbik.ao.tmass.ui.theme.TextColorSecondary
import kotlin.random.Random

@Composable
fun HomeProjectsModalSheet(onDismiss: () -> Unit) {
    var selectedProjectIndex by remember { mutableIntStateOf(Random.nextInt(0, 100)) }

    val projectList = remember {
        List(30) { index -> "Project #${index + 1}" to "Date: 2024-10-${index + 100}" }
    }

    Box {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 124.dp)
        ) {
            item {
                Text(
                    text = "Select a Project",
                    style = MaterialTheme.typography.bodyLarge.copy(color = TextColorSecondary),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            items(projectList.size) { index ->
                ProjectCard(
                    projectTitle = projectList[index].first,
                    projectDate = projectList[index].second,
                    isSelected = (index == selectedProjectIndex),
                    onClick = { selectedProjectIndex = index }
                )
            }
        }
        AppButton(
            text = "Done ✓",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 56.dp, start = 12.dp, end = 12.dp)
                .height(56.dp)
        ) {
            onDismiss()
        }
    }
}

@Composable
fun ProjectCard(
    projectTitle: String,
    projectDate: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) SecondaryColor else Color.White
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = projectTitle,
                    style = MaterialTheme.typography.headlineSmall,
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = projectDate,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }

            if (isSelected) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Selected",
                    tint = Color.White // Icon color, based on selection
                )
            }
        }
    }
}