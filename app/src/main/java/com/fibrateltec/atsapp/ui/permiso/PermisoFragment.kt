package com.fibrateltec.navbar.ui.permiso

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
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import androidx.constraintlayout.widget.ConstraintLayout

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fibrateltec.atsapp.R
import com.fibrateltec.atsapp.databinding.FragmentPermisoBinding
import com.fibrateltec.atsapp.ui.permiso2.PermisoFragment2
import com.itextpdf.text.Document
import com.itextpdf.text.Image
import com.itextpdf.text.pdf.PdfWriter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class PermisoFragment : Fragment() {

    private var _binding: FragmentPermisoBinding? = null
    private var isCreatePDFButtonVisible = true
    private var btnNextVisibility = View.VISIBLE
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPermisoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val permisoViewModel = ViewModelProvider(this).get(PermisoViewModel::class.java)

        val textView: TextView = binding.Permiso
        permisoViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        // Llama a la función de selección aquí
        seleccion(root.context)
        if (checkPermission()) {
            Toast.makeText(requireContext(), "Permiso Aceptado", Toast.LENGTH_LONG).show()
        } else {
            requestPermissions()
        }

        val nxtboton: Button = root.findViewById(R.id.next)
        btnNextVisibility = nxtboton.visibility
        nxtboton.setOnClickListener {
            val intent = Intent(requireContext(), PermisoFragment2.FourteenthActivity::class.java)
            startActivity(intent)

        }

        val btnxml3: Button = root.findViewById(R.id.btnxml)
        btnxml3.setOnClickListener {
            isCreatePDFButtonVisible = !isCreatePDFButtonVisible
            btnxml3.visibility = if (isCreatePDFButtonVisible) View.VISIBLE else View.GONE
            exportToPDF()
        }
        val etBirthDate: TextView = root.findViewById(R.id.fecha_Realizacion)

        val fechaActual = obtenerFechaActual()

        etBirthDate.text = "Fecha Actual:$fechaActual"

        val spinner19: Spinner = root.findViewById(R.id.nombresyapellidos)

        // Define los elementos del Spinner
        //val items18 = listOf("Bueno", "Regular", "Para cambio")
        val items19 = arrayOf("Tecnico", *resources.getStringArray(R.array.tecnicos))
        // Crea un adaptador y establece los elementos en el Spinner
        val adapter19 =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items19)
        adapter19.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner19.adapter = adapter19

        spinner19.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0) { // Si no es el pretexto
                    val selectedItem19 = items19[position]
                    Toast.makeText(requireContext(), "$selectedItem19", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }

        spinner19.onItemSelectedListener = null

        val spinner20: Spinner = root.findViewById(R.id.cedula)

        // Define los elementos del Spinner
        //val items18 = listOf("Bueno", "Regular", "Para cambio")
        val items20 = arrayOf("Cedula Técnico", *resources.getStringArray(R.array.cedulas))

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter20 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items20)
        adapter20.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner20.adapter = adapter20

        spinner20.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0) { // Si no es el pretexto
                    val selectedItem20 = items20[position]
                    Toast.makeText(requireContext(), "$selectedItem20", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }

        spinner20.onItemSelectedListener = null

        return root
    }


    private fun obtenerFechaActual(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val fecha = Date()
        return dateFormat.format(fecha)
    }

    private fun checkPermission(): Boolean {
        val permission1 = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val permission2 = ContextCompat.checkSelfPermission(
            requireContext(),
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
                    Toast.makeText(requireContext(), "Permisos concedidos", Toast.LENGTH_LONG)
                        .show()
                } else {
                    Toast.makeText(requireContext(), "Permisos Denegados", Toast.LENGTH_LONG).show()
                    // En lugar de finalizar la actividad, puedes mostrar un mensaje o realizar otra acción adecuada.
                }
            }
        }
    }

    private fun seleccion(context: Context) {
        val button: Button = binding.btnHerramientas // Usa la referencia de la vista inflada
        val selectedItems = mutableListOf<String>()
        button.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Factor y agente de riesgo")

            val primerItems = resources.getStringArray(R.array.spinnerHerramienta)

            val items = mutableListOf<String>().apply {
                addAll(primerItems)
            }.toTypedArray()

            val checkedItems = BooleanArray(items.size)

            builder.setMultiChoiceItems(items, checkedItems) { _, i, b ->
                if (i != primerItems.size && i != items.size - 0) {
                    if (b) {
                        selectedItems.add(items[i])
                    } else {
                        selectedItems.remove(items[i])
                    }
                }
            }

            builder.setPositiveButton("Aceptar") { _, _ ->
                mostrarSelecciones(selectedItems)
            }

            builder.show()
        }
    }

    private fun mostrarSelecciones(selecciones: List<String>) {
        val textView: TextView = binding.textHerramientas // Reemplaza con tu ID de TextView
        textView.text = "Selecciones: ${selecciones.joinToString(", ")}"
    }

    private fun exportToPDF() {
        val document = Document()
        val path = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            "DatosBasicos.pdf"
        ).absolutePath
        val file = File(path)
        try {
            val fileOutputStream = FileOutputStream(file)

            PdfWriter.getInstance(document, fileOutputStream)

            document.open()

            // Agrega el primer contenido al documento PDF
            val constraint: ConstraintLayout = binding.constraint12
            addViewToPDF(document, constraint)

            Toast.makeText(requireContext(), "Guardado exitosamente en $path", Toast.LENGTH_LONG)
                .show()

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Error al guardar: ${e.message}", Toast.LENGTH_LONG)
                .show()
        }
        document.close()
        binding.next.visibility = btnNextVisibility

    }


    private fun addViewToPDF(document: Document, view: View) {
        val btnCreatePDF = view.findViewById<Button>(R.id.btnxml)

        btnCreatePDF.visibility = if (isCreatePDFButtonVisible) View.VISIBLE else View.GONE

        binding.next.visibility = View.GONE
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
                    Bitmap.createBitmap(view.width, sectionHeight.toInt(), Bitmap.Config.ARGB_8888)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}