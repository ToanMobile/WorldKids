package com.app.ui.card

import com.app.domain.model.Card
import com.app.domain.model.RarityEnum
import com.app.domain.model.TypeEnum

object ConstantPreviewCard {

    val CARD_LIST = listOf(
        Card(
            "Charizard",
            "Dracaufeu",
            "リザードン",
            TypeEnum.FIRE,
            RarityEnum.RARE_HOLO,
            6,
            "https://lopspower.github.io/poke/img/BaseSet16.webp",
            "Mitsuhiro Arita",
            "https://bulbapedia.bulbagarden.net/wiki/Charizard_(Base_Set_4)",
            16,
            true
        ),
        Card(
            "Bulbasaur",
            "Bulbizarre",
            "フシギダネ",
            TypeEnum.GRASS,
            RarityEnum.COMMON,
            1,
            "https://lopspower.github.io/poke/img/BaseSet1.webp",
            "Mitsuhiro Arita",
            "https://bulbapedia.bulbagarden.net/wiki/Bulbasaur_(Base_Set_44)",
            1,
            false
        )
    )

    val CARD = CARD_LIST[0]
}
