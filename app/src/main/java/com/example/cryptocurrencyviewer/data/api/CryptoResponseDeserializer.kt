package com.example.cryptocurrencyviewer.data.api

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class CryptoResponseDeserializer : JsonDeserializer<CryptoResponse> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): CryptoResponse {
        val dataList = mutableListOf<CryptoData>()
        val dataArray = json.asJsonObject.getAsJsonArray("Data")

        dataArray.forEach { dataElement ->
            val dataObject = dataElement.asJsonObject
            val coinInfoObject = dataObject.getAsJsonObject("CoinInfo")
            val displayObject = dataObject.getAsJsonObject("DISPLAY")

            if (displayObject != null) {
                val usdObject = displayObject.getAsJsonObject("USD")

                if (usdObject != null) {
                    val coinInfo = CoinInfo(
                        fullName = coinInfoObject.get("FullName").asString
                    )

                    val usd = Usd(
                        price = usdObject.get("PRICE").asString,
                        lastUpdate = parseLastUpdate(usdObject.get("LASTUPDATE")),
                        low24h = usdObject.get("LOW24HOUR").asString,
                        high24h = usdObject.get("HIGH24HOUR").asString
                    )

                    val display = Display(usd)
                    val cryptoData = CryptoData(coinInfo, display)
                    dataList.add(cryptoData)
                } else {
                    println("Error: USD object is null for data element: $dataElement")
                }
            } else {
                println("Error: DISPLAY object is null for data element: $dataElement")
            }
        }

        return CryptoResponse(dataList)
    }

    private fun parseLastUpdate(lastUpdateElement: JsonElement): Long {
        return try {
            if (lastUpdateElement.asString == "Just now") {
                System.currentTimeMillis() / 1000
            } else {
                lastUpdateElement.asLong
            }
        } catch (e: Exception) {
            System.currentTimeMillis() / 1000
        }
    }
}