# Autocomplete-System

-This project is an autocomplete system that provides a typeahead suggestions based on the user input, with an option to insert
words into the system that no suggestions were found for.

-The main data store for this project is a trie data structure, if you want to go directly to the implementation of it, you can 
open the "Trie" module then src the implementation is found in a package called data.

-The system is preoccuppied with data like some egyptian governorates, greeetings, progragramming languages, and my name :)

-All the dependencies needed to run the application are found in the pom.xml files, but you will need to have redis cache installed
on your device.

## Technologies used

Springboot, redis cache, jedis(a java library used as an interface to the redis commands from a java application), (html,css,javascript).
## Future work

-The data will be persisted into a nosql or sql database instead of the in memory store 
