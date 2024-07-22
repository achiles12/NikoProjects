# place URLs here
# copied from urls.py from music_controller

#from django.contrib import admin
from django.urls import path # , include
from .views import index

urlpatterns = [
    path('', index) #add this to get to the api.urls.py
]