package com.fibrateltec.atsapp.ui.gallery2

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.fibrateltec.atsapp.R

class GalleryFragment2_1 {
    class SecondActivity2_1 : AppCompatActivity(){
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_gallery2_1)

            seleccion2(this)
            seleccion3(this)
            seleccion6(this)
            seleccion8(this)
            seleccion11(this)
            seleccion12(this)
        }
        fun seleccion2(context: Context) {
            val selectedItems = mutableListOf<String>()
            val button2: Button = findViewById(R.id.btnquimico)
            button2.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Factor y agente de riesgo")

                val primerItems = resources.getStringArray(R.array.quimico)
                val segundoItems = resources.getStringArray(R.array.quimico2)

                val items = mutableListOf<String>().apply {
                    addAll(primerItems)
                    add("Medidas de control")
                    addAll(segundoItems)
                }.toTypedArray()

                val checkedItems = BooleanArray(items.size)

                builder.setMultiChoiceItems(items, checkedItems) { _, a, c ->
                    if (a != primerItems.size && a != items.size - 1) {
                        // Ignora el texto separador y las acciones asociadas
                        Toast.makeText(context, "Selección: $a estado $c", Toast.LENGTH_SHORT)
                            .show()

                        if (c) {
                            // Si el elemento está marcado, agrégalo a la lista de selecciones
                            selectedItems.add(items[a])
                        } else {
                            // Si el elemento está desmarcado, remuévelo de la lista de selecciones
                            selectedItems.remove(items[a])
                        }
                    }
                }

                builder.setPositiveButton("Aceptar") { _, _ ->
                    // Acciones a realizar al hacer clic en Aceptar
                    mostrarSelecciones1(selectedItems)
                }

                builder.show()
            }
        }

        fun seleccion3(context: Context) {
            val selectedItems = mutableListOf<String>()
            val button3: Button = findViewById(R.id.btnpsicosocial)
            button3.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Factor y agente de riesgo")

                val primerItems = resources.getStringArray(R.array.psicosocial)
                val segundoItems = resources.getStringArray(R.array.psicosocial2)

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
                    mostrarSelecciones2(selectedItems)
                }

                builder.show()
            }
        }
        fun seleccion6(context: Context) {
            val selectedItems = mutableListOf<String>()
            val button6: Button = findViewById(R.id.btnmecánicos)
            button6.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Factor y agente de riesgo")

                val primerItems = resources.getStringArray(R.array.mecanicos)
                val segundoItems = resources.getStringArray(R.array.mecanicos2)

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
                    mostrarSelecciones5(selectedItems)
                }

                builder.show()
            }
        }
        fun seleccion8(context: Context) {
            val selectedItems = mutableListOf<String>()
            val button8: Button = findViewById(R.id.btnconfinados)
            button8.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Factor y agente de riesgo")

                val primerItems = resources.getStringArray(R.array.confinados)
                val segundoItems = resources.getStringArray(R.array.confinados2)

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
                    mostrarSelecciones7(selectedItems)
                }

                builder.show()
            }
        }
        fun seleccion11(context: Context) {
            val selectedItems = mutableListOf<String>()
            val button11: Button = findViewById(R.id.btnseguridad)
            button11.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Factor y agente de riesgo")

                val primerItems = resources.getStringArray(R.array.seguridad)
                val segundoItems = resources.getStringArray(R.array.seguridad2)

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
                    mostrarSelecciones9(selectedItems)
                }

                builder.show()
            }
        }
        fun seleccion12(context: Context) {
            val selectedItems = mutableListOf<String>()
            val button12: Button = findViewById(R.id.btnfenomenos)
            button12.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Factor y agente de riesgo")

                val primerItems = resources.getStringArray(R.array.fenomenos)
                val segundoItems = resources.getStringArray(R.array.fenomenos2)

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
                    mostrarSelecciones10(selectedItems)
                }

                builder.show()
            }
        }

        private fun mostrarSelecciones9(selecciones: List<String>) {
            val textView: TextView = findViewById(R.id.textView32) // Reemplaza con tu ID de TextView
            textView.text = "Selecciones: ${selecciones.joinToString(", ")}"

        }
        private fun mostrarSelecciones10(selecciones: List<String>) {
            val textView: TextView = findViewById(R.id.textView33) // Reemplaza con tu ID de TextView
            textView.text = "Selecciones: ${selecciones.joinToString(", ")}"

        }
        private fun mostrarSelecciones1(selecciones: List<String>) {
            val textView: TextView = findViewById(R.id.textView24) // Reemplaza con tu ID de TextView
            textView.text = "Selecciones: ${selecciones.joinToString(", ")}"

        }
        private fun mostrarSelecciones2(selecciones: List<String>) {
            val textView: TextView = findViewById(R.id.textView25) // Reemplaza con tu ID de TextView
            textView.text = "Selecciones: ${selecciones.joinToString(", ")}"

        }
        private fun mostrarSelecciones5(selecciones: List<String>) {
            val textView: TextView = findViewById(R.id.textView28) // Reemplaza con tu ID de TextView
            textView.text = "Selecciones: ${selecciones.joinToString(", ")}"

        }
        private fun mostrarSelecciones7(selecciones: List<String>) {
            val textView: TextView = findViewById(R.id.textView29) // Reemplaza con tu ID de TextView
            textView.text = "Selecciones: ${selecciones.joinToString(", ")}"

        }
    }
}