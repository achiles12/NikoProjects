from django.shortcuts import render
from rest_framework import generics
from .serializers import RoomSerializer
from .models import Room
#from django.http import HttpResponse

# Create your views here.
#def main(request):
#    return HttpResponse("<h1>hello world niko</h1><a href='https://www.google.com'>niko pogi</a>")

class RoomView(generics.ListAPIView): # this is a GET request CreateAPIView is a POST request
    queryset = Room.objects.all()
    serializer_class = RoomSerializer