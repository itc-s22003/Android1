package jp.ac.it_college.std.nakasone.intentsample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import jp.ac.it_college.std.nakasone.intentsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // RecyclerView にアダプタとか使うための初期設定。
        // 呼び出すプロパティ・メソッドがいっぱいあるので apply 関数で短縮してる。
        binding.lvMenu.apply {
            // アダプタとデータをセット
            adapter = MenuListAdapter(menuList) { name, price ->
                /* アイテムを選択されたときの処理はここに書くことができる。 */
                // インテントオブジェクトの作成
                val intent2MenuThanks = Intent(
                    this@MainActivity,
                    MenuThanksActivity::class.java
                )
                // 次の画面に送るデータをセットする
                intent2MenuThanks.putExtra("menuName", name)
                intent2MenuThanks.putExtra("menuPrice", price)
                // 次の画面を起動
                startActivity(intent2MenuThanks)
            }

            // RecyclerView の表示に関する設定
            LinearLayoutManager(this@MainActivity).let {
                // 直線的(垂直方向)に並べる
                layoutManager = it
                // 区切り線を並べる方向に合わせて表示する
                addItemDecoration(DividerItemDecoration(this@MainActivity, it.orientation))
            }
        }
    }
}