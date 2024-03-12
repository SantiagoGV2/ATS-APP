package com.fibrateltec.atsapp.ui.gallery



import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fibrateltec.atsapp.R
import com.fibrateltec.atsapp.databinding.FragmentGalleryBinding
import com.fibrateltec.atsapp.ui.gallery2.GalleryFragment2


class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val galleryViewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        val spinner: Spinner = root.findViewById(R.id.mySpinner1)

        // Define los elementos del Spinner
        val items = listOf("Bueno", "Regular", "Para cambio")

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = items[position]
                Toast.makeText(requireContext(), "$selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        // Encuentra el Spinner por su ID
        val spinner1: Spinner = root.findViewById(R.id.spinner2)

        // Define los elementos del Spinner
        val items1 = listOf("Bueno", "Regular", "Para cambio")

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter1 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items1)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = adapter1

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem1 = items1[position]
                Toast.makeText(requireContext(), "$selectedItem1", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        val spinner2: Spinner = root.findViewById(R.id.spinner3)

        // Define los elementos del Spinner
        val items2 = listOf("Bueno", "Regular", "Para cambio")

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter2 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items2)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = adapter2

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem2 = items2[position]
                Toast.makeText(requireContext(), "$selectedItem2", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        val spinner3: Spinner = root.findViewById(R.id.spinner4)

        // Define los elementos del Spinner
        val items3 = listOf("Bueno", "Regular", "Para cambio")

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter3= ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items3)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner3.adapter = adapter3

        spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem3 = items3[position]
                Toast.makeText(requireContext(), "$selectedItem3", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }

        val spinner4: Spinner = root.findViewById(R.id.spinner5)

        // Define los elementos del Spinner
        val items4 = listOf("Bueno", "Regular", "Para cambio")

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter4 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items4)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner4.adapter = adapter4

        spinner4.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem4 = items4[position]
                Toast.makeText(requireContext(), "$selectedItem4", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        val spinner5: Spinner = root.findViewById(R.id.spinner6)

        // Define los elementos del Spinner
        val items5 = listOf("Bueno", "Regular", "Para cambio")

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter5 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items5)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner5.adapter = adapter5

        spinner5.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem5 = items5[position]
                Toast.makeText(requireContext(), "$selectedItem5", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        val spinner6: Spinner = root.findViewById(R.id.spinner7)

        // Define los elementos del Spinner
        val items6 = listOf("Bueno", "Regular", "Para cambio")

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter6 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items6)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner6.adapter = adapter6

        spinner6.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem6 = items6[position]
                Toast.makeText(requireContext(), "$selectedItem6", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        val spinner7: Spinner = root.findViewById(R.id.spinner8)

        // Define los elementos del Spinner
        val items7 = listOf("Bueno", "Regular", "Para cambio")

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter7 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items7)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner7.adapter = adapter7

        spinner7.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem7 = items7[position]
                Toast.makeText(requireContext(), "$selectedItem7", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        val spinner8: Spinner = root.findViewById(R.id.spinner9)

        // Define los elementos del Spinner
        val items8 = listOf("Bueno", "Regular", "Para cambio")

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter8 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items8)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner8.adapter = adapter8

        spinner8.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem8 = items8[position]
                Toast.makeText(requireContext(), "$selectedItem8", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        val spinner9: Spinner = root.findViewById(R.id.spinner10)

        // Define los elementos del Spinner
        val items9 = listOf("Bueno", "Regular", "Para cambio")

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter9 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items9)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner9.adapter = adapter9

        spinner4.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem9 = items9[position]
                Toast.makeText(requireContext(), "$selectedItem9", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        val spinner10: Spinner = root.findViewById(R.id.spinner11)

        // Define los elementos del Spinner
        val items10 = listOf("Bueno", "Regular", "Para cambio")

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter10 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items10)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner10.adapter = adapter10

        spinner10.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem10 = items10[position]
                Toast.makeText(requireContext(), "$selectedItem10", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        val spinner11: Spinner = root.findViewById(R.id.spinner12)

        // Define los elementos del Spinner
        val items11 = listOf("Bueno", "Regular", "Para cambio")

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter11 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items11)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner11.adapter = adapter11

        spinner11.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem11 = items11[position]
                Toast.makeText(requireContext(), "$selectedItem11", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        val spinner12: Spinner = root.findViewById(R.id.spinner13)

        // Define los elementos del Spinner
        val items12 = listOf("Bueno", "Regular", "Para cambio")

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter12 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items12)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner12.adapter = adapter12

        spinner12.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem12 = items12[position]
                Toast.makeText(requireContext(), "$selectedItem12", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        val spinner13: Spinner = root.findViewById(R.id.spinner14)

        // Define los elementos del Spinner
        val items13 = listOf("Bueno", "Regular", "Para cambio")

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter13 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items13)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner13.adapter = adapter13

        spinner13.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem13 = items13[position]
                Toast.makeText(requireContext(), "$selectedItem13", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        val spinner14: Spinner = root.findViewById(R.id.spinner15)

        // Define los elementos del Spinner
        val items14 = listOf("Bueno", "Regular", "Para cambio")

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter14 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items14)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner14.adapter = adapter14

        spinner14.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem14 = items14[position]
                Toast.makeText(requireContext(), "$selectedItem14", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        val spinner15: Spinner = root.findViewById(R.id.spinner16)

        // Define los elementos del Spinner
        val items15 = listOf("Bueno", "Regular", "Para cambio")

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter15 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items15)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner15.adapter = adapter15

        spinner15.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem15 = items15[position]
                Toast.makeText(requireContext(), "$selectedItem15", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        val spinner16: Spinner = root.findViewById(R.id.spinner17)

        // Define los elementos del Spinner
        val items16 = listOf("Bueno", "Regular", "Para cambio")

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter16 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items16)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner16.adapter = adapter16

        spinner16.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem16 = items16[position]
                Toast.makeText(requireContext(), "$selectedItem16", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        val spinner17: Spinner = root.findViewById(R.id.spinner18)

        // Define los elementos del Spinner
        val items17 = listOf("Bueno", "Regular", "Para cambio")

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter17 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items17)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner17.adapter = adapter17

        spinner17.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem17 = items17[position]
                Toast.makeText(requireContext(), "$selectedItem17", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        val spinner18: Spinner = root.findViewById(R.id.spinner19)

        // Define los elementos del Spinner
        val items18 = listOf("Bueno", "Regular", "Para cambio")

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter18 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items18)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner18.adapter = adapter18

        spinner18.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem18 = items18[position]
                Toast.makeText(requireContext(), "$selectedItem18", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }

        val nxtboton: Button = root.findViewById(R.id.nextBtn)
        nxtboton.setOnClickListener {
            // Al hacer clic en el botón, inicia la SecondActivity desde el contexto del fragmento
            val intent = Intent(requireContext(), GalleryFragment2.SecondActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}





