from email import generator
from errno import EADDRNOTAVAIL
from mailbox import NoSuchMailboxError
from urllib import request
from django.shortcuts import render
from django.http import HttpResponse
from .models import Auto, Cliente

# Create your views here.
def index(request):
    return render(request, "appSeguros/index.html", {})

def agrega_cliente(request):
    nombre = request.POST.get("nombre")
    apellidos = request.POST.get("apellidos")
    genero = request.POST.get("genero")
    edad = int(request.POST.get("edad"))
    estado = request.POST.get("estado")
    cliente = Cliente(nombre = nombre,
                    apellidos = apellidos,
                    genero = genero,
                    edad = edad,
                    estado = estado)    
    cliente.save()
    return render(request, 'appSeguros/cotizacion.html',{'cliente': cliente})

def agrega_auto(request):
    marca = request.POST.get("marca")
    modelo = int(request.POST.get("modelo"))
    placas = request.POST.get("placas")
    auto = Auto(marca = marca,
                modelo = modelo,
                placas = placas)
    auto.save()
    return render(request, 'appSeguros/cotizacion.html', {'auto': auto})

def listado_clientes(request):
    clientes = Cliente.objects.order_by('nombre')
    context = {
        'clientes': clientes,
    }
    return render(request, "appSeguros/cotizacion.html", context)