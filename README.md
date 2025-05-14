# ThreeInARow
ThreeInARowGame.java and ThreeInARowBlock.java are a basic Java implementation of the Three in a Row game.

### How to build and test (from Terminal):

1. Make sure that you have Apache Ant installed. Run each ant command in the threeinarow folder, which contains the `build.xml` build file.

2. Run `ant document` to generate the javadoc (a hypertext description) for all of the java classes. Generated hypertext description will be in the `jdoc` folder. Open the `index.html` file. 

3. Run `ant compile` to compile all of the java classes. Compiled classes will be in the `bin` folder.

4. Run `ant test` to run all unit tests.

### How to run (from Terminal):

After building the project (i.e., running `ant`), run the following command in the threeinarow folder: `java -cp bin ThreeInARowGame`

### How to clean up (from Terminal):

1. Run `ant clean` to clean the project (i.e., delete all generated files).


##Change log 

### MVC Architecture Refactoring
- Implemented Model-View-Controller (MVC) architecture pattern
- Created `model` package to separate business logic from presentation
- Moved `ThreeInARowGame.java` and `ThreeInARowBlock.java` to `model` package
- Updated test files to reflect new package structure
- Set foundation for future MVC improvements (view and controller separation)
- Tested functionality by compiling and running test suite. 

### Game Board Model Refactoring
- Created new `ThreeInARowModel` class to encapsulate game board data
- Moved game board data (`blocksData`) from `ThreeInARowGame` to `ThreeInARowModel`
- Updated all code to use the new model class
- Added new unit tests for the model class
- Improved encapsulation by making game board data private

### Bug Fixes
- Fixed input validation in `ThreeInARowBlock.setContents()` method
- Added unit test to verify null input validation
- Updated method to check input parameter instead of instance variable

