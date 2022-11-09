package com.isaiasmtp.bizcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.isaiasmtp.bizcard.ui.theme.BizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BizCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard(modifier: Modifier = Modifier) {
    val buttonProjectState = remember {
        mutableStateOf(false)
    }
    val buttonContactState = remember {
        mutableStateOf(false)
    }

    Surface( modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
    ){
        Card(modifier = Modifier
            .width(200.dp)
            .height(390.dp)
            .padding(12.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            backgroundColor = Color.White
        ) {
            Column(modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                Avatar()
                Divider()
                Info()
                ShowProjects(buttonProjectState, buttonContactState)
                if (buttonProjectState.value){
                    Content()
                }
                if (buttonContactState.value){
                    Contact()
                }

            }
        }

    }
}

@Composable
private fun Contact() {
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(15.dp)
    ){
        Column(modifier = Modifier
            .padding(3.dp)
        ) {
            Text(text = "Email: isaiasmteixeira@gmail.com",
                style = MaterialTheme.typography.subtitle1
            )
            Text(text = "Github: @isaiasmtp",
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Composable
private fun Content() {
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(5.dp)
    ){
        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
            shape = RoundedCornerShape(corner = CornerSize(5.dp)),
        ) {
            Portifolio(data = listOf("Project 1", "Project 2", "Project 3"))
        }
    }
}

@Composable
private fun Portifolio(data: List<String>){
     LazyColumn{
         items(data){ item ->
             Card(modifier = Modifier
                 .padding(13.dp)
                 .fillMaxWidth(),
                 shape = RectangleShape
             ) {
                Column(modifier = Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colors.surface)
                    .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(item, style = MaterialTheme.typography.h6)
                    Text(text = "A wonderful project with wonderful people", style = MaterialTheme.typography.subtitle1)
                    Text(text = "2020-2022", style = MaterialTheme.typography.caption)
                }
             }
         }
     }
}

@Composable
private fun ShowProjects(buttonProjectState: MutableState<Boolean>, buttonContactState: MutableState<Boolean>) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Button(onClick = {
            buttonProjectState.value = !buttonProjectState.value
            if(buttonProjectState.value){
                buttonContactState.value = false
            }
        }) {
            Text(
                text = "Show Projects",
                style = MaterialTheme.typography.button
            )
        }
        Button(onClick = {
            buttonContactState.value = !buttonContactState.value
            if(buttonContactState.value){
                buttonProjectState.value = false
            }

        }) {
            Text(
                text = "Show Contact",
                style = MaterialTheme.typography.button
            )
        }
    }
}

@Composable
private fun Info(){
    Column(modifier = Modifier
        .padding(20.dp)
    ) {
        Text(text = "Isaías Martins",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
        )
        Text(text = "Mobile Programmer",
            style = MaterialTheme.typography.subtitle1
        )


    }
}

@Composable
private fun Avatar(modifier: Modifier = Modifier){
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(10.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 10.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "Avatar",
            modifier = Modifier
                .size(135.dp),
            contentScale = ContentScale.Crop
        )
    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BizCardTheme {
        CreateBizCard()
    }
}