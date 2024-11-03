package com.app.data_transfer.websocket

/**
    WebSocket в контексте Android-разработки — это протокол, который обеспечивает постоянное и двунаправленное соединение между клиентом (например, приложением на Android) и сервером. Это позволяет обмениваться данными в реальном времени, что делает WebSocket отличным выбором для приложений, требующих мгновенных обновлений, таких как чаты, онлайн-игры или приложения для обмена сообщениями.

    Основные характеристики WebSocket:
    Двусторонняя связь: WebSocket позволяет как клиенту, так и серверу отправлять данные в любое время, что обеспечивает более интерактивный опыт пользователя.
    Низкая задержка: После установки соединения протокол обеспечивает минимальные накладные расходы, что позволяет быстро передавать данные.
    Постоянное соединение: WebSocket устанавливает одно соединение, которое остается открытым для обмена данными, в отличие от традиционных HTTP-запросов, где каждое взаимодействие требует нового соединения.
    Как работать с WebSocket в Android
    Для работы с WebSocket в Android можно использовать библиотеки, такие как OkHttp, которая упрощает создание и управление WebSocket-соединениями. Вот основные шаги по реализации WebSocket в Android:

    1. Добавьте зависимости
    Добавьте зависимость OkHttp в ваш build.gradle файл:

    implementation 'com.squareup.okhttp3:okhttp:4.9.3'
    2. Создайте WebSocket-клиент
    Вот пример того, как можно создать WebSocket-клиент с использованием OkHttp:

    import okhttp3.*
    import java.util.concurrent.TimeUnit

    class WebSocketClient {
    private val client: OkHttpClient = OkHttpClient.Builder()
    .pingInterval(30, TimeUnit.SECONDS)
    .build()

    private var webSocket: WebSocket? = null

    fun connect(url: String) {
    val request = Request.Builder()
    .url(url)
    .build()

    webSocket = client.newWebSocket(request, object : WebSocketListener() {
    override fun onOpen(webSocket: WebSocket, response: Response) {
    super.onOpen(webSocket, response)
    // Соединение установлено
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
    super.onMessage(webSocket, text)
    // Обработка входящего сообщения
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
    super.onFailure(webSocket, t, response)
    // Обработка ошибок
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
    super.onClosing(webSocket, code, reason)
    webSocket.close(1000, null) // Закрытие соединения
    }
    })
    }

    fun sendMessage(message: String) {
    webSocket?.send(message)
    }

    fun close() {
    webSocket?.close(1000, "Client disconnecting")
    }
    }

    3. Используйте WebSocket в вашем Activity или Fragment
    Пример использования WebSocket-клиента в Activity:

    class MainActivity : AppCompatActivity() {
    private lateinit var webSocketClient: WebSocketClient

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    webSocketClient = WebSocketClient()
    webSocketClient.connect("wss://example.com/websocket") // Замените на ваш URL

    // Пример отправки сообщения
    findViewById<Button>(R.id.sendButton).setOnClickListener {
    webSocketClient.sendMessage("Hello, Server!")
    }
    }

    override fun onDestroy() {
    super.onDestroy()
    webSocketClient.close() // Закрытие соединения
    }
    }
    Важные моменты
    Управление жизненным циклом: Обязательно управляйте соединением WebSocket в соответствии с жизненным циклом вашего Activity или Fragment, чтобы избежать утечек памяти.
    Обработка ошибок: Имейте в виду, что могут возникать ошибки соединения, поэтому важно их обрабатывать.
    Безопасность: Используйте wss:// для безопасных соединений WebSocket.
    Заключение
    WebSocket является мощным инструментом для реализации функциональности реального времени в Android-приложениях. С помощью таких библиотек, как OkHttp, разработчики могут легко устанавливать и управлять WebSocket-соединениями, создавая более интерактивные и отзывчивые приложения.
  * */