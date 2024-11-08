package org.eonarma.typesafenavkmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import org.eonarma.typesafenavkmp.navigation.RootNavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
        RootNavGraph(rememberNavController())
    }
}