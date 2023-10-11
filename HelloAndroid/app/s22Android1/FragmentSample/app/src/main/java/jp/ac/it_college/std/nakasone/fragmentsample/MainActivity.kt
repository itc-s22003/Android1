package jp.ac.it_college.std.nakasone.fragmentsample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import jp.ac.it_college.std.nakasone.fragmentsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // イベント識別名、LifecycleOwner,イベントリスナ を渡して
        // Fragment からの応答を待ち受ける
        supportFragmentManager.run {
            setFragmentResultListener(
                REQUEST_SELECTED_MENU, this@MainActivity, ::onSelectedMenu
            )
            setFragmentResultListener(
                REQUEST_BACK_MENU, this@MainActivity, ::onBackMenu
            )
        }

    }

    private fun onSelectedMenu(requestKey: String, bundle: Bundle) {
        Log.i("SELECTED_MENU", "requestKey: ${requestKey}, bundle: ${bundle}.")
        // MenuListFragment から受け取ったデータを詰め直して
        // MenuThanksFragment を表示させる。
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            val args = bundleOf(
                ARG_NAME to bundle.getString(RESULT_NAME, ""),
                ARG_PRICE to bundle.getInt(RESULT_PRICE, 0)
            )
            // fragmentMainContainer があればスマホ版
            if (binding.fragmentMainContainer != null) {
                addToBackStack("Only List")
                replace(R.id.fragmentMainContainer, MenuThanksFragment::class.java, args)
            } else {
                // fragmentMainContainer がないのでタブレットの横向き版
                replace(R.id.fragmentThanksContainer, MenuThanksFragment::class.java, args)
            }
        }
    }

    private fun onBackMenu(requestKey: String, bundle: Bundle) {
        if (binding.fragmentMainContainer != null) {
            supportFragmentManager.popBackStack()
        } else {
            supportFragmentManager.commit {
                binding.fragmentThanksContainer?.let {
                    remove(it.getFragment())
                }
            }
        }
    }
}