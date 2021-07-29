package ui.smartpro.mvprxjava2dagger2moxy

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), MainView {

  private val presenter = MainPresenter(this,model = CountersModel())

    private val btn_counter1: Button by lazy { findViewById(R.id.btn_counter1) }
    private val btn_counter2: Button by lazy { findViewById(R.id.btn_counter2) }
    private val btn_counter3: Button by lazy { findViewById(R.id.btn_counter3) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_counter1.setOnClickListener {
            presenter.counterClick1()
        }
        btn_counter2.setOnClickListener {
            presenter.counterClick2()
        }
        btn_counter3.setOnClickListener {
            presenter.counterClick3()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("BTN_1", btn_counter1.text as String?)
        outState.putString("BTN_2", btn_counter2.text as String?)
        outState.putString("BTN_3", btn_counter3.text as String?)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        if (savedInstanceState != null) {
            btn_counter1.text = savedInstanceState.getString("BTN_1", "");
            btn_counter2.text = savedInstanceState.getString("BTN_2", "");
            btn_counter3.text = savedInstanceState.getString("BTN_3", "");
        }
    }

    override fun setButtonText1(text: String) {
           btn_counter1.text = text
    }

    override fun setButtonText2(text: String) {
        btn_counter2.text = text
    }

    override fun setButtonText3(text: String) {
        btn_counter3.text = text
    }
}
