package com.example.intentsapplications

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.intentsapplications.ui.theme.IntentsApplicationsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntentsApplicationsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Homescreen()
                }
            }
        }
    }
}

@Composable
fun Homescreen() {
  Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.SpaceEvenly
  ) {
      //shorturl.at/stwQ9 has all materials for intents
      var context = LocalContext.current
      Button(onClick = {
          val uri = Uri.parse("smsto:0712982944")
          val intent = Intent(Intent.ACTION_SENDTO, uri)
          intent.putExtra("sms_body", "The SMS text")
          context.startActivity(intent)
      }) {
          Text(text = "SMS")
      }
      Button(onClick = {
          val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "praisemunene87@gmail.com", null))
          emailIntent.putExtra(Intent.EXTRA_SUBJECT, "job application")
          emailIntent.putExtra(Intent.EXTRA_TEXT, "dear sir following the job advertisement...")
          context.startActivity(Intent.createChooser(emailIntent, "Send email..."))
      }) {
          Text(text = "Email")
      }
      Button(onClick = {
          val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
          startActivityForResult(context as Activity, takePictureIntent, 1,null)
      }) {
          Text(text = "Camera")
      }
      Button(onClick = {
          val shareIntent = Intent(Intent.ACTION_SEND)
          shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
          shareIntent.type = "text/plain"
          shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey, download this app! from htttps://playstore.com")
          context.startActivity(shareIntent)
      }) {
          Text(text = "Share")
      }
      Button(onClick = {
          val simToolKitLaunchIntent: Intent = context.getPackageManager().getLaunchIntentForPackage("com.android.stk")!!
          if (simToolKitLaunchIntent != null) {
              context.startActivity(simToolKitLaunchIntent)
          }
      }) {
          Text(text = "Mpesa")
      }
      Button(onClick = {
          val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+254740690592"))
          if (ContextCompat.checkSelfPermission(
                  context,
                  android.Manifest.permission.CALL_PHONE
              ) != PackageManager.PERMISSION_GRANTED
          ) {
              ActivityCompat.requestPermissions(
                  context as Activity,
                  arrayOf<String>(android.Manifest.permission.CALL_PHONE),
                  1
              )
          } else {
              context.startActivity(intent)
          }

      }) {
          Text(text = "Call")
      }

  }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    IntentsApplicationsTheme {
        Homescreen()
    }
}