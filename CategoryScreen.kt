@Composable
fun CategoryScreen()
{

    var categoryName by remember {
        mutableStateOf("")
    }
    var categoryList by remember {
        mutableStateOf(listOf<Category>())
    }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val db = AppDatabase.getDatabase(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text("Categories", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = categoryName,
            onValueChange = { categoryName = it },
            label = { Text("Category Name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (categoryName.isNotEmpty())
            {
                scope.launch {
                    db.dao().insertCategory(Category(name = categoryName))
                    categoryName = ""

                    categoryList = db.dao().getCategories()
                }
            }
        })
        {
            Text("Add Category")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            scope.launch {
                categoryList = db.dao().getCategories()
            }
        })
        {
            Text("Load Categories")
        }

        Spacer(modifier = Modifier.height(16.dp))

        categoryList.forEach
        {
            Text("- ${it.name}")
        }
    }
}

Button(onClick = {
    onNavigateToExpenses()
})
{
    Text("Go to Expenses")
}

Button(
onClick = { /* back */ }
)
{
    Text("Back")
}