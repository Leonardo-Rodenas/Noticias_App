package com.example.noticias_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noticias_app.R
import com.example.noticias_app.databinding.ItemRecyclerviewBinding
import com.example.noticias_app.model.Article
import com.squareup.picasso.Picasso
import java.util.ArrayList

class AdaptadorNoticias : RecyclerView.Adapter<AdaptadorNoticias.CustomViewHolder>() {

    private var lista : List<Article> = ArrayList()
    lateinit var listener: ListenerParaClicksEnRV

    class CustomViewHolder(itemView: View, var listener: ListenerParaClicksEnRV) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemRecyclerviewBinding.bind(itemView)

        fun bindData(noticia:Article) {

            binding.tituloNoticia.text = noticia.title
            binding.tvFechaPublicaion.text = noticia.publishedAt
            binding.tvAutor.text = noticia.author

            Picasso.get().load(noticia.urlToImage).fit().centerCrop()
                .placeholder(R.drawable.user_placeholder)
                .error(R.drawable.user_placeholder_error)
                .into(binding.ivImagenNoticia)

            itemView.setOnClickListener{
                listener.alClickearItem(noticia)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview,parent,false)
        return CustomViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindData(lista[position])
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun setNoticia(noticias:List<Article>)
    {
        lista = noticias as ArrayList<Article>
        notifyDataSetChanged()
    }

    interface ListenerParaClicksEnRV{
        fun alClickearItem(noticia: Article)
    }

    fun setOnItemClickListener(listener: ListenerParaClicksEnRV)
    {
        this.listener = listener
    }

}