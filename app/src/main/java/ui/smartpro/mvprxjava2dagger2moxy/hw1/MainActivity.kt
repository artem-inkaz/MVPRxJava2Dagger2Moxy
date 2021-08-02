package ui.smartpro.mvprxjava2dagger2moxy

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ui.smartpro.mvprxjava2dagger2moxy.databinding.ActivityCountersBinding
import ui.smartpro.mvprxjava2dagger2moxy.R.layout.activity_counters
import ui.smartpro.mvprxjava2dagger2moxy.hw1.click
import ui.smartpro.mvprxjava2dagger2moxy.hw2.CountersPresenter2

//добавляем Moxy
class MainActivity : MvpAppCompatActivity(activity_counters), CountersView {

    private var viewBinding: ActivityCountersBinding? = null

//  private val presenter = MainPresenter(model = CountersModel())
    private val presenter by moxyPresenter { CountersPresenter2(model = CountersModel2()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // без viewbinding
//        setContentView(R.layout.activity_main)
//        btn_counter1.setOnClickListener {presenter.counterClick1()}
//        btn_counter2.setOnClickListener {presenter.counterClick2()}
//        btn_counter3.setOnClickListener {presenter.counterClick3()}

        // стандартный вариант с viewBinding
//        viewBinding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(viewBinding?.root)
//        viewBinding?.btnCounter1?.setOnClickListener { presenter.counterClick1() }
//        viewBinding?.btnCounter2?.setOnClickListener { presenter.counterClick2() }
//        viewBinding?.btnCounter3?.setOnClickListener { presenter.counterClick3() }

        // продвинутый вариант с viewBinding
        viewBinding =
            ActivityCountersBinding
                .inflate(layoutInflater)
                .also { viewBinding -> setContentView(viewBinding.root) }
                .apply {
                    // click через PopularLibrariesExtensions
                    btnCounter1.click(presenter::incrementCounter1)
                    btnCounter2.click(presenter::incrementCounter2)
                    btnCounter3.click(presenter::incrementCounter3)
                }
    }

    override fun showOnBoarding() {
        AlertDialog
            .Builder(this)
            .setMessage(R.string.onboarding_message)
            .create()
            .show()
    }

    // не нужен т.к. есть viewState от Moxy
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putString("BTN_1", btn_counter1.text as String?)
//        outState.putString("BTN_2", btn_counter2.text as String?)
//        outState.putString("BTN_3", btn_counter3.text as String?)
//    }

    // не нужен т.к. есть viewState от Moxy
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//
//        if (savedInstanceState != null) {
//            btn_counter1.text = savedInstanceState.getString("BTN_1", "");
//            btn_counter2.text = savedInstanceState.getString("BTN_2", "");
//            btn_counter3.text = savedInstanceState.getString("BTN_3", "");
//        }
//    }

    override fun setButtonText1(counter: String) {
        viewBinding?.btnCounter1?.text = counter
    }

    override fun setButtonText2(counter: String) {
        viewBinding?.btnCounter2?.text = counter
    }

    override fun setButtonText3(counter: String) {
        viewBinding?.btnCounter3?.text = counter
    }

    override fun showCounterMessage() {
        Toast.makeText(this, R.string.counter_message, Toast.LENGTH_SHORT)
            .show()
    }
}
