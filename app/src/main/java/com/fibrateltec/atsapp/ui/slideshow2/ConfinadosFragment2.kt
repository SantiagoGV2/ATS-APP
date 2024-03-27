package com.fibrateltec.atsapp.ui.slideshow2

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fibrateltec.atsapp.R
import com.fibrateltec.atsapp.databinding.FragmentConfinados2Binding
import com.fibrateltec.atsapp.ui.gallery3.GalleryFragment3
import com.fibrateltec.atsapp.ui.slideshow3.ConfinadosFragment3
import com.itextpdf.text.Document
import com.itextpdf.text.Image
import com.itextpdf.text.pdf.PdfWriter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class ConfinadosFragment2 : Fragment() {

    private var _binding: FragmentConfinados2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val galleryViewModel2 = ViewModelProvider(this).get(ConfinadosViewModel::class.java)

        _binding = FragmentConfinados2Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textFragmentConfinados
        galleryViewModel2.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }
    class FourActivity : AppCompatActivity() {

        private var isCreatePDFButtonVisible = true
        private var btnNextVisibility = View.VISIBLE
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_confinados2)
            seleccion(this)
            seleccion5(this)
            seleccion6(this)

            if (checkPermission()) {
                Toast.makeText(this, "Permiso Aceptado", Toast.LENGTH_LONG).show()
            } else {
                requestPermissions()
            }

            val nxtboton5: Button = findViewById(R.id.nextBtn6)
            btnNextVisibility = nxtboton5.visibility
            nxtboton5.setOnClickListener {
                val intent = Intent(this, ConfinadosFragment3.FifthActivity::class.java)
                startActivity(intent)

            }

            val btnxml5: Button = findViewById(R.id.btnxml6)
            btnxml5.setOnClickListener {
                isCreatePDFButtonVisible = !isCreatePDFButtonVisible
                btnxml5.visibility = if (isCreatePDFButtonVisible) View.VISIBLE else View.GONE
                exportToPDF()
            }


        }

        fun seleccion(context: Context) {
            val button: Button = findViewById(R.id.btnbiologico)
            val selectedItems = mutableListOf<String>()
            button.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Factor y agente de riesgo")

                val primerItems = resources.getStringArray(R.array.Biologico)
                val segundoItems = resources.getStringArray(R.array.Biologicos2)

                val items = mutableListOf<String>().apply {
                    addAll(primerItems)
                    add("**Medidas de control**")
                    addAll(segundoItems)
                }.toTypedArray()

                val checkedItems = BooleanArray(items.size)

                builder.setMultiChoiceItems(items, checkedItems) { _, i, b ->
                    if (i != primerItems.size && i != items.size - 0) {
                        // Ignora el texto separador y las acciones asociadas

                        if (b) {
                            // Si el elemento está marcado, agrégalo a la lista de selecciones
                            selectedItems.add(items[i])
                        } else {
                            // Si el elemento está desmarcado, remuévelo de la lista de selecciones
                            selectedItems.remove(items[i])
                        }
                    }
                }

                builder.setPositiveButton("Aceptar") { _, _ ->
                    // Acciones a realizar al hacer clic en Aceptar
                    mostrarSelecciones(selectedItems)
                }

                builder.show()
            }
        }


        fun seleccion5(context: Context) {
            val selectedItems = mutableListOf<String>()
            val button5: Button = findViewById(R.id.btnbiomecanicos)
            button5.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Factor y agente de riesgo")

                val primerItems = resources.getStringArray(R.array.biomecanicos3)
                val segundoItems = resources.getStringArray(R.array.biomecanicos4)

                val items = mutableListOf<String>().apply {
                    addAll(primerItems)
                    add("**Medidas de control**")
                    addAll(segundoItems)
                }.toTypedArray()

                val checkedItems = BooleanArray(items.size)

                builder.setMultiChoiceItems(items, checkedItems) { _, i, b ->
                    if (i != primerItems.size && i != items.size - 0) {
                        // Ignora el texto separador y las acciones asociadas

                        if (b) {
                            // Si el elemento está marcado, agrégalo a la lista de selecciones
                            selectedItems.add(items[i])
                        } else {
                            // Si el elemento está desmarcado, remuévelo de la lista de selecciones
                            selectedItems.remove(items[i])
                        }
                    }
                }

                builder.setPositiveButton("Aceptar") { _, _ ->
                    // Acciones a realizar al hacer clic en Aceptar
                    mostrarSelecciones4(selectedItems)
                }

                builder.show()
            }
        }
        fun seleccion6(context: Context) {
            val selectedItems = mutableListOf<String>()

            val button5: Button = findViewById(R.id.btnelectrico)
            button5.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Factor y agente de riesgo")

                val primerItems = resources.getStringArray(R.array.electrico4)
                val segundoItems = resources.getStringArray(R.array.electrico5)

                val items = mutableListOf<String>().apply {
                    addAll(primerItems)
                    add("**Medidas de control**")
                    addAll(segundoItems)
                }.toTypedArray()

                val checkedItems = BooleanArray(items.size)

                builder.setMultiChoiceItems(items, checkedItems) { _, i, b ->
                    if (i != primerItems.size && i != items.size - 0) {
                        // Ignora el texto separador y las acciones asociadas

                        if (b) {
                            // Si el elemento está marcado, agrégalo a la lista de selecciones
                            selectedItems.add(items[i])
                        } else {
                            // Si el elemento está desmarcado, remuévelo de la lista de selecciones
                            selectedItems.remove(items[i])
                        }
                    }
                }

                builder.setPositiveButton("Aceptar") { _, _ ->
                    // Acciones a realizar al hacer clic en Aceptar
                    mostrarSelecciones5(selectedItems)
                }

                builder.show()
            }
        }


        private fun mostrarSelecciones(selecciones: List<String>) {
            val textView: TextView =
                findViewById(R.id.textView23) // Reemplaza con tu ID de TextView
            textView.text = "Selecciones: ${selecciones.joinToString(", ")}"

        }



        private fun mostrarSelecciones4(selecciones: List<String>) {
            val textView: TextView =
                findViewById(R.id.textView27) // Reemplaza con tu ID de TextView
            textView.text = "Selecciones: ${selecciones.joinToString(", ")}"

        }
        private fun mostrarSelecciones5(selecciones: List<String>) {
            val textView: TextView =
                findViewById(R.id.textView28) // Reemplaza con tu ID de TextView
            textView.text = "Selecciones: ${selecciones.joinToString(", ")}"

        }

        private fun checkPermission(): Boolean {
            val permission1 = ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            val permission2 = ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED
        }

        private fun requestPermissions() {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                200
            )
        }

        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if (requestCode == 200) {
                if (grantResults.size > 0) {
                    val writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED
                    val readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED
                    if (writeStorage && readStorage) {
                        Toast.makeText(this, "Permisos concedidos", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Permisos Denegados", Toast.LENGTH_LONG).show()
                        finish()
                    }
                }
            }


        }

        private fun exportToPDF() {
            val document = Document()
            val path = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Peligros_potenciales3.pdf").absolutePath
            val file = File(path)
            try {
                val fileOutputStream = FileOutputStream(file)

                PdfWriter.getInstance(document, fileOutputStream)

                document.open()

                // Agrega el primer contenido al documento PDF
                val constraint: ConstraintLayout = findViewById(R.id.constraint5)
                addViewToPDF(document, constraint)

                Toast.makeText(this, "Guardado exitosamente en $path", Toast.LENGTH_LONG).show()

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Error al guardar: ${e.message}", Toast.LENGTH_LONG)
                    .show()
            }
            document.close()
            findViewById<Button>(R.id.nextBtn6).visibility = btnNextVisibility

        }


        private fun addViewToPDF(document: Document, view: View) {
            view.measure(
                View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(view.height, View.MeasureSpec.EXACTLY)
            )
            view.layout(0, 0, view.measuredWidth, view.measuredHeight)

            val btnCreatePDF = view.findViewById<Button>(R.id.btnxml6)

            btnCreatePDF.visibility = if (isCreatePDFButtonVisible) View.VISIBLE else View.GONE

            findViewById<Button>(R.id.nextBtn6).visibility = View.GONE

            // Convierte la vista a un bitmap
            val fixedWidth = 1030 // T
            val fixedHeight = 1980
            val bitmap1 = Bitmap.createBitmap(fixedWidth, fixedHeight, Bitmap.Config.ARGB_8888)
            val canvas1 = Canvas(bitmap1)
            view.draw(canvas1)

            // Convierte el bitmap a bytes para agregarlo al documento PDF
            val stream = ByteArrayOutputStream()
            bitmap1.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val image = Image.getInstance(stream.toByteArray())


            // Ajusta el tamaño de la imagen al documento
            val documentWidth =
                document.pageSize.width - document.leftMargin() - document.rightMargin()
            val documentHeight =
                document.pageSize.height - document.topMargin() - document.bottomMargin()
            image.scaleToFit(documentWidth, documentHeight)

            val aspectRatio = image.width / image.height
            val newWidth = documentWidth * 0.8f
            val newHeight = newWidth / aspectRatio

            image.scaleToFit(newWidth, newHeight)
            // Agrega la imagen al documento
            document.add(image)

            // Agrega los checkboxes seleccionados al documento


        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}