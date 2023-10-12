#############################################################
# Contents from Categories.json is taken                    #
# and cleaned to form Bag of Words,                         #
# then each pattern of categories is taken                  #
# and trained for their corresponding tag as their output.  #
# Final neural network model is then saved in a file        #
#############################################################

import random
import json
import pickle
import numpy as np

import nltk
nltk.download('punkt')
nltk.download('wordnet')
from nltk.stem import WordNetLemmatizer


from tensorflow import keras
from keras.layers import Dense, Activation, Dropout
from keras.models import Sequential, load_model
from keras.optimizers import SGD
from keras.models import model_from_json

lemmatizer = WordNetLemmatizer()

intents = json.loads (open ('Categories.json').read())

words = []
classes = []
documents = []
ignoreCharacters = [ ',', '.', '!', '?' ] 

for intent in intents ['categories']:
    for pattern in intent ['patterns']:
        wordList = nltk.word_tokenize (pattern) #patterngulake tokenize kore list banacche
        words.extend (wordList)
        documents.append ( (wordList, intent ['tag']) )
        if intent ['tag'] not in classes:
            classes.append (intent ['tag'])

words = [ lemmatizer.lemmatize (word) for word in words if word not in ignoreCharacters] #same word er different form ke eksthe korlam
words = sorted (set (words))

classes = sorted ( set (classes))

pickle.dump (words, open ('words.pkl', 'wb'))
pickle.dump (classes, open ('classes.pkl', 'wb'))

training = []
outputEmpty = [0] * len(classes)
train_x = []
train_y = []
for document in documents:
    bag = []
    wordPatterns = document[0]
    wordPatterns = [lemmatizer.lemmatize (word.lower ()) for word in wordPatterns]
    for word in words:
        bag.append (1) if word in wordPatterns else bag.append (0)

    outputRow = list (outputEmpty)
    outputRow [classes.index (document[1])] = 1
    training.append ([bag, outputRow])
    train_x.append(bag)
    train_y.append(outputRow)

random.shuffle (training)
#training = np.array (training)

#train_x = list (training[:, 0])
#train_y = list (training[:, 1])

model = Sequential()
model.add (Dense (128, input_shape = (len (train_x[0]),), activation='relu'))
model.add (Dropout (0.5))
model.add (Dense (64, activation='relu'))
model.add (Dropout (0.5))
model.add (Dense (len (train_y[0]), activation='softmax'))

sgd = SGD (learning_rate = 0.01, weight_decay = 1e-6, momentum = 0.5, nesterov = True)

model.compile (loss = 'categorical_crossentropy', optimizer = sgd, metrics = ['accuracy'])

history = model.fit (np.array (train_x), np.array (train_y), epochs = 200, batch_size = 5, verbose = 1 )
model.save ('chatbot.h5', history)

json_model = model.to_json()                     #save the model architecture to JSON file
with open('config.json', 'w') as json_file:
    json_file.write(json_model)                  #saving the weights of the model
model.save_weights('weights.h5')                 #Model loss and accuracy
#loss,acc = model.evaluate(test_images,  test_labels, verbose=2)

print('Training Completed')
