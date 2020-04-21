package me.loterio.androidautobackup

import android.app.backup.BackupAgentHelper
import android.app.backup.SharedPreferencesBackupHelper

class MyBackupAgent: BackupAgentHelper() {
    override fun onCreate() {
        super.onCreate()
        val PREFS = getString(R.string.preference_key)
        val PREFS_BACKUP_KEY = "prefs"

        SharedPreferencesBackupHelper(this, PREFS).also {
            addHelper(PREFS_BACKUP_KEY, it)
        }
    }
}