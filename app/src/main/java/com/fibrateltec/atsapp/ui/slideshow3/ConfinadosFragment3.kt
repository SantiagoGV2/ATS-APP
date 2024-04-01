package com.fibrateltec.atsapp.ui.slideshow3

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
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fibrateltec.atsapp.R
import com.fibrateltec.atsapp.databinding.FragmentConfinados3Binding
import com.fibrateltec.atsapp.ui.Signature.SignaturePad
import com.fibrateltec.atsapp.ui.pdf2.PdfFragment2
import com.fibrateltec.atsapp.ui.pdf3.PdfFragment3
import com.itextpdf.text.Document
import com.itextpdf.text.Image
import com.itextpdf.text.pdf.PdfWriter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class ConfinadosFragment3 : Fragment() {
    private var _binding: FragmentConfinados3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val galleryViewModel2 = ViewModelProvider(this).get(ConfinadosViewModel3::class.java)

        _binding = FragmentConfinados3Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textFragmentConfinados3
        galleryViewModel2.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }
    class FifthActivity : AppCompatActivity() {
        private var btnNextVisibility = View.VISIBLE
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_confinados3)

            if(checkPermission()){
                Toast.makeText(this,"Permiso Aceptado",Toast.LENGTH_LONG).show()
            }else{
                requestPermissions()
            }

            val clearButton = findViewById<Button>(R.id.clear2)
            clearButton.setOnClickListener {
                val signaturePad = findViewById<SignaturePad>(R.id.signature_pad)
                signaturePad.clearSignature()
            }

            val btnxml: Button = findViewById(R.id.btnxml7)
            btnxml.setOnClickListener {
                exportToPDF()
            }
            val nxtboton2: Button = findViewById(R.id.button3)
            btnNextVisibility = nxtboton2.visibility
            nxtboton2.setOnClickListener {
                val intent = Intent(this, PdfFragment3.TwelfthActivity::class.java)
                startActivity(intent)

            }

        }
        private fun checkPermission(): Boolean {
            val permission1 = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            val permission2 = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.READ_EXTERNAL_STORAGE)
            return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED
        }
        private fun requestPermissions(){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE),
                200
            )
        }

        override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if (requestCode == 200){
                if (grantResults.size >0){
                    val writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED
                    val readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED
                    if (writeStorage && readStorage){
                        Toast.makeText(this, "Permisos concedidos",Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this, "Permisos Denegados",Toast.LENGTH_LONG).show()
                        finish()
                    }
                }
            }


        }
        private fun exportToPDF() {
            val document = Document()
            val path = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Tareas_criticas3.pdf").absolutePath
            val file = File(path)
            try {
                val fileOutputStream = FileOutputStream(file)

                PdfWriter.getInstance(document, fileOutputStream)

                document.open()

                // Agrega el primer contenido al documento PDF
                val constraint: ConstraintLayout = findViewById(R.id.constraint6)
                addViewToPDF(document, constraint)

                document.close() // Cerrar el documento aquí después de agregar
                findViewById<Button>(R.id.button3).visibility = btnNextVisibility
                Toast.makeText(this, "Guardado exitosamente en $path", Toast.LENGTH_LONG).show()

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Error al guardar: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }



        private fun addViewToPDF(document: Document, view: View) {
            view.measure(
                View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(view.height, View.MeasureSpec.EXACTLY)
            )
            view.layout(0, 0, view.measuredWidth, view.measuredHeight)


            val btnClear = view.findViewById<Button>(R.id.clear2)
            val btnCreatePDF = view.findViewById<Button>(R.id.btnxml7)

            if (btnClear != null && btnCreatePDF != null) {
                btnClear.visibility = View.GONE
                btnCreatePDF.visibility = View.GONE
            }
            findViewById<Button>(R.id.button3).visibility = View.GONE

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