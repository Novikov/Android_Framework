package com.app.navigation.fragment_theory

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * A Fragment represents a reusable portion of your app's UI
 *
 * Фрагмент как и активити может находиться в разных состояниях
 *
 *  static final int INITIALIZING = -1;          // Not yet attached.
 *  static final int ATTACHED = 0;               // Attached to the host.
 *  static final int CREATED = 1;                // Created.
 *  static final int VIEW_CREATED = 2;           // View Created.
 *  static final int AWAITING_EXIT_EFFECTS = 3;  // Downward state, awaiting exit effects
 *  static final int ACTIVITY_CREATED = 4;       // Fully created, not started.
 *  static final int STARTED = 5;                // Created and started, not resumed.
 *  static final int AWAITING_ENTER_EFFECTS = 6; // Upward state, awaiting enter effects
 *  static final int RESUMED = 7;                // Created started and resumed.
 *
 *  Нужно думать о том что именно гарантируется в данном состоянии.
 *  Если состояние - Resumed, то все предыдущие состояния уже произошли.
 *
 *  Существуют удобные методы проверки состояния
 *  getActivity() != null
 *  isAdded()
 *  isDetached()
 *  isRemoving()
 *  getView() != null
 *
 *  Необходимо помнить что фрагмент нужно создавать не через конструктор, а фабричный метод и аргументы.
 *  Иначе при пересоздании фрагмента вызовется дефолтный конструктор без параметров
 *  И мы потеряем свойства которые вытягивали данные из аргументов
 * */
class FirstFragment : Fragment() {

    /**
     * Вызывается когда фрагмент привязывается к своей Activity. После вызова данного метода - становится доступным Activity.
     * Метод getActivity вернет не null.
     * В этом методе удобно проверять реализует ли Activity или parentFragment определенные интерфейсы.
     * */
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    /**
     * Данный метод нужен для того, чтобы первоначально инициализировать фрагмент, используя сохраненное состояние.
     * Сохранение состояния в bundle происходит в onSaveInstanceState()
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setRetainInstance(true) // Запрет пекресоздания задиприкейтили и намекают на испльзование ViewModel
    }

    /**
     * отвечает за создание и возврат корневого представления фрагмента. Этот метод вызывается после того,
     * как фрагмент был создан, но до того, как он будет добавлен в активность
     * */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Инфлейтируем XML-разметку по id контейнера если не используем ViewBinding
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /**
     * Данный метод вызывает сразу после onCreateView(). В нем нужно инициализировать view.
     * */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * View на экране, но пользователь не может с ним взаимодействовать.
     * */
    override fun onStart() {
        super.onStart()
    }

    /**
     * Полная аналогия с методами actitivy. Данный метод сигнализирует о том пользователь начал/заканчил взаимодействие с фрагментом.
     * */
    override fun onResume() {
        super.onResume()
    }

    /**
     * Фрагмент виден, но пользователь не может с ним взаимодействовать
     * */
    override fun onPause() {
        super.onPause()
    }

    /**
     * В данном методе нужно обнулить ссылки на view. Если не уничтожим то, будет утечка. Вообще желательно не деражать ссылки на View.
     * */
    override fun onDestroyView() {
        super.onDestroyView()
    }

    /**
     * Вызывается при уничтожении фрагмента. Так же как и у активити данный метод не гарантированно вызывается.
     * Поэтому не нужно в данном методе делать что то критично.
     * */
    override fun onDestroy() {
        super.onDestroy()
    }

    /**
     * Метод вызывается в момент открепления фрагмента от его Activity. После него getActivity() вернет null.
     * */
    override fun onDetach() {
        super.onDetach()
    }
}

/**
 * State loss (Глянь onStop у Template fragment и поэксперементируй)

    State Loss у фрагментов происходит, когда состояние фрагмента (например, данные, связанные с пользовательским интерфейсом или его состояние) теряется из-за того, что приложение переходит в фоновый режим или происходит изменение конфигурации (например, поворот экрана). Это может произойти, если вы пытаетесь выполнить транзакцию фрагмента после вызова метода onSaveInstanceState().

    Когда происходит State Loss
    State Loss может произойти, если:

    Вы пытаетесь выполнить транзакцию фрагмента после того, как активность была остановлена (например, во время изменения конфигурации).
    Вы вызываете commit() или commitAllowingStateLoss() после того, как активность вызвала onSaveInstanceState().
    Как воспроизвести State Loss
    Вот пример, как можно воспроизвести состояние потери:

    Запустите приложение и добавьте фрагмент.
    Поверните устройство (или выполните действие, которое вызывает изменение конфигурации).
    Попробуйте выполнить транзакцию фрагмента в методе, который вызывается после onSaveInstanceState(), например, в onStop() или onPause():

    override fun onStop() {
    super.onStop()
    supportFragmentManager.beginTransaction()
    .replace(R.id.fragment_container, NewFragment())
    .commit()  // Это может вызвать State Loss
    }
    В этом случае, если вы запустите приложение и выполните поворот экрана, система может вызвать onStop() и попытаться выполнить транзакцию фрагмента, что приведет к IllegalStateException, указывающему на потерю состояния.

    Как избежать State Loss
    Используйте commitAllowingStateLoss(): Если вы готовы проигнорировать потенциальную потерю состояния.
    Проверяйте состояние активности: Убедитесь, что активность все еще активна перед выполнением транзакции.
    Используйте ViewModel: Сохраняйте состояние фрагментов в ViewModel, чтобы минимизировать потерю данных при изменениях конфигурации.
    Таким образом, управление состоянием и его сохранение — ключ к предотвращению проблем с потерей состояния в приложениях на Android.

    Стараться не делать транзации в небезопастных методах onPause и onStop, а использовать безопастный onStart, onResume.
 * */