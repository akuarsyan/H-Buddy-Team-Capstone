FROM python:3.10

WORKDIR /app

# Copy requirements file and install dependencies
COPY requirements.txt ./
RUN pip install --no-cache-dir -r requirements.txt

# Copy the rest of the application code
COPY . .

# Set environment variable for Python
ENV PYTHONUNBUFFERED=1
ENV PORT=8080

EXPOSE 8080

# Command to start the server
CMD ["python", "app.py"]