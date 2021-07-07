package work.racka.wallpapergallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toolbar
import work.racka.wallpapergallery.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, MainFragment.newInstance())
//                .commitNow()
//        }
//        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
//        setSupportActionBar(toolbar)
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main_menu, menu)
//        return true
//    }
}