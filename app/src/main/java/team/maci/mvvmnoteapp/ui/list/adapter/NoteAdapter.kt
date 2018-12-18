package team.maci.mvvmnoteapp.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import team.maci.mvvmnoteapp.R
import team.maci.mvvmnoteapp.databinding.ItemNoteBinding
import team.maci.mvvmnoteapp.model.Note
import team.maci.mvvmnoteapp.ui.list.ListViewModel
import team.maci.mvvmnoteapp.ui.list.item.NoteItemViewModel

class NoteViewHolder(
    val binding: ItemNoteBinding
) : RecyclerView.ViewHolder(binding.root)

class NoteAdapter(
    private val parentViewModel: ListViewModel
): RecyclerView.Adapter<NoteViewHolder>() {
    private val items = ArrayList<NoteItemViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = DataBindingUtil.inflate<ItemNoteBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_note,
            parent,
            false
        )
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.binding.viewModel = items[position]
        holder.binding.executePendingBindings()
    }

    fun setItems(items: List<Note>){
        this.items.clear()
        this.items.addAll(
            items.map {
                NoteItemViewModel(it, parentViewModel)
            }
        )

        notifyDataSetChanged()
    }

    fun removeItem(predicate: (item: NoteItemViewModel) -> Boolean){
        items.removeAll(predicate)
        notifyDataSetChanged()
    }
}