package icu.bluedream.historior.backend.web

import de.jensklingenberg.ktorfit.Ktorfit

private val ktorfit = Ktorfit.Builder()
    .baseUrl("https://floor.huluxia.com/")
    .build()

val hlxApi = ktorfit.createRequests()