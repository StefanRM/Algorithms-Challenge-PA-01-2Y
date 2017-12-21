all: build

build:
	javac Points.java
	javac Adn.java
	javac Stropitori.java
	javac Warriors.java

run-p1:
	java Points

run-p2:
	java Adn

run-p3:
	java Stropitori

run-p4:
	java Warriors

clean:
	rm *.class
