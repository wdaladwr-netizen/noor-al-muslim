package com.nooralmoslim.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Green = Color(0xFF138A72)
private val Cream = Color(0xFFF7FBF8)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { NoorApp() }
    }
}

@Composable
fun NoorApp() {
    var selected by remember { mutableIntStateOf(0) }
    val titles = listOf("الرئيسية", "القرآن", "المسبحة", "الأذكار")
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = Cream) {
            Scaffold(
                containerColor = Cream,
                topBar = { AppHeader(titles[selected]) },
                bottomBar = {
                    NavigationBar(containerColor = Color.White) {
                        val icons = listOf(Icons.Default.Home, Icons.Default.MenuBook, Icons.Default.Spa, Icons.Default.Book)
                        titles.forEachIndexed { index, title ->
                            NavigationBarItem(
                                selected = selected == index,
                                onClick = { selected = index },
                                icon = { Icon(icons[index], contentDescription = title) },
                                label = { Text(title) }
                            )
                        }
                    }
                }
            ) { padding ->
                when (selected) {
                    0 -> HomeScreen(Modifier.padding(padding))
                    1 -> QuranScreen(Modifier.padding(padding))
                    2 -> TasbeehScreen(Modifier.padding(padding))
                    else -> AzkarScreen(Modifier.padding(padding))
                }
            }
        }
    }
}

@Composable
private fun AppHeader(title: String) {
    Row(
        modifier = Modifier.fillMaxWidth().background(Cream).padding(horizontal = 20.dp, vertical = 18.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.End) {
            Text("نور المسلم", color = Green, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(title, color = Color.DarkGray, fontSize = 13.sp)
        }
        Text("﷽", color = Green, fontSize = 28.sp)
    }
}

@Composable
private fun HomeScreen(modifier: Modifier) {
    Column(modifier.fillMaxSize().padding(20.dp), horizontalAlignment = Alignment.End) {
        Card(colors = CardDefaults.cardColors(containerColor = Green), shape = RoundedCornerShape(22.dp)) {
            Column(Modifier.fillMaxWidth().padding(22.dp), horizontalAlignment = Alignment.End) {
                Text("وَاذْكُر رَّبَّكَ إِذَا نَسِيتَ", color = Color.White, fontSize = 21.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text("اجعل لسانك عامرًا بذكر الله", color = Color.White.copy(alpha = .85f))
            }
        }
        Spacer(Modifier.height(22.dp))
        Text("الوصول السريع", fontSize = 19.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(10.dp))
        QuickCard("القرآن الكريم", "تابع وردك اليومي", Icons.Default.MenuBook)
        QuickCard("المسبحة", "سبح واستغفر", Icons.Default.Spa)
        QuickCard("أذكار اليوم", "أذكار الصباح والمساء", Icons.Default.Book)
        Spacer(Modifier.height(12.dp))
        Text("القراء", fontSize = 19.sp, fontWeight = FontWeight.Bold)
        Text("المنشاوي • عبد الباسط • الحصري • السديس", color = Color.Gray, modifier = Modifier.padding(top = 8.dp))
    }
}

@Composable
private fun QuickCard(label: String, subtitle: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Card(Modifier.fillMaxWidth().padding(vertical = 5.dp), colors = CardDefaults.cardColors(Color.White)) {
        Row(Modifier.fillMaxWidth().padding(15.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = Green, modifier = Modifier.size(30.dp))
            Column(Modifier.weight(1f).padding(start = 15.dp), horizontalAlignment = Alignment.End) {
                Text(label, fontWeight = FontWeight.Bold, fontSize = 17.sp)
                Text(subtitle, color = Color.Gray, fontSize = 13.sp)
            }
        }
    }
}

@Composable
private fun QuranScreen(modifier: Modifier) {
    val surahs = listOf("الفاتحة", "البقرة", "آل عمران", "النساء", "المائدة", "الأنعام", "الأعراف", "الأنفال")
    Column(modifier.fillMaxSize().padding(20.dp), horizontalAlignment = Alignment.End) {
        Text("سور القرآن الكريم", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("اختر سورة للقراءة والاستماع", color = Color.Gray, modifier = Modifier.padding(vertical = 8.dp))
        LazyColumn { items(surahs) { surah ->
            Card(Modifier.fillMaxWidth().padding(vertical = 4.dp), colors = CardDefaults.cardColors(Color.White)) {
                Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.PlayArrow, "تشغيل", tint = Green)
                    Text(surah, Modifier.weight(1f), textAlign = TextAlign.End, fontSize = 18.sp)
                }
            }
        } }
    }
}

@Composable
private fun TasbeehScreen(modifier: Modifier) {
    var count by remember { mutableIntStateOf(0) }
    Column(modifier.fillMaxSize().padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("المسبحة الإلكترونية", fontSize = 21.sp, fontWeight = FontWeight.Bold)
        Text("سبحان الله والحمد لله والله أكبر", color = Color.Gray, modifier = Modifier.padding(top = 8.dp))
        Spacer(Modifier.height(40.dp))
        Box(Modifier.size(220.dp).background(Green, CircleShape).clickable { count++ }, contentAlignment = Alignment.Center) {
            Text(count.toString(), color = Color.White, fontSize = 55.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(20.dp))
        Text("اضغط على الدائرة للتسبيح", color = Color.Gray)
        IconButton(onClick = { count = 0 }) { Icon(Icons.Default.Refresh, "تصفير", tint = Green) }
    }
}

@Composable
private fun AzkarScreen(modifier: Modifier) {
    val azkar = listOf(
        "أستغفر الله وأتوب إليه",
        "سبحان الله وبحمده، سبحان الله العظيم",
        "لا إله إلا الله وحده لا شريك له، له الملك وله الحمد وهو على كل شيء قدير",
        "اللهم صل وسلم وبارك على نبينا محمد ﷺ"
    )
    LazyColumn(modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        item { Text("أذكار مختارة", fontSize = 21.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End) }
        items(azkar) { dhikr ->
            Card(colors = CardDefaults.cardColors(Color.White)) {
                Text(dhikr, Modifier.fillMaxWidth().padding(20.dp), textAlign = TextAlign.End, fontSize = 18.sp, lineHeight = 30.sp)
            }
        }
    }
}
