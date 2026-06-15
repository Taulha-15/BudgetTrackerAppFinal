import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen()
{

    var totalSpent by remember {
        mutableStateOf(0.0)
    }
    var maxGoal by remember {
        mutableStateOf(1.0)
    }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val db = AppDatabase.getDatabase(context)

    LaunchedEffect(Unit)
    {

        scope.launch {

            totalSpent = db.dao().getTotalSpent() ?: 0.0

            val budgets = db.dao().getBudgets()

            if (budgets.isNotEmpty())
            {
                maxGoal = budgets.last().maxGoal
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            "Budget Progress",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Spent: R$totalSpent")

        Text("Goal: R$maxGoal")

        Spacer(modifier = Modifier.height(16.dp))

        LinearProgressIndicator(
            progress = (totalSpent / maxGoal).toFloat(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun DashboardScreen(
    onCategories: () -> Unit,
    onExpenses: () -> Unit,
    onBudget: () -> Unit,
    onGraph: () -> Unit,
    onAchievements: () -> Unit,
    onSettings: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            "Budget Tracker Dashboard",
            style = MaterialTheme.typography.headlineMedium
        )

        Button(onClick = onCategories)
        {
            Text("Categories")
        }

        Button(onClick = onExpenses)
        {
            Text("Expenses")
        }

        Button(onClick = onBudget)
        {
            Text("Budget Goals")
        }

        Button(onClick = onGraph)
        {
            Text("Spending Graph")
        }

        Button(onClick = onAchievements)
        {
            Text("Achievements")
        }

        Button(onClick = onSettings)
        {
            Text("Settings")
        }
    }
}