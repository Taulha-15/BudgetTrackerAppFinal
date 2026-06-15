class MainActivity : ComponentActivity()
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            var currentScreen by remember {
                mutableStateOf(Screen.LOGIN)
            }

            when (currentScreen) {

                Screen.LOGIN -> LoginScreen(
                    onLoginSuccess = {
                        currentScreen = Screen.DASHBOARD
                    }
                )

                Screen.DASHBOARD -> DashboardScreen(
                    onCategories = {
                        currentScreen = Screen.CATEGORY
                    },
                    onExpenses = {
                        currentScreen = Screen.EXPENSE
                    },
                    onBudget = {
                        currentScreen = Screen.BUDGET
                    },
                    onGraph = {
                        currentScreen = Screen.GRAPH
                    },
                    onAchievements = {
                        currentScreen = Screen.ACHIEVEMENTS
                    },
                    onSettings = {
                        currentScreen = Screen.SETTINGS
                    }
                )

                Screen.CATEGORY -> CategoryScreen()

                Screen.EXPENSE -> ExpenseScreen()

                Screen.BUDGET -> BudgetScreen()

                Screen.GRAPH -> GraphScreen()

                Screen.ACHIEVEMENTS -> AchievementScreen()

                Screen.SETTINGS -> SettingsScreen()
            }
        }
    }
}