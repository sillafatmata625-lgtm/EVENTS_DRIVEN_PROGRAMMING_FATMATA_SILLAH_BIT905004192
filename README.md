# Java Swing Assignment (PROG209 - Event Driven Programming)

A basic interactive GUI built with Java Swing.

## Features
- `SwingAssignment` class with a `JFrame` (frame), `JPanel` (panel) and `JLabel` (label)
- Inheritance: `SwingAssignment extends BaseWindow`
- Constructors in both classes (`super(...)` call)
- Window title "Java Swing Assignment", size 800 x 800, closes on X
- Own application icon and an `ImageIcon` on the label
- Change the label text (text field + button or Enter key)
- Change the panel background (colour chooser) and reset

## Run
```
javac -d out src/*.java
cp -r src/resources out/
java -cp out SwingAssignment
```
In IntelliJ IDEA or VS Code, open the folder and run `SwingAssignment.main()`.
