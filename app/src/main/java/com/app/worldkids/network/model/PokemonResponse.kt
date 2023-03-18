package com.app.worldkids.network.model

import com.app.worldkids.model.ListMode
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
  val count: Int,
  val next: String?,
  val previous: String?,
  val results: List<ListMode>
)
