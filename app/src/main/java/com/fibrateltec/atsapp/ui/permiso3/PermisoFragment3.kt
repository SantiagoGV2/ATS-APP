package com.fibrateltec.atsapp.ui.permiso3

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
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ScrollView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fibrateltec.atsapp.R
import com.fibrateltec.atsapp.databinding.FragmentPermiso3Binding
import com.fibrateltec.atsapp.ui.Signature.SignaturePad
import com.fibrateltec.atsapp.ui.pdf4.PdfFragment4
import com.itextpdf.text.Document
import com.itextpdf.text.Image
import com.itextpdf.text.pdf.PdfWriter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream


class PermisoFragment3 : Fragment() {

    private var _binding: FragmentPermiso3Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val permisoViewModel =
            ViewModelProvider(this).get(PermisoViewModel3::class.java)

        _binding = FragmentPermiso3Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.Permiso3
        permisoViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }
    class FifteenthActivity : AppCompatActivity() {

        private var isCreatePDFButtonVisible = true
        private var btnNextVisibility = View.VISIBLE
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_permiso3)

            val scrollView = findViewById<ScrollView>(R.id.scroll2)
            val signaturePad = findViewById<SignaturePad>(R.id.signaturePad)
            val signaturePad2 = findViewById<SignaturePad>(R.id.signaturePad2)
            signaturePad.setScrollView(scrollView)
            signaturePad2.setScrollView(scrollView)
            if (checkPermission()) {
                Toast.makeText(this, "Permiso Aceptado", Toast.LENGTH_LONG).show()
            } else {
                requestPermissions()
            }
            val nxtboton2: Button = findViewById(R.id.button5)
            btnNextVisibility = nxtboton2.visibility
            nxtboton2.setOnClickListener {
                val intent = Intent(this, PdfFragment4.SixteenthActivity::class.java)
                startActivity(intent)

            }

            val btnxml2: Button = findViewById(R.id.button6)
            btnxml2.setOnClickListener {
                isCreatePDFButtonVisible = !isCreatePDFButtonVisible
                btnxml2.visibility = if (isCreatePDFButtonVisible) View.VISIBLE else View.GONE
                exportToPDF()
            }
            val clearButton = findViewById<Button>(R.id.clear1)
            clearButton.setOnClickListener {
                val signaturePad = findViewById<SignaturePad>(R.id.signaturePad)
                signaturePad.clearSignature()
            }
            val clearButton2 = findViewById<Button>(R.id.clear2)
            clearButton2.setOnClickListener {
                val signaturePad22 = findViewById<SignaturePad>(R.id.signaturePad2)
                signaturePad22.clearSignature2()
            }
            val spinner: Spinner = findViewById(R.id.spinner)

            // Define los elementos del Spinner
            val items = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem = items[position]
                    Toast.makeText(this@FifteenthActivity, selectedItem, Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner.onItemSelectedListener = null
            // Encuentra el Spinner por su ID
            val spinner1: Spinner = findViewById(R.id.spinner2)

            // Define los elementos del Spinner
            val items1 = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items1)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner1.adapter = adapter1

            spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem1 = items1[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem1", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner1.onItemSelectedListener = null
            val spinner2: Spinner = findViewById(R.id.spinner3)

            // Define los elementos del Spinner
            val items2 = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items2)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner2.adapter = adapter2

            spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem2 = items2[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem2", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner2.onItemSelectedListener = null
            val spinner3: Spinner = findViewById(R.id.spinner21)

            // Define los elementos del Spinner
            val items3 = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter3 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items3)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner3.adapter = adapter3

            spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem3 = items3[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem3", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner3.onItemSelectedListener = null
            val spinner4: Spinner = findViewById(R.id.spinner22)

            // Define los elementos del Spinner
            val items4 = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter4 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items4)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner4.adapter = adapter4

            spinner4.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem4 = items4[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem4", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner4.onItemSelectedListener = null
            val spinner5: Spinner = findViewById(R.id.spinner23)
            // Define los elementos del Spinner
            val items5 = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter5 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items5)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner5.adapter = adapter5

            spinner5.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem5 = items5[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem5", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner5.onItemSelectedListener = null
            val spinner6: Spinner = findViewById(R.id.spinner24)

            // Define los elementos del Spinner
            val items6 =resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter6 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items6)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner6.adapter = adapter6

            spinner6.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem6 = items6[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem6", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner6.onItemSelectedListener = null
            val spinner7: Spinner = findViewById(R.id.spinner25)

            // Define los elementos del Spinner
            val items7 = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter7 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items7)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner7.adapter = adapter7

            spinner7.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem7 = items7[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem7", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner7.onItemSelectedListener = null
            val spinner8: Spinner = findViewById(R.id.spinner26)


            // Define los elementos del Spinner
            val items8 = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter8 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items8)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner8.adapter = adapter8


            spinner8.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem8 = items8[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem8", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner8.onItemSelectedListener = null
            val spinner9: Spinner = findViewById(R.id.spinner27)

            // Define los elementos del Spinner
            val items9 = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter9 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items9)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner9.adapter = adapter9

            spinner9.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem9 = items9[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem9", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner9.onItemSelectedListener = null
            val spinner10: Spinner = findViewById(R.id.spinner28)

            // Define los elementos del Spinner
            val items10 = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter10 =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, items10)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner10.adapter = adapter10

            spinner10.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem10 = items10[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem10", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner10.onItemSelectedListener = null
            val spinner11: Spinner = findViewById(R.id.spinner29)

            // Define los elementos del Spinner
            val items11 = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter11 =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, items11)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner11.adapter = adapter11

            spinner11.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem11 = items11[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem11", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner11.onItemSelectedListener = null
            val spinner12: Spinner = findViewById(R.id.spinner30)

            // Define los elementos del Spinner
            val items12 = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter12 =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, items12)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner12.adapter = adapter12

            spinner12.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem12 = items12[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem12", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner12.onItemSelectedListener = null
            val spinner13: Spinner = findViewById(R.id.spinner31)

            // Define los elementos del Spinner
            val items13 = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter13 =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, items13)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner13.adapter = adapter13

            spinner13.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem13 = items13[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem13", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner13.onItemSelectedListener = null
            val spinner14: Spinner = findViewById(R.id.spinner32)

            // Define los elementos del Spinner
            val items14 = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter14 =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, items14)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner14.adapter = adapter14

            spinner14.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem14 = items14[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem14", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner14.onItemSelectedListener = null

            val spinner15: Spinner = findViewById(R.id.nombre_supervisor)

            // Define los elementos del Spinner
            val items15 = arrayOf("Supervisor", *resources.getStringArray(R.array.supervisores))

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter15 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items15)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner15.adapter = adapter15

            spinner15.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position != 0) { // Si no es el pretexto
                        val selectedItem15 = items15[position]
                        Toast.makeText(this@FifteenthActivity, "$selectedItem15", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner15.onItemSelectedListener = null
            val spinner16: Spinner = findViewById(R.id.cedula)

            // Define los elementos del Spinner
            val items16 = arrayOf("Cedula Supervisor", *resources.getStringArray(R.array.cedula_supervisor))

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter16 =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, items16)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner16.adapter = adapter16

            spinner16.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position != 0) { // Si no es el pretexto
                        val selectedItem16 = items16[position]
                        Toast.makeText(this@FifteenthActivity, "$selectedItem16", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner16.onItemSelectedListener = null



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
                "MedidasPrevencion2.pdf"
            ).absolutePath
            val file = File(path)
            try {
                val fileOutputStream = FileOutputStream(file)

                PdfWriter.getInstance(document, fileOutputStream)

                document.open()

                // Agrega el primer contenido al documento PDF
                val constraint: ConstraintLayout = findViewById(R.id.constraint14)
                addViewToPDF(document, constraint)

                document.close()
                findViewById<Button>(R.id.button5).visibility = btnNextVisibility
                Toast.makeText(this, "Guardado exitosamente en $path", Toast.LENGTH_LONG).show()

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Error al guardar: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }


        private fun addViewToPDF(document: Document, view: View) {
            val btnCreatePDF = view.findViewById<Button>(R.id.button6)
            btnCreatePDF.visibility = if (isCreatePDFButtonVisible) View.VISIBLE else View.GONE

            val btnClear = view.findViewById<Button>(R.id.clear1)
            if (btnClear != null) {
                btnClear.visibility = View.GONE
            }
            val btnClear2 = view.findViewById<Button>(R.id.clear2)
            if (btnClear2 != null) {
                btnClear2.visibility = View.GONE
            }

            findViewById<Button>(R.id.button5).visibility = View.GONE

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