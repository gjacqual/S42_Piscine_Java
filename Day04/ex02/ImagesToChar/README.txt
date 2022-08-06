# del the target dir if exist
rm -rf target

#del lib dir
rm -rf lib

# create dir target
mkdir target

#create dir lib
mkdir lib

#Download jcommander lib
curl -o lib/jcommander-1.82.jar https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar
curl -o lib/JCDP-4.0.2.jar https://repo1.maven.org/maven2/com/diogonunes/JCDP/4.0.2/JCDP-4.0.2.jar

#extract from archive
jar -xf lib/jcommander-1.82.jar
jar -xf lib/JCDP-4.0.2.jar


#Set destination for class files and compile
javac -d ./target -sourcepath src/java -cp lib/JCDP-4.0.2.jar:lib/jcommander-1.82.jar:. src/java/edu/school21/printer/*/*.java 

#copy resources to target dir
cp -R ./src/resources ./target

# clean metaing folder and move com dir to target dir
rm -rf META-INF
mv ./com ./target

#crate jar 
jar cfm ./target/images-to-chars-printer.jar ./src/manifest.txt -C ./target .

#Starting execution of the program from jar archive with BLACK-COLOR WHITE arguments
java -jar ./target/images-to-chars-printer.jar --white=BLUE --black=GREEN