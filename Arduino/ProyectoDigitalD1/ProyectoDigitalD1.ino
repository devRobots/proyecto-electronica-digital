#include "FirebaseESP8266.h"
#include <ESP8266WiFi.h>

// CONFIGURACIONES DE LA RED WIFI
const String WIFI_SSID     = "redprueba";
const String WIFI_PASSWORD = "12345678";

// CONFIGURACIONES DE LA BASE DE DATOS
FirebaseData firebaseData;
#define FIREBASE_HOST "https://digital-3d127.firebaseio.com/"
#define FIREBASE_AUTH "MRo9cUWGD6479Pey8WvWEgg8l2lAh60BGgUoTIwa"

// CONFIGURACION DE LOS PUERTOS
#define PUERTA D12
#define ALARMA D13
#define MOVIMIENTO D14

// CONFIGURACION DEL PISO
const String piso = "piso4";

// VARIABLES
int sensor_magnetico;
int sensor_magnetico_anterior;
int sensor_movimiento;

void setup() {
  Serial.begin(115200);

  conectarARedWifi();
  conectarConBaseDatos();

  pinMode(ALARMA, OUTPUT);
  pinMode(PUERTA, INPUT);
  pinMode(MOVIMIENTO, INPUT);

  sensor_magnetico_anterior = 1;
}

void conectarARedWifi()
{
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("Connecting to Wi-Fi");

  while (WiFi.status() != WL_CONNECTED)
  {
    Serial.print(".");
    delay(300);
  }

  Serial.println();
  Serial.print("Connected with IP: ");
  Serial.println(WiFi.localIP());
  Serial.println();
}

void conectarConBaseDatos()
{
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
  Firebase.reconnectWiFi(true);

  //Set database read timeout to 1 minute (max 15 minutes)
  Firebase.setReadTimeout(firebaseData, 1000 * 60);
  //tiny, small, medium, large and unlimited.
  //Size and its write timeout e.g.
  //tiny (1s), small (10s), medium (30s) and large (60s).
  Firebase.setwriteSizeLimit(firebaseData, "tiny");
}

void escrituraDigitalBD(int entero, String path)
{
  bool error = Firebase.setInt(firebaseData, path, entero);
  mostrarInfo(firebaseData, error);
}

int lecturaDigitalBD(String path)
{
  bool error = Firebase.getDouble(firebaseData, path);
  mostrarInfo(firebaseData, error);
  return firebaseData.intData();
}

void mostrarInfo(FirebaseData &data, bool error)
{
  if (error != false)
  {
    Serial.println("PASSED");
    Serial.println("PATH: " + data.dataPath());
    Serial.println("TYPE: " + data.dataType());
    Serial.println("ETag: " + data.ETag());
    Serial.print("VALUE: ");
    Serial.println(data.doubleData());
    Serial.println("------------------------------------");
    Serial.println();
  }
  else
  {
    Serial.println("FAILED");
    Serial.println("REASON: " + data.errorReason());
    Serial.println("------------------------------------");
    Serial.println();
  }
}

void loop() {
  sensor_magnetico = digitalRead(PUERTA);
  if(sensor_magnetico != sensor_magnetico_anterior) {
    sensor_magnetico_anterior = sensor_magnetico;
    escrituraDigitalBD(sensor_magnetico, piso + "/magnetico");
  }
  
  sensor_movimiento = digitalRead(MOVIMIENTO);
  if(sensor_movimiento == HIGH) {
    escrituraDigitalBD(0, piso +"/movimiento");

    if(sensor_magnetico == HIGH) {
      escrituraDigitalBD(0, "alarma");
    }
  }

  int estado_alarma = lecturaDigitalBD("alarma");
  digitalWrite(ALARMA, 1 - estado_alarma);

  delay(250);
}
