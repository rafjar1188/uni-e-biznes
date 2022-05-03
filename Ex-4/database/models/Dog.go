package models

import "gorm.io/gorm"

type Dog struct {
	gorm.Model

	Name string
	Race string
}
