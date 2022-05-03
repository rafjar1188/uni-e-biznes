package main

import (
	"github.com/labstack/echo/v4"
	"go-orm/database"
	"go-orm/database/models"
	"net/http"
)

func main() {
	db := database.Connect()
	e := echo.New()

	e.GET("/user", func(c echo.Context) error {
		var users []models.User
		db.Preload("CreditCards").Find(&users)
		return c.JSON(http.StatusOK, users)
	})

	e.POST("/user", func(c echo.Context) error {
		car := new(models.User)
		if err := c.Bind(car); err != nil {
			return err
		}
		db.Omit("CreditCards").Create(&car)
		return c.String(http.StatusCreated, "Created")
	})

	e.GET("/card", func(c echo.Context) error {
		var homes []models.CreditCard
		db.Find(&homes)
		return c.JSON(http.StatusOK, homes)
	})

	e.POST("/card", func(c echo.Context) error {
		home := new(models.CreditCard)
		if err := c.Bind(home); err != nil {
			return err
		}
		db.Create(&home)
		return c.String(http.StatusCreated, "Created")
	})

	e.GET("/car", func(c echo.Context) error {
		var cars []models.Car
		db.Find(&cars)
		return c.JSON(http.StatusOK, cars)
	})

	e.POST("/car", func(c echo.Context) error {
		car := new(models.Car)
		if err := c.Bind(car); err != nil {
			return err
		}
		db.Create(&car)
		return c.String(http.StatusCreated, "Created")
	})

	e.GET("/dog", func(c echo.Context) error {
		var dogs []models.Dog
		db.Find(&dogs)
		return c.JSON(http.StatusOK, dogs)
	})

	e.POST("/dog", func(c echo.Context) error {
		dog := new(models.Dog)
		if err := c.Bind(dog); err != nil {
			return err
		}
		db.Create(&dog)
		return c.String(http.StatusCreated, "Created")
	})

	e.GET("/city", func(c echo.Context) error {
		var cities []models.City
		db.Find(&cities)
		return c.JSON(http.StatusOK, cities)
	})

	e.POST("/city", func(c echo.Context) error {
		city := new(models.City)
		if err := c.Bind(city); err != nil {
			return err
		}
		db.Create(&city)
		return c.String(http.StatusCreated, "Created")
	})

	e.Start(":8080")
}
