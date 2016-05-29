#include <SoftwareSerial.h>
SoftwareSerial BTSerial(2,3);

int ps_Pin[] = {A0,A1,A2,A3};
int ps_Value[] = {0,0,0,0};
int i;
int default_Value[]={0,0,0,0};
int cnt_time[] = {0,0};

void setup() {
  Serial.begin(9600);
  Serial.println("Hello!");
  BTSerial.begin(9600);
  for(i=0;i<5;i++)
  {
    default_Value[0] += analogRead(ps_Pin[0]);
    default_Value[1] += analogRead(ps_Pin[1]);
    default_Value[2] += analogRead(ps_Pin[2]);
    default_Value[3] += analogRead(ps_Pin[3]);
    delay(1000);
  }
  for(i=0;i<4;i++) {
    default_Value[i] = default_Value[i]/5;
  }
    
}

void loop() {
  
  if (BTSerial.available()) {
    Serial.write(BTSerial.read());
  }
  if (Serial.available()) {
    BTSerial.write(Serial.read());
  }
  
  ps_Value[0] = analogRead(ps_Pin[0]);
  ps_Value[1] = analogRead(ps_Pin[1]);
  ps_Value[2] = analogRead(ps_Pin[2]);
  ps_Value[3] = analogRead(ps_Pin[3]);
  Serial.print("A1 : ");
  Serial.println(ps_Value[0]);
  Serial.print("A2 : ");
  Serial.println(ps_Value[1]);
  Serial.print("A3 : ");
  Serial.println(ps_Value[2]);
  Serial.print("A4 : ");
  Serial.println(ps_Value[3]);
  Serial.println();
  /*
  BTSerial.print(ps_Value1);
  delay(1000);
  */
  if(default_Value[0] > ps_Value[0]+100) {
    cnt_time[0] += 1;
    if(cnt_time[0]>=3) {
      Serial.println("Left Leg");
    }
  }
  else {
    cnt_time[0]=0;
  }
  if(default_Value[1] > ps_Value[1]+100) {
    cnt_time[1] += 1;
    if(cnt_time[1]>=3) {
      Serial.println("Right Leg");
    }
  }
  else {
    cnt_time[1]=0;
  }
  delay(1000);
}
