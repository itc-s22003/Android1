package jp.ac.it_college.std.nakasone.listviewreplacesample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import jp.ac.it_college.std.nakasone.listviewreplacesample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var menuList = mutableListOf(
            "から揚げ定食",
            "ハンバーグ定食",
            "生姜焼き定食",
            "ステーキ定食",
            "野菜炒め定食",
            "とんかつ定食",
            "ミンチかつ定食",
            "チキンカツ定食",
            "コロッケ定食",
            "回鍋肉定食",
            "麻婆豆腐定食",
            "青椒肉絲定食",
            "八宝菜定食",
            "酢豚定食",
            "豚の角煮定食",
            "焼き鳥定食",
            "焼き魚定食",
            "焼肉定食",
        )

        // RecyclerView の設定
        binding.rvMenu.apply {
            // 表示方法を決める。LinearLayoutManager は直線的に表示させるやつ。
            val linearLayoutManager = LinearLayoutManager(this@MainActivity)
            layoutManager = linearLayoutManager

            // データを管理する Adapter を設定する。自作した MenuListAdapter を使う。
            adapter = MenuListAdapter(menuList)

            // アイテムの装飾として、区切り線をセットする
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity, linearLayoutManager.orientation
                )
            )
        }
    }
}