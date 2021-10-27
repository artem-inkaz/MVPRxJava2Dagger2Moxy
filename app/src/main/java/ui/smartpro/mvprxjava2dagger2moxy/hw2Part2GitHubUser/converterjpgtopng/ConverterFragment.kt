package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.converterjpgtopng

import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ui.smartpro.mvprxjava2dagger2moxy.R.layout.view_converter
import ui.smartpro.mvprxjava2dagger2moxy.R
import ui.smartpro.mvprxjava2dagger2moxy.databinding.ViewConverterBinding
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.converterjpgtopng.ext.click
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUserscheduler.scheduler.SchedulersFactory

class ConverterFragment : MvpAppCompatFragment(view_converter), ConverterView {

    companion object {

        fun newInstance(): Fragment = ConverterFragment()

    }

    private val presenter by moxyPresenter {
        ConverterPresenter(
            converter = ConverterFactory.create(requireContext()),
            schedulers = SchedulersFactory.create()
        )
    }

    private val viewBinding: ViewConverterBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().title = getString(R.string.converter_title)
        viewBinding.button.click(::pickImage)
    }

    private fun pickImage() {
        val getIntent = Intent(ACTION_GET_CONTENT)
        getIntent.type = "image/*"

        startActivityForResult(getIntent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        data?.data?.let(presenter::convert)
            ?: Toast.makeText(requireContext(), "Изображение не выбрано", Toast.LENGTH_SHORT).show()
    }

    override fun showOnBoarding() {
        viewBinding.progress.visibility = View.VISIBLE

        viewBinding.button.click(presenter::cancel)
        viewBinding.button.text = getString(R.string.cancel)
    }

    override fun onPositiveClick() {
        TODO("Not yet implemented")
    }

    override fun showContent(uri: Uri?) {
        val bitmap: Bitmap? = uri?.let { MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri) }

        viewBinding.progress.visibility = View.GONE
        viewBinding.imageView.setImageBitmap(bitmap)

        viewBinding.button.click(::pickImage)
        viewBinding.button.text = getString(R.string.select_image)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
    }
}