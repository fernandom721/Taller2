package com.naldana.ejemplo10

class Coin {
    var country: String? = null
    var image: Int? = null
    var value:Int? = null
    var name:String? = null

    constructor(country: String, image: Int, value: Int, name: String) {
        this.country = country
        this.image = image
        this.value = value
        this.name = name
    }
}