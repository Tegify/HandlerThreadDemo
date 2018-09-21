package com.tegify.handlerthreaddemo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.profile_list_item.view.*

/**
 * @author thuannv
 * @since 19/09/2018
 */
class ProfileAdapter : RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    private val profiles: MutableList<Profile> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.profile_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return profiles.size
    }

    override fun onBindViewHolder(hodler: ViewHolder, position: Int) {
        hodler.bind(profiles[position])
    }

    fun lastPosition(): Int {
        return if (profiles.isEmpty()) -1 else (profiles.size - 1)
    }

    fun setProfiles(profiles: List<Profile>?) {
        val adapterData = this.profiles
        profiles?.apply {
            adapterData.clear()
            adapterData.addAll(profiles)
            notifyDataSetChanged()
        }
    }

    fun add(profile: Profile?) {
        profile?.apply {
            if (profiles.indexOf(profile) < 0) {
                val position = profiles.size
                profiles.add(profile)
                notifyItemInserted(position)
            }
        }
    }

    /**
     * ViewHolder for profile list adapter
     */
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(profile: Profile) {
            profile.avatar?.apply { Picasso.get().load(this).into(itemView.avatar) }
            itemView.displayName.text = profile.displayName
        }
    }
}