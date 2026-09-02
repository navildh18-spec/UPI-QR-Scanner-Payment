package com.upi.qrscanner.utils

object UPIParser {

    /**
     * Parse UPI string from QR code
     * Format: upi://pay?pa=UPI_ID&pn=NAME&tn=NOTE&am=AMOUNT&tr=TRANSACTION_REF
     */
    fun parse(upiString: String): Map<String, String>? {
        return try {
            if (!upiString.startsWith("upi://")) {
                return null
            }

            val data = mutableMapOf<string, String>()
            val parts = upiString.replace("upi://pay?", "").split("&")

            for (part in parts) {
                val keyValue = part.split("=")
                if (keyValue.size == 2) {
                    val key = keyValue[0]
                    val value = keyValue[1]
                    data[key] = value
                }
            }

            if (data.containsKey("pa")) {
                data
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    fun getUPIId(data: Map<String, String>): String? = data["pa"]

    fun getPayeeName(data: Map<String, String>): String? = data["pn"]

    fun getTransactionNote(data: Map<String, String>): String? = data["tn"]

    fun getAmount(data: Map<String, String>): String? = data["am"]

    fun getTransactionRef(data: Map<String, String>): String? = data["tr"]
}
