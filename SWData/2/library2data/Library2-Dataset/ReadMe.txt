Library 2 dataset.
http://www.ulb.ac.be/di/mlg/sensorNet/datasets.html

At start the radio power level of the mote is set to minimal.
Every 10 sequence the radio power level is incremented.

A sequence last for one minut.
During a sequence, each node sends :
-> 1 sensing packet with all the sensors data.
-> X neighbors packet with the neighbors lqi & rssi. (X depends on the number of neighbors reached by the node (2 neighbors per packet))



2 parts
-------

Neighbors data:
***************

File : neighbors.txt


line example : 		1163438005984 3 0 15 18 4 235
a line is composed of : A 	      B C D  E  F G

A -> timestamp : the time at which paquet arrived at base station.
B -> sequence number : the node internal sequence counter.
C -> packet number : this is the packet number in a sequence.
D -> node id : the node sending the data   
E -> srcnode id : the node for which the rssi & lqi value are gathered
F -> lqi value : the lqi value to srcnode
G -> rssi value : the rssi value to srcnode



Sensors data:
*************

Files : readings.txt


line example : 		1163438041250 20 1 21,21 2,81 51,53 22,08 20 28
a line is composed of : A 	      B  C D 	 E    F     G     H  I

A -> timestamp : the time at which paquet arrived at base station
B -> node id : the node sending the data   
C -> sequence number : the node internal sequence counter 
D -> internal temperature : (*)
E -> internal voltage : (*)
F -> humidity : (*)
G -> temperature : (*)
H -> PAR value : (*)
I -> TSR value : (*)

* See tmote sky quickstart (1)  or datasheet (2)
(1) http://www.moteiv.com/products/docs/tmote-sky-quickstart.pdf
(2) http://www.moteiv.com/products/docs/tmote-sky-datasheet.pdf
