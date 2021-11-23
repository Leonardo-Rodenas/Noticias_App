package com.example.noticias_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noticias_app.databinding.FragmentDetalleArticuloBinding
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import com.example.noticias_app.R
import com.example.noticias_app.model.Article
import com.example.noticias_app.viewmodel.NoticiaViewModel
import com.squareup.picasso.Picasso
import com.example.noticias_app.MainActivity




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

        var noticia = arguments?.getSerializable("noticia") as Article

        //asignar lo que recibo con el binding a cada vista

        with(binding) {
            tvAutor.text = noticia.author
            tvFechaPublicaiN.text = noticia.publishedAt
            tvFuente.text = noticia.source.name
            tvTituloDetalle.text = noticia.title
            cuerpoNoticia.text = noticia.description

            Picasso.get().load(noticia.urlToImage).fit().centerCrop()
                .placeholder(R.drawable.user_placeholder)
                .error(R.drawable.user_placeholder_error)
                .into(binding.ivImagenDetalle)
        }

        // De imagen a navegador para ver la noticia completa

        binding.ivImagenDetalle.setOnClickListener(View.OnClickListener {
            val webIntent: Intent = Uri.parse("${noticia.url}").let { webpage ->
                Intent(Intent.ACTION_VIEW, webpage)

            }

            startActivity(webIntent)
        })

        //Configurar Boton compartir

        binding.btnCompartir.setOnClickListener() {

            val compartirIntent = Intent()
            compartirIntent.action = Intent.ACTION_SEND
            compartirIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Hola, me pareció que esta noticia podria interesarte: " +
                        "\n ${noticia.url}"
            )
            compartirIntent.type = "text/plain"
            startActivity(compartirIntent)

        }

        return binding.root
    }


}