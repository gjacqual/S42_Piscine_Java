# del the target dir if exist
rm -rf target

# create dir target
mkdir target

#Set destination for class files and compile
javac -d ./target src/java/edu/school21/printer/*/*.java 

#copy resources to target dir
cp -R ./src/resources ./target

#crate jar 
jar cfm ./target/images-to-chars-printer.jar ./src/manifest.txt -C ./target .

#Starting execution of the program from jar archive with BLACK-COLOR WHITE arguments
java -jar ./target/images-to-chars-printer.jar . 0