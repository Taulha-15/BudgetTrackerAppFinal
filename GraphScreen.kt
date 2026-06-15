@Composable
fun GraphScreen()
{

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    )
    {

        Text(
            text = "Spending Graph",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Graph will display category spending")

        Text("Minimum Goal Line")

        Text("Maximum Goal Line")
    }
}

Button
(
onClick = { /* back */ }
)
{
    Text("Back")
}