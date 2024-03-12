package com.fibrateltec.navbar.ui.riesgo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fibrateltec.atsapp.databinding.FragmentRiesgoBinding


class RiesgoFragment : Fragment() {

    private var _binding: FragmentRiesgoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val riesgoViewModel =
            ViewModelProvider(this).get(RiesgoViewModel::class.java)

        _binding = FragmentRiesgoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textRiesgo
        riesgoViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}