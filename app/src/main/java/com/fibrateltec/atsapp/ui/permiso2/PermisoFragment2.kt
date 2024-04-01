package com.fibrateltec.atsapp.ui.permiso2

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
import com.fibrateltec.atsapp.databinding.FragmentPermiso2Binding
import com.fibrateltec.atsapp.ui.permiso3.PermisoFragment3
import com.itextpdf.text.Document
import com.itextpdf.text.Image
import com.itextpdf.text.pdf.PdfWriter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class PermisoFragment2 : Fragment() {

    private var _binding: FragmentPermiso2Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val permisoViewModel =
            ViewModelProvider(this).get(PermisoViewModel2::class.java)

        _binding = FragmentPermiso2Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.Permiso2
        permisoViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }
    class FourteenthActivity : AppCompatActivity() {

        private var isCreatePDFButtonVisible = true
        private var btnNextVisibility = View.VISIBLE
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_permiso2)
            seleccion(this)
            seleccion2(this)
            if (checkPermission()) {
                Toast.makeText(this, "Permiso Aceptado", Toast.LENGTH_LONG).show()
            } else {
                requestPermissions()
            }
            val nxtboton2: Button = findViewById(R.id.button4)
            btnNextVisibility = nxtboton2.visibility
            nxtboton2.setOnClickListener {
                val intent = Intent(this, PermisoFragment3.FifteenthActivity::class.java)
                startActivity(intent)

            }

            val btnxml2: Button = findViewById(R.id.button)
            btnxml2.setOnClickListener {
                isCreatePDFButtonVisible = !isCreatePDFButtonVisible
                btnxml2.visibility = if (isCreatePDFButtonVisible) View.VISIBLE else View.GONE
                exportToPDF()
            }


        }
        fun seleccion2(context: Context) {
            val button: Button = findViewById(R.id.btnTareas)
            val selectedItems = mutableListOf<String>()
            button.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Factor y agente de riesgo")

                val primerItems = resources.getStringArray(R.array.tareas)

                val items = mutableListOf<String>().apply {
                    addAll(primerItems)
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
                    mostrarSelecciones2(selectedItems)
                }

                builder.show()
            }
        }
        fun seleccion(context: Context) {
            val button: Button = findViewById(R.id.btnProcedimiento)
            val selectedItems = mutableListOf<String>()
            button.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Factor y agente de riesgo")

                val primerItems = resources.getStringArray(R.array.procedimiento)

                val items = mutableListOf<String>().apply {
                    addAll(primerItems)
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

        private fun mostrarSelecciones2(selecciones: List<String>) {
            val textView: TextView =
                findViewById(R.id.text_tarea) // Reemplaza con tu ID de TextView
            textView.text = "Selecciones: ${selecciones.joinToString(", ")}"

        }
        private fun mostrarSelecciones(selecciones: List<String>) {
            val textView: TextView =
                findViewById(R.id.text_procedimiento) // Reemplaza con tu ID de TextView
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
            val path = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                "MedidasPrevencion.pdf"
            ).absolutePath
            val file = File(path)
            try {
                val fileOutputStream = FileOutputStream(file)

                PdfWriter.getInstance(document, fileOutputStream)

                document.open()

                // Agrega el primer contenido al documento PDF
                val constraint: ConstraintLayout = findViewById(R.id.constraint13)
                addViewToPDF(document, constraint)

                document.close()
                findViewById<Button>(R.id.button4).visibility = btnNextVisibility
                Toast.makeText(this, "Guardado exitosamente en $path", Toast.LENGTH_LONG).show()

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Error al guardar: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }


        private fun addViewToPDF(document: Document, view: View) {
            val btnCreatePDF = view.findViewById<Button>(R.id.button)
            btnCreatePDF.visibility = if (isCreatePDFButtonVisible) View.VISIBLE else View.GONE

            findViewById<Button>(R.id.button4).visibility = View.GONE

            // Calcula el margen del documento
            val margin = -90f

            // Calcula el tamaño de la página del documento
            val pageSize = document.pageSize
            val pageWidth = pageSize.width - margin * 2.2f
            val pageHeight = pageSize.height - margin * 1.86f//ACA

            // Convierte la vista a un bitmap
            val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            view.draw(canvas)

            // Convierte el bitmap a bytes para agregarlo al documento PDF
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val image = Image.getInstance(stream.toByteArray())

            // Ajusta el tamaño de la imagen al documento
            val aspectRatio = image.width / image.height
            val newWidth = pageWidth * 1.4f
            val newHeight = newWidth / aspectRatio

            // Si la imagen es más grande que la página, divide la imagen en varias partes
            if (newHeight > pageHeight) {
                val numHorizontalSections = Math.ceil((newHeight / pageHeight).toDouble()).toInt()
                val sectionHeight = pageHeight
                val sectionWidth = pageWidth * 0.65f

                for (i in 0 until numHorizontalSections) {
                    val startY = i * sectionHeight
                    val endY = startY + sectionHeight

                    // Crea una nueva imagen para la sección actual
                    val sectionBitmap =
                        Bitmap.createBitmap(
                            view.width,
                            sectionHeight.toInt(),
                            Bitmap.Config.ARGB_8888
                        )
                    val sectionCanvas = Canvas(sectionBitmap)
                    sectionCanvas.drawBitmap(bitmap, 0f, -startY, null)

                    // Convierte el bitmap a bytes para agregarlo al documento PDF
                    val sectionStream = ByteArrayOutputStream()
                    sectionBitmap.compress(Bitmap.CompressFormat.PNG, 100, sectionStream)
                    val sectionImage = Image.getInstance(sectionStream.toByteArray())

                    // Ajusta el tamaño de la imagen al documento
                    sectionImage.scaleToFit(sectionWidth, sectionHeight)

                    // Agrega la imagen al documento
                    document.newPage()
                    document.add(sectionImage)
                }
            } else {
                // Ajusta el tamaño de la imagen al documento
                image.scaleToFit(newWidth, newHeight)

                // Agrega la imagen al documento
                document.add(image)

                // Agrega los checkboxes seleccionados al documento

            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

