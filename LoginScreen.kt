import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen() {import androidx.compose.runtime.rememberCoroutineScope
        import kotlinx.coroutines.launch
        import android.widget.Toast
        import androidx.compose.ui.platform.LocalContext

        import androidx.compose.runtime.*
        import androidx.compose.foundation.layout.*
        import androidx.compose.material3.*
        import androidx.compose.ui.Modifier
        import androidx.compose.ui.unit.dp

        @Composable
        fun LoginScreen() {

            var username by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            val context = LocalContext.current
            val scope = rememberCoroutineScope()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {

                    if (username.isEmpty() || password.isEmpty()) {
                        Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    val db = AppDatabase.getDatabase(context)

                    scope.launch {
                        val user = db.dao().login(username, password)

                        if (user != null) {
                            Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Invalid login", Toast.LENGTH_SHORT).show()
                        }
                    }

                }) {
                    Text("Login")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = {
                    Toast.makeText(context, "Register screen next", Toast.LENGTH_SHORT).show()
                }) {
                    Text("Register")
                }
            }
        }

    @Composable
    fun LoginScreen(onNavigateToRegister: () -> Unit) {
    }

    Button(onClick = {
        onNavigateToRegister()
    }) {
        Text("Register")
    }

    @Composable
    fun RegisterScreen(onBackToLogin: () -> Unit) {

        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        val context = LocalContext.current
        val scope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Register", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                val db = AppDatabase.getDatabase(context)

                scope.launch {
                    db.dao().insertUser(User(username = username, password = password))
                    Toast.makeText(context, "User Registered", Toast.LENGTH_SHORT).show()

                    onBackToLogin()
                }

            }) {
                Text("Register")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                onBackToLogin()
            }) {
                Text("Back to Login")
            }
        }
    }

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
                return@Button
            }

            val db = AppDatabase.getDatabase(context)

            scope.launch {
                val user = db.dao().login(username, password)

                if (user != null) {
                    Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Invalid login", Toast.LENGTH_SHORT).show()
                }
            }

        }) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            Toast.makeText(context, "Register screen next", Toast.LENGTH_SHORT).show()
        }) {
            Text("Register")
        }
    }
}

@Composable
fun LoginScreen(onNavigateToRegister: () -> Unit) {
}

Button(onClick = {
    onNavigateToRegister()
}) {
    Text("Register")
}

@Composable
fun RegisterScreen(onBackToLogin: () -> Unit) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Register", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
                return@Button
            }

            val db = AppDatabase.getDatabase(context)

            scope.launch {
                db.dao().insertUser(User(username = username, password = password))
                Toast.makeText(context, "User Registered", Toast.LENGTH_SHORT).show()

                onBackToLogin()
            }

        }) {
            Text("Register")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            onBackToLogin()
        }) {
            Text("Back to Login")
        }
    }
}