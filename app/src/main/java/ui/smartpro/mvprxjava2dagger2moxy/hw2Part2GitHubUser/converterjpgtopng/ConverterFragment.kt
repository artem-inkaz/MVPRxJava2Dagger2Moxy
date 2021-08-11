package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.converterjpgtopng

import android.Manifest
import android.annotation.SuppressLint
import android.content.*
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.text.format.DateFormat
import android.util.Log
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ui.smartpro.mvprxjava2dagger2moxy.BuildConfig
import ui.smartpro.mvprxjava2dagger2moxy.R
import ui.smartpro.mvprxjava2dagger2moxy.R.layout.activity_converter
import ui.smartpro.mvprxjava2dagger2moxy.databinding.ActivityConverterBinding
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.converterjpgtopng.ext.arguments
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.converterjpgtopng.ext.click
import java.io.*
import java.util.*
import java.util.concurrent.TimeUnit

class ConverterFragment : MvpAppCompatFragment(activity_converter), ConverterView {

    companion object {

        fun newInstance(): Fragment =
            ConverterFragment()
                .arguments()

     private var PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        const val KEY_PATH_IMAGE_PICKED = "pathImagePicked"
        const val KEY_PATH_IMAGE_CONVERTED = "pathImageConverted"
        const val KEY_IS_CONVERTING = "isConverting"
    }

    private var pathImagePicked: String? = null
    lateinit var currentPhotoPath: String
    private var pathImageConverted: String? = null
    private var isConverting: Boolean = false

    private var profileImageUri: Uri? = null

    var compressedBitmap: Bitmap? = null
    var realFile: String? = null

    private var converterDisposable =  CompositeDisposable()

    private val presenter: ConverterPresenter by moxyPresenter {
        ConverterPresenter()
    }

    private val viewBinding: ActivityConverterBinding by viewBinding()

    //Для проверки пермишен
    val permReqLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val granted = permissions.entries.all {
                it.value == true
            }
            if (granted) {

            }
        }

    // для загрузки камеры
    var registerTakePicture = registerForActivityResult(ActivityResultContracts.TakePicture())
    { success ->
        if (success) {

            profileImageUri?.let {
                mock(it)
                viewBinding.imagePicked.background = null
                viewBinding.textPathImagePicked.text = currentPhotoPath
            }
        }
        else Toast.makeText(requireContext(), "Данных нет", Toast.LENGTH_LONG).show()
    }

    // Gallery
    @SuppressLint("SetTextI18n")
    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { data: Uri? ->
        val imagePickedUri: Uri? = data
        if (imagePickedUri != null) {
            profileImageUri = imagePickedUri

            // Create the File where the photo should go
            val photoFile: File? = try {
                createImageFile()
            } catch (ex: IOException) {
                // Error occurred while creating the File
                null
            }
            viewBinding.imagePicked.background = null
            viewBinding.imagePicked.setImageURI(imagePickedUri)
            viewBinding.textPathImagePicked.text = currentPhotoPath

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.imagePicked.click {
            showAlertDialog(requireContext())
        }
        viewBinding.buttonConvert.click {
            if (profileImageUri == null) return@click
            takePhoto()
        }

        /**
         * восстанавливаем состояние конвертации, пути при переворотоах экрана
         */
        savedInstanceState?.let {
            pathImagePicked = it.getString(KEY_PATH_IMAGE_PICKED)
            pathImagePicked?.let { path ->
//                viewBinding.imagePicked.setImageURI(Uri.parse(path))
                viewBinding.imagePicked.background = null
                viewBinding.textPathImagePicked.text = path

                isConverting = it.getBoolean(KEY_IS_CONVERTING, false)
                if (isConverting) {
                    convertProcess()
                }
            }

            pathImageConverted = it.getString(KEY_PATH_IMAGE_CONVERTED)
            pathImageConverted?.let { path ->
//                viewBinding.imageConverted.setImageURI(Uri.parse(path))
                viewBinding.imageConverted.background = null
                viewBinding.textPathImageConverted.text = path
            }
        }
    }

    /**
     * сохраняем состояние конвертации, пути
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        with(outState) {
            putString(KEY_PATH_IMAGE_PICKED, pathImagePicked)
            putString(KEY_PATH_IMAGE_CONVERTED, pathImageConverted)
            putBoolean(KEY_IS_CONVERTING, isConverting)
        }
    }

    // Диалоговое окно по нажатию на картинку которую нужно выбрать и конвертировать
    private fun showAlertDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Выберите какой файл хотите загрузить")
            .setItems(
                R.array.attachment_type,
                DialogInterface.OnClickListener { dialog, which ->
                    when (which) {
                        0 ->
                            //Gallery
                            photoGalleryIntent()
                        1 ->
                            //Camera
                            photoCameraIntent()
                    }
                })
        val resultFL = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        if (resultFL == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                100
            )
        } else {
            builder.create().show()
        }
    }

    /**
     * для галлереи запуск окна галлереи
     */
    private fun photoGalleryIntent() {
        val intent = Intent(context, ConverterActivity::class.java)
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        getContent.launch("image/*")
    }

    /**
     * для показа камеры
     */
    @SuppressLint("QueryPermissionsNeeded")
    private fun photoCameraIntent() {
        // Create the File where the photo should go
        val photoFile: File? = try {
            createImageFile()
        } catch (ex: IOException) {
            // Error occurred while creating the File
            null
        }
        // Continue only if the File was successfully created
        photoFile?.also {
            val photoURI: Uri = FileProvider.getUriForFile(
                requireContext(),
                BuildConfig.APPLICATION_ID + ".provider",
                it
            )

            viewBinding.textPathImagePicked.text = File(photoFile.path).toString()
            profileImageUri = photoURI
            registerTakePicture.launch(photoURI)
        }
    }

    // Создание временного файла
    @Throws(IOException::class)
    private fun createImageFile(): File? {
        var fOut: OutputStream? = null
        val timeStamp: String = DateFormat.format("yyyy-MM-dd_hhmmss", Date()).toString()
        val storageDir: File? =
            requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return try {
            val file = File(storageDir, "$timeStamp.jpg")
            if (file.createNewFile() || file.exists()) {
                file.apply {
                    currentPhotoPath = absolutePath
                    fOut = FileOutputStream(this)}

            } else {
                null
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }

    }

    // Permission
    private fun hasPermissions(context: Context, permissions: Array<String>): Boolean =
        permissions.all {
            ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }

    // Нажатие кнопки Convert
    private fun takePhoto() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            convertProcess()
            showAlertMessage()
        }
        activity?.let {
            if (hasPermissions(it as Context, PERMISSIONS)) {
                convertProcess()
                showAlertMessage()
            } else {
                permReqLauncher.launch(
                    PERMISSIONS
                )
            }
        }
    }

    // Функции getFileExtension и getCursorContent чтобы взять имя файла "IMG_111111ппапа.jpg"
    /* get actual file name or extension */
    private fun getFileExtension(uri: Uri): String? = when (uri.scheme) {
        // get file extension
        ContentResolver.SCHEME_FILE -> File(uri.path!!).extension
        // get actual name of file
        //ContentResolver.SCHEME_FILE -> File(uri.path!!).name
        ContentResolver.SCHEME_CONTENT -> getCursorContent(uri)
        else -> null
    }

    private fun getCursorContent(uri: Uri): String? = try {
        var res: String? = null
        context?.contentResolver?.query(uri, null, null, null, null)?.let { cursor ->
            cursor.run {
                val mimeTypeMap: MimeTypeMap = MimeTypeMap.getSingleton()
                if (moveToFirst()) mimeTypeMap.getExtensionFromMimeType(
                    context?.contentResolver?.getType(
                        uri
                    )
                )
                // case for get actual name of file
                if (moveToFirst()) getString(getColumnIndex(OpenableColumns.DISPLAY_NAME))
                else null
            }.also { cursor.close() }
        }
    } catch (e: Exception) {
        null
    }

    // Extension function Converter JPG to PNG
    fun Bitmap.compress(
        format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
        quality: Int = 100
    ): Bitmap {
        // Initialize a new ByteArrayStream
        val stream = ByteArrayOutputStream()

        /*
            **** reference source developer.android.com ***

            public boolean compress (Bitmap.CompressFormat format, int quality, OutputStream stream)
                Write a compressed version of the bitmap to the specified outputstream.
                If this returns true, the bitmap can be reconstructed by passing a
                corresponding inputstream to BitmapFactory.decodeStream().

                Note: not all Formats support all bitmap configs directly, so it is possible
                that the returned bitmap from BitmapFactory could be in a different bitdepth,
                and/or may have lost per-pixel alpha (e.g. JPEG only supports opaque pixels).

                Parameters
                format : The format of the compressed image
                quality : Hint to the compressor, 0-100. 0 meaning compress for small size,
                    100 meaning compress for max quality. Some formats,
                    like PNG which is lossless, will ignore the quality setting
                stream: The outputstream to write the compressed data.

                Returns
                    true if successfully compressed to the specified stream.


            Bitmap.CompressFormat
                Specifies the known formats a bitmap can be compressed into.

                    Bitmap.CompressFormat  JPEG
                    Bitmap.CompressFormat  PNG
                    Bitmap.CompressFormat  WEBP
        */

        // Compress the bitmap with JPEG format and quality 50%
        this.compress(
            format,
            quality,
            stream
        )

        val byteArray = stream.toByteArray()

        // Finally, return the compressed bitmap
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }


    private fun convertProcess() {

        val disposableGetUserID = Completable.fromAction {

            // Получаем из пути URI название файла: "IMG_13dvfdvdf.jpg"
           realFile= getFileExtension(profileImageUri!!)

            // Процесс конвертации из JPG в PNG
           compressedBitmap =
                (viewBinding.imagePicked.drawable as BitmapDrawable).bitmap.compress(
                    Bitmap.CompressFormat.PNG
                )
        }
            .delay(3, TimeUnit.SECONDS)
            .cache()
 //            .filter{/* do something */ }
//            .flatMap{/* do something */ }
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .doOnComplete { /* do something */  }
           .doOnDispose { /* do something */ }
           .doOnError { /* do something */ }
           .subscribe(
               {
                   Log.d("ConverterJPGtoPNG", "Успешно")
                   Toast.makeText(
                       requireContext(),
                       "converted to png.",
                       Toast.LENGTH_LONG
                   )
                       .show()

                viewBinding.imageConverted.background = null
                viewBinding.imageConverted.setImageBitmap(compressedBitmap)
                viewBinding.textPathImageConverted.text = "$realFile/$compressedBitmap"
               },
               {
                   Log.e("ConverterJPGtoPNG", "Ошибка")
                   Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
               }
           )
        converterDisposable.add(disposableGetUserID)

    }

    // Glide для загрузки
    private fun mock(url: Uri) {
        Glide.with(requireContext())
            .load(url)
            .circleCrop()
            .into(viewBinding.imagePicked)
    }
    // Glide для загрузки
    private fun mock(url: File) {
        Glide.with(requireContext())
            .load(url)
            .circleCrop()
            .into(viewBinding.imagePicked)
    }

    // Диалоговое окно при конвертации
    private fun showAlertMessage() {
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.conversion_message)
            .setPositiveButton("Stop") { dialog, id ->
                converterDisposable.dispose()
            }
            .setNegativeButton("Закрыть окно") { dialog, id ->
                dialog.dismiss()
            }
            .create()
            .show()

    }

    override fun onDestroy() {
        super.onDestroy()
        converterDisposable.dispose()
    }

    override fun showOnBoarding() {
        TODO("Not yet implemented")
    }

    override fun onPositiveClick() {
        presenter.showDialog()
    }
}