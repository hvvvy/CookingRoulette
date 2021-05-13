package com.example.cookingroulette

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.insert
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.cookingroulette.db.CuisineOpenHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    //オプションメニューを初期化する
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            //登録が選択された時の処理
            R.id.action_registration -> {
                val editText = EditText(this)
                editText.hint = "料理名"
                //アラートダイアログ
                AlertDialog.Builder(this)
                        .setTitle("料理名を記入してください")
                        //.setMessage("メッセージ")
                        .setView(editText)
                        .setPositiveButton("登録", DialogInterface.OnClickListener { dialog, which ->
                            // 登録ボタンが押された時の処理
                                val helper = CuisineOpenHelper(applicationContext)

                                //use{}はDB使用後半強制的にクローズしてくれる
                                //editTextに入力した料理をDBに登録する
                                helper.use{
                                    insert(CuisineOpenHelper.TABLE_NAME,
                                            "title" to editText.text.toString())
                                }
                                toast("データを追加しました")
                                finish()
                        })
                        .setNegativeButton("キャンセル", null)
                        .show()
                return true
            }
            R.id.action_delete -> {
                toast("データを削除しました")
                finish()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}