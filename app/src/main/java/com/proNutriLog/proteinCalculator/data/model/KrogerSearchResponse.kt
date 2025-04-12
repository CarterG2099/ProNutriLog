package com.proNutriLog.proteinCalculator.data.model

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("expires_in") val expiresIn: Int,
    @SerializedName("token_type") val tokenType: String
)


class KrogerLocationResponse {

}

@kotlinx.serialization.Serializable
data class KrogerProductResponse(val data: List<KrogerProduct>)

@kotlinx.serialization.Serializable
data class KrogerProduct(
    val productId: String,
    val productPageURI: String,
    val aisleLocations: List<AisleLocation> = emptyList(),
    val brand: String,
    val categories: List<String> = emptyList(),
    val countryOrigin: String,
    val description: String,
    val items: List<KrogerItem> = emptyList(),
    val itemInformation: ItemInformation? = null,
    val temperature: Temperature? = null,
    val images: List<KrogerImage> = emptyList(),
    val upc: String
)

@kotlinx.serialization.Serializable
data class AisleLocation(
    val bayNumber: String,
    val description: String,
    val number: String,
    val numberOfFacings: String,
    val sequenceNumber: String,
    val side: String,
    val shelfNumber: String,
    val shelfPositionInBay: String
)

@kotlinx.serialization.Serializable
data class KrogerItem(
    val itemId: String,
    val inventory: Inventory,
    val favorite: Boolean,
    val fulfillment: Fulfillment,
    val price: KrogerPrice,
    val nationalPrice: KrogerPrice,
    val size: String,
    val soldBy: String
)

@kotlinx.serialization.Serializable
data class Inventory(val stockLevel: String)

@kotlinx.serialization.Serializable
data class Fulfillment(
    val curbside: Boolean,
    val delivery: Boolean,
    val instore: Boolean,
    val shiptohome: Boolean
)

@kotlinx.serialization.Serializable
data class KrogerPrice(
    val regular: Double,
    val promo: Double,
    val regularPerUnitEstimate: Double,
    val promoPerUnitEstimate: Double
)

@kotlinx.serialization.Serializable
data class ItemInformation(
    val depth: String,
    val height: String,
    val width: String
)

@kotlinx.serialization.Serializable
data class Temperature(
    val indicator: String,
    val heatSensitive: Boolean
)

@kotlinx.serialization.Serializable
data class KrogerImage(
    val perspective: String,
    val default: Boolean,
    val sizes: List<ImageSize>
)

@kotlinx.serialization.Serializable
data class ImageSize(
    val id: String,
    val size: String,
    val url: String
)
