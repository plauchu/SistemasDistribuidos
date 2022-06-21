from django.contrib import admin
from .models import Auto, Cliente
# Register your models here.

admin.site.register(Cliente, Auto)