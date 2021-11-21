package com.example.noticias_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noticias_app.databinding.FragmentDetalleArticuloBinding
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.example.noticias_app.R
import com.example.noticias_app.model.Article
import com.example.noticias_app.viewmodel.NoticiaViewModel
import com.squareup.picasso.Picasso


class DetalleArticuloFragment : Fragment() {

    private var _binding: FragmentDetalleArticuloBinding? = null
    private val binding get() = _binding!!
    private lateinit var vmodel: NoticiaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetalleArticuloBinding.inflate(inflater, container, false)

        vmodel = ViewModelProvider(this).get(NoticiaViewModel::class.java)

         //recibir lo que llega de la lista de las noticias

        var noticia = arguments?.getSerializable("noticia").toString()

        //asignar lo que recibo con el binding a cada vista

      //


        binding.btnCompartir.setOnClickListener() {

            val compartirIntent = Intent()
            compartirIntent.action = Intent.ACTION_SEND
            compartirIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Hola, esta noticia me pareció que podria interesarte "
            )
            compartirIntent.type = "text/plain"
            startActivity(compartirIntent)

        }


        return binding.root
    }


}