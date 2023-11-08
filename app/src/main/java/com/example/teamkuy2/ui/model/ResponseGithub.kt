package com.example.teamkuy2.ui.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

data class ResponseGithub(
	val incomplate_results: Boolean,
	val responseGithub: MutableList<item>,
	val total_count: Int

) {
	@Parcelize
	@Entity(tableName = "user")
	data class item(
		@ColumnInfo(name = "avatar_url")
		val avatar_url: String,
		@PrimaryKey
		val id: Int,
		@ColumnInfo(name = "login")
		val login: String,
	) : Parcelable
//	data class ResponseGithubItem(
//		val avatar_url: String,
//		val events_url: String,
//		val followers_url: String,
//		val following_url: String,
//		val gists_url: String,
//		val gravatar_id: String,
//		val html_url: String,
//		val id: Int,
//		val login: String,
//		val node_id: String,
//		val organizations_url: String,
//		val received_events_url: String,
//		val repos_url: String,
//		val score: Double,
//		val site_admin: Boolean,
//		val starred_url: String,
//		val subscriptions_url: String,
//		val type: String,
//		val url: String
//	)
}