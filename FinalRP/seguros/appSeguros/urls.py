#from django.contrib import admin
from django.urls import path

from . import views

app_name = 'appSeguros'

urlpatterns = [
    path('', views.index,name="index"),
    path('auto', views.auto,name="auto"),
    path('cotizacion', views.cotizacion, name = "cotizacion"),
]