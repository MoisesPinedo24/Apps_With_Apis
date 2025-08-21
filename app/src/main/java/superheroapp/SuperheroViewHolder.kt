package superheroapp

import android.view.View
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.RecyclerView
import com.example.appbodymassindexcalculator_xml.databinding.ItemSuperheroBinding

class SuperheroViewHolder(view:View) : RecyclerView.ViewHolder(view) {


        private val binding = ItemSuperheroBinding.bind(view)

        fun bind(superheroItemResponse: SuperheroItemResponse){
            binding.tvSuperheroName.text = superheroItemResponse.name
        }


    }
