# -*- coding: utf-8 -*-
"""
@author: Octavio

"""

#pip install suds

from suds.client import Client

client = Client("http://localhost:8000/?wsdl")

result = client.service.suma(10, 8)

print(result)

