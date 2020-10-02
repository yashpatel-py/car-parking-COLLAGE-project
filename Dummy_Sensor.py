from firebase import firebase
firebase = firebase.FirebaseApplication('https://iot-car-parking-system.firebaseio.com/') #this line is for the firebase connection
import time
while(True):
    for i in reversed(range(11)):
        print("currently the distance is =",i)
        if(i<5):
            result = firebase.put('user','PARKING','true')
        else:
            result = firebase.put('user','PARKING','false')
        print(result)
        time.sleep(1)
