package com.example.paulina.wisehome.blinds

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.model.businessobjects.BlindDirection
import com.example.paulina.wisehome.model.businessobjects.BlindState
import com.example.paulina.wisehome.model.transportobjects.Blind
import kotlinx.android.synthetic.main.item_blind.view.*

internal class BlindsAdapter(private val context: Context) : RecyclerView.Adapter<BlindsAdapter.ViewHolder>() {

    var blinds: List<Blind> = emptyList<Blind>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_blind, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.blindNameTextView.text = blinds[position].name
        holder.blindStateTextView.text = blinds[position].state.toStringLocale()
        setupStateTextColor(holder, blinds[position].state)
        setupDirectionImages(holder, blinds[position]._id)
    }

    private fun setupStateTextColor(holder: ViewHolder, state: BlindState) {
        val stateColor: Int
        when (state) {
            BlindState.PARTLY_CLOSED -> stateColor = R.color.colorOrange
            BlindState.MOVING -> stateColor = R.color.colorAccent
            BlindState.OPENED -> stateColor = R.color.colorGreenLight
            BlindState.CLOSED -> stateColor = R.color.colorPurple
        }
        holder.blindStateTextView.setTextColor(context.resources.getColor(stateColor))
    }

    private fun setupDirectionImages(holder: ViewHolder, id: String) {
        holder.downButton.setOnClickListener(({ view -> (context as BlindsView).onChangeBlindDirectionClick(BlindDirection.DOWN, id) }))
        holder.upButton.setOnClickListener(({ view -> (context as BlindsView).onChangeBlindDirectionClick(BlindDirection.UP, id) }))
        holder.stopImageView.setOnClickListener(({ view -> (context as BlindsView).onChangeBlindDirectionClick(BlindDirection.STOP, id) }))
    }

    override fun getItemCount(): Int {
        return blinds.size
    }

    internal class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val blindNameTextView = view.blindNameTextView
        val blindStateTextView = view.blindStateTextView
        val downButton = view.downButton
        val upButton = view.upButton
        val stopImageView = view.stopImageView
    }

    fun updateBlindState(id: String, state: BlindState) {
        for (blind in blinds) {
            if (blind._id == id) {
                blind.state = state
                break
            }
        }
        notifyDataSetChanged()
    }
}