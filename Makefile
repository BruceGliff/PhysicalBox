JC =javac
.SUFFIXES:.java .class

.java.class:
	$(JC) -cp ${PWD}/bin -d ${PWD}/bin $*.java

CLASSES = \
	../src/geometry/TwoDMO.java\
	../src/geometry/Point.java\
	../src/geometry/Vector.java\
	../src/geometry/HitResult.java\
	../src/objects/Border.java\
	../src/objects/Box.java\
	../src/Main.java

default:classes

classes:$(CLASSES:.java=.class)

clean:\
	$(RM) *.class
