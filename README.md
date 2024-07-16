# Download Manager

This is a GUI-based download manager implemented in Java.

## Project Overview

The Download Manager is a Java application that provides a graphical user interface (GUI) for managing file downloads. It allows users to easily start, pause, resume, and cancel downloads. The application is designed to handle multiple downloads simultaneously and provides detailed information about the status of each download.

## Features

- **Start Downloads**: Users can initiate new downloads by providing the URL of the file to be downloaded.
- **Pause and Resume Downloads**: Downloads can be paused and resumed at any time, giving users control over their download process.
- **Cancel Downloads**: Users can cancel downloads that are no longer needed.
- **Multiple Downloads**: The application supports multiple simultaneous downloads.
- **Download Information**: Detailed information about each download, including progress, speed, and estimated time remaining.

## How It Works

1. **User Interface**: The GUI is designed using Java's Swing library, providing a user-friendly interface for managing downloads.

2. **Download Management**: The application uses multi-threading to handle multiple downloads simultaneously. Each download runs in its own thread, allowing independent control over each download task.

3. **Download Control**: Users can start, pause, resume, and cancel downloads using the interface. The application maintains the state of each download, allowing for smooth transitions between paused and resumed states.
