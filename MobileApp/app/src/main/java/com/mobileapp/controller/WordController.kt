package com.mobileapp.controller

class WordController {
    var words =
        mutableMapOf<String, String>(
            "entity" to "организация", "work out" to "решить", "congestion" to "затор",
            "vendor" to "продавец", "threat" to "угроза", "engage" to "обручаться",
            "ban" to "запрет", "appreciate" to "ценить", "offend" to "обижать",
            "guilt" to "вина", "hurt" to "ранить", "merry" to "радостный",
            "scared" to "напуганный", "angry" to "злой", "unhappy" to "несчастный",
            "optimism" to "оптимизм", "desire" to "желание", "fear" to "страх",
            "gift" to "подарок", "cake" to "торт", "delivery" to "доставка",
            "entity" to "организация", "terrible" to "ужасный", "be" to "быть",
            "occasion" to "возможность", "pie" to "пирог", "vase" to "ваза",
            "suit" to "костюм", "candy" to "конфета", "sweet" to "сладкий",
            "earning" to "серьга", "ring" to "кольцо", "tie" to "галстук"
        )

    fun addToWordsMap(key: String, value: String) {
        words[key] = value
    }

}