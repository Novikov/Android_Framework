package com.app.other.security

/**
 * SSL/TLS Pinning — это механизм безопасности, который используется для защиты приложений от атак типа "человек посередине" (MITM). Он позволяет приложению проверять, что сертификат сервера, с которым оно соединяется, является тем, который ожидается. Это достигается путем "привязывания" (pinning) публичного ключа или сертификата к приложению.
 *
 * Как работает SSL/TLS Pinning?
 * Привязка сертификата: Приложение сохраняет (или "привязывает") публичный ключ или сертификат сервера.
 * Проверка при соединении: При установлении HTTPS-соединения приложение проверяет, совпадает ли сертификат, предоставленный сервером, с привязанным сертификатом. Если они совпадают, соединение устанавливается; если нет — соединение отклоняется.
 * Зачем использовать SSL/TLS Pinning?
 * Защита от MITM атак: Даже если злоумышленник скомпрометировал сертификат центра сертификации, приложение все равно будет проверять, что оно соединяется с правильным сервером.
 * Увеличение безопасности: Pinning помогает предотвратить утечку данных и атаки на конфиденциальность.
 * Реализация SSL/TLS Pinning в Android
 * В Android можно реализовать SSL/TLS Pinning несколькими способами, в зависимости от используемой библиотеки для сетевых запросов. Приведем пример реализации с использованием OkHttp и встроенной библиотеки HttpsURLConnection.
 *
 * 1. Использование OkHttp
 * // Подключите зависимость в build.gradle
 * implementation 'com.squareup.okhttp3:okhttp:4.9.0'
 *
 * // Реализация
 * import okhttp3.CertificatePinner;
 * import okhttp3.OkHttpClient;
 * import okhttp3.Request;
 * import okhttp3.Response;
 *
 * public class MyHttpClient {
 *     public OkHttpClient createClient() {
 *         // Создание экземпляра CertificatePinner
 *         CertificatePinner certificatePinner = new CertificatePinner.Builder()
 *                 .add("your.server.com", "sha256/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=") // Хэш публичного ключа
 *                 .build();
 *
 *         // Создание клиента
 *         OkHttpClient client = new OkHttpClient.Builder()
 *                 .certificatePinner(certificatePinner)
 *                 .build();
 *
 *         return client;
 *     }
 *
 *     public void makeRequest() {
 *         OkHttpClient client = createClient();
 *         Request request = new Request.Builder()
 *                 .url("https://your.server.com")
 *                 .build();
 *
 *         try (Response response = client.newCall(request).execute()) {
 *             // Обработка ответа
 *         } catch (IOException e) {
 *             // Обработка ошибки
 *         }
 *     }
 * }
 *
 * 2. Использование HttpsURLConnection
 * import javax.net.ssl.HttpsURLConnection;
 * import javax.net.ssl.SSLContext;
 * import javax.net.ssl.TrustManager;
 * import javax.net.ssl.X509TrustManager;
 *
 * public class MyHttpClient {
 *
 *     public void makeRequest() {
 *         try {
 *             // Установка SSLContext с вашим TrustManager
 *             SSLContext sslContext = SSLContext.getInstance("TLS");
 *             sslContext.init(null, new TrustManager[] { new X509TrustManager() {
 *                 public X509Certificate[] getAcceptedIssuers() { return null; }
 *                 public void checkClientTrusted(X509Certificate[] certs, String authType) {}
 *                 public void checkServerTrusted(X509Certificate[] certs, String authType) {
 *                     // Здесь можно проверить сертификаты
 *                 }
 *             }}, new SecureRandom());
 *
 *             HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
 *
 *             // Создание запроса
 *             URL url = new URL("https://your.server.com");
 *             HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
 *             connection.connect();
 *
 *             // Обработка ответа
 *         } catch (Exception e) {
 *             // Обработка ошибки
 *         }
 *     }
 * }
 *
 * */