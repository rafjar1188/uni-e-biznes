package models

import "gorm.io/gorm"

type City struct {
	gorm.Model

	Name string
	lat  float64
	lng  float64
}
