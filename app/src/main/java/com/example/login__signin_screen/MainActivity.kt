package com.example.login__signin_screen

import android.graphics.drawable.Icon
import android.os.Bundle
import android.provider.CalendarContract
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.login__signin_screen.ui.theme.Login__SignIn_ScreenTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
val viewModel = remember { MainViewModel() }

//viewModel.getNews()
            val news=viewModel.newsResponse.collectAsState()
val topic=remember {
    mutableStateOf("")

}
            val isSearched=remember { mutableStateOf(false) }

Column(modifier = Modifier
    .fillMaxSize()
    .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

    OutlinedTextField(colors = OutlinedTextFieldDefaults.colors(Color.Black),trailingIcon = {
        Icon(modifier = Modifier.clickable {
            if (topic.value.isEmpty()){
                isSearched.value=true

            } else
            {
                viewModel.getNews(topic.value)

            }
                                           },tint = Color.Black,imageVector = Icons.Default.Search, contentDescription = null)
    },label = {
        Text(text = "Search",color = Color.Black,fontWeight = FontWeight.Bold,fontSize = 15.sp)
    },placeholder = {
        Text(text = "Search")
    },shape = RoundedCornerShape(10.dp),modifier = Modifier.fillMaxWidth(.9f).shadow(elevation = 20.dp),value = topic.value, onValueChange = {
        topic.value=it
    })
    Spacer(modifier = Modifier.height(10.dp))

Button(colors = ButtonDefaults.buttonColors(Color.Black),border = BorderStroke(2.dp,Color.Black),modifier = Modifier.fillMaxWidth(.6f),onClick = {
    if (topic.value.isEmpty()){
        isSearched.value=true

    } else
    {
        viewModel.getNews(topic.value)

    }


}) {
    Text(text = "Search",color = Color.White)

}
    if (isSearched.value){

        AlertDialog(iconContentColor = AlertDialogDefaults.iconContentColor,containerColor = Color.DarkGray,title = {
            Text(text = "please enter a valid topic", fontSize = 20.sp,color = Color.White)
        },icon = {
            Icon(tint = Color.White,imageVector = Icons.Default.Warning, contentDescription = null)
        },onDismissRequest = { isSearched.value=false},confirmButton = {
            Button(colors = ButtonDefaults.buttonColors(Color.Black),border = BorderStroke(2.dp,Color.Black),onClick = {
                Toast.makeText(this@MainActivity, "ok enter your topic", Toast.LENGTH_LONG).show()
                isSearched.value=false
            }){
                Text(text = "Ok",color = Color.White)
            }
            },dismissButton = {

            Button(colors = ButtonDefaults.buttonColors(Color.Black),border = BorderStroke(2.dp,Color.Black),onClick = {

               Toast.makeText(this@MainActivity, "Enter the topic you want to search about it", Toast.LENGTH_LONG).show()
                isSearched.value=false
            }){
                Text(text = "I don't Understand",color = Color.White)
            }

            },modifier = Modifier.fillMaxHeight(0.2f).fillMaxWidth())
    }
    LazyColumn {
        items(news.value?.articles?: emptyList()){
            Card(colors = CardDefaults.cardColors(Color.LightGray),border = BorderStroke(2.dp,Color.Black),shape = RectangleShape,modifier = Modifier.padding(10.dp),elevation = CardDefaults.cardElevation(10.dp)){
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(text = it.title?:"No Title",fontWeight = FontWeight.Bold,fontSize = 20.sp,color = Color.Red)
                    Text(text = it.description?:"No description",fontWeight = FontWeight.Bold,fontSize = 20.sp,color = Color.Blue)
                    Text(text = it.content?:"No content",fontWeight = FontWeight.Bold,fontSize =10.sp,color = Color.Black)
                    Text(text = it.author?:"No author",fontWeight = FontWeight.Bold,fontSize = 10.sp,color = Color.Yellow)
                    Text(text = it.publishedAt?:"No publishedAt",fontWeight = FontWeight.Bold,fontSize = 10.sp,color = Color.Red)
AsyncImage(model = it.urlToImage?:"No Image", contentDescription = null,modifier = Modifier.size(300.dp))

                }
            }
    }

}











//            val navController = rememberNavController()
//            NavHost(navController = navController, startDestination ="Login" ) {
//
//                composable("Login"){
//                    LoginScreen(navController) }
//
//                composable("Register/{name}/{password}"){ data ->
//                    val name = data.arguments?.getString("name")
//                    val password = data.arguments?.getString("password")
//                    RegisterScreen(navController, name ,password)
//
//                }
//
//
//
//            }

//
//            val isLogin = remember {
//                mutableStateOf(true)
//            }
//            if (isLogin.value){
//                LoginScreen(onClick = {
//                    isLogin.value=false
//                })
//            } else
//            {
//              RegisterScreen(onClick = {
//                  isLogin.value=true
//              })
//            }

        }
    }
}

//@Composable
//fun LoginScreen(onClick:()->Unit) {
//    val username= remember {
//        mutableStateOf("")
//    }
//    val password= remember {
//        mutableStateOf("")
//    }
//
//
// Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
//     Image(painter = painterResource(id = R.drawable.p1), contentDescription = " الصوره لم تتحمل جيدا")
//     Spacer(modifier = Modifier.height(10.dp))
//     OutlinedTextField(modifier = Modifier.fillMaxWidth(.9f),prefix = {
//                                Icons.Default.AccountCircle
//     },placeholder = {
//                                     Text(text = "Username")
//     },value=username.value, onValueChange = {
//         username.value=it
//     },leadingIcon = {
//         Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
//     })
//     Spacer(modifier = Modifier.height(10.dp))
//
//     OutlinedTextField(modifier = Modifier.fillMaxWidth(.9f),colors = OutlinedTextFieldDefaults.colors(Color.Black),prefix = {
//                                Icons.Default.MoreVert
//     },placeholder = {
//         Text(text = "password")
//     },value=password.value, onValueChange = {
//         password.value=it
//     },leadingIcon = {
//         Icon(imageVector = Icons.Default.Lock, contentDescription = null)
//     }
//     )
//
//     Spacer(modifier = Modifier.height(20.dp))
//
//     Button(modifier = Modifier
//         .fillMaxWidth(.9f)
//         .fillMaxHeight(.10f),colors = ButtonDefaults.buttonColors(Color.Blue),onClick = {onClick()  }) {
//         Text(text = " Login")
//     }
//     Spacer(modifier = Modifier.height(10.dp))
//
//     Row {
//         Text(text = " Don't have an account? ", fontWeight = FontWeight.Bold)
//         Text(modifier = Modifier.clickable { onClick() },text = "Sign Up", color = Color.Blue)
//     }
//     Spacer(modifier = Modifier.height(30.dp))
//     Row {
//         Box{
//             Image(modifier = Modifier
//                 .fillMaxWidth(0.11f)
//                 .fillMaxHeight(.11f),painter = painterResource(id = R.drawable.facebook), contentDescription =null )
//
//         }
//         Row {
//             Spacer(modifier = Modifier.width(10.dp))
//             OutlinedTextField(modifier = Modifier.fillMaxWidth(.8f),value = " Login with Facebook", onValueChange = {})
//         }
//     }
//
//
//
//
//
//     Spacer(modifier = Modifier.height(20.dp))
//
//
//     Row {
//         Box{
//             Image(modifier = Modifier
//                 .fillMaxWidth(0.11f)
//                 .fillMaxHeight(.11f),painter = painterResource(id = R.drawable.google), contentDescription =null )
//
//         }
//         Row {
//             Spacer(modifier = Modifier.width(10.dp))
//             OutlinedTextField(modifier = Modifier.fillMaxWidth(.8f),value = " Login with Facebook", onValueChange = {})
//         }
//     }
//
//
//
// }
//
//}

//@Composable
//fun RegisterScreen(onClick:()->Unit) {
//    val username= remember {
//        mutableStateOf("")
//    }
//    val password= remember {
//        mutableStateOf("")
//    }
//
//
//    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
//        Image(painter = painterResource(id = R.drawable.p2), contentDescription = " الصوره لم تتحمل جيدا")
//        Spacer(modifier = Modifier.height(10.dp))
//        OutlinedTextField(modifier = Modifier.fillMaxWidth(.9f),prefix = {
//            Icons.Default.AccountCircle
//        },placeholder = {
//            Text(text = "Email")
//        },value=username.value, onValueChange = {
//            username.value=it
//        },leadingIcon = {
//            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
//        })
//        Spacer(modifier = Modifier.height(10.dp))
//
//        OutlinedTextField(modifier = Modifier.fillMaxWidth(.9f),colors = OutlinedTextFieldDefaults.colors(Color.Black),prefix = {
//            Icons.Default.MoreVert
//        },placeholder = {
//            Text(text = "password")
//        },value=password.value, onValueChange = {
//            password.value=it
//        },leadingIcon = {
//            Icon(imageVector = Icons.Default.Lock, contentDescription = null)}
//
//
//
//        )
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        Button(modifier = Modifier
//            .fillMaxWidth(.9f)
//            .fillMaxHeight(.10f),colors = ButtonDefaults.buttonColors(Color.Blue),onClick = {onClick()  }) {
//            Text(text = " Sign Up")
//        }
//        Spacer(modifier = Modifier.height(10.dp))
//
//        Row {
//            Text(text = "Already have an account? ", fontWeight = FontWeight.Bold)
//            Text(modifier = Modifier.clickable { onClick() },text = "Login", color = Color.Blue, fontWeight = FontWeight.Bold)
//        }
//        Spacer(modifier = Modifier.height(30.dp))
//
//        Row {
//            Box{
//                Image(modifier = Modifier
//                    .fillMaxWidth(0.11f)
//                    .fillMaxHeight(.11f),painter = painterResource(id = R.drawable.facebook), contentDescription =null )
//
//            }
//            Row {
//                Spacer(modifier = Modifier.width(10.dp))
//                OutlinedTextField(modifier = Modifier.fillMaxWidth(.8f),value = " Sign with Facebook", onValueChange = {})
//            }
//        }
//
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//
//        Row {
//            Box{
//                Image(modifier = Modifier
//                    .fillMaxWidth(0.11f)
//                    .fillMaxHeight(.11f),painter = painterResource(id = R.drawable.google), contentDescription =null )
//
//            }
//            Row {
//                Spacer(modifier = Modifier.width(10.dp))
//                OutlinedTextField(modifier = Modifier.fillMaxWidth(.8f),value = " Sign with Facebook", onValueChange = {})
//            }
//        }
//
//
//
//    }
//
//}

@Composable
fun LoginScreen(navController: NavHostController) {

    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    Column(modifier= Modifier
        .fillMaxSize()
        .padding(12.dp)) {

        Text(text = "Login Screen", fontSize =50.sp,fontWeight = FontWeight.Bold, color = Color.Black,modifier = Modifier.padding(12.dp))

        OutlinedTextField(leadingIcon = { Icon(tint = Color.LightGray,imageVector = Icons.Default.AccountCircle, contentDescription = null) },supportingText = { if(username.value.isEmpty()

            ) Text(text = "Username is required",color = Color.Black)},shape = RoundedCornerShape(5.dp),label = { Text(text = "Username",color = Color.Black) },placeholder = { Text(text = "Username")},value = username.value, onValueChange = { newValue ->username.value= newValue})

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(trailingIcon = { Icon(tint = Color.LightGray,imageVector = Icons.Default.Lock, contentDescription = null) },supportingText = { if(password.value.isEmpty()

        ) Text(text = "password is required",color = Color.Black)},shape = RoundedCornerShape(5.dp),label = { Text(text = "password",color = Color.Black) },placeholder = { Text(text = "password")},value = password.value, onValueChange = { newValue -> password.value= newValue})
        Spacer(modifier = Modifier.height(10.dp))

        Button(colors =  ButtonDefaults.buttonColors(Color.Black),border =BorderStroke(1.dp,Color.Black),shape = RoundedCornerShape(
            10.dp
        ),onClick = { navController.navigate("Register/${username.value?:"Guest"}/${password.value?:"Guest123"}")}) {
            Text(text = " Don't have an account? Register", color = Color.White,fontWeight = FontWeight.Bold,fontSize = 15.sp)
        }
    }



}

@Composable
fun RegisterScreen(navController: NavHostController, name:String?,pass:String?) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    Column(modifier= Modifier
        .fillMaxSize()
        .padding(12.dp)) {

        Text(text = "Register Screen for : $name and $pass ", color = Color.Black, fontSize =40.sp,fontWeight = FontWeight.Bold, modifier = Modifier.padding(12.dp))
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(leadingIcon = { Icon(tint = Color.LightGray,imageVector = Icons.Default.AccountCircle, contentDescription = null) },supportingText = { if(username.value.isEmpty()

        ) Text(text = "Username is required",color = Color.Black)},shape = RoundedCornerShape(5.dp),label = { Text(text = "Username",color = Color.Black) },placeholder = { Text(text = "Username")},value = username.value, onValueChange = { newValue ->username.value= newValue})
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(trailingIcon = { Icon(tint = Color.LightGray,imageVector = Icons.Default.Lock, contentDescription = null) },supportingText = { if(password.value.isEmpty()

        ) Text(text = "password is required",color = Color.Black)},shape = RoundedCornerShape(5.dp),label = { Text(text = "password",color = Color.Black) },placeholder = { Text(text = "password")},value = password.value, onValueChange = { newValue -> password.value= newValue})
        Spacer(modifier = Modifier.height(20.dp))
        Button(colors =  ButtonDefaults.buttonColors(Color.Black),border =BorderStroke(2.dp,Color.Black),shape = RoundedCornerShape(
            10.dp
        ),onClick = {navController.popBackStack() }) {
            Text(text = " Already have an account? Login", color = Color.White)}
    }}

    @Composable
    fun show() {

        AlertDialog(onDismissRequest = { /*TODO*/ },confirmButton = {},dismissButton = {})


    }

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Login__SignIn_ScreenTheme {
        Greeting("Android")
    }
}}