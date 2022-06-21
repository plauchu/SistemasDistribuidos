from django.db import models

# Create your models here.
class Cliente(models.Model):
    nombre = models.CharField(max_length=200)
    apellidos = models.CharField(max_length=200)
    genero = models.CharField(max_length=10)
    edad = models.IntegerField(default=0)
    estado = models.CharField(max_length=200)
    def __str__(self):
        resp = "Nombre: "+self.nombre
        resp += "\t|\tApellido: "+self.apellidos
        return resp

class Auto(models.Model):
    marca = models.CharField(max_length=200)
    modelo = models.IntegerField(default=0)
    placas = models.CharField(max_length=200)
    def __str__(self):
        resp = "Auto: "+self.marca
        resp += "\t|\tModelo: "+str(self.modelo)
        resp += "\t|\tPlacas: "+self.placas
        return resp
    def getCot(self):
        if(self.modelo < 2000):
            return 3000.50
        else:
            return 6001.75

