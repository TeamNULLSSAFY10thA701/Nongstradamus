FROM python:3.9.13 AS builder


RUN mkdir /app
WORKDIR /app
COPY . .
RUN pip install --upgrade pip
RUN pip wheel --no-cache-dir --no-deps --wheel-dir /app/wheels -r requirements.txt


FROM python:3.9.13
WORKDIR /app
COPY --from=builder /app/wheels /wheels
COPY --from=builder /app/requirements.txt .
RUN pip install --no-cache /wheels/*
COPY . .
CMD ["python", "app.py"]
