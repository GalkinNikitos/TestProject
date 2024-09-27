package com.example.messenger

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.messenger.databinding.FragmentSettings1Binding
import kotlinx.coroutines.launch
import java.io.File

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsFragment1 : Fragment() {

    private var _binding: FragmentSettings1Binding? = null
    private val binding get() = _binding!!

    private lateinit var themeSpinner: Spinner
    private lateinit var languageSpinner: Spinner
    private lateinit var notificationSpinner: Spinner
    private lateinit var fontSizeSpinner: Spinner


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSettings1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        themeSpinner = view.findViewById(R.id.themeSpinner)
        languageSpinner = view.findViewById(R.id.languageSpinner)
        notificationSpinner = view.findViewById(R.id.notificationSpinner)
        fontSizeSpinner = view.findViewById(R.id.fontSizeSpinner)

        val sharedPreferences = requireContext().getSharedPreferences("settings", Context.MODE_PRIVATE)

        binding.buttonSaveS1SH.setOnClickListener {

            val selectedTheme = themeSpinner.selectedItem.toString()
            val selectedLanguage = languageSpinner.selectedItem.toString()
            val selectedFontSize = fontSizeSpinner.selectedItem.toString()
            val selectedNotification = notificationSpinner.selectedItem.toString()

            val editor = sharedPreferences.edit()
            editor.putString("theme", selectedTheme)
            editor.putString("language", selectedLanguage)
            editor.putString("fontSize", selectedFontSize)
            editor.putString("notification", selectedNotification)
            editor.apply()

            Toast.makeText(requireContext(), "Настройки сохранены", Toast.LENGTH_SHORT).show()
        }

        binding.buttonSaveS1DS.setOnClickListener {

            val selectedTheme: String = themeSpinner.selectedItem.toString()
            val selectedLanguage: String = languageSpinner.selectedItem.toString()
            val selectedFontSize: String = fontSizeSpinner.selectedItem.toString()
            val selectedNotification: String = notificationSpinner.selectedItem.toString()

            lifecycleScope.launch {
                requireContext().dataStore.edit { preferences ->
                    preferences[stringPreferencesKey("theme")] = selectedTheme
                    preferences[stringPreferencesKey("language")] = selectedLanguage
                    preferences[stringPreferencesKey("fontSize")] = selectedFontSize
                    preferences[stringPreferencesKey("notification")] = selectedNotification
                }
            }
            Toast.makeText(requireContext(), "Настройки сохранены", Toast.LENGTH_SHORT).show()
        }

        binding.buttonReadS1DS.setOnClickListener {
            readValuesFromDataStore()
        }

        binding.buttonReadS1SH.setOnClickListener {
            readValuesFromSharedPreferences()
        }

        binding.buttonCheckFileInDocuments.setOnClickListener {
            val fileName = "heroes_6.txt"
            val exists = isFileExistsInDocuments(fileName)
            val message = if (exists) {
                "Файл $fileName присутствует в директории Documents"
            } else {
                "Файл $fileName отсутствует в директории Documents"
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

        binding.buttonDeleteFileFromDocuments.setOnClickListener {
            val fileName = "heroes_6.txt"
            val permission = Manifest.permission.WRITE_EXTERNAL_STORAGE
            if (isFileExistsInDocuments(fileName)) {
                if (ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED) {
                    saveFileToInternalStorage(fileName)
                    deleteFileFromDocuments(fileName, requireContext())
                    Toast.makeText(requireContext(), "Файл $fileName удален из директории Documents", Toast.LENGTH_SHORT).show()
                }
                else {
                    requestPermissions(arrayOf(permission), REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION)
                }
            } else {
                Toast.makeText(requireContext(),"Файл $fileName отсутствует в директории Documents", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonRecoverFileInDocuments.setOnClickListener {
            val fileName = "heroes_6.txt"
            val permission = Manifest.permission.WRITE_EXTERNAL_STORAGE
            if (isFileExistsInInternalStorage(fileName)) {
                if (ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED) {
                    restoreFileToDocuments(fileName)
                    Toast.makeText(requireContext(), "Файл $fileName восстановлен во внешнее хранилище", Toast.LENGTH_SHORT).show()
                } else {
                    requestPermissions(arrayOf(permission), REQUEST_ACCESS_EXTERNAL_STORAGE_PERMISSION)
                }
            } else {
                Toast.makeText(requireContext(), "Файл $fileName отсутствует во внутреннем хранилище", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readValuesFromSharedPreferences() {
        val sharedPreferences = requireContext().getSharedPreferences("settings", Context.MODE_PRIVATE)
        val savedTheme = sharedPreferences.getString("theme", "Светлая")
        val savedLanguage = sharedPreferences.getString("language", "Русский")
        val savedFontSize = sharedPreferences.getString("fontSize", "14")
        val savedNotification = sharedPreferences.getString("notification", "Выключены")

        val themeArray = resources.getStringArray(R.array.theme_array)
        val languageArray = resources.getStringArray(R.array.language_array)
        val fontArray = resources.getStringArray(R.array.font_size_array)
        val notificationArray = resources.getStringArray(R.array.notification_array)

        val defaultThemePosition = themeArray.indexOf("Светлая")
        val defaultLanguagePosition = languageArray.indexOf("Русский")
        val defaultFontSizePosition = fontArray.indexOf("14")
        val defaultNotificationPosition = notificationArray.indexOf("Выключены")

        themeSpinner.setSelection(defaultThemePosition)
        languageSpinner.setSelection(defaultLanguagePosition)
        fontSizeSpinner.setSelection(defaultFontSizePosition)
        notificationSpinner.setSelection(defaultNotificationPosition)

        themeSpinner.setSelection(themeArray.indexOf(savedTheme))
        languageSpinner.setSelection(languageArray.indexOf(savedLanguage))
        fontSizeSpinner.setSelection(fontArray.indexOf(savedFontSize))
        notificationSpinner.setSelection(notificationArray.indexOf(savedNotification))
    }

    private fun readValuesFromDataStore() {
        lifecycleScope.launch {
            val dataStore = requireContext().dataStore
            dataStore.data.collect { preferences ->
                val currentTheme = preferences[stringPreferencesKey("theme")]
                val currentLanguage = preferences[stringPreferencesKey("language")]
                val currentFontSize = preferences[stringPreferencesKey("font_size")]
                val currentNotification = preferences[stringPreferencesKey("notification")]

                val themeArray = resources.getStringArray(R.array.theme_array)
                val languageArray = resources.getStringArray(R.array.language_array)
                val fontArray = resources.getStringArray(R.array.font_size_array)
                val notificationArray = resources.getStringArray(R.array.notification_array)

                val defaultThemePosition = themeArray.indexOf("Светлая")
                val defaultLanguagePosition = languageArray.indexOf("Русский")
                val defaultFontSizePosition = fontArray.indexOf("14")
                val defaultNotificationPosition = notificationArray.indexOf("Выключены")

                themeSpinner.setSelection(defaultThemePosition)
                languageSpinner.setSelection(defaultLanguagePosition)
                fontSizeSpinner.setSelection(defaultFontSizePosition)
                notificationSpinner.setSelection(defaultNotificationPosition)

                themeSpinner.setSelection(themeArray.indexOf(currentTheme))
                languageSpinner.setSelection(languageArray.indexOf(currentLanguage))
                fontSizeSpinner.setSelection(fontArray.indexOf(currentFontSize))
                notificationSpinner.setSelection(notificationArray.indexOf(currentNotification))
            }
        }
    }
    private fun isFileExistsInDocuments(fileName: String): Boolean {
        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), fileName)
        return file.exists()
    }

    private fun deleteFileFromDocuments(fileName: String, context: Context) {
        val documentsDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val file = File(documentsDir, fileName)
        file.delete()
    }

    private fun saveFileToInternalStorage(fileName: String) {
        val externalDirectory = Environment.getExternalStorageDirectory()
        val externalFilePath = externalDirectory.absolutePath + File.separator + "Documents" + File.separator + fileName
        val externalFile = File(externalFilePath)

        val internalDirectory = requireContext().filesDir
        val internalFilePath = internalDirectory.absolutePath + File.separator + fileName
        val internalFile = File(internalFilePath)

        externalFile.copyTo(internalFile, overwrite = true)
    }

    private fun restoreFileToDocuments(fileName: String) {
        val externalDirectory = Environment.getExternalStorageDirectory()
        val externalFilePath = externalDirectory.absolutePath + File.separator + "Documents" + File.separator + fileName
        val externalFile = File(externalFilePath)

        val internalDirectory = requireContext().filesDir
        val internalFilePath = internalDirectory.absolutePath + File.separator + fileName
        val internalFile = File(internalFilePath)

        internalFile.copyTo(externalFile, overwrite = true)
    }

    private fun isFileExistsInInternalStorage(fileName: String): Boolean {
        val internalDirectory = requireContext().filesDir
        val internalFilePath = internalDirectory.absolutePath + File.separator + fileName
        val internalFile = File(internalFilePath)

        return internalFile.exists()
    }

    private companion object {
        const val REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION = 1
        const val REQUEST_ACCESS_EXTERNAL_STORAGE_PERMISSION = 2
    }

}