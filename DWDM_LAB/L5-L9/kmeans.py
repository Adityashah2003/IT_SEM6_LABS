import numpy as np
import math
import pandas as pd
from operator import itemgetter

data = {
    "Day": ["D1", "D2", "D3", "D4", "DS", "D6", "D7", "D8", "D9", "D10", "D11", "D12", "D13", "D14"],
    "Outlook": ["Sunny", "Sunny", "Overcast", "Rain", "Rain", "Rain", "Overcast", "Sunny", "Sunny", "Rain", "Sunny", "Overcast", "Overcast", "Rain"],
    "Temperature": ["Hot", "Hot", "Hot", "Mild", "Cool", "Cool", "Cool", "Mild", "Cool", "Mild", "Mild", "Mild", "Hot", "Mild"],
    "Humidity": ["High", "High", "High", "High", "Normal", "Normal", "Normal", "High", "Normal", "Normal", "Normal", "High", "Normal", "High"],
    "Wind": ["Weak", "Strong", "Weak", "Weak", "Weak", "Strong", "Strong", "Weak", "Weak", "Weak", "Strong", "Strong", "Weak", "Strong"],
    "Play ball": ["No", "No", "Yes", "Yes", "Yes", "No", "Yes", "No", "Yes", "Yes", "Yes", "Yes", "Yes", "No"]
}

df = pd.DataFrame(data)

print(df)

class DecisionTree:
    def __init__(self, df, target, positive, parent_option, parent):
        self.total_data = df
        self.target = target
        self.pos = positive
        self.parent_val = parent_option
        self.parent = parent
        self.childs = []
        self.decision = ''
def calc_entropy(self,total_data):
        p=sum(total_data[self.target]==self.pos)
        n=total_data.shape[0]-p
        pr=p/(p+n) 
        nr=1-pr
        pval=-pr*math.log2(pr)
        nval=-nr*math.log2(nr)
        return pval+nval

def calc_gain(self,f,total_data):
        average=0
        gain=calc_entropy(total_data)
        for v in np.unique(total_data[f]):
            subset=total_data[total_data[f]==v]
            subset_entropy=calc_entropy(subset)
            gain=gain-(len(subset)/len(total_data))*subset_entropy
            print("\n",gain)
            return gain
def best_split(self):
        self.split = max(self.gains, key = itemgetter(1))[0]
def change_nodes(self,total_data,f):
     # maxg=0
     #maxf=""
      '''for feature in f:
            gain=calc_gain(self,feature,total_data)
            if gain>maxg:
                  maxg=gain
                  maxf=feature'''
      self.features = [c for c in self.data.columns if c != self.target]
      self.entropy = self.calc_entropy(total_data)
      if self.entropy != 0:
            self.gains = [(feat, self.calc_gain(feat,total_data)) for feat in self.features]
            #pass gains to best_split function to gett the max gain attribute
            self.best_split()
            inverse = [k for k in self.data.columns if k != self.splitter]
            unique_options=self.total_data[self.split].unique()
            for choice in unique_options:
                  temp=self.total_data[self[total_data[self.split==val]]][inverse]
                  print(temp)
        


