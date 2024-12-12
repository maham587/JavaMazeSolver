# Variables
SRC_DIR = src
BIN_DIR = bin
PACKAGE_DIRS = $(SRC_DIR)/maze $(SRC_DIR)/dijkstra $(SRC_DIR)/uiMvc $(SRC_DIR)/uiButtons $(SRC_DIR)/uiTextAreaComponents
JAVA_FILES = $(foreach dir,$(PACKAGE_DIRS),$(wildcard $(dir)/*.java))
MAIN_CLASS = uiMvc.MainView

# Targets
.PHONY: all clean run

# Default target: compile the project
all: $(BIN_DIR)
	javac -d $(BIN_DIR) $(JAVA_FILES)

# Create the bin directory if it doesn't exist
$(BIN_DIR):
	mkdir -p $(BIN_DIR)

# Run the main program
run: all
	java -cp $(BIN_DIR) $(MAIN_CLASS)

# Clean compiled files
clean:
	rm -rf $(BIN_DIR)
	find $(SRC_DIR) -name "*.class" -delete
