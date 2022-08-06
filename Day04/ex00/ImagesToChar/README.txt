# del the target dir if exist
rm -rf target

# create dir target
mkdir target

#Set destination for class files and compile
javac -d ./target src/java/edu/school21/printer/*/*.java 

#Starting execution of the program from target dir with BLACK WHITE FILEPATH arguments
java -classpath ./target edu.school21.printer.app.Program . 0 ${PWD}/it.bmp