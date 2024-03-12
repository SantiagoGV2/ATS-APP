package com.fibrateltec.atsapp.ui.gallery2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.fibrateltec.atsapp.R


import com.fibrateltec.atsapp.ui.gallery3.GalleryFragment3

class GalleryFragment2 {

    class SecondActivity : AppCompatActivity() {


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_gallery2)
            seleccion(this)
            seleccion4(this)
            seleccion5(this)
            seleccion10(this)
            val nxtboton2: Button = findViewById(R.id.nextBtn2)
            nxtboton2.setOnClickListener {
                val intent = Intent(this, GalleryFragment2_1.SecondActivity2_1::class.java)
                startActivity(intent)
            }


        }
        fun seleccion(context: Context) {
            val button: Button = findViewById(R.id.btnfisico)
            val selectedItems = mutableListOf<String>()
            button.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Factor y agente de riesgo")

                val primerItems = resources.getStringArray(R.array.fisico)
                val segundoItems = resources.getStringArray(R.array.fisico2)

                val items = mutableListOf<String>().apply {
                    addAll(primerItems)
                    add("**Medidas de control**")
                    addAll(segundoItems)
                }.toTypedArray()

                val checkedItems = BooleanArray(items.size)

                builder.setMultiChoiceItems(items, checkedItems) { _, i, b ->
                    if (i != primerItems.size && i != items.size - 1) {
                        // Ignora el texto separador y las acciones asociadas
                        Toast.makeText(context, "Selección: $i estado $b", Toast.LENGTH_LONG)
                            .show()

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
        fun seleccion4(context: Context) {
            val selectedItems = mutableListOf<String>()
            val button4: Button = findViewById(R.id.btnalturas)
            button4.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Factor y agente de riesgo")

                val primerItems = resources.getStringArray(R.array.alturas)
                val segundoItems = resources.getStringArray(R.array.alturas2)

                val items = mutableListOf<String>().apply {
                    addAll(primerItems)
                    add("Medidas de control")
                    addAll(segundoItems)
                }.toTypedArray()

                val checkedItems = BooleanArray(items.size)

                builder.setMultiChoiceItems(items, checkedItems) { _, i, b ->
                    if (i != primerItems.size && i != items.size - 1) {
                        // Ignora el texto separador y las acciones asociadas
                        Toast.makeText(context, "Selección: $i estado $b", Toast.LENGTH_SHORT)
                            .show()

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
                    mostrarSelecciones3(selectedItems)
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

                val primerItems = resources.getStringArray(R.array.biomecanicos)
                val segundoItems = resources.getStringArray(R.array.biomecanicos2)

                val items = mutableListOf<String>().apply {
                    addAll(primerItems)
                    add("Medidas de control")
                    addAll(segundoItems)
                }.toTypedArray()

                val checkedItems = BooleanArray(items.size)

                builder.setMultiChoiceItems(items, checkedItems) { _, i, b ->
                    if (i != primerItems.size && i != items.size - 1) {
                        // Ignora el texto separador y las acciones asociadas
                        Toast.makeText(context, "Selección: $i estado $b", Toast.LENGTH_SHORT).show()

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
        fun seleccion10(context: Context) {
            val selectedItems = mutableListOf<String>()
            val button10: Button = findViewById(R.id.btneléctrico)
            button10.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Factor y agente de riesgo")

                val primerItems = resources.getStringArray(R.array.electrico)
                val segundoItems = resources.getStringArray(R.array.electrico2)

                val items = mutableListOf<String>().apply {
                    addAll(primerItems)
                    add("Medidas de control")
                    addAll(segundoItems)
                }.toTypedArray()

                val checkedItems = BooleanArray(items.size)

                builder.setMultiChoiceItems(items, checkedItems) { _, i, b ->
                    if (i != primerItems.size && i != items.size - 1) {
                        // Ignora el texto separador y las acciones asociadas
                        Toast.makeText(context, "Selección: $i estado $b", Toast.LENGTH_SHORT)
                            .show()

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
                    mostrarSelecciones8(selectedItems)
                }

                builder.show()
            }
        }
        private fun mostrarSelecciones(selecciones: List<String>) {
            val textView: TextView = findViewById(R.id.textView23) // Reemplaza con tu ID de TextView
            textView.text = "Selecciones: ${selecciones.joinToString(", ")}"

        }
        private fun mostrarSelecciones3(selecciones: List<String>) {
            val textView: TextView = findViewById(R.id.textView26) // Reemplaza con tu ID de TextView
            textView.text = "Selecciones: ${selecciones.joinToString(", ")}"

        }
        private fun mostrarSelecciones4(selecciones: List<String>) {
            val textView: TextView = findViewById(R.id.textView27) // Reemplaza con tu ID de TextView
            textView.text = "Selecciones: ${selecciones.joinToString(", ")}"

        }
        private fun mostrarSelecciones8(selecciones: List<String>) {
            val textView: TextView = findViewById(R.id.textView31) // Reemplaza con tu ID de TextView
            textView.text = "Selecciones: ${selecciones.joinToString(", ")}"

        }


    }
}
