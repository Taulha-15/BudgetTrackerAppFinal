import android.net.Uri
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.asImageBitmap
import android.graphics.BitmapFactory

@Composable
fun ExpenseScreen()

var amount by remember {
    mutableStateOf("")
}
var description by remember {
    mutableStateOf("")
}
var category by remember {
    mutableStateOf("")
}
var date by remember {
    mutableStateOf("")
}
var searchText by remember {
    mutableStateOf("")
}
var expenseList by remember {
    mutableStateOf(listOf<Expense>())
}

val context = LocalContext.current
val scope = rememberCoroutineScope()
val db = AppDatabase.getDatabase(context)

Column(
modifier = Modifier
.fillMaxSize()
.padding(24.dp)
) {

    Text("Add Expense", style = MaterialTheme.typography.headlineMedium)

    Spacer(modifier = Modifier.height(16.dp))

    TextField(
        value = amount,
        onValueChange = { amount = it },
        label = { Text("Amount") }
    )

    Spacer(modifier = Modifier.height(8.dp))

    TextField(
        value = description,
        onValueChange = { description = it },
        label = { Text("Description") }
    )

    Spacer(modifier = Modifier.height(8.dp))

    TextField(
        value = category,
        onValueChange = { category = it },
        label = { Text("Category") }
    )

    Spacer(modifier = Modifier.height(8.dp))

    TextField(
        value = date,
        onValueChange = { date = it },
        label = { Text("Date") }
    )

    TextField(
        value = searchText,
        onValueChange = { searchText = it },
        label = { Text("Search Expenses") }
    )

    Spacer(modifier = Modifier.height(16.dp))

    Button(onClick = {

        if (amount.isEmpty() || description.isEmpty() || category.isEmpty() || date.isEmpty())
        {
            Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
            return@Button
        }

        scope.launch {
            db.dao().insertExpense(
                Expense(
                    amount = amount.toDouble(),
                    description = description,
                    categoryName = category,
                    date = date
                )
            )

            Toast.makeText(context, "Expense Added", Toast.LENGTH_SHORT).show()

            amount = ""
            description = ""
            category = ""
            date = ""

            expenseList = db.dao().getExpenses()
        }

    })
    {
        Text("Save Expense")
    }

    Spacer(modifier = Modifier.height(16.dp))

    Button(onClick = {
        scope.launch {
            expenseList = db.dao().getExpenses()
        }
    })
    {
        Text("Load Expenses")
    }

    Spacer(modifier = Modifier.height(16.dp))

    expenseList.forEach {
        Text("R${it.amount} - ${it.categoryName} - ${it.date}")
    }
}
}

Button(onClick = {
    onNavigateToBudget()
})
{
    Text("Go to Budget")
}

var totals by remember {
    mutableStateOf(listOf<CategoryTotal>())
}

Button(onClick = {
    scope.launch {
        totals = db.dao().getCategoryTotals()
    }
})
{
    Text("Show Totals")
}

totals.forEach {
    Text("${it.categoryName}: R${it.total}")
}

var imageUri by remember { mutableStateOf<Uri?>(null) }
val launcher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.GetContent()
) { uri: Uri? ->
    imageUri = uri
}

Button(onClick = {
    launcher.launch("image/*")
})
{
    Text("Select Receipt Image")
}

db.dao().insertExpense(
Expense(
amount = amount.toDouble(),
description = description,
categoryName = category,
date = date,
imagePath = imageUri?.toString()
)
)

expenseList
.filter {
    it.description.contains(searchText, true)
}
.forEach {
    Text("R${it.amount} - ${it.categoryName}")
}

    it.imagePath?.let { path ->
        val uri = Uri.parse(path)
        val inputStream = context.contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream)

        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = "Receipt",
            modifier = Modifier.size(100.dp)
        )
    }

Button(
onClick = {  }
)
{
    Text("Back")
}