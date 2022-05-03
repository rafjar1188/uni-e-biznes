package database

import (
	"go-orm/database/models"
	"gorm.io/driver/sqlite"
	"gorm.io/gorm"
)

func Connect() *gorm.DB {
	db, err := gorm.Open(sqlite.Open("database.db"))
	if err != nil {
		panic("Database not working")
	}

	db.AutoMigrate(&models.User{})
	db.AutoMigrate(&models.CreditCard{})
	db.AutoMigrate(&models.Car{})
	db.AutoMigrate(&models.Dog{})
	db.AutoMigrate(&models.City{})

	return db
}
