package com.example.spaceweekapp.fragments.SpeakerElement


import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import com.example.spaceweekapp.R
import com.squareup.picasso.Picasso


class SpeakerAdapter (val context: Context, private val list: List<HashMap<String, Any>>,val manager: FragmentManager) : RecyclerView.Adapter<SpeakerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeakerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SpeakerViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: SpeakerViewHolder, position: Int) {
        var speaker: HashMap<String, Any> = list[position]
        holder.bind(speaker, position, manager, context)
    }

    override fun getItemCount(): Int = list.size

}

class SpeakerViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(com.example.spaceweekapp.R.layout.one_speaker_element, parent, false)) {
    var moreInfo: MutableList<String>? = null
    private var description: TextView? = null
    private var organisation: TextView? = null
    private var title: TextView? = null
    private var photo: ImageView?=null
    private var place: TextView? = null
    private  var pos : Int = 0
    private lateinit var ev : HashMap<String, Any>
    init {
        title = itemView.findViewById(com.example.spaceweekapp.R.id.speaker_name)
        description = itemView.findViewById(com.example.spaceweekapp.R.id.speaker_description)
        organisation = itemView.findViewById(com.example.spaceweekapp.R.id.speaker_organisation)
        photo=itemView.findViewById(com.example.spaceweekapp.R.id.speaker_photo)


    }

    @SuppressLint("SetTextI18n")
    fun bind(speaker: HashMap<String, Any>, position: Int, manager: FragmentManager, context : Context)  {
        var titleTemp =speaker.getOrDefault("degree", "") as String
         titleTemp= titleTemp.plus(" ").plus( speaker.getOrDefault("name", " ") as String)
        titleTemp = titleTemp.plus(" ").plus(speaker.getOrDefault("surname", " ") as String)
        title?.text= titleTemp
        description?.text=speaker.getOrDefault("description", " ") as String
        organisation?.text=speaker.getOrDefault("organisation", " ") as String
        photo?.setImageResource(R.drawable.lecture)


        Picasso.get()
            .load(speaker.getOrDefault("photo_url", "") as String)
            .placeholder(R.drawable.lecture)
            .error(R.drawable.lecture)
            .fit()
            .into(photo)

    }

}