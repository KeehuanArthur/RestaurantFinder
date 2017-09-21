# RestaurantFinder
Android App to find restaurants around your area. This project relies heavily on the Google Maps and Google Places APIs.

## Install instructions
Download this project from GitHub then open it in Android Studio. Then connect your Android Device to the computer and click on the run button at the top. Make sure that your device is in developer mode. When the device selection window comes up, select your device to run.

## Usage
On launch, the app will display a map with restaurant locations. When clicked, it should display the name of the restaurant. When you click the name, it opens a new Activity with more information about the restaurant.

The restaurants can also be seen in a list view using the tab on the bottom right. The items populating the list view is determined by the current location of the map. So if you change the location on the map, the items in the list view will change.

If there are no restaurants in your area, the app will display a toast at the bottom saying that it couldn't find any restaurants in your area.

The ImageView when you click on a restaurant is also a ViewPager. If there are more images given by Google for that location, you can scroll right or left to see more images.


## Testing
This app was tested on a Samsung Galaxy S6 running Android 7.0. The min SDK version is 21 (5.0 Lolipop)
