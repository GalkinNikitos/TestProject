package com.example.messenger

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.messenger.databinding.SettingsFragmentBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.File

class SettingsFragment : Fragment() {
    private var _binding: SettingsFragmentBinding? = null
    private val binding get() = _binding!!
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSaveDS.setOnClickListener {
            lifecycleScope.launch {
                requireContext().saveDS(
                    binding.etSaveKey.text.toString(),
                    binding.etSaveValue.text.toString()
                )
            }
        }

        binding.btnReadDS.setOnClickListener {
            lifecycleScope.launch {
                val value = requireContext().readDS(binding.etReadkey.text.toString())
                binding.tvReadValue.text = value ?: "No value found"
            }
        }

        binding.btnSaveSP.setOnClickListener {
            requireContext().saveSP(
                binding.etSaveKey.text.toString(),
                binding.etSaveValue.text.toString()
            )
        }

        binding.btnReadSP.setOnClickListener {
            val value = requireContext().readSP(binding.etReadkey.text.toString())
            binding.tvReadValue.text = value ?: "No value found"
        }

        binding.buttonCheckFile.setOnClickListener {
            lifecycleScope.launch {
                val exists = checkFileExists("data_page_6_size_50.txt")
                binding.textViewResult.text = if(exists) {
                    "File exists"
                } else {
                    "File does not exist"
                }
            }
        }

        binding.buttonDeleteFile.setOnClickListener {
            saveFileToInternalStorage("data_page_6_size_50.txt")
            deleteFile("data_page_6_size_50.txt")
        }

        binding.buttonRecoverFile.setOnClickListener {
            recoverFileFromInternalStorage("data_page_6_size_50.txt")
        }
    }

    private suspend fun Context.saveDS(key: String, value: String) {
        val dataStoreKey = stringPreferencesKey(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    private suspend fun Context.readDS(key: String): String? {
        val dataStoreKey = stringPreferencesKey(key)
        val preferences = dataStore.data.first()
        return preferences.get(dataStoreKey)
    }

    private fun Context.saveSP(key: String, value: String) {
        val sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(key, value).apply()
    }

    private fun Context.readSP(key: String) : String? {
        val sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun checkFileExists(fileName: String): Boolean {
        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName)
        return file.exists()
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun saveFileToInternalStorage(fileName: String) {
        val externalFile = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName)
        if (externalFile.exists()) {
            val internalFile = File(requireContext().filesDir, fileName)
            externalFile.copyTo(internalFile, true)
            Toast.makeText(requireContext(), "Файл сохранен во внутреннем хранилище", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Файл не существует", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun deleteFile(fileName: String) {
        if (checkFileExists(fileName)) {
            val externalFile = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName)
            externalFile.delete()
            Toast.makeText(requireContext(), "Файл удален", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Файл не существует", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun recoverFileFromInternalStorage(fileName: String) {
        val internalFile = File(requireContext().filesDir, fileName)
        if (internalFile.exists()) {
            val externalFile = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName)
            internalFile.copyTo(externalFile, true)
            internalFile.delete()
            Toast.makeText(requireContext(), "Файл восстановлен в папку Download", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Файл не найден во внутреннем хранилище", Toast.LENGTH_SHORT).show()
        }
    }
}
