package net.sokum.mordern.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import net.sokum.mordern.app.module.LikeUserStorage

data class UserList(
    @SerializedName("total_count")
    val totalCount : Int,
    @SerializedName("incomplete_results")
    val incompleteResults : Boolean,
    @SerializedName("items")
    val items : List<UserItem>
)

@Entity(tableName = "users")
data class UserItem(
    @SerializedName("login")
    val login : String,

    @PrimaryKey
    @SerializedName("id")
    val id : Long,
    @SerializedName("node_id")
    val nodeId : String,
    @SerializedName("avatar_url")
    val avatarUrl : String,
    @SerializedName("gravatar_id")
    val gravatarId : String,
    @SerializedName("url")
    val url : String,
    @SerializedName("html_url")
    val htmlUrl : String,
    @SerializedName("followers_url")
    val followersUrl : String,
    @SerializedName("following_url")
    val followingUrl : String,
    @SerializedName("gists_url")
    val gistsUrl : String,
    @SerializedName("starred_url")
    val starredUrl : String,
    @SerializedName("subscriptions_url")
    val subscriptionsUrl : String,
    @SerializedName("organizations_url")
    val organizationsUrl : String,
    @SerializedName("repos_url")
    val reposUrl : String,
    @SerializedName("events_url")
    val eventsUrl : String,
    @SerializedName("received_events_url")
    val receivedEventsUrl : String,
    @SerializedName("type")
    val type : String,
    @SerializedName("site_admin")
    val siteAdmin : Boolean,
    @SerializedName("score")
    val score : Float
)
