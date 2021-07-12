package com.example.appmvvm.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appmvvm.DetailsActivity
import com.example.appmvvm.R
import com.example.appmvvm.localdatasource.entities.Permit
import kotlinx.android.synthetic.main.permit_item.view.*

class PermitAdapter(private val context: Context, private val list: ArrayList<Permit>) :
    RecyclerView.Adapter<PermitAdapter.PermitViewHolder>() {

    class PermitViewHolder(private val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

        var employeeNameTextView: TextView
        var employeeSurnameTextView: TextView

        init {
            employeeNameTextView = itemView.findViewById(R.id.permit_employee_name)
            employeeSurnameTextView = itemView.findViewById(R.id.permit_employee_surname)

        }

        fun bind(permit: Permit) {

            itemView.setOnClickListener {
                val intent = Intent(context, DetailsActivity::class.java)
                //intent.putExtra(DetailsActivity.EXTRAS_MOVIE_ID, movie.id)
                context.startActivity(intent)
            }
            itemView.permit_employee_name.text = permit.EmployeeName
            itemView.permit_employee_surname.text = permit.EmployeeSurname
            //Glide.with(context).load(Config.IMAGE_URL + movie.poster_path)
            //    .apply(RequestOptions().override(400, 400).centerInside().placeholder(R.drawable.placehoder)).into(itemView.ivPoster)
            //itemView.tvGenre.text = Genre.getGenre(movie.genre_ids)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PermitViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.permit_item, parent, false)
        return PermitViewHolder(context, view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PermitViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun updateData(newList: List<Permit>) {
        list.clear()
        val sortedList = newList//.sortedBy { movie -> movie.popularity }
        list.addAll(sortedList)
        notifyDataSetChanged()
    }
}
