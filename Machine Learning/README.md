# H-Buddy Machine Learning Documentation

## Dataset
<p align="left">
We collect the dataset ourselves by photographing the herb plants directly in the environment where we live. the data that has been obtained is then augmented using python code by making changes such as image position, image rescale, adding noise to the image, etc.
</p>
<p align="center">
  <img src="https://github.com/akuarsyan/H-Buddy-Team-Capstone/blob/main/Machine%20Learning/Result/bar%20chart%20data.png" alt="Deskripsi Gambar" style="width:70%">
</p>

#### Link to dataset
- [RAW DATASET](https://github.com/akuarsyan/H-Buddy-Team-Capstone/tree/main/Machine%20Learning/Dataset/Raw%20Dataset)
- [PROCESSED DATASET](https://github.com/akuarsyan/H-Buddy-Team-Capstone/tree/main/Machine%20Learning/Dataset/Processed%20Dataset)

## Model
### EfficientNetV2B0
<p align="left">
  EfficientNetV2B0 is a deep learning model designed for image classification, including ten types of herbal plants. The model has been pre-trained on a very large dataset so that it is able to recognize and extract important features from herbal images.  By training using already labeled images of herbs, the model can provide probability predictions for each plant species when given a new image. EfficientNetV2B0 is very effective in recognizing herbal plant species.
</p>
<p align="center">
  <img src="https://github.com/akuarsyan/H-Buddy-Team-Capstone/blob/main/Machine%20Learning/Result/arsitektur.jpg" alt="Deskripsi Gambar" style="width:70%">
</p>

## Evaluation and Visualitation
Once the model training process is complete, the next step is to evaluate its performance. At this stage, accuracy and other relevant evaluation metrics will be measured to assess the model's ability to perform classification.

### Model Accuracy & Lose
<p align="left">
  <img src="https://github.com/akuarsyan/H-Buddy-Team-Capstone/blob/main/Machine%20Learning/Result/accuration%20and%20loss.png" alt="Deskripsi Gambar" style="width:50%; border: 1px solid black;">
</p>

### Classification Report 
<p align="left">
  <img src="https://github.com/akuarsyan/H-Buddy-Team-Capstone/blob/main/Machine%20Learning/Result/classification%20report.png" alt="Deskripsi Gambar" style="width:50%; border: 1px solid black;">
</p>

### Confusion Matrix
<p align="left">
  <img src="https://github.com/akuarsyan/H-Buddy-Team-Capstone/blob/main/Machine%20Learning/Result/confusion%20matrix.png" alt="Deskripsi Gambar" style="width:50%; border: 1px solid black;">
</p>
