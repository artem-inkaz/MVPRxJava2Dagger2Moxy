package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.userrepolist.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import by.kirich1409.viewbindingdelegate.viewBinding
import ui.smartpro.mvprxjava2dagger2moxy.databinding.ViewUserRepoItemBinding
import ui.smartpro.mvprxjava2dagger2moxy.ext.click
import ui.smartpro.mvprxjava2dagger2moxy.ext.setStartDrawableCircleImageFromUri
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.GitHubUserRepoViewModel

class UserRepoViewHolder(view: View): ViewHolder(view) {

    private val viewBinding: ViewUserRepoItemBinding by viewBinding()

    fun bind(user: GitHubUserRepoViewModel, delegate: UserRepoAdapter.Delegate?) {
        with(viewBinding) {
            userLogin.setStartDrawableCircleImageFromUri(user.name)
            userLogin.text = user.name
            root.click { delegate?.onUserPickedRepo(user) }
        }
    }

}