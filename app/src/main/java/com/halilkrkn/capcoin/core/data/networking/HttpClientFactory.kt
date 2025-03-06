package com.halilkrkn.capcoin.core.data.networking

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientFactory {

    fun create(engine: HttpClientEngine): HttpClient {
        return HttpClient(engine) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
               json(
                   json = Json {
                          ignoreUnknownKeys = true
                   }
               )
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
            }
        }
    }
}


/*
# HttpClientFactory Kod Analizi

Bu kod bloğu, Ktor kütüphanesini kullanarak HTTP istemcisi oluşturmak için bir fabrika (factory) tasarım desenini uygulayan bir yapıdır. İşte adım adım açıklaması:

## 1. Temel Yapı
- `HttpClientFactory` bir Kotlin `object`'i (singleton) olarak tanımlanmıştır.
- Bu fabrika, farklı HTTP istemci motorlarıyla çalışabilen özelleştirilmiş `HttpClient` nesneleri üretir.

## 2. create() Metodu
Bu metot bir `HttpClientEngine` parametresi alır ve aşağıdaki özelliklere sahip yapılandırılmış bir `HttpClient` döndürür:

### Eklenen Özellikler:

#### a. Logging
```
install(Logging) {
    logger = Logger.DEFAULT
    level = LogLevel.ALL
}
```
- Tüm HTTP isteklerini ve yanıtlarını loglar
- `LogLevel.ALL` ile tüm detaylar (istek gövdesi, başlıklar, yanıtlar) loglanır

#### b. ContentNegotiation
```
install(ContentNegotiation) {
   json(
       json = Json {
              ignoreUnknownKeys = true
       }
   )
}
```
- JSON verilerinin otomatik serileştirilmesi/deserileştirilmesi için gerekli yapılandırma
- `ignoreUnknownKeys = true` ayarı, API'den gelen bilinmeyen alanları yok sayarak model eşleşmelerinde esneklik sağlar

#### c. Default İstek Ayarları
```
defaultRequest {
    contentType(ContentType.Application.Json)
}
```
- Tüm isteklere varsayılan olarak `Content-Type: application/json` başlığı eklenir

Bu fabrika sayesinde uygulama genelinde tutarlı yapılandırılmış HTTP istemcileri oluşturulabilir ve kod tekrarından kaçınılır.
 */