package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.users.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import by.kirich1409.viewbindingdelegate.viewBinding
import ui.smartpro.mvprxjava2dagger2moxy.databinding.ViewUserBinding
import ui.smartpro.mvprxjava2dagger2moxy.ext.click
import ui.smartpro.mvprxjava2dagger2moxy.ext.setStartDrawableCircleImageFromUri
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.GitHubUserViewModel

class UserViewHolder(view: View): ViewHolder(view) {

    private val viewBinding: ViewUserBinding by viewBinding()

    fun bind(user: GitHubUserViewModel, delegate: UsersAdapter.Delegate?) {
        with(viewBinding) {
            userLogin.setStartDrawableCircleImageFromUri(user.avatar)
            userLogin.text = user.login

            root.click { delegate?.onUserPicked(user) }
        }
    }

}