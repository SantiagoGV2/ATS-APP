package com.fibrateltec.atsapp.ui.gallery3

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.os.Environment
import android.view.View
import com.itextpdf.text.Document
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.fibrateltec.atsapp.R
import com.fibrateltec.atsapp.ui.Signature.SignaturePad
import com.itextpdf.text.pdf.PdfWriter
import java.io.ByteArrayOutputStream
import com.itextpdf.text.Image
import java.io.File
import java.io.FileOutputStream




class GalleryFragment3 {
    class ThirdActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_gallery3)

            val clearButton = findViewById<Button>(R.id.clear)
            clearButton.setOnClickListener {
                val signaturePad = findViewById<SignaturePad>(R.id.signature_pad)
                signaturePad.clearSignature()
            }

            val btnxml : Button = findViewById(R.id.btnxml)
            btnxml.setOnClickListener {
                exportToPDF()
            }

        }
        private fun exportToPDF() {
            try {
                val document = Document()
                val path = Environment.getExternalStorageDirectory().absolutePath + "/GalleryExport.pdf"
                val file = File(path)
                val fileOutputStream = FileOutputStream(file)
                val constraint : ConstraintLayout = findViewById(R.id.constraint)
                val btnClear = findViewById<Button>(R.id.clear)
                val btnCreatePDF = findViewById<Button>(R.id.btnxml)
                btnClear.visibility = View.VISIBLE
                btnCreatePDF.visibility = View.VISIBLE

                PdfWriter.getInstance(document, fileOutputStream)

                document.open()

                // Agrega el contenido al documento PDF
                addViewToPDF(document, constraint)

                document.close()

                Toast.makeText(this, "PDF creado exitosamente en $path", Toast.LENGTH_SHORT).show()

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Error al crear el PDF", Toast.LENGTH_SHORT).show()
            }
        }
        private fun addViewToPDF(document: Document, view: View) {
            val btnClear = view.findViewById<Button>(R.id.clear)
            val btnCreatePDF = view.findViewById<Button>(R.id.btnxml)
            btnClear.visibility = View.GONE
            btnCreatePDF.visibility = View.GONE

            // Convierte la vista a un bitmap
            val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            view.draw(canvas)

            // Convierte el bitmap a bytes para agregarlo al documento PDF
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val image = Image.getInstance(stream.toByteArray())

            // Ajusta el tamaño de la imagen al documento
            val documentWidth = document.pageSize.width - document.leftMargin() - document.rightMargin()
            val documentHeight = document.pageSize.height - document.topMargin() - document.bottomMargin()
            image.scaleToFit(documentWidth, documentHeight)

            // Agrega la imagen al documento
            document.add(image)

            // Agrega los checkboxes seleccionados al documento



        }

    }
}






