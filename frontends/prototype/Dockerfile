FROM python:3.7
RUN pip install pipenv
COPY . /app
WORKDIR /app
RUN pipenv lock --requirements > requirements.txt
RUN pip install -r requirements.txt
EXPOSE 5000
ENTRYPOINT ["python"]
CMD ["app.py"]
