package com.example.jetpack.compose.practice

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.jetpack.compose.practice.ui.theme.JetpackComposePracticeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    /*Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Greeting("I am learning Compose")
                        ShowButton("Hit to show", "Welcome to Compose!")

                        // below line is use to get
                        // the context for our app.
                        val context = LocalContext.current
                        displayToast(context, simpleMaterialTextFieldComponent().text)

                    }*/
                    LoadSingleUI()
                }
            }
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoadSingleUI() {
    Box(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            val keyboardController = LocalSoftwareKeyboardController.current

            Text(text = "Hello I'm learning the Jetpack Compose!")

            var emailState by remember { mutableStateOf(TextFieldValue("")) }
            var passwordState by remember { mutableStateOf(TextFieldValue("")) }
            var mobileNumberState by remember { mutableStateOf(TextFieldValue("")) }
            var ageState by remember { mutableStateOf(TextFieldValue("")) }
            var shortDescription by remember { mutableStateOf(TextFieldValue("")) }

            TextField(
                value = emailState,
                modifier = Modifier
                    .padding(16.dp, 3.dp, 16.dp, 3.dp)
                    .fillMaxWidth(),
                onValueChange = { emailState = it },
                placeholder = { Text("Enter Email*") },
                maxLines = 1,
                visualTransformation = VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .size(20.dp),
                        painter = painterResource(id = R.drawable.ic_mail),
                        contentDescription = null // decorative element
                    )
                },
            )

            OutlinedTextField(
                value = passwordState,
                modifier = Modifier
                    .padding(16.dp, 3.dp, 16.dp, 3.dp)
                    .fillMaxWidth(),
                onValueChange = { passwordState = it },
                placeholder = { Text("Password*") },
                maxLines = 1,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                shape = RoundedCornerShape(27.dp),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }),
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .size(20.dp),
                        painter = painterResource(id = R.drawable.ic_password),
                        contentDescription = null // decorative element
                    )
                },
            )

            OutlinedTextField(
                value = mobileNumberState,
                modifier = Modifier
                    .padding(16.dp, 3.dp, 16.dp, 3.dp)
                    .fillMaxWidth(),
                onValueChange = { if (it.text.length <= 10) mobileNumberState = it },
                label = { Text(text = stringResource(R.string.mobile_number)) },
                maxLines = 1,
                visualTransformation = VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                ),
                leadingIcon = {
                    Icon(
                        modifier = Modifier
                            .size(20.dp),
                        painter = painterResource(id = R.drawable.ic_mobile),
                        contentDescription = null // decorative element
                    )
                },
            )

            Text(
                text = "Age*", modifier = Modifier
                    .padding(16.dp, 3.dp, 16.dp, 0.dp)
                    .align(Alignment.Start)
            )
            TextField(
                value = ageState,
                modifier = Modifier
                    .padding(16.dp, 0.dp, 16.dp, 3.dp)
                    .fillMaxWidth(),
                maxLines = 1,
                onValueChange = {
                    if (it.text.length <= 2)
                        ageState = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
            )

            OutlinedTextField(
                value = shortDescription,
                modifier = Modifier
                    .padding(16.dp, 10.dp, 16.dp, 0.dp)
                    .height(150.dp)
                    .fillMaxWidth(),
                onValueChange = { if (it.text.length <= 50) shortDescription = it },
                placeholder = { Text(text = stringResource(R.string.long_text)) },
                maxLines = 5,
                visualTransformation = VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }),
            )
            Text(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(0.dp, 0.dp, 16.dp, 0.dp),
                text = shortDescription.text.length.toString() + " / 50",
            )

            // below line is use to get
            // the context for our app.
            val context = LocalContext.current
            // below line is use to create a button.
            Button(
                // below line is use to add onclick
                // parameter for our button onclick
                onClick = {
                    keyboardController?.hide()
                    if (emailState.text.isNotEmpty() && validEMailEditTextValue(emailState.text)) {
                        if (passwordState.text.isNotEmpty() && passwordState.text.length > 3) {
                            if (mobileNumberState.text.isNotEmpty() && mobileNumberState.text.length == 10) {
                                if (ageState.text.toInt() > 0) {
                                    displayToast(context, "All the values are valid now!")
                                } else
                                    displayToast(context, "Enter the valid age")
                            } else
                                displayToast(
                                    context,
                                    "Enter mobile number " + mobileNumberState.text.length.toString()
                                )
                        } else
                            displayToast(context, "Enter valid password")
                    } else
                        displayToast(context, "Enter a valid mail address")
                },
                // in below line we are using modifier
                // which is use to add padding to our button
                modifier = Modifier
                    .padding(all = Dp(10F)),

                // below line is use to set or
                // button as enable or disable.
                enabled = true,

                // below line is use to
                // add border to our button.
                border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Yellow)),

                // below line is use to add shape for our button.
                shape = MaterialTheme.shapes.medium,
            )
            // below line is use to
            // add text on our button
            {
                Text(text = "Validate Now", color = Color.White)
            }
        }
    }
}

fun displayToast(context: Context, toastText: String) {
    Toast.makeText(
        context,
        toastText,
        Toast.LENGTH_SHORT
    ).show()
}

fun validEMailEditTextValue(editText: String): Boolean {
    val pattern1 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    val pattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+"
    return editText.trim()
        .matches(pattern1.toRegex()) || editText.trim()
        .matches(pattern2.toRegex())
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposePracticeTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LoadSingleUI()
        }
    }
}