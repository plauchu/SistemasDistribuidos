# -*- coding: utf-8 -*-
"""
@author: Octavio Gutierrez

"""
#pip install stomp

import stomp
import time

conn = stomp.Connection()
conn.connect("admin", "admin", wait=True)
conn.send(body="hi", destination="a_queue")
conn.disconnect()

class MyListener(stomp.ConnectionListener):
    def on_error(self, message):
        print("Received an error %s" % message)
    
    def on_message(self, message):
        print("Received a message %s" % message)

conn = stomp.Connection()
conn.set_listener("my_listener", MyListener())
conn.connect("admin", "admin", wait=True)
conn.subscribe(destination="a_queue", id=1, ack="auto")
time.sleep(2)
conn.disconnect()







