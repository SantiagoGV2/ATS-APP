package com.fibrateltec.navbar.ui.permiso

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fibrateltec.atsapp.databinding.FragmentPermisoBinding


class PermisoFragment : Fragment() {

    private var _binding: FragmentPermisoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val permisoViewModel =
            ViewModelProvider(this).get(PermisoViewModel::class.java)

        _binding = FragmentPermisoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.Permiso
        permisoViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}