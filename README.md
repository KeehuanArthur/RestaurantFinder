# RestaurantFinder
This is an Android App that finds restaurants around your area. This project relies heavily on the Google Maps and Google Places APIs and requires location permissions.

## Install instructions
First, download this project from GitHub then open it in Android Studio. Then connect your Android Device to the computer and click on the run button at the top. Make sure that your device is in developer mode. When the device selection window comes up, select your device to run the application. This should automatically download the app onto your device.

## Usage
On launch, the app will display a map with icons where restaurants are. When and icon is clicked, the name of the restaurant should pop up next to the icon. Clicking on the name will open a new Activity with more information about the restaurant.

The restaurants can also be seen in a list view using the tab on the bottom right. The list of items populating the list view is determined by the current location of the map. So if you scroll to a different location on the map, the items in the list view will change.

If there are no restaurants in your area, the app will display a toast at the bottom of the screen saying that it couldn't find any restaurants.

The ImageView in the detailed view of a single restaurant is also a ViewPager. If there are more images given by Google for that location, you can scroll right or left to see more images.


## Testing
This app was tested on a Samsung Galaxy S6 running Android 7.0. The min SDK version is 21 (5.0 Lolipop)
