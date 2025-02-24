Updated as 22022025
Extra code to read data from internal storage-

Before this mechanism -
/*public DBHandler(Context context){
        super(context, DB_NAME,null,DB_VERSION);
    }*/


After this mechanism -
    public DBHandler(Context context){
        super(context, getCustomDatabasePath(context),null,DB_VERSION);
    }

private static String getCustomDatabasePath(Context context) {
        File directory = context.getExternalFilesDir(null);
        String customDbPath = directory.getAbsolutePath() + "/participantdatabase.db";
        Log.d(TAG, "Custom Database Path: " + customDbPath);
        return customDbPath;
    }
