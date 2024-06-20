# H-Buddy Herbs Prediction Backend API Documentation

# Overview
The Plant Prediction API allows users to upload an image of a plant leaf, which is then processed and classified using a pre-trained TensorFlow model. The API returns the predicted plant type along with detailed information about the plant.

## Based URL
```bash
  https://flask-tensorflow-gcolab-jvtjfxo25q-et.a.run.app
```
## Endpoints

### 1. Predict Plant type
- **URL**: /predict
- **Method**: POST
- **Description**: Upload an image to predict the plant type and get detailed information about the plant.

## Request
- **Headers**
  - `Content-Type: Multipart/form-data`
  - `image` (file): The image file of the herbs.

## Example Request 
```bash
curl -X POST https://flask-tensorflow-gcolab-jvtjfxo25q-et.a.run.app/predict -F "image.jpg"
```
## Response
- **Success Response** :
  - **Code** : 200 OK
  - **Content** :
  ```bash
  {
  "message": "Model is predicted successfully.",
  "data": {
    "result": "Asoka",
    "confidenceScore": 98.5,
    "isAboveThreshold": true,
    "binomial": "Saraca Asoca",
    "description": "Tumbuhan ini dikenal sebagai simbol keindahan dan kedamaian dalam banyak budaya di seluruh dunia...",
    "benefit": [
      "1. Meringkan nyeri Haid...",
      "2. Menjaga Kesehatan Kulit...",
      "3. Menjaga Kesehatan Mulut dan Gigi..."
      ]
    }
  }
  ```
- **Error Response**
  - **Code** : 400 Bad Request
  - **Content** :
  ```bash
  {
  "error": "Error message"
  }
  ```
## Error codes 
- **400** : Bad Request - The request was invalid or cannot be otherwise served.
- **500** :  Internal Server Error - An error occurred on the server side.
## Model information
The API uses a TensorFlow Keras model (`EfficientNetV2B0.h5`) stored in the `model` directory.
## Plant information
The API provides 10 dataset information about various plants, including:
- **Asoka**
- **Bunga Telang**
- **Daun Jambu Biji**
- **Daun Jarak**
- **Daun Jeruk Nipis**
- **Daun Pepaya**
- **Kayu Putih**
- **Lidah Buaya**
- **Semanggi**
- **Sirih**
## Additional information
- The API uses Flask for handling HTTP requests.
- CORS is enabled to allow cross-origin requests.
- Images are preprocessed to match the model's expected input size (224x224 pixels).
## Running the API
To run the API, execute the following command:
```bash
python app.py
```
Ensure the TensorFlow model is correctly placed in the model directory with the filename `EfficientNetV2B0.h5`.




## *Note:* 
1. With Python 3.10.12, you can run it on WSL (Windows Subsystem Linux)
2. Start the server with `python app.py` on terminal
3. Optionally you can use venv to handle the package management of pip

*Install package with command:*
```
pip install -r requirements.txt
```
