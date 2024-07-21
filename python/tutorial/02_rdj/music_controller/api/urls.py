# place URLs here
# copied from urls.py from music_controller

#from django.contrib import admin
from django.urls import path# , include
from .views import RoomView
#from .views import main

#urlpatterns = [
#    path('home',main) #add this to get to the api.urls.py
#]

urlpatterns = [
    path('room',RoomView.as_view()) #add this to get to the api.urls.py
]

