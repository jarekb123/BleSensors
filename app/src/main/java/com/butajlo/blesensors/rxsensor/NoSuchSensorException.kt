package com.butajlo.blesensors.rxsensor

class NoSuchSensorException(sensorType: Int) : Exception("No such sensor with id == $sensorType")