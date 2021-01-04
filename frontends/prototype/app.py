from flask import Flask, render_template
app = Flask(__name__)


@app.route('/')
def hello_world():
    print("hello world")
    return render_template("index.html")


@app.route('/lobby')
def lobby():
    return render_template("lobby.html")


@app.route('/game_lobby')
def game_lobby():
    return render_template("game_lobby.html")


@app.route('/game')
def game():
    return render_template("game.html")


if __name__ == '__main__':
    app.run(host="0.0.0.0")
