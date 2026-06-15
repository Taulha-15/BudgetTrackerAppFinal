@Composable
fun BudgetScreen() {

    var min by remember { mutableStateOf("") }
    var max by remember { mutableStateOf("") }

    var budgetList by remember { mutableStateOf(listOf<Budget>()) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val db = AppDatabase.getDatabase(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text("Budget Goals", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = min,
            onValueChange = { min = it },
            label = { Text("Minimum Goal") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = max,
            onValueChange = { max = it },
            label = { Text("Maximum Goal") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {

            if (min.isEmpty() || max.isEmpty()) {
                Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
                return@Button
            }

            scope.launch {
                db.dao().insertBudget(
                    Budget(
                        minGoal = min.toDouble(),
                        maxGoal = max.toDouble()
                    )
                )

                Toast.makeText(context, "Budget Saved", Toast.LENGTH_SHORT).show()

                min = ""
                max = ""

                budgetList = db.dao().getBudgets()
            }

        }) {
            Text("Save Budget")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            scope.launch {
                budgetList = db.dao().getBudgets()
            }
        }) {
            Text("Load Budget")
        }

        Spacer(modifier = Modifier.height(16.dp))

        budgetList.forEach {
            Text("Min: R${it.minGoal} | Max: R${it.maxGoal}")
        }
    }
}