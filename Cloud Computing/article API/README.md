# __h-buddy-articles REST API Documentation__

## Overview
This documentation provides an overview of the h-buddy-articles REST API, including the setup, structure, and available endpoints. The API is designed to scrape articles about various herbs from specified URLs and provide this data through a GET request.

## Project Structure
h-buddy-articles/
├── controllers/
│   └── articles.js
├── node_modules/
├── routes/
│   └── articles.js
├── .dockerignore
├── Dockerfile
├── package.json
├── package-lock.json
└── server.js

## File Descriptions

- controllers/articles.js: Contains the logic for scraping articles from specified URLs and extracting relevant data.
- routes/articles.js: Defines the route for the API endpoint.
- .dockerignore: Specifies files and directories to be ignored by Docker.
- Dockerfile: Contains instructions to build a Docker image for the application.
- package.json: Lists dependencies and scripts for the application.
- package-lock.json: Records the exact versions of the installed npm packages.
- server.js: Initializes and starts the Hapi server, and sets up the routes.

## Setting Up the API
###Prerequisites
Node.js (version 20 or higher)
Docker (optional, for containerization)

## Installation

### Clone the repository:
```
git clone https://github.com/your-repo/h-buddy-articles.git
cd h-buddy-articles
```
### Install dependencies:
```
npm install
```
### Run the server:
```
npm start
The server will start on port 8080 by default.
```
## Docker Setup

### Build the Docker image:
```
docker build -t h-buddy-articles .
```
### Run the Docker container:
```
docker run -p 8080:8080 h-buddy-articles
```

## API Endpoint
Get Articles
URL: /articles
Method: GET
Description: Fetches a list of articles about various herbs.

## Request
No request body or parameters are needed for this endpoint.

## Response
Status: 200 OK
Content-Type: application/json
Body:
{
    "status": true,
    "articles": [
        {
            "title": "Bunga Asoka : Mitologi, Taksonomi, Morfologi, Ciri, Manfaat & Cara Tanam",
            "url": "https://rimbakita.com/bunga-asoka/#:~:text=Caranya%20adalah%20dengan%20merebus%20bunga%20asoka%2C%20bunga%20mawar%2C,maksimal.%20Luka%20memar%20pun%20akan%20sembuh%20lebih%20cepat",
            "description": "Tanaman asoka lebih dikenal sebagai tanaman hias karena bentuknya yang cantik dan warna bunga cerah. Tetapi, pohon asoka juga memiliki manfaat baik lainnya, terutama bagi kesehatan tubuh.",
            "photoUrl": "https://rimbakita.com/wp-content/uploads/2020/08/bunga-asoka.jpg",
            "herb": "Asoka"
        },
        {
            "title": "12 Manfaat Bunga Telang untuk Kesehatan, Simak Cara Mengolahnya!",
            "url": "https://mediaindonesia.com/humaniora/642780/12-manfaat-bunga-telang-untuk-kesehatan-simak-cara-mengolahnya#:~:text=12%20Manfaat%20Bunga%20Telang%20untuk%20Kesehatan%2C%20Simak%20Cara,8%208.%20Meningkatkan%20daya%20ingat%20...%20More%20items",
            "description": "Manfaat bunga telang telah dikenal secara turun-menurun untuk membantu mengatasi beberapa penyakit, terutama bagi masyarakat di Indonesia.",
            "photoUrl": "https://disk.mediaindonesia.com/thumbs/480x320/news/2024/01/1b98cb79989fb0a036d76c99bfe0e618.jpg",
            "herb": "Bunga Telang"
        },
        ...
    ]
}


## Example using Postman
Import the collection: You can import the provided Postman collection to quickly test the API.
Send GET request: Send a GET request to http://localhost:8080/articles to retrieve the list of articles.
