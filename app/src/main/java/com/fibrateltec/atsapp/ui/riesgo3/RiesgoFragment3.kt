package com.fibrateltec.atsapp.ui.riesgo3

import android.Manifest
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
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fibrateltec.atsapp.R
import com.fibrateltec.atsapp.databinding.FragmentRiesgo3Binding
import com.fibrateltec.atsapp.ui.Signature.SignaturePad
import com.fibrateltec.atsapp.ui.riesgo4.RiesgoFragment4
import com.itextpdf.text.Document
import com.itextpdf.text.Image
import com.itextpdf.text.pdf.PdfWriter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
class RiesgoFragment3 : Fragment() {
    private var _binding: FragmentRiesgo3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val galleryViewModel2 = ViewModelProvider(this).get(RiesgoViewModel3::class.java)

        _binding = FragmentRiesgo3Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textFragmentRiesgo3
        galleryViewModel2.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    class SeventhActivity : AppCompatActivity() {

        private var isCreatePDFButtonVisible = true
        private var btnNextVisibility = View.VISIBLE
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_riesgo3)


            if (checkPermission()) {
                Toast.makeText(this, "Permiso Aceptado", Toast.LENGTH_LONG).show()
            } else {
                requestPermissions()
            }

            val btnxml: Button = findViewById(R.id.btnxml6)
            btnxml.setOnClickListener {
                isCreatePDFButtonVisible = !isCreatePDFButtonVisible
                btnxml.visibility = if (isCreatePDFButtonVisible) View.VISIBLE else View.GONE
                exportToPDF()
            }
            val nxtboton9: Button = findViewById(R.id.nextBtn6)
            btnNextVisibility = nxtboton9.visibility
            nxtboton9.setOnClickListener {
                val intent = Intent(this, RiesgoFragment4.EighthActivity::class.java)
                startActivity(intent)

            }

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
                "Tareas_criticas2.pdf"
            ).absolutePath
            val file = File(path)
            try {
                val fileOutputStream = FileOutputStream(file)

                PdfWriter.getInstance(document, fileOutputStream)

                document.open()

                // Agrega el primer contenido al documento PDF
                val constraint: ConstraintLayout = findViewById(R.id.constraint10)
                addViewToPDF(document, constraint)

                document.close() // Cerrar el documento aquí después de agregar todo el contenido
                val nextBtn = findViewById<Button>(R.id.nextBtn6)
                nextBtn.visibility = btnNextVisibility

                Toast.makeText(this, "Guardado exitosamente en $path", Toast.LENGTH_LONG).show()

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Error al guardar: ${e.message}", Toast.LENGTH_LONG)
                    .show()
            }

        }


        private fun addViewToPDF(document: Document, view: View) {
            val btnCreatePDF = view.findViewById<Button>(R.id.btnxml6)

            btnCreatePDF.visibility = if (isCreatePDFButtonVisible) View.VISIBLE else View.GONE

            findViewById<Button>(R.id.nextBtn6).visibility = View.GONE

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
