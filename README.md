# NUTRON

## Usecases definition

The following is a step by step description of a normal usage during a day.

### Setup personal configuration
The user configures:
* name
* profile pic
* age
* weight at date (history is stored)
* height
* activity level: Activity level can be selected among: sedentary, casual, active, athlete. This will modify the scaling of daily macros according to day type. 
* goals: Goals can be selected among: lose weight, maintain, increase weight. This will modify the overall daily calorie count by a factor.
* numer of meals per day: Daily nutrient targets will be spread among this number for meal suggestions. Default 5.
* time of meals of day: Each meal will be registered with a time of day. Default: even spread.
### Start of day
* The day is automatically started by the application at 00 hours local time.
* A day starts with 0 on all nutrient counts.
* A day can be of type sedentary, rest, easy training, hard training. This will scale the macro targets accordingly.
* A day is subdivided in periods given by the number of meals: for example, a 2500 calorie day with 5 meals is divided in 5 periods of 500 calorie (and so on with the other nutrients).
### Meal suggestions
* An hour before a configured meal time, a suggestion of meal will be done. The meal will be suggested to try to balance macros, cover for deficits and compensate excesses:
* for example, after a very protein heavy breakfast on a training day, it would be recommendable to have a significant carb portion, so maybe a dish of pasta will be recommended.
* ideally, all meals should contain a balance of the three macros, and the macros of the day should be spread evenly along the complete day.
* for example, starving yourself from morning to evening and then eating 70% of your calories at dinner is not sustainable.
### Create Ingredient(s) containing specific macro values
* Ingredients are stored in persistence
### Create Recipe using said Ingredients
* Recipe can also re-utilize previously created Ingredients
### Create a Meal based on the newly created Recipe or a previously existing one
* Creating a Meal represents that the user has ingested the meal, so it's nutrient values will be added to the daily count and approximate the user to the projected nutrient targets.
* After creating a meal, the user should get clear feedback on the quality of the meal ingested in terms of nutrient input.
* Nutrient input quality is calculated by the target nutrient input for the period: a meal that provides exactly the nutrient input required by the period is a perfect quality meal.
* Nutrient input quality is further decreased in case of **trash nutrients:** sugar/total carb ratio, satFat/total fat ratio, etc. So the least quality meal is one that provides the nutrients that you don't need, and also they are trash.
### End of day
* A day is ended after the last meal of the day is logged, or after 23:59:59
* An end of day summary is provided