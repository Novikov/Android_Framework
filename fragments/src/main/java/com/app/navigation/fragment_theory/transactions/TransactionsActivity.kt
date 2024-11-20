
package com.app.navigation.fragment_theory.transactions

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.app.navigation.R
import com.app.navigation.databinding.ActivityTransactionsBinding

/**
 * Добавление фрагментов
 * 1. commit()
 * Этот метод добавляет транзакцию в очередь и выполняет её асинхронно. Фрагменты могут не обновляться сразу,
 * так как выполнение происходит после завершения текущего цикла обработки событий.
 *
 * 2. commitNow()
 * В отличие от commit(), этот метод синхронно выполняет транзакцию, и изменения будут видны сразу же.
 * Он полезен, когда нужно мгновенно обновить пользовательский интерфейс.
 *
 * 3. commitAllowingStateLoss()
 * Этот метод работает аналогично commit(), но не вызывает исключение, если состояние теряется
 * (например, если транзакция выполняется после вызова onSaveInstanceState()). Это может быть полезно, но требует осторожности,
 * так как может привести к потере данных.
 *
 * 4. commitNowAllowingStateLoss()
 * Этот метод объединяет возможности commitNow() и commitAllowingStateLoss(). Он синхронно выполняет транзакцию, но при этом
 * игнорирует возможную потерю состояния.
 *
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 * Удаление фрагментов
 *
 * Методы для управления стеком фрагментов
 * popBackStack()
 *
 * Асинхронный: Удаляет верхний фрагмент из стека.
 * popBackStack(String name, int flags)
 *
 * Синхронный: Удаляет все фрагменты до указанного фрагмента.
 * popBackStackImmediate()
 *
 * Синхронный: Немедленно удаляет верхний фрагмент из стека.
 * popBackStackImmediate(String name, int flags)
 *
 * Синхронный: Немедленно удаляет все фрагменты до указанного.
 * remove(Fragment fragment)
 *
 * Асинхронный: Удаляет конкретный фрагмент, но нужно использовать в транзакции и вызвать commit().
 * backStackEntryCount
 *
 * Возвращает количество записей в стеке (не требует синхронизации).
 *
 * */

class TransactionsActivity : AppCompatActivity() {

    lateinit var binding: ActivityTransactionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val templateFragmentAdapter = TemplateFragmentAdapter()

        //Слушаем бэкстек
        binding.backStackCountTextView.text = supportFragmentManager.backStackEntryCount.toString()

        /**
         * Кнопка назад работать не будет, фрагмент ляжет в контейнер
         * */
        binding.addButton.setOnClickListener {
            supportFragmentManager.commit{
                setReorderingAllowed(true) //Выключение методов жизненого цикла тех фрагментов которые немедленно д=обавляются и затем удаляются
                add(R.id.fragment_container, templateFragmentAdapter.createFragment())
            }
            /** Старый синтаксис*/
//            supportFragmentManager.beginTransaction()
//                .add(R.id.fragment_container, templateFragmentAdapter.createFragment())
//                .commit()
        }

        /** При добавлении фрагмента бэк стек будет учитывать кнопку назад*/
        binding.addToBackStackButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, templateFragmentAdapter.createFragment())
                .addToBackStack(TAG)
                .commit()
        }

        /** Replace это remove для всех транзакций которые были добавлены до + одна add
         * Проверь это на кнопках add и replace
         * Набор фрагментов будет уничтожаться.
         * */
        binding.replaceButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, templateFragmentAdapter.createFragment())
                .commit()
        }

        /**
         * Replace + B тоже самое что и выше
         * Набор фрагментов уничтожится и новый фрагмент ляжет последним, но при нажатии кнопки назад старый набор восстановится
         * */
        binding.replaceAndAddToBackStackButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, templateFragmentAdapter.createFragment())
                .addToBackStack(null)
                .commit()
        }

        /**
         * Уничтожит верхний фрагмент стека без изменения стека
         * */
        binding.removeButton.setOnClickListener {
            val currentFragment = supportFragmentManager.fragments.first()
            supportFragmentManager.beginTransaction().remove(currentFragment).commit()
        }

        /**
         * Положит в backStack null
         * */
        binding.nullToBackstackButton.setOnClickListener {
            supportFragmentManager.beginTransaction().addToBackStack(null)
                .commit()
        }

        /**
         * Скрытие верхнего фрагмента
         * */
        binding.hideButton.setOnClickListener {
            val currentFragment = supportFragmentManager.fragments.first()
            supportFragmentManager.beginTransaction()
                .hide(currentFragment)
                .commit()
        }

        /**
         * Показ верхнего фрагмента
         * */
        binding.showButton.setOnClickListener {
            val currentFragment = supportFragmentManager.fragments.first()
            supportFragmentManager.beginTransaction()
                .show(currentFragment)
                .commit()
        }

        binding.popButton.setOnClickListener {
            supportFragmentManager.popBackStack()
        }

        binding.popImmediate.setOnClickListener {
            val res = supportFragmentManager.popBackStackImmediate()
            Log.i(TAG, "$res")
        }

        /**
         * Выгрузка стэка
         *
         * Есть вот такой способ
         * val fragmentManager = supportFragmentManager
         * while (fragmentManager.backStackEntryCount > 0) {
         *     fragmentManager.popBackStack()
         * }
         *
         * Ниже способ по тэгу
         * */
        binding.popAll.setOnClickListener {
            val res = supportFragmentManager.popBackStack(TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            Log.i(TAG, "$res")
        }

        supportFragmentManager.addOnBackStackChangedListener {
            binding.backStackCountTextView.text =
                supportFragmentManager.backStackEntryCount.toString()
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}