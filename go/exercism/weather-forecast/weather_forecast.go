// Package weather provides tools to calculate current weather conditions.
package weather

var (
	// CurrentCondition represents the current weather conditions of a location.
	CurrentCondition string
	// CurrentLocation represents the current location for a weather forecast.
	CurrentLocation string
)

// Forecast returns the current weather condition of a given city.
func Forecast(city, condition string) string {
	CurrentLocation, CurrentCondition = city, condition
	return CurrentLocation + " - current weather condition: " + CurrentCondition
}
