package com.example.systemghost.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.systemghost.ui.theme.*

@Composable
fun MainToggleButton(isProtected: Boolean, onClick: () -> Unit) {
    val buttonColor = if (isProtected) NeonGreen else DangerRed
    val buttonText = if (isProtected) "PROTECTED" else "UNPROTECTED"

    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .size(180.dp)
            .shadow(
                elevation = if (isProtected) 20.dp else 0.dp,
                shape = CircleShape,
                ambientColor = buttonColor,
                spotColor = buttonColor
            ),
        shape = CircleShape,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = CyberBackground,
            contentColor = buttonColor
        ),
        border = BorderStroke(3.dp, buttonColor)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = if (isProtected) Icons.Filled.Lock else Icons.Filled.Warning,
                contentDescription = "Status Icon",
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = buttonText,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                letterSpacing = 2.sp
            )
        }
    }
}

@Composable
fun InfoCard(title: String, value: String, icon: ImageVector) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = CyberSurface),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, CyberSilver.copy(alpha = 0.2f))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = NeonGreen,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, color = CyberSilver, fontSize = 12.sp)
                Text(text = value, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
