package com.example.challengeEvents.ui.event

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.challengeEvents.R
import com.example.challengeEvents.model.Event

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailScreen(event: Event, navController: NavController) {
    val context = LocalContext.current

    // Função para compartilhar
    fun shareEvent() {
        val shareText = "Confira este evento: ${event.title}\nDescrição: ${event.description}\nPreço: R$ ${event.price}\nLink: ${event.image}"
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        context.startActivity(Intent.createChooser(shareIntent, "Compartilhar evento"))
    }

    /// Busca imagem padrão de acordo com o [eventID]
    /// Caso seja uma difentente das listadas anteriormente, adiciona imagem genérica
    @Composable
    fun getDefaultImage(eventID: Int): Painter {
        return when (eventID % 6) { // Altere o número de acordo com o número de imagens padrão
            1 -> painterResource(R.drawable.default_image_1)
            2 -> painterResource(R.drawable.default_image_2)
            3 -> painterResource(R.drawable.default_image_3)
            4 -> painterResource(R.drawable.default_image_4)
            5 -> painterResource(R.drawable.default_image_5)
            else -> painterResource(R.drawable.default_image_0)
        }
    }

    Column {
        TopAppBar(
            title = { Text(text = "Detalhes do Evento") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                }
            },
            actions = {
                IconButton(onClick = { shareEvent() }) {
                    Icon(Icons.Default.Share, contentDescription = "Compartilhar")
                }
            }
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = event.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(
                model = event.image,
                contentDescription = null,
                error = getDefaultImage(event.id.toInt()),       // Imagem padrão em caso de erro
                modifier = Modifier.fillMaxWidth().height(200.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Descrição:", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = event.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Preço: R$ ${event.price}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
