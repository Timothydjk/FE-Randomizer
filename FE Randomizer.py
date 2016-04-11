##Made by Tim Kierzkowski
##How to use:
##    Importing from a CSV:
##        make sure that it is formatted as follows:
##            first column is name, 2nd is level, 3rd is class, 4th is whether it is a prepromote
##            5th is effectiveLevel, 6th is previous promotions
##            e.g.
##            Chrom, 19, Great Lord, FALSE, 39, .Lord-20
##            with no column headers
##        save it as name.csv
##        run
##        teamVar = Team(name)
##        creates a team instance called teamVar with the name
##    Save it afterwards using teamVar.saveTeam()
##    Updating a character
##        currently, call
##            teamVar.units[charName].function()
##        functions are:
##            levelUp()
##            Promote(newClass)
##    Deleting a character (dipshit):
##        teamVar.delChar(charName)
##    generate a random team:
##        randomTeam(teamName,number of units you can use, strat number, any required units as a list)
##        strat 1 is unit random
##        strat 2 prioritizes stronger units
##        strat 3 prioritizes weaker units
##        e.g.
##        randomTeam(teamName, 13, 3, ["Chrom"])

import random

class  Unit:
    def __init__(self,n,l,c,pre = False,el = 0, pc = []):
        self.name = n
        self.level = l
        self.Class = c
        self.previousClass = pc
        if el != 0:
            self.effectiveLevel = el
        elif pre:
            self.effectiveLevel = l+20
        else:
            self.effectiveLevel = l
        if len(self.previousClass) == 0:
            self.proList = "None"
        else:
            self.proList = ""
            for a in self.previousClass:
                self.proList = self.proList + "\n"+a[0]+" at "+str(a[1])
    def levelUp(self):
        self.level = self.level+1
        self.effectiveLevel = int(self.effectiveLevel)+1
    def Promote(self, newClass):
        self.effectiveLevel = int(self.effectiveLevel)+1
        self.previousClass.append((self.Class,self.level))
        if len(self.previousClass) ==1:
            self.proList = self.Class + " at " + str(self.level)
        else:
            self.proList = self.proList + "\n "+self.Class + " at " + str(self.level)
        self.level = 1
        self.Class = newClass
    def dup(self):
        return Unit(self.name,self.level,self.Class,False,self.effectiveLevel,self.previousClass)
    def Status(self):
        print("Name: "+self.name + "\n Level:" + str(self.level) + "\n Class:" + self.Class + "\n Promotions:"+self.proList+"\n")
    def sStatus(self):
        print(self.name+ ": lvl "+str(self.level)+" "+self.Class + "(EL is " + str(self.effectiveLevel)+")")

class Team:
    def __init__(self,n):
        self.name = n
        self.loadTeam()
        
    def addUnit(self,n,l,c,pre,el = 0, pc = []):
        self.units[n] = (Unit(n,l,c,pre,el,pc))
    def saveTeam(self):
        f = open(self.name+".csv","w")
        f.seek(0)
        for unit in self.units:
            strPC = ""
            for a in self.units[unit].previousClass:
                strPC = strPC+"."+a[0]+"-"+str(a[1])
            f.write(self.units[unit].name+","+str(self.units[unit].level)+","+self.units[unit].Class+","+str(self.units[unit].effectiveLevel)+","+strPC.rstrip()+"\n")
        f.close()
    def loadTeam(self):
        self.units = {}
        try:
            f = open(self.name+".csv", "r")
        except IOError:
            self.units = {}
            self.saveTeam()
            f = open(self.name+".csv","r")
        f.seek(0)
        fileArray = []
        for rows in f:
            fileArray.append(rows.split(","))
        for a in fileArray:
            pc = []
            try:
                b = a[4].split(".")
                for c in range(len(b)):
                    b[c] = b[c].strip().split("-")
                pc = b[1::]
            except:
                pc = []
            self.addUnit(a[0],int(a[1]),a[2],False, int(a[3]),pc)
        f.close()
    def status(self):
        for unit in self.units:
            self.units[unit].Status()
            
    def sStatus(self):
        for unit in self.units:
            self.units[unit].sStatus()
    def sortTeamByEL(self):
        t = []
        for unit in self.units:
            t.append((self.units[unit].effectiveLevel,unit))
        t.sort()
        return t[::-1]
    def delUnit(self,n):
        try:
            del self.units[n]
        except Exception:
            return
    def newTeam(self):
        self.addChar("Chrom")
        self.addChar("Avatar")
        self.addChar("Lissa")
        self.addChar("Frederick")
    def lvlChar(self,name):
        self.units[name].levelUp()
    def promoteChar(self,name,nClass):
        self.units[name].promote(nClass)
    def addChar(self,name):
        self.units[name] = awakeniningBase[name].dup()

        
def lvlUp(name,team):
    team.units[name].levelUp()
    team.saveTeam()

def randomTeam(team, size, strat,reqUnits = []):
    ans = []
    weights = {}
    ttlWeight = 0
    ans = ans + reqUnits
    
    for a,unit in enumerate(team.sortTeamByEL()):
        if strat ==1:
            weights[unit[1]] = 1
        elif strat ==2:
            weights[unit[1]] = team.units[unit[1]].effectiveLevel
        elif strat ==3:
            weights[unit[1]] = int(a)+1
    potTeam = []
    for unit in weights:
        for a in range(int(weights[unit])):
            potTeam.append(unit)
    while len(ans)<size:
        nextUnit = potTeam[random.randint(0,len(potTeam)-1)]
        if nextUnit not in ans:
            ans.append(nextUnit)
    return ans

awakeniningBase = {"Chrom": Unit("Chrom",1,"Lord",False),"Lissa":Unit("Lissa",1,"Cleric",False),"Avatar":Unit("Avatar",1,"Tactition",False),"Frederick":Unit("Frederick",1,"Great Knight",True),"Sully":Unit("Sully",2,"Cavalier",False),"Virion":Unit("Virion",2,"Archer",False),"Stahl":Unit("Stahl",2,"Cavalier",False),"Vaike":Unit("Vaike",3,"Fighter",False),"Miriel":Unit("Miriel",1,"Mage",False),"Sumia":Unit("Sumia",1,"Pegasus Knight",False),"Kellam":Unit("Kellam",5,"Knight",False),"Donnel":Unit("Donnel",1,"Villager",False),"Lon'qu":Unit("Lon'qu",4,"Myrmidon",False),"Ricken":Unit("Ricken",3,"Mage",False),"Maribelle":Unit("Maribelle",3,"Troubadour",False),"Panne":Unit("Panne",6,"Taguel",False),"Gaius":Unit("Gaius",5,"Thief",False),"Cordelia":Unit("Cordelia",7,"Pegasus Knight",False),"Gregor":Unit("Gregor",10,"Mercenary",False),"Nowi":Unit("Nowi",3,"Manakete",False),"Libra":Unit("Libra",1,"War Monk",True),"Tharja":Unit("Tharja",10,"Dark Mage",False),"Anna":Unit("Anna",1,"Trickster",True),"Olivia":Unit("Olivia",1,"Dancer",False),"Cherche":Unit("Cherche",12,"Wyvern Rider",False),"Henry":Unit("Henry",12,"Dark Mage",False),"Say'ri":Unit("Say'ri",1,"Swordmaster",True),"Tiki":Unit("Tiki",20,"Manakete",False),"Basilio":Unit("Basilio",10,"Warrior",True),"Flavia":Unit("Flavia",10,"Hero",True),"Lucina":Unit("Lucina",10,"Lord",False),"Owain":Unit("Owain",10,"Myrmidon",False),"Inigo":Unit("Inigo",10,"Mercenary",False),"Brady":Unit("Brady",10,"Priest",False),"Kjelle":Unit("Kjelle",10,"Knight",False),"Severa":Unit("Severa",10,"Mercenary",False),"Gerome":Unit("Gerome",10,"Wyvern Rider",False),"Morgan":Unit("Morgan",10,"BASEDONTHEMOTHER",False),"Yarne":Unit("Yarne",10,"Taguel",False),"Laurent":Unit("Laurent",10,"Mage",False),"Cynthia":Unit("Cynthia",10,"Pegasus Knight",False),"Noire":Unit("Noire",10,"Archer",False),"Nah":Unit("Nah",10,"Manakete",False)}
