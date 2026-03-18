package com.example.systemghost.utils

import kotlin.random.Random

object IdentitySimulator {

    data class LocationGPS(val cityName: String, val latitude: Double, val longitude: Double)

    fun generateValidIMEI(): String {
        val baseImei = StringBuilder()
        for (i in 0 until 14) {
            baseImei.append(Random.nextInt(0, 10))
        }

        val checkDigit = calculateLuhnCheckDigit(baseImei.toString())
        return "$baseImei$checkDigit"
    }

    private fun calculateLuhnCheckDigit(imeiBase: String): Int {
        var sum = 0
        for (i in imeiBase.indices) {
            var digit = imeiBase[i].digitToInt()
            if (i % 2 != 0) {
                digit *= 2
                if (digit > 9) digit -= 9
            }
            sum += digit
        }
        val modulo = sum % 10
        return if (modulo == 0) 0 else 10 - modulo
    }

    fun generateRandomMAC(): String {
        val hexChars = "0123456789ABCDEF"
        return (1..6).joinToString(":") {
            "${hexChars.random()}${hexChars.random()}"
        }
    }

    fun getRandomGlobalLocation(): LocationGPS {
        return globalCities.random()
    }

    private val globalCities = listOf(
        LocationGPS("Tokyo", 35.6895, 139.6917),
        LocationGPS("New York", 40.7128, -74.0060),
        LocationGPS("London", 51.5074, -0.1278),
        LocationGPS("Paris", 48.8566, 2.3522),
        LocationGPS("Moscow", 55.7558, 37.6173),
        LocationGPS("Beijing", 39.9042, 116.4074),
        LocationGPS("Dubai", 25.2048, 55.2708),
        LocationGPS("Singapore", 1.3521, 103.8198),
        LocationGPS("Los Angeles", 34.0522, -118.2437),
        LocationGPS("Berlin", 52.5200, 13.4050),
        LocationGPS("Sydney", -33.8688, 151.2093),
        LocationGPS("Toronto", 43.6510, -79.3470),
        LocationGPS("Seoul", 37.5665, 126.9780),
        LocationGPS("Istanbul", 41.0082, 28.9784),
        LocationGPS("Mumbai", 19.0760, 72.8777),
        LocationGPS("São Paulo", -23.5505, -46.6333),
        LocationGPS("Mexico City", 19.4326, -99.1332),
        LocationGPS("Cairo", 30.0444, 31.2357),
        LocationGPS("Buenos Aires", -34.6037, -58.3816),
        LocationGPS("Rome", 41.9028, 12.4964),
        LocationGPS("Madrid", 40.4168, -3.7038),
        LocationGPS("Bangkok", 13.7563, 100.5018),
        LocationGPS("Hong Kong", 22.3193, 114.1694),
        LocationGPS("Shanghai", 31.2304, 121.4737),
        LocationGPS("Cape Town", -33.9249, 18.4241),
        LocationGPS("Johannesburg", -26.2041, 28.0473),
        LocationGPS("Rio de Janeiro", -22.9068, -43.1729),
        LocationGPS("Chicago", 41.8781, -87.6298),
        LocationGPS("Jakarta", -6.2088, 106.8456),
        LocationGPS("Kuala Lumpur", 3.1390, 101.6869),
        LocationGPS("Manila", 14.5995, 120.9842),
        LocationGPS("Lima", -12.0464, -77.0428),
        LocationGPS("Bogotá", 4.7110, -74.0721),
        LocationGPS("Riyadh", 24.7136, 46.6753),
        LocationGPS("Tehran", 35.6892, 51.3890),
        LocationGPS("Lagos", 6.5244, 3.3792),
        LocationGPS("Nairobi", -1.286389, 36.817223),
        LocationGPS("Amsterdam", 52.3676, 4.9041),
        LocationGPS("Vienna", 48.2082, 16.3738),
        LocationGPS("Stockholm", 59.3293, 18.0686),
        LocationGPS("Oslo", 59.9139, 10.7522),
        LocationGPS("Copenhagen", 55.6761, 12.5683),
        LocationGPS("Helsinki", 60.1695, 24.9354),
        LocationGPS("Athens", 37.9838, 23.7275),
        LocationGPS("Lisbon", 38.7223, -9.1393),
        LocationGPS("Dublin", 53.3498, -6.2603),
        LocationGPS("Warsaw", 52.2297, 21.0122),
        LocationGPS("Prague", 50.0755, 14.4378),
        LocationGPS("Budapest", 47.4979, 19.0402),
        LocationGPS("Zurich", 47.3769, 8.5417)
    )
}
