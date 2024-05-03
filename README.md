# CheckinSystem

# Backend Service for Android Checkin System

## Introduction
This backend service for the Android Checkin System handles all interactions with the database and processes data received from the mobile application. It's built with Spring Boot and hosted on Google Cloud.

## Prerequisites
- JDK (Java Development Kit), preferably version 11 or newer
- Maven or Gradle, for building the project
- Google Cloud account
- Access to Google Cloud SQL

## Installation

### Clone the Repository
```bash
git clone https://github.com/tanmayayay/CheckinSystem_Backend.git
cd CheckinSystem_Backend
```

## Configure Google Cloud Credentials
1. Ensure you have created a Google Cloud project.
2. Set up a Google Cloud SQL instance for the database.
3. Download a JSON key file for your Google Cloud service account.
3. Set the environment variable GOOGLE_APPLICATION_CREDENTIALS to the path of your JSON key file.
