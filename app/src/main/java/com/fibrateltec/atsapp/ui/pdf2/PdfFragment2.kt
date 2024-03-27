package com.fibrateltec.atsapp.ui.pdf2

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fibrateltec.atsapp.MainActivity
import com.fibrateltec.atsapp.R
import com.fibrateltec.atsapp.databinding.FragmentPdf2Binding
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfReader
import com.itextpdf.kernel.pdf.PdfWriter
import java.io.File
import java.io.FileOutputStream

class PdfFragment2 : Fragment() {

    private var _binding: FragmentPdf2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val PdfViewModel =
            ViewModelProvider(this,).get(PdfViewModel2::class.java)

        _binding = FragmentPdf2Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textPdf2
        PdfViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    class EleventhActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_pdf2)
            if (checkPermission()) {
                Toast.makeText(this, "Permiso Aceptado", Toast.LENGTH_LONG).show()
            } else {
                requestPermissions()
            }

            val btnDescarga: Button = findViewById(R.id.btnDescarga2)
            btnDescarga.setOnClickListener {
                exportToPDF()
            }
            val btnvolver2: Button = findViewById(R.id.btnvolver2)
            btnvolver2.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            val linkTextView: TextView = findViewById(R.id.linkTextView2)
            linkTextView.setOnClickListener {
                val url =
                    "https://drive.google.com/drive/folders/1G8AQEhAG4MKW4vaV_QhP8r2nBDJKjJzu" // Reemplaza con tu URL
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)

            }

        }

        private fun checkPermission(): Boolean {
            val permission1 = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            val permission2 = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED
        }

        private fun requestPermissions() {
            requestPermissions(
                arrayOf(
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
                        Toast.makeText(this, "Permisos concedidos", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(this, "Permisos Denegados", Toast.LENGTH_LONG).show()
                        // En lugar de finalizar la actividad, puedes mostrar un mensaje o realizar otra acción adecuada.
                    }
                }
            }
        }

        private fun exportToPDF() {
            val path = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                "FormularioAlturas.pdf"
            ).absolutePath
            val file = File(path)

            try {
                val fileOutputStream = FileOutputStream(file)
                val writer = PdfWriter(fileOutputStream)
                val document = PdfDocument(writer)
                document.addNewPage()

                // Agrega el contenido de los tres PDFs al documento
                val pdfFiles =
                    listOf("Alturas.pdf", "Peligros_potenciales.pdf", "Tareas_criticas.pdf")
                pdfFiles.forEach { fileName ->
                    val pdfFile = File(
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                        fileName
                    )
                    val pdfReader = PdfReader(pdfFile.absolutePath)
                    val pdfDocument = PdfDocument(pdfReader)
                    pdfDocument.copyPagesTo(1, pdfDocument.numberOfPages, document)
                    pdfReader.close()
                }

                document.close()
                writer.close()

                Toast.makeText(this, "PDF creado exitosamente en $path", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Error al crear el PDF: ${e.message}", Toast.LENGTH_LONG)
                    .show()
            }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
