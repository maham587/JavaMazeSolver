# Java Maze Project

This is a Java program that generates and solves mazes using various algorithms. It includes a graphical user interface for displaying the mazes and the solving process.  
![maze demo](https://github.com/user-attachments/assets/4f00f5dc-f172-4557-9bea-9c14c9e6b88f)

## Getting Started

To use this program, you'll need to have Java installed on your machine. You can download it from [here](https://www.java.com/en/download/).

Once you have Java installed, you can download or clone this repository and open it in your favorite Java IDE. You can then run the `MainView.java` file to launch the program inside the `uiMVC` package.

## Features

The program includes the following features:

- **Maze generation algorithms:**
  - Implemented in the `maze` package.

- **Maze solving algorithms:**
  - Implemented in the `dijkstra` package (Dijkstra's algorithm).

## Usage

When you launch the program, you'll be presented with a window where you can create a new maze by sizing it. You will also be able to save it and open it later if needed.

You don't need to click the `Solve Button`; the maze is solved dynamically. Once a path exists, the program will display the solving process graphically, showing the path being explored by the solving algorithm.

## Cleaning and Building

### Compilation

The project uses a `Makefile` for building and running. To compile the project, use:
```bash
make all
```
This will compile all `.java` files and place the resulting `.class` files in the `bin` directory.

### Running the Program

After compilation, you can run the program with:
```bash
make run
```
This command will launch the main view of the maze program.

### Cleaning

To remove all compiled files, including `.class` files and the `bin` directory, use:
```bash
make clean
```
This ensures a clean working directory by deleting all temporary files.


## Contributing

If you find a bug or have an idea for an improvement, feel free to open an issue or submit a pull request.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
