from datetime import *
from math import *
import pyrebase

allRecurringSymptoms = ['watery diarrhea']
allNonRecurringSymptoms = ['abdominal cramping','abdominal tenderness','abdominal pain','rapid heart rate','fever','blood in stool','pus in stool', 'nausea', 'dehydration','loss of appetite','weight loss','swollen abdomen','kidney failure','increased white blood cell count']

C_DifficileSymptoms = ['watery diarrhea', 'abdominal cramping', 'abdominal tenderness', 'abdominal pain', 'rapid heart rate', 'fever', 'blood in stool', 'pus in stool', 'nausea', 'dehydration', 'loss of appetite', 'weight loss', 'swollen abdomen', 'kidney failure', 'increased white blood cell count']

E_ColiSymptoms = ['diarrhea','abdominal pain', 'abdonminal cramping', 'abdominal tenderness','nausea','vomiting']



config = {
  "apiKey": "AIzaSyDROdrGq8dmJe1BdbSXMrV54i_84EQhroc",
  "authDomain": "patientiva.firebaseapp.com",
  "databaseURL": "https://patientiva.firebaseio.com",
  "storageBucket": "patientiva.appspot.com"
}

firebase = pyrebase.initialize_app(config)

def getSymptom(patient):
    symptoms = firebase.database().child('patients').child(patient).child('symptoms').get().val()
    return symptoms

{
  0: ['diarrhea','nausea','vomiting'],
  12: ['abdominal cramping'],
  -14: ['diarrhea','vomiting']
}


def ProbabilityOfC_Difficile(patient):
    lastTwo = updatedLog[0]+updatedLog[1]
    allSymptomsFound = updatedLog[:]
    symptomCount = 1
    dick=getSymptom(patient)
    for key in dick:
      if key<0:
        updatedlog.remove(dick[key])
      else:
        updatedlog.append(len(list(set(dick).intersection(updatedlog))))
    if lastTwo.count('watery diarrhea') >= 1 or ('abdominal tenderness' in allSymptomsFound or 'mild abdominal cramping' in allSymptomsFound):
        if lastTwo.count('watery diarrhea') >= 1 and ('abdominal tenderness' in allSymptomsFound or 'mild abdominal cramping' in allSymptomsFound):
            symptomCount += 1
        if (7 <= allSymptomsFound.count('watery diarrhea')/len(allSymptomsFound)) <= 15:
            symptomCount += 1
        for symptom in C_DifficileSymptoms[3:]:
            if symptom in allSymptomsFound:
                symptomCount += 1
    return min(2- 2*symptomCount/len(C_DifficileSymptoms),1)


def ProbabilityOfStaph(patient):
    lastTwo = updatedLog[0]+updatedLog[1]
    allSymptomsFound = updatedLog[:]
    symptomCount = 1
    dick=getSymptom(patient)
    for key in dick:
      if key<0:
        updatedlog.remove(dick[key])
      else:
        updatedlog.append(len(list(set(dick).intersection(updatedlog))))
    #there are 4 parts that staph infection can be depending on where the infection took place. These are skin, Food Poisoning, Toxic Shock, Septic arthritis.
    # we calculate these individually, then take the maximum probability of each to determine the final probability

    allP = []
    #probability for skin
    skinCounter = 0
    for symptom in staphSymptoms_skin:
        if symptom in allSymptomsFound:
            skinCounter += 1
    allP.append(skinCounter/4)
    #for food
    foodCounter = 0
    for symptom in staphSymptoms_food:
        if symptom in allSymptomsFound:
            foodCounter += 1
    allP.append(foodCounter/5)
    #for tss
    TSSCounter = 0
    for symptom in staphSymptoms_TSS:
        if symptom in allSymptomsFound:
            TSSCounter += 1
    allP.append(TSSCounter/5)
    #for septic arthritis
    SACounter = 0
    for symptom in staphSymptoms_SA:
        if symptom in allSymptomsFound:
            SACounter += 1
    allP.append(SACounter/5)

    return max(allP)/2
def ProbabilityOfKlebsiella(patient):
    klebsiellasymptoms=["fever","chills","coughing","yellow or bloody mucus","shortness of breath","chest pain"]
    lastTwo = updatedLog[0]+updatedLog[1]
    allSymptomsFound = updatedLog[:]
    symptomCount = 1
    dick=getSymptom(patient)
    for key in dick:
      if key<0:
        updatedlog.remove(dick[key])
      else:
        updatedlog.append(len(list(set(dick).intersection(updatedlog))))
    return len(list(set(updatedlog).intersection(klebsiellasymptoms)))/6
def ProbabilityOfE_Coli(patient):
    lastTwo = updatedLog[0]+updatedLog[1]
    allSymptomsFound = updatedLog[:]
    symptomCount = 1
    dick=getSymptom(patient)
    for key in dick:
      if key<0:
        updatedlog.remove(dick[key])
      else:
        updatedlog.append(len(list(set(dick).intersection(updatedlog))))

    pCount = 0
    for s in E_ColiSymptoms:
        if s in updatedLog:
            pCount += 1

    return pCount/len(E_ColiSymptoms)/5

allPatientIds = firebase.database().child('patients').get().val()
allPatientPositions = {} #<-- this is the dictionary
for ID in allPatientIds:
  allPatientPositions[ID] = firebase.database().child('patients').child(ID).get().val()['patientRoom']

patient=[[[1,0,0,0,0],[2,0,0,0,0],[3,0,0,0,0]],[[4,0,0,0,0],[5,0,0,0,0],[6,0,0,0,0]],[[7,0,0,0,0],[8,0,0,0,0],[9,0,0,0,0]]]
#first is c_difficile, second staph, third klebsiella, fourth e. coli
while True:
    for i in range(3):
        for j in range(3):
            ree=0
            for key in allPatientPositions:
                if allPatientPositions[key]==patient[i][j][0]:
                  ree=key
            patient[i][j][1]+=ProbabilityOfC_Difficile(ree)
            for x in range(3):
                for y in range(3):
                    patient[x][y][1]+=0.05**(sqrt((i-x)^2+(j-y)^2))
    for i in range(3):
        for j in range(3):
            ree=0
            for key in allPatientPositions:
                if allPatientPositions[key]==patient[i][j][0]:
                  ree=key
            patient[i][j][2]+=ProbabilityOfStaph(ree)
            for x in range(3):
                for y in range(3):
                    patient[x][y][2]+=0.15**sqrt((i-x)^2+(j-y)^2)
    for i in range(3):
        for j in range(3):
            ree=0
            for key in allPatientPositions:
                if allPatientPositions[key]==patient[i][j][0]:
                  ree=key
            patient[i][j][3]+=ProbabilityOfKlebsiella(ree)
            for x in range(3):
                for y in range(3):
                    patient[x][y][3]+=0.15**sqrt((i-x)^2+(j-y)^2)
    for i in range(3):
        for j in range(3):
            ree=0
            for key in allPatientPositions:
                if allPatientPositions[key]==patient[i][j][0]:
                  ree=key
            patient[i][j][4]+=ProbabilityOfE_Coli(ree)
            for x in range(3):
                for y in range(3):
                    patient[x][y][4]+=0.05**sqrt((i-x)^2+(j-y)^2)
    for i in range(3):
        for j in range(3):
            if patient[i][j][1] > 1:
                patient[i][j][1]=1
            if patient[i][j][2] > 1:
                patient[i][j][2]=1
            if patient[i][j][3] > 1:
                patient[i][j][3]=1
            if patient[i][j][4] > 1:
                patient[i][j][4]=1
for ID in allPatientIds:
  room = allPatientPositions[ID]
  room,cd,s,k,ec = patient[room-1]
  pastID = firebase.database().child('patients').child(ID).get().val()
  pastID['C_Difficile'] = cd
  pastID['Staph'] = s
  pastID['Klebstriella'] = k
  pastID['E_Coli'] = ec
  firebase.database().child('patients').child(ID).set(pastID)