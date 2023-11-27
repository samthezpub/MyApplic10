package edu.itstep.myapplic10;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

// App.java
public class App extends Application {
    private static App app;
    private PhoneBookDB phoneBookDB;
    private ContactDAO contactDAO;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        phoneBookDB = Room.databaseBuilder(
                        this,
                        PhoneBookDB.class,
                        "PhoneBook"
                ).addMigrations(new Migration(1, 2) {
                    @Override
                    public void migrate(@NonNull SupportSQLiteDatabase database) {
                        database.execSQL("CREATE TABLE IF NOT EXISTS `Contact_new` " +
                                "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                "`full_name` TEXT, " +
                                "`phone_number` TEXT, " +
                                "`address` TEXT)"); // Поле теперь называется "address"

                        database.execSQL("INSERT INTO `Contact_new` " +
                                "(`id`, `full_name`, `phone_number`, `address`) " +
                                "SELECT `id`, `full_name`, `phone_number`, `address`.`city` FROM `Contact`"); // Используйте "adress.city" вместо "address"

                        database.execSQL("DROP TABLE `Contact`");

                        database.execSQL("ALTER TABLE `Contact_new` RENAME TO `Contact`");
                    }
                })  // Добавляем конвертер для Address
                .allowMainThreadQueries()
                .build();

        contactDAO = phoneBookDB.contactDAO();
    }

    public static App getInstance() {
        return app;
    }

    public ContactDAO getContactDAO() {
        return contactDAO;
    }
}
