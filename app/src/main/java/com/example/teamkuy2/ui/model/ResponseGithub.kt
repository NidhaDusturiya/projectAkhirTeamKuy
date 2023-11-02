package com.example.teamkuy2.ui.model

data class ResponseGithub(
	val incomplate_results: Boolean,
	val responseGithub: MutableList<ResponseGithubItem>,
	val total_count: Int

) {
	data class ResponseGithubItem(
		val gistsUrl: String? = null,
		val reposUrl: String? = null,
		val followingUrl: String? = null,
		val starredUrl: String? = null,
		val login: String? = null,
		val followersUrl: String? = null,
		val type: String? = null,
		val url: String? = null,
		val subscriptionsUrl: String? = null,
		val receivedEventsUrl: String? = null,
		val avatarUrl: String? = null,
		val eventsUrl: String? = null,
		val htmlUrl: String? = null,
		val siteAdmin: Boolean? = null,
		val id: Int? = null,
		val gravatarId: String? = null,
		val nodeId: String? = null,
		val organizationsUrl: String? = null
	)
}