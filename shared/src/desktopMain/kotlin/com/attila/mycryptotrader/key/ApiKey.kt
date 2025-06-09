package com.attila.mycryptotrader.key

import io.github.cdimascio.dotenv.dotenv

actual fun getApiKey() = dotenv()["API_KEY"]