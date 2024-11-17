package com.umbrella.compose

/**
    Interop (от англ. interoperability)

    Activity
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
        setContent {
        //Compose element
        }
    }

    Fragment
    onCreateView():View {
    return ComposeView (requireContext()).apply {
        setContent {
        //Compose element
        }
    }
    }

*/

/**
    Так же существует interop между Compose и Android View.
    @Composable
    fun AndroidViewExample() {
    val context = LocalContext.current

    // Используем AndroidView для добавления стандартного Android View в Compose
    AndroidView(
        factory = { context ->
                Button(context).apply {
                text = "Нажми меня"
                setOnClickListener {
                Toast.makeText(context, "Кнопка нажата", Toast.LENGTH_SHORT).show()
                }
            }
        },
        modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
        )
    }
 * */

/**
 * Flow and LiveData
 * вызов функции collectAsState() возвращает mutableState Compose
 * При изменении StateFlow - меняется value у mutableState что за собой влечет перевызов Composable функции
 *
 * */