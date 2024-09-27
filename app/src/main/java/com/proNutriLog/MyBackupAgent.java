package com.proNutriLog;

import android.app.backup.BackupAgentHelper;
import android.app.backup.SharedPreferencesBackupHelper;

public class MyBackupAgent extends BackupAgentHelper {
    static final String PREFS_BACKUP_KEY = "prefs_backup";

    @Override
    public void onCreate() {
        SharedPreferencesBackupHelper helper = new SharedPreferencesBackupHelper(this, "MyPrefs");
        addHelper(PREFS_BACKUP_KEY, helper);
    }
}

