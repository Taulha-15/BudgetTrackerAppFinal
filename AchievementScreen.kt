import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AchievementScreen()
{

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    )
    {

        Text(
            "Achievements",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card {

            Column(
                modifier = Modifier.padding(16.dp)
            )
            {

                Text("Budget Champion !!")

                Text("You Stayed within your budget")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card {

            Column(
                modifier = Modifier.padding(16.dp)
            )
            {

                Text("Expense Master !!")

                Text("Added 10 expenses")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card {

            Column(
                modifier = Modifier.padding(16.dp)
            )
            {

                Text("Consistent Tracker !!")

                Text("Tracked expenses regularly")
            }
        }
    }
}

Button(
onClick = {  }
)
{
    Text("Back")
}